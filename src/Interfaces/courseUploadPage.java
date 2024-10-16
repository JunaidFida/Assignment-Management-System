package Interfaces;

import javax.swing.SwingUtilities;

public class courseUploadPage {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            displaUploadMaterialGUI();
        });
    }

    public static void displaUploadMaterialGUI() {
        UploadCourseMaterialGUI courseMaterialGUI = new UploadCourseMaterialGUI(null);
        courseMaterialGUI.setVisible(true);
    }
}
