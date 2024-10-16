// package Database;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// }
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Applicaton.Student;
import Applicaton.Teacher;

public class AssignmentSubmissionDatabase {
    private static final String SAVE_SUBMISSION_QUERY = "INSERT INTO SubmittedAssignment2 (Sbassignment_id,Sbassignment_Name, Sbassignment_Path) VALUES (?,?,?)";
    private static final String SAVE_RESUBMISSION_QUERY = "INSERT INTO SubmittedAssignment2 (Sbassignment_id, Sbassignment_Name, Sbassignment_Path) VALUES (?,?,?)";
    private static final String SAVE_TEACHER_ASSIGNMENT_QUERY = "INSERT INTO uploadedassignment (Uassignment_id, Uassignment_Name, Uassignment_Path) VALUES (?,?,?)";
    private static final String SAVE_UPLOAD_QUERY = "INSERT INTO uploadedassignment (Uassignment_id, Uassignment_Name, Uassignment_Path) VALUES (?,?,?)";

    public static void saveSubmissionInfo(Student student, String Sbassignment_Name, String Sbassignment_Path) {
        try (Connection connection = DatabaseController.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SUBMISSION_QUERY)) {
            preparedStatement.setInt(1, student.getStudentID());
            preparedStatement.setString(2, Sbassignment_Name);
            preparedStatement.setString(3, Sbassignment_Path);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public static void saveReSubmissionInfo(Student student, String Sbassignment_Name, String Sbassignment_Path) {
        try (Connection connection = DatabaseController.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RESUBMISSION_QUERY)) {
            preparedStatement.setInt(1, student.getStudentID());
            preparedStatement.setString(2, Sbassignment_Name);
            preparedStatement.setString(3, Sbassignment_Path);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public static void saveUploadedAssignment(Teacher student, String Uassignment_Name, String Uassignment_Path) {
        try (Connection connection = DatabaseController.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_TEACHER_ASSIGNMENT_QUERY)) {
            preparedStatement.setInt(1, student.getTeacherID());
            preparedStatement.setString(2, Uassignment_Name);
            preparedStatement.setString(3, Uassignment_Path);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    public static void saveTeacherSubmissionInfo(Teacher teacher, String Sbassignment_Name, String Sbassignment_Path) {
        try (Connection connection = DatabaseController.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_UPLOAD_QUERY)) {
            preparedStatement.setInt(1, teacher.getTeacherID());
            preparedStatement.setString(2, Sbassignment_Name);
            preparedStatement.setString(3, Sbassignment_Path);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private static void handleSQLException(SQLException e) {
        if (isDuplicateKeyViolation(e)) {
            System.out.println("Assignment with the same key already exists in the database.");
         
        } else {
            e.printStackTrace();
            throw new RuntimeException("Error saving submission in the database");
        }
    }

    private static boolean isDuplicateKeyViolation(SQLException e) {
       
        int errorCode = e.getErrorCode();
        return errorCode == 1062 || errorCode == 23505;
    }
}
