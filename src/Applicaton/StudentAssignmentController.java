package Applicaton;

import javax.swing.JOptionPane;
import Database.AssignmentSubmissionDatabase;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StudentAssignmentController {
    private final String serverDirectory = "C:/ServerFiles";
    private String filePath;
    private Student student;

    public void setNewPath(String newPath) {
        this.filePath = newPath;
    }

    public boolean submitAssignment(Student student, String filePath) {
     
        this.student = student;
        File directory = new File(serverDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File sourceFile = new File(filePath);
        String destinationPath = serverDirectory + "/" + sourceFile.getName();

        try {
            Path sourcePath = sourceFile.toPath();
            Path destination = new File(destinationPath).toPath();
            Files.copy(sourcePath, destination, StandardCopyOption.REPLACE_EXISTING);
            this.filePath = destinationPath;
            AssignmentSubmissionDatabase.saveSubmissionInfo(student, student.getStudentName(), destinationPath);
            return true;
        } catch (FileAlreadyExistsException e) {
            JOptionPane.showMessageDialog(null, "File with the same name already exists on the server.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error copying file to server.");
        }

        return false;
    }

    public boolean resubmitAssignment(Student student, String filePath) {
      
        this.student = student;
        File directory = new File(serverDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File sourceFile = new File(filePath);
        String destinationPath = serverDirectory + "/" + sourceFile.getName();

        try {
            Path sourcePath = sourceFile.toPath();
            Path destination = new File(destinationPath).toPath();
            Files.copy(sourcePath, destination, StandardCopyOption.REPLACE_EXISTING);
            this.filePath = destinationPath;
            AssignmentSubmissionDatabase.saveReSubmissionInfo(student, student.getStudentName(), destinationPath);
            return true;
        } catch (IOException e) {
            if (e instanceof FileAlreadyExistsException) {
                showMessageDialog("File with the same name already exists on the server.");
            } else {
                e.printStackTrace();
                showMessageDialog("Error copying file to server: " + e.getMessage());
            }
        }

        return false;
    }

    public String getPath() {
        return this.filePath;
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}