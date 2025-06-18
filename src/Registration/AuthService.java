package Registration;

import Model.*;

import java.io.*;
import java.util.*;

public class AuthService {
    private static final String FILE_PATH = "src/data.txt";
    private Map<String, User> userDatabase = new HashMap<>();

    public AuthService() {
        loadUsersFromFile();
    }

    public boolean signUp(Scanner scanner) {
        System.out.println("Are you a Student or Supervisor? (Enter 'S' or 'P'): ");
        String type = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter User ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        if (userDatabase.containsKey(email)) {
            System.out.println("Email already registered.");
            return false;
        }
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String role = type.equals("S") ? "Student" : "Supervisor";

        User newUser;
        if (type.equals("S")){
            newUser = new Student(userId, name, email, password, role);
        }
        else if(type.equals("P")) {
            newUser = new Supervisor(userId, name, email, password, role, new ArrayList<>());
        } else {
            System.out.println("Invalid type.");
            return false;
        }

        userDatabase.put(email, newUser);
        saveUserToFile(newUser);
        System.out.println("User signed up successfully!");
        return true;
    }

    public User login(Scanner scanner) {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        for (User u : userDatabase.values()) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, " + u.getName());
                return u;
            }
        }
        System.out.println("Invalid credentials.");
        return null;
    }



    private void afterLogin(Scanner scanner, User user) {
        while (true) {
            System.out.println("Type 'logout' to log out.");
            String cmd = scanner.nextLine().trim().toLowerCase();
            if (cmd.equals("logout")) {
                System.out.println("Logged out.");
                break;
            }
        }
    }

    private void saveUserToFile(User user) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getUserId()).append(",");
            sb.append(user.getName()).append(",");
            sb.append(user.getEmail()).append(",");
            sb.append(user.getPassword()).append(",");
            sb.append(user.getRole());



            fw.write(sb.toString());
            fw.write("\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void loadUsersFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int userId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String password = parts[3];
                String role = parts[4];

                if (role.equals("Student") && parts.length == 6) {
                    int groupId = Integer.parseInt(parts[5]);
                    userDatabase.put(email, new Student(userId, name, email, password, role));
                } else if (role.equals("Supervisor")) {
                    userDatabase.put(email, new Supervisor(userId, name, email, password, role, new ArrayList<>()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
