package Interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCourseInfoGUI extends JFrame {
    private JComboBox<String> courseList;
    private JTextField newTitleField;
    private JTextField newDescriptionField;
    private JButton updateButton;

    public UpdateCourseInfoGUI() {
        setTitle("Update Course Info");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Select Course:"));
        courseList = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        panel.add(courseList);

        panel.add(new JLabel("New Title:"));
        newTitleField = new JTextField();
        panel.add(newTitleField);

        panel.add(new JLabel("New Description:"));
        newDescriptionField = new JTextField();
        panel.add(newDescriptionField);

        updateButton = new JButton("Update Course Info");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdate();
            }
        });
        panel.add(updateButton);

        add(panel);
    }

    private void handleUpdate() {
        String selectedCourse = (String) courseList.getSelectedItem();
        String newTitle = newTitleField.getText();
        String newDescription = newDescriptionField.getText();
        String message = "Course: " + selectedCourse + "\nNew Title: " + newTitle + "\nNew Description: " + newDescription;
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UpdateCourseInfoGUI().setVisible(true);
        });
    }
}
