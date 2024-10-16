package Interfaces;
import javax.swing.SwingUtilities;
public class viewAssignmentTeacherPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            displaySubmitAssignmentGUI();
        });
    }
    public static void displaySubmitAssignmentGUI() {
        viewAssignmentTeacher viewAssignmentTeacher = new viewAssignmentTeacher(null);
        viewAssignmentTeacher.setVisible(true);
    }
}
