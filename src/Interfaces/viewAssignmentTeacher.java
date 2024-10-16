package Interfaces;

import Applicaton.Teacher;
import Applicaton.TeacherAssignment;
import Database.RetreiverDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class viewAssignmentTeacher extends JPanel {

    private Teacher teacher;
    // private TeacherAssignment assignment;
    private final String Sub_serverDirectory = "C:/ServerFiles/Course Material/TeacherAssignments"; 
    public viewAssignmentTeacher(Teacher teacher) {
        this.teacher = teacher;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addVerticalSpace(100);

        String[] courseList = getCourses();
        JComboBox<String> courseComboBox = createComboBox(courseList);
        add(createTitledPanel("Select Course:", courseComboBox));

        JButton viewAssignmentsButton = new JButton("View Assignments");
        viewAssignmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(viewAssignmentsButton);

        addVerticalSpace(20);

        viewAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewAssignments(courseComboBox.getSelectedItem());
            }
        });
    }

    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        return comboBox;
    }

    private void handleViewAssignments(Object selectedCourse) {
        if (selectedCourse != null) {
            //String courseName = selectedCourse.toString();
            String[] assignments = RetreiverDB.getSubmittedAssignments();
            displayAssignments(assignments);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course.");
        }
    }
    private void displayAssignments(String[] assignments) {
        if (assignments.length == 0) {
            JOptionPane.showMessageDialog(this, "No assignments found.");
            return;
        }
    
        JComboBox<String> assignmentsComboBox = new JComboBox<>(assignments);
    
        int choice = JOptionPane.showConfirmDialog(this, assignmentsComboBox, "Select Assignment", JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            String selectedAssignment = (String) assignmentsComboBox.getSelectedItem();
            int pathIndex = selectedAssignment.indexOf("ServerFiles");
            String result = pathIndex != -1 ? selectedAssignment.substring(pathIndex + 11) : "";
            openFile(Sub_serverDirectory+result);
           
        }
    }
    
    private void openFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(this, "File not found: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage());
        }
    }
    private JPanel createTitledPanel(String title, JComponent component) {
        JPanel titledPanel = new JPanel();
        titledPanel.setLayout(new BoxLayout(titledPanel, BoxLayout.Y_AXIS));
        titledPanel.setBorder(BorderFactory.createTitledBorder(title));
        titledPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        titledPanel.add(component);
        return titledPanel;
    }

    public String[] getCourses() {
        try {
            return RetreiverDB.getCourses();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching courses.");
        }
        return new String[0];
    }

    private void addVerticalSpace(int height) {
        add(Box.createRigidArea(new Dimension(0, height)));
    }

}
