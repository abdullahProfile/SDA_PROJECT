package Login;

import java.util.Scanner;

public class LoginService {
    private final Scanner scanner = new Scanner(System.in);

    public User handleRegistration() {
        System.out.println("\n--- Registration ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter role (student/supervisor/committee): ");
        String role = scanner.nextLine().toLowerCase();

        String studentId = null;
        if (role.equals("student")) {
            System.out.print("Enter student ID: ");
            studentId = scanner.nextLine();
        }

        User newUser = User.register(username, password, role, studentId);
        if (newUser != null) {
            System.out.println("Registration successful! Please login.");
        }
        return null; // Return null to force login after registration
    }

    public User handleExistingUserLogin() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = User.getUserByUsername(username);
        if (user != null && user.login(username, password)) {
            System.out.println("Login successful!");
            return user;
        }
        System.out.println("Login failed!");
        return null;
    }
}