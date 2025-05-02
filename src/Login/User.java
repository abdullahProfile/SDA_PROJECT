package Login;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private static List<User> registeredUsers = new ArrayList<>();
    private String username;
    private String password;
    private boolean isRegistered;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isRegistered = false;
    }

    // First define the helper method
    public static User getUserByUsername(String username) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // Then define the register method that uses it
    public static User register(String username, String password, String role, String studentId) {
        // Now this will work because getUserByUsername is defined above
        if (getUserByUsername(username) != null) {
            System.out.println("Username already exists!");
            return null;
        }

        User newUser;
        try {
            switch (role.toLowerCase()) {
                case "student":
                    if (studentId == null || studentId.isEmpty()) {
                        throw new IllegalArgumentException("Student ID is required");
                    }
                    newUser = new Student(studentId, username, password);
                    break;
                case "supervisor":
                    newUser = new Supervisor(username, password);
                    break;
                case "committee":
                    newUser = new Committee(username, password);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid role");
            }

            registeredUsers.add(newUser);
            newUser.isRegistered = true;
            System.out.println("Registration successful for " + role + ": " + username);
            return newUser;
        } catch (IllegalArgumentException e) {
            System.out.println("Registration error: " + e.getMessage());
            return null;
        }
    }

    public boolean login(String inputUsername, String inputPassword) {
        if (!isRegistered) {
            System.out.println("Please register first!");
            return false;
        }
        boolean success = this.username.equals(inputUsername) && this.password.equals(inputPassword);
        if (!success) {
            System.out.println("Invalid credentials!");
        }
        return success;
    }

    public String getUsername() {
        return username;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public abstract String getRole();
    public abstract void showMenu();
    public abstract void handleMenuChoice(int choice);
}