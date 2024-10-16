package Interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAssignmentStudentGUI extends JFrame {

    private JComboBox<String> assignmentList;
    private JTextArea assignmentDetails;
    private JButton viewButton;

    public ViewAssignmentStudentGUI() {
        setTitle("View Assignments");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel assignmentLabel = new JLabel("Select Assignment:");
        assignmentList = new JComboBox<>(new String[]{"Assignment 1", "Assignment 2", "Assignment 3"});
        viewButton = new JButton("View Assignment");
        assignmentDetails = new JTextArea();
        assignmentDetails.setEditable(false);

        panel.add(assignmentLabel);
        panel.add(assignmentList);
        panel.add(viewButton);
        panel.add(new JScrollPane(assignmentDetails));

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAssignmentDetails();
            }
        });

        add(panel);
    }

    private void viewAssignmentDetails() {
       
        String assignmentName = assignmentList.getSelectedItem().toString();
        String assignmentDescription = "This is the description for " + assignmentName + ".";
        String dueDate = "Due Date: January 15, 2023";
        String feedback = "Feedback: Excellent work!";

        assignmentDetails.setText(String.format("%s\n%s\n%s\n%s", assignmentName, assignmentDescription, dueDate, feedback));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewAssignmentStudentGUI().setVisible(true);
        });
    }
}
