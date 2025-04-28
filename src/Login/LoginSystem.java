// LoginSystem.java
package Login;

import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User[] users = {
                new Student("Aizaz", "123"),
                new Supervisor("sup", "123"),
                new CommitteeMember("com", "123")
        };

        System.out.println("=== FYP Management System ===");

        while (true) {
            System.out.print("\nEnter username (or 'exit' to quit): ");
            String username = scanner.nextLine().trim();

            if (username.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            User loggedInUser = null;

            for (User user : users) {
                if (user.login(username, password)) {
                    loggedInUser = user;
                    break;
                }
            }

            if (loggedInUser != null) {
                System.out.println("\n✅ Login successful!");
                System.out.println("Welcome, " + loggedInUser.getRole() + " " + loggedInUser.getUsername());

                // Menu loop
                while (true) {
                    loggedInUser.showMenu();
                    System.out.print("Enter your choice: ");

                    try {
                        int choice = Integer.parseInt(scanner.nextLine());
                        if (choice == getLogoutOption(loggedInUser)) {
                            break;
                        }
                        loggedInUser.handleMenuChoice(choice);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }
            } else {
                System.out.println("Login failed. Incorrect username or password.");
            }
        }

        System.out.println("Thank you for using FYP Management System. Goodbye!");
        scanner.close();
    }

    private static int getLogoutOption(User user) {
        if (user instanceof Student) return 5;
        if (user instanceof Supervisor) return 6;
        if (user instanceof CommitteeMember) return 5;
        return -1;
    }
}