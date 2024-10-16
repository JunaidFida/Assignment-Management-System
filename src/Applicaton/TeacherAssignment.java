package Applicaton;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Database.AssignmentSubmissionDatabase;

public class TeacherAssignment {
    private String assignmentName;

    private final String serverDirectory = "C:/ServerFiles/Course Material/TeacherAssignments"; 
    private String assignmentPath;
    private Date dueDate;
    private Teacher teacher;
    private Course course;

    public TeacherAssignment(String courseName,String assignmentName, String assignmentPath, Date dueDate) {
        this.assignmentName = assignmentName;
        this.assignmentPath = assignmentPath;
        this.dueDate = dueDate;
    }
     public String returnSUbmittedPath(){
        return assignmentPath;
    }
    public String getAssignmentName(){
        return assignmentName;
    }
    public boolean uploadAssignment(Teacher teacher,String assignmentName,String filePath) {
        this.teacher=teacher;
        this.assignmentName=assignmentName;
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
            filePath = destinationPath;
            AssignmentSubmissionDatabase.saveUploadedAssignment(teacher,assignmentName,assignmentPath);
            return true;
        } catch (FileAlreadyExistsException e) {
            JOptionPane.showMessageDialog(null, "File with the same name already exists on the server.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error copying file to server.");
        }

        return false;
    }


}
