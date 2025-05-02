
package student;
import java.util.ArrayList;
import java.util.List;

public class StudentClass {
    private String studentId;
    private String name;
    private String password;
    private Project assignedProject;
    private List<Document> submittedDocuments;
    private List<Feedback> receivedFeedback;
    private boolean loggedIn;

    public StudentClass(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.submittedDocuments = new ArrayList<>();
        this.receivedFeedback = new ArrayList<>();
        this.loggedIn = false;
    }

    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            loggedIn = true;
            System.out.println("Login successful. Welcome, " + name + "!");
            return true;
        }
        System.out.println("Login failed. Incorrect password.");
        return false;
    }

    public void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}