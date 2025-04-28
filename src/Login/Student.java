// Student.java
package Login;

import java.util.Scanner;

public class Student extends User {

    public Student(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Student";
    }

    public void viewGroupMembers() {
        System.out.println("Displaying group members...");
    }

    public void assignSupervisor() {
        System.out.println("Assigning a supervisor...");
    }

    public void startChat() {
        System.out.println("Starting chat with supervisor...");
    }

    public void giveFeedback() {
        System.out.println("Giving feedback...");
    }

    @Override
    public void showMenu() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. View Group Members");
        System.out.println("2. Assign Supervisor");
        System.out.println("3. Start Chat");
        System.out.println("4. Give Feedback");
        System.out.println("5. Logout");
    }

    @Override
    public void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                viewGroupMembers();
                break;
            case 2:
                assignSupervisor();
                break;
            case 3:
                startChat();
                break;
            case 4:
                giveFeedback();
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}