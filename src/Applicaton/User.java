package Applicaton;
public class User {
    public String username;
    public String password;
    public String usertype;
    public User(String username,String password,String usertype)
    {
        this.username=username;
        this.password=password;
        this.usertype=usertype;
    }
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserType() {
        return this.usertype;
    }
    
}
