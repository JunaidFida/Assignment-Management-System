package Applicaton;
import Database.LoginService ;
public class LoginController {
    private LoginService loginService; //to connect with database fascade i.e Login service in our case
    public LoginController() {
        this.loginService = new LoginService();
    }
    public boolean login(String username, String password, String userType) {
        return loginService.authenticateLogin(username, password, userType);
    }
    public boolean login(User user) {
        return loginService.authenticateLogin(user.username, user.password, user.usertype);
    }
}
