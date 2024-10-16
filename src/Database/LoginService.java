package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginService {
    private Connection ConnectLogin;
    public LoginService()
    {
        this.ConnectLogin=DatabaseController.connect();
    }
    public boolean authenticateLogin(String username, String password, String userType) {
        String query = "SELECT * FROM Login WHERE username = ? AND password = ? AND usertype = ?";
        try (PreparedStatement preparedStatement = ConnectLogin.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, userType);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If success
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if failed
        return false;
    }
    
    
}
