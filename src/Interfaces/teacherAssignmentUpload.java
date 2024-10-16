package Interfaces;

import Database.RetreiverDB;
import Applicaton.Teacher;
import Applicaton.TeacherAssignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

public class teacherAssignmentUpload extends JPanel {
    private JTextField filePathField;
    private JTextField assignmentNameField;
    private JComboBox<String> courseComboBox;
    private JSpinner dueDateSpinner;

    public teacherAssignmentUpload(Teacher teacher) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addVerticalSpace(100);

        String[] courseList = getCourses();
        courseComboBox = createComboBox(courseList);
        add(createTitledPanel("Select Course:", courseComboBox));
        add(createTitledPanel("Enter Assignment Name:", assignmentNameField = createTextField()));

        // Add Spinner for Due Date
        add(createTitledPanel("Select Due Date:", createDueDateSpinner()));

        add(createTitledPanel("Select Assignment File:", filePathField = createFilePicker()));

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

        JButton submitButton = new JButton("Submit Material");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(submitButton);

        addVerticalSpace(20);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUploadAssignment(teacher);
            }
        });
    }
   

    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        return comboBox;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        return textField;
    }

    private JTextField createFilePicker() {
        JTextField filePathField = new JTextField();
        return filePathField;
    }

    private JPanel createDueDateSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        dueDateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dueDateSpinner, "MM/dd/yyyy hh:mm a");
        dueDateSpinner.setEditor(editor);

        JPanel spinnerPanel = new JPanel();
        spinnerPanel.add(dueDateSpinner);
        return spinnerPanel;
    }

    private void handleUploadAssignment(Teacher teacher) {
        String courseName = (String) courseComboBox.getSelectedItem();
        String assignmentName = assignmentNameField.getText();
        String filePath = filePathField.getText();
        Date dueDate = (Date) dueDateSpinner.getValue();

        // Use these values in your TeacherAssignment and courseMaterial methods
        TeacherAssignment teacherAssignment = new TeacherAssignment(courseName, assignmentName, filePath, dueDate);
        if (teacherAssignment.uploadAssignment(teacher, assignmentName, filePath)) {
            JOptionPane.showMessageDialog(this, "Assignment uploaded by Teacher successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error uploading course material.");
        }
    }

    private void handleFilePicker(JTextField filePathField) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private JPanel createTitledPanel(String title, JComponent component) {
        JPanel titledPanel = new JPanel();
        titledPanel.setLayout(new BoxLayout(titledPanel, BoxLayout.Y_AXIS));
        titledPanel.setBorder(BorderFactory.createTitledBorder(title));
        titledPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Set maximum height
        titledPanel.add(component);
        return titledPanel;
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

    private void addVerticalSpace(int height) {
        add(Box.createRigidArea(new Dimension(0, height)));
    }
}
