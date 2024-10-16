package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetreiverDB {
    private static final String SELECT_UPLOADED_ASSIGNMENTS_QUERY = "SELECT Uassignment_Name, Uassignment_Path, Uassignment_date FROM uploadedassignment";
    private static final String SELECT_COURSES_QUERY = "SELECT crs_id,crs_Name From course";
   
    public static String[] getUploadedAssignments() {
        List<String> teacherAssignmentList = new ArrayList<>();
        try (Connection connection = DatabaseController.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UPLOADED_ASSIGNMENTS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String assignmentName = resultSet.getString("Uassignment_Name");
                String dueDate = resultSet.getString("Uassignment_date");
                teacherAssignmentList.add("Name: " + assignmentName + ", Path: "  + ", Due Date: " + dueDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching uploaded assignments from the database");
        }

        return teacherAssignmentList.toArray(new String[0]);
    }
    public static String[] getSubmittedAssignments() {
        final String SELECT_SUBMITTED_ASSIGNMENTS_QUERY = "SELECT Sbassignment_Name, Sbassignment_Path FROM SubmittedAssignment2";
        List<String> studentAssignmentList = new ArrayList<>();
    
        try (Connection connection = DatabaseController.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBMITTED_ASSIGNMENTS_QUERY)) {    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String assignmentName = resultSet.getString("Sbassignment_Name");
                    String path = resultSet.getString("Sbassignment_Path");
                    studentAssignmentList.add("Name: " + assignmentName + ", Path: " + path);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching submitted assignments from the database");
        }
    
        return studentAssignmentList.toArray(new String[0]);
    }
    
    
    public static String[] getCourses() {
        List<String> courses = new ArrayList<>();

        try (Connection connection = DatabaseController.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSES_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String courseId = resultSet.getString("crs_id");
                String courseName = resultSet.getString("crs_Name");
                courses.add("ID: " + courseId + " |  " + courseName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching uploaded assignments from the database");
        }

        return courses.toArray(new String[0]);
    }
    
}