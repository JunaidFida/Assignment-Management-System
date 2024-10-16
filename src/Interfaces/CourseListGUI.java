package Interfaces;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CourseListGUI extends JFrame {
    private JList<String> courseList;
    private DefaultListModel<String> listModel;

    public CourseListGUI() {
        setTitle("Course List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        courseList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(courseList);

        add(scrollPane, BorderLayout.CENTER);

        courseList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedCourse = courseList.getSelectedValue();
                    if (selectedCourse != null) {
                        // Open course details or material interface for the selected course
                        // For now, let's just display a message
                        JOptionPane.showMessageDialog(CourseListGUI.this, "Selected Course: " + selectedCourse);
                    }
                }
            }
        });
    }

    public void populateCourseList(List<String> courses) {
        for (String course : courses) {
            listModel.addElement(course);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CourseListGUI courseListGUI = new CourseListGUI();
                List<String> courses = Arrays.asList("Course 1", "Course 2", "Course 3"); // Replace with actual course data
                courseListGUI.populateCourseList(courses);
                courseListGUI.setVisible(true);
            }
        });
    }
}