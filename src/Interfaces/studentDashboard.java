package Interfaces;

import javax.swing.*;

import Applicaton.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class studentDashboard extends JFrame {

    private JLabel welcomeLabel;
    private JPanel dataPanel;
    private Student student;
    public studentDashboard(Student student) {
        this.student=student;
        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        
        JPanel tabsPanel = new JPanel();
        tabsPanel.setLayout(new BoxLayout(tabsPanel, BoxLayout.Y_AXIS));
        tabsPanel.setPreferredSize(new Dimension(300, getHeight())); //tabs pannel width!
        welcomeLabel = new JLabel("Welcome, " + student.getStudentName() + "!");
    
        welcomeLabel.setForeground(Color.BLUE);
        tabsPanel.add(welcomeLabel);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Assignments", createAssignmentsPanel());
        tabbedPane.addTab("Course Material", createCoursesPanel());
        tabbedPane.addTab("Shedule", createShedulePanel());
        
        tabsPanel.add(tabbedPane);

        //displays side data
        dataPanel = new JPanel();
    dataPanel.setLayout(new BoxLayout(dataPanel,BoxLayout.Y_AXIS));
    dataPanel.setPreferredSize(new Dimension(400,200));
    
        add(tabsPanel, BorderLayout.WEST);
        add(dataPanel, BorderLayout.CENTER);
    }

    private JPanel createAssignmentsPanel() {
        JPanel assignmentsPanel = new JPanel();
        assignmentsPanel.setLayout(new GridLayout(3, 1));

        JButton viewButton = new JButton("View Assignment");
        JButton submitButton = new JButton("Submit Assignment");
        JButton updateButton = new JButton("Update Assignment");
      
        assignmentsPanel.add(submitButton);
        assignmentsPanel.add(viewButton);
        assignmentsPanel.add(updateButton);

        submitButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    SubmitAssignmentGUI submitAssignmentPanel = new SubmitAssignmentGUI(student);
                    dataPanel.removeAll();
                    dataPanel.add(submitAssignmentPanel, BorderLayout.CENTER);
                    dataPanel.revalidate();
                    dataPanel.repaint();
                });
            }
        });
        
        

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataPanel("Viewing assignment...");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    UpdateAssignmentGUI updateAssignmentPanel = new UpdateAssignmentGUI(student);
                    dataPanel.removeAll();
                    dataPanel.add(updateAssignmentPanel, BorderLayout.CENTER);
                    dataPanel.revalidate();
                    dataPanel.repaint();
                   
                });
            }
        });

        return assignmentsPanel;
    }

    private JPanel createCoursesPanel() {
        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new GridLayout(2, 1));
        JButton viewMaterialsButton = new JButton("View Course Materials");

        coursesPanel.add(viewMaterialsButton);


        viewMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataPanel("Viewing course materials...");
            }
        });

        return coursesPanel;
    }
    private JPanel createShedulePanel() {
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridLayout(1, 1));

        JButton viewSchedule = new JButton("View Course Shedule");
    
        schedulePanel.add(viewSchedule);
        // Add action listeners for the buttons (implement your logic)
        viewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataPanel("view  course schedule...");
            }
        });

        return schedulePanel;
    }

    private void updateDataPanel(String message) {
        dataPanel.removeAll();
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        dataPanel.add(messageLabel, BorderLayout.CENTER);
        dataPanel.revalidate();
        dataPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Student student=new Student("shoaib","shoaib");
            new studentDashboard(student).setVisible(true);
        });
    }
}
