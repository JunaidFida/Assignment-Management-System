package Interfaces;
import javax.swing.*;
import Applicaton.LoginController;
import Applicaton.Student;
import Applicaton.Teacher;
import Applicaton.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userType;
    private JPanel panel;
    public Login()  {
     
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);

       // Input form for login
       panel = new JPanel(new GridLayout(4, 1));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);
        
        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel("User Type:"));
        userType= new JComboBox<>(new String[]{"Student", "Teacher"});
        panel.add(userType);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.BLACK);
        panel.add(loginButton);
        add(panel);
    }

    private void handleLogin() {

        //extracts inputs into the varialbles and pass to the application layer ; Login Controller
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        Object usertype=userType.getSelectedItem();
        String selectedUserType=usertype.toString();

        LoginController loginController = new LoginController();
        User user=new User(username, password, selectedUserType); //creating a new user insatnce 
        boolean loggedInUser = loginController.login(user); //call to login controller(Application layer) 

        if (loggedInUser) {
            if(selectedUserType=="Student"){
                JOptionPane.showMessageDialog(this, "Login successful!\nUser Name : "+ username);
                SwingUtilities.invokeLater(() -> {
                    Student  student=new Student(username,username+"@gmail.com");
                    new studentDashboard(student).setVisible(true);
                    panel.setVisible(false);
                });
            }
            else{
                JOptionPane.showMessageDialog(this, "Login successful!\nUser Name: " + username);
                SwingUtilities.invokeLater(() -> {
                    Teacher teacher=new Teacher(username,username+"@gmail.com");
                    new TeacherDashboard(teacher).setVisible(true);
                    panel.setVisible(false);
                });
            }    
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Invalid username or password.");
        }
    }

}

