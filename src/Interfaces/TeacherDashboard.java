package Interfaces;

import Applicaton.Teacher;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherDashboard extends JFrame {

    private JLabel welcomeLabel;
    private JPanel dataPanel;
    private Teacher teacher;

    public TeacherDashboard(Teacher teacher) {
        setTitle("Teacher Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1980, 1080);
        setLocationRelativeTo(null);
        this.teacher=teacher;
        JPanel tabsPanel = new JPanel();
        tabsPanel.setLayout(new BoxLayout(tabsPanel, BoxLayout.Y_AXIS));
        welcomeLabel = new JLabel("Welcome, " + teacher.getTeacherName() + "!");
        tabsPanel.add(welcomeLabel);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Course Material", LecturesTab());
        tabbedPane.addTab("Assignments", AssignmentsTab());
        tabbedPane.addTab("Courses", CoursesTab());

        tabsPanel.add(tabbedPane);

        dataPanel = new JPanel();
        dataPanel.setLayout(new BorderLayout());
       
        dataPanel.setPreferredSize(new Dimension(500,getHeight()));
        add(tabsPanel, BorderLayout.WEST);
        add(dataPanel, BorderLayout.CENTER);
    }

    //course Material panel tab
    private JPanel LecturesTab() {
        JPanel lecturesPanel = new JPanel();
        lecturesPanel.setLayout(new GridLayout(3, 1));

        JButton viewLecturesButton = new JButton("View Lectures");
        JButton viewSheduleButton = new JButton("View Schedule");
        JButton uploadMaterialButton=new JButton("Upload Material");

        lecturesPanel.add(uploadMaterialButton);
        lecturesPanel.add(viewLecturesButton);
        lecturesPanel.add(viewSheduleButton);
        uploadMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    UploadCourseMaterialGUI uploadCourseMaterialGUI = new UploadCourseMaterialGUI(teacher );
                    dataPanel.removeAll();
                    dataPanel.add(uploadCourseMaterialGUI, BorderLayout.CENTER);
                    dataPanel.revalidate();
                    dataPanel.repaint();
                });
            }
        });
        viewLecturesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDataPanel("Viewing Lectures...");
            }
        });
        viewSheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateDataPanel("Viewing Schedule...");
            }

        });

        return lecturesPanel;
    }
//Assignments tab
    private JPanel AssignmentsTab() {
        JPanel assignmentsPanel = new JPanel();
        assignmentsPanel.setLayout(new GridLayout(3, 1));

        JButton viewAssignmentsButton = new JButton("View Assignments");
        JButton uploadAssignmentButton = new JButton("Upload Assignments");

        assignmentsPanel.add(viewAssignmentsButton);
        assignmentsPanel.add(uploadAssignmentButton);
        // Add action listeners for the buttons (implement your logic)
        viewAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    viewAssignmentTeacher viewAssignmentTeacher = new viewAssignmentTeacher(teacher );
                    dataPanel.removeAll();
                    dataPanel.add(viewAssignmentTeacher, BorderLayout.CENTER);
                    dataPanel.revalidate();
                    dataPanel.repaint();
                });
            }
        });
        uploadAssignmentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(() -> {
                        teacherAssignmentUpload teacherAssignmentUpload = new teacherAssignmentUpload(teacher );
                        dataPanel.removeAll();
                        dataPanel.add(teacherAssignmentUpload, BorderLayout.CENTER);
                        dataPanel.revalidate();
                        dataPanel.repaint();
                    });
                }
        });

        return assignmentsPanel;
    }

//Courses Tab
    private JPanel CoursesTab() {
        JPanel uploadMaterial = new JPanel();
        uploadMaterial.setLayout(new GridLayout(3, 1));
        JButton uploadCourseMaterialButton= new JButton("Upload Course Material");
        uploadMaterial.add(uploadCourseMaterialButton);
        //upload course material
        uploadCourseMaterialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    UploadCourseMaterialGUI uploadCourseMaterialGUI = new UploadCourseMaterialGUI(teacher);
                    dataPanel.removeAll();
                    dataPanel.add(uploadCourseMaterialGUI, BorderLayout.CENTER);
                    dataPanel.revalidate();
                    dataPanel.repaint();
                });
            }
        });
            return uploadMaterial;
    }

    public void updateDataPanel(String message) {
       
        dataPanel.removeAll();
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        dataPanel.add(messageLabel, BorderLayout.CENTER);
        dataPanel.revalidate();
        dataPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
             Teacher teacher=new Teacher("onaiza","onaiza");
            new TeacherDashboard(teacher).setVisible(true);
        });
    }
}
