package Applicaton;

public class Teacher {
    private String teacherName;
    private int teacherID;
    private String teacherEmail;

    public Teacher(String name,String email) {
        this.teacherName = name;
        this.teacherEmail=email;
    }
    public String getTeacherName() {
        return teacherName;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }
}
