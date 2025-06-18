import Model.User;
import Registration.AuthService;
import java.util.*;

import static Controller.Login.proceedAfterLogin;

public class Main {
    public static void main(String[] args) {
        AuthService auth = new AuthService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAre you already register up? (yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                User loggedInUser = auth.login(scanner);
                if (loggedInUser != null) {
                    String role = loggedInUser.getRole();
                    proceedAfterLogin(scanner, role);
                } else {
                    System.out.println("Invalid login. Please try again.");
                }
            } else if (input.equals("no")) {
                if (auth.signUp(scanner)) {
                    System.out.println("Sign up successful!");
                } else {
                    System.out.println("Sign up failed. Please try again.");
                }
            } else {
                System.out.println("Invalid input.");
            }
        }
    }







}
