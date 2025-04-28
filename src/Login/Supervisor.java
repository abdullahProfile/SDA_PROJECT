// Supervisor.java
package Login;

public class Supervisor extends User {

    public Supervisor(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Supervisor";
    }

    public void startChat() {
        System.out.println("Chatting with students...");
    }

    public void giveFeedback() {
        System.out.println("Providing feedback...");
    }

    public void setMeetingTimings() {
        System.out.println("Setting meeting timings...");
    }

    public void evaluateStudent() {
        System.out.println("Evaluating student...");
    }

    public void viewStudents() {
        System.out.println("Viewing all students...");
    }

    @Override
    public void showMenu() {
        System.out.println("\nSupervisor Menu:");
        System.out.println("1. Start Chat");
        System.out.println("2. Give Feedback");
        System.out.println("3. Set Meeting Timings");
        System.out.println("4. Evaluate Student");
        System.out.println("5. View Students");
        System.out.println("6. Logout");
    }

    @Override
    public void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                startChat();
                break;
            case 2:
                giveFeedback();
                break;
            case 3:
                setMeetingTimings();
                break;
            case 4:
                evaluateStudent();
                break;
            case 5:
                viewStudents();
                break;
            case 6:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}