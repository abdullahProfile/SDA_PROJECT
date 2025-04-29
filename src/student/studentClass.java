package student;
import java.util.ArrayList;
import java.util.List;
import Login.LoginSystem;

public class StudentClass {
    private String studentId;
    private String name;
    private String password;
    private Project assignedProject;
    private List<Document> submittedDocuments;
    private List<Feedback> receivedFeedback;
    private boolean loggedIn;

    // Constructor
    public Student(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.submittedDocuments = new ArrayList<>();
        this.receivedFeedback = new ArrayList<>();
        this.loggedIn = false;
    }

    // Authentication methods
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

    // Project-related methods
    public void submitProposal(Document proposal) {
        if (!loggedIn) {
            System.out.println("Please login first.");
            return;
        }
        
        submittedDocuments.add(proposal);
        System.out.println("Proposal submitted successfully!");
    }

    public void updateDocument(Document oldDoc, Document newDoc) {
        if (!loggedIn) {
            System.out.println("Please login first.");
            return;
        }
        
        if (submittedDocuments.remove(oldDoc)) {
            submittedDocuments.add(newDoc);
            System.out.println("Document updated successfully!");
        } else {
            System.out.println("Document not found. Update failed.");
        }
    }

    public void viewAssignedProject() {
        if (!loggedIn) {
            System.out.println("Please login first.");
            return;
        }
        
        if (assignedProject != null) {
            System.out.println("Assigned Project: " + assignedProject.getTitle());
            System.out.println("Description: " + assignedProject.getDescription());
            System.out.println("Supervisor: " + assignedProject.getSupervisor().getName());
        } else {
            System.out.println("No project assigned yet.");
        }
    }

    // Feedback methods
    public void viewFeedback() {
        if (!loggedIn) {
            System.out.println("Please login first.");
            return;
        }
        
        if (receivedFeedback.isEmpty()) {
            System.out.println("No feedback available.");
        } else {
            System.out.println("--- Your Feedback ---");
            for (Feedback feedback : receivedFeedback) {
                System.out.println("From: " + feedback.getEvaluatorName());
                System.out.println("Marks: " + feedback.getMarks());
                System.out.println("Comments: " + feedback.getComments());
                System.out.println("-------------------");
            }
            }
        }
}
