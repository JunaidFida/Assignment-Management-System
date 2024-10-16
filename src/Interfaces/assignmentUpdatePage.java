package Interfaces;

import javax.swing.SwingUtilities;

public class assignmentUpdatePage {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            displayUpdateAssignmentUI();
        });
    }

    public static void displayUpdateAssignmentUI() {
        UpdateAssignmentGUI submitAssignmentGUI = new UpdateAssignmentGUI(null);
        submitAssignmentGUI.setVisible(true);
    }
}
