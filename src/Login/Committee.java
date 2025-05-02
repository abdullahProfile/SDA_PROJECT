// Committee.java
package Login;

public class Committee extends User {

    public Committee(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Committee";
    }

    @Override
    public void showMenu() {
        System.out.println("\nCommittee Menu:");
        System.out.println("1. Review Project");
        System.out.println("2. Give Final Approval");
        System.out.println("3. Logout");
    }

    @Override
    public void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Reviewing project...");
                break;
            case 2:
                System.out.println("Giving final approval...");
                break;
            case 3:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}