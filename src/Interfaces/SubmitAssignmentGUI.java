
package Interfaces;
import javax.swing.*;
import Applicaton.Student;
import Applicaton.StudentAssignmentController;

import Applicaton.TeacherAssignment;

import Database.RetreiverDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SubmitAssignmentGUI extends JPanel {
    private JTextField filePathField;
    private JComboBox<String> assignmentComboBox; // Add this
    private JComboBox<String> courseComboBox; // Add this
    Student student;
    TeacherAssignment teacher;
    public SubmitAssignmentGUI(Student student) {
        this.student=student;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        String[] courseList=getCourses();
        courseComboBox = createComboBox(courseList);
        add(createTitledPanel("Select Course:", courseComboBox));

        String[] assignments=getUploadedAssignments();
        assignmentComboBox = createComboBox(assignments);
        add(createTitledPanel("Select Assignment:", assignmentComboBox)); // Use the combo box here
 
        addVerticalSpace(10);
        add(createTitledPanel("Select File:", filePathField = createFilePicker()));

        JButton filePickerButton = new JButton("Browse");
        filePickerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(filePickerButton);

        addVerticalSpace(10);

        filePickerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFilePicker(filePathField);
            }
        });
 
        JButton submitButton = new JButton("Submit Assignment");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(submitButton);

        addVerticalSpace(20);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitAssignment();
            }
        });
    }
    public String[] getUploadedAssignments() {
        try {
            // Retrieve uploaded assignments list from the database
            return RetreiverDB.getUploadedAssignments();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching uploaded assignments.");
        }
        return new String[0];
    }
    public String[] getCourses() {
        try {
            // Retrieve uploaded assignments list from the database
            return RetreiverDB.getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching uploaded assignments.");
        }
        return new String[0];
    }
    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        return comboBox;
    }


    private JTextField createFilePicker() {
        JTextField filePathField = new JTextField();
        return filePathField;
    }

    private JPanel createTitledPanel(String title, JComponent component) {
        JPanel titledPanel = new JPanel();
        titledPanel.setLayout(new BoxLayout(titledPanel, BoxLayout.Y_AXIS));
        titledPanel.setBorder(BorderFactory.createTitledBorder(title));
        titledPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Set maximum height
        titledPanel.add(component);
        return titledPanel;
    }

    private void addVerticalSpace(int height) {
        add(Box.createRigidArea(new Dimension(0, height)));
    }

    private void handleFilePicker(JTextField filePathField) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void handleSubmitAssignment() {
       String submissionPath=filePathField.getText();
       StudentAssignmentController controller=new StudentAssignmentController();
       if (controller.submitAssignment(student,submissionPath)) {
        JOptionPane.showMessageDialog(this, "Assignment submitted successfully!\nFile Path: " + controller.getPath());
    } 
    }
    

    
}
