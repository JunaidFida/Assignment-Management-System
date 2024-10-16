package Interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLecturesGUI extends JFrame {
    private JComboBox<String> courseList;
    private JTextArea lectureDetailsArea;
    private JButton viewLectureButton;

    public ViewLecturesGUI() {
        setTitle("View Lectures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Select Course:"));
        courseList = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        panel.add(courseList);

        panel.add(new JLabel("Lecture Details:"));
        lectureDetailsArea = new JTextArea();
        lectureDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(lectureDetailsArea);
        panel.add(scrollPane);

        viewLectureButton = new JButton("View Lecture");
        viewLectureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewLecture();
            }
        });
        panel.add(viewLectureButton);

        add(panel);
    }

    private void handleViewLecture() {
        String selectedCourse = (String) courseList.getSelectedItem();
        String lectureDetails = "Lecture Title: Introduction to Java\n"
                + "Date: 2023-01-01\n"
                + "Description: This is a sample lecture.";
        lectureDetailsArea.setText(lectureDetails);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewLecturesGUI().setVisible(true);
        });
    }
}
