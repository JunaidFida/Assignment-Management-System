package Interfaces;
import Applicaton.courseMaterial;
import Database.RetreiverDB;
import Applicaton.Teacher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UploadCourseMaterialGUI extends JPanel {
    private JTextField filePathField;
    private JComboBox<String> materialType; // Add this
    private JComboBox<String> courseComboBox;

    public UploadCourseMaterialGUI(Teacher teacher) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addVerticalSpace(100);
        String[] courseList=getCourses();
        courseComboBox = createComboBox(courseList);
        add(createTitledPanel("Select Course:", courseComboBox));

        String[] materialTypes={"Lecture","Datesheet"};
        materialType = createComboBox(materialTypes);
        add(createTitledPanel("Select Material Type:", materialType));
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

        JButton submitButton = new JButton("Submit Material");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(submitButton);

        addVerticalSpace(20);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUploadCourseMaterial(teacher);
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
            // Retrieve course list from the database
            return RetreiverDB.getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching Courses");
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

    private void handleUploadCourseMaterial(Teacher teacher) {
         String filePath = filePathField.getText();
        courseMaterial courseMaterial = new courseMaterial();
        if (courseMaterial.uploadMaterial(teacher, filePath)) {
            JOptionPane.showMessageDialog(this, "Course material uploaded successfully for "+courseComboBox.getSelectedItem()+"\nFile Path: "+courseMaterial.getPath());
        } else {
            JOptionPane.showMessageDialog(this, "Error uploading course material.");
        }
    }
} 
