package Applicaton;
public class StudentAssignment {
    private String assignmentName; 
    private String submittedPath;
    
    public StudentAssignment(String assignmentName, String assignmentPath) {
        this.assignmentName = assignmentName;
        this.submittedPath = assignmentPath;
    }

    public String getAssignmentName(){
        return assignmentName;
    }
    public String getSumitPath(){
        return submittedPath;
    }


}
