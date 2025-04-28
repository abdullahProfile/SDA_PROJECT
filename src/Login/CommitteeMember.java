// CommitteeMember.java
package Login;

public class CommitteeMember extends User {

    public CommitteeMember(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Committee Member";
    }

    public void giveFeedback() {
        System.out.println("Committee giving feedback...");
    }

    public void viewStudents() {
        System.out.println("Committee viewing students...");
    }

    public void evaluateStudent() {
        System.out.println("Committee evaluating student...");
    }

    public void viewMeetingTimings() {
        System.out.println("Viewing meeting timings...");
    }

    @Override
    public void showMenu() {
        System.out.println("\nCommittee Member Menu:");
        System.out.println("1. View Students");
        System.out.println("2. Give Feedback");
        System.out.println("3. View Meeting Timings");
        System.out.println("4. Evaluate Student");
        System.out.println("5. Logout");
    }

    @Override
    public void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                viewStudents();
                break;
            case 2:
                giveFeedback();
                break;
            case 3:
                viewMeetingTimings();
                break;
            case 4:
                evaluateStudent();
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}