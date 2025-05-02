package student;

import java.util.ArrayList;
import java.util.List;

public class StudentClass {
    private String studentId;
    private String name;
    private String password;
    private boolean loggedIn;

    // Constructor for initializing the student object
    public StudentClass(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.loggedIn = false;
    }

    // Getter for studentId
    public String getStudentId() {
        return studentId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for password (this method should be public to be accessible outside)
    public String getPassword() {
        return password;
    }

    // Method to handle login
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            loggedIn = true;
            System.out.println("Login successful. Welcome, " + name + "!");
            return true;
        }
        System.out.println("Login failed. Incorrect password.");
        return false;
    }

    // Method to handle logout
    public void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }
}
