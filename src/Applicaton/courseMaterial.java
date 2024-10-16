
package Applicaton;

import javax.swing.JOptionPane;
import Database.AssignmentSubmissionDatabase;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class courseMaterial {
    private final String serverDirectory = "C:/ServerFiles/Course Material";
    private String filePath;
    Teacher teacher;

    public void setNewPath(Teacher teacher,String newPath) {
        this.filePath = newPath;
    }

    public boolean uploadMaterial(Teacher teacher,String filePath) {
        // Create a directory if it doesn't exist
        this.teacher=teacher;
        File directory = new File(serverDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Copy the file to the server directory
        File sourceFile = new File(filePath);
        String destinationPath = serverDirectory + "/" + sourceFile.getName();

        try {
            Path sourcePath = sourceFile.toPath();
            Path destination = new File(destinationPath).toPath();
            Files.copy(sourcePath, destination, StandardCopyOption.REPLACE_EXISTING);
            this.filePath = destinationPath;
            AssignmentSubmissionDatabase.saveTeacherSubmissionInfo(teacher,teacher.getTeacherName(),destinationPath);
            return true;
        } catch (FileAlreadyExistsException e) {
            JOptionPane.showMessageDialog(null, "File with the same name already exists on the server.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error copying file to server.");
        }

        return false;
    }

    public String getPath() {
        return this.filePath;
    }
}

