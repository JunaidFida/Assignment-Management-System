package Interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewScheduleGUI extends JFrame {
    private JComboBox<String> courseList;
    private JTextArea scheduleDetailsArea;
    private JButton viewScheduleButton;

    public ViewScheduleGUI() {
        setTitle("View Schedule");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Select Course:"));
        courseList = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        panel.add(courseList);

        panel.add(new JLabel("Schedule Details:"));
        scheduleDetailsArea = new JTextArea();
        scheduleDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(scheduleDetailsArea);
        panel.add(scrollPane);

        viewScheduleButton = new JButton("View Schedule");
        viewScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleViewSchedule();
            }
        });
        panel.add(viewScheduleButton);

        add(panel);
    }

    private void handleViewSchedule() {
        String selectedCourse = (String) courseList.getSelectedItem();
        String scheduleDetails = "Monday, 9:00 AM - 11:00 AM: Java Programming\n"
                + "Wednesday, 1:00 PM - 3:00 PM: Database Management";
        scheduleDetailsArea.setText(scheduleDetails);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewScheduleGUI().setVisible(true);
        });
    }
}
