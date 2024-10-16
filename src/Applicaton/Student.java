package Applicaton;

public class Student {
    private String studentName;
    private int studentID;
    private String studentEmail;

    public Student(String name,String email) {
        this.studentName = name;
        this.studentEmail=email;
    }
    public String getStudentName() {
        return studentName;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }
}
