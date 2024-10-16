package Interfaces;
import javax.swing.SwingUtilities;
public class assignmentSubmitPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            displaySubmitAssignmentGUI();
        });
    }
    public static void displaySubmitAssignmentGUI() {
        SubmitAssignmentGUI submitAssignmentGUI = new SubmitAssignmentGUI(null);
        submitAssignmentGUI.setVisible(true);
    }
}
