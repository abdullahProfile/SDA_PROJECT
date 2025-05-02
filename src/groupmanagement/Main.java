import Login.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginService loginService = new LoginService();
        User currentUser = null;

        while (true) {
            System.out.println("\nWelcome to FYP Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            String mainChoice = scanner.nextLine();

            switch (mainChoice) {
                case "1":
                    currentUser = loginService.handleRegistration();
                    if (currentUser != null) {
                        showUserMenu(currentUser, scanner);
                    }
                    break;
                case "2":
                    currentUser = loginService.handleExistingUserLogin();
                    if (currentUser != null) {
                        showUserMenu(currentUser, scanner);
                    }
                    break;
                case "3":
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void showUserMenu(User user, Scanner scanner) {
        boolean logout = false;
        
        while (!logout) {
            user.showMenu();
            System.out.print("Enter your choice: ");
            int choice;
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                user.handleMenuChoice(choice);
                
                // Check logout condition based on user type
                if ((user instanceof Student && choice == 3) ||
                    (user instanceof Supervisor && choice == 3) ||
                    (user instanceof Committee && choice == 3)) {
                    logout = true;
                    System.out.println("Logging out...");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}