package Applicaton;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String courseName;
    private List<StudentAssignment> assignments;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.assignments = new ArrayList<>();
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<StudentAssignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(String assignmentName, String assignmentPath) {
        StudentAssignment assignment = new StudentAssignment(assignmentName, assignmentPath);
        assignments.add(assignment);
    }
}
