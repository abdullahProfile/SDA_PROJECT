package Controller;

import Model.*;

import java.io.*;
import java.util.*;

public class studentController {
    private List<User> users;

    public studentController() {
        this.users = loadUsersFromFile("src/data.txt");
    }

    public void submitProposal() {
        System.out.println("Submitting proposal...");
    }

    public void updateDocument() {
        System.out.println("Updating document...");
    }

    public void viewFeedback() {
        System.out.println("Viewing feedback...");
    }

    public void trackProgress() {
        System.out.println("Tracking progress...");
    }

    public void createGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Group ID: ");
        int groupId = Integer.parseInt(scanner.nextLine());

        List<String> students = new ArrayList<>();
        System.out.print("Enter number of students in group: ");
        int numStudents = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= numStudents; i++) {
            System.out.print("Enter name of student " + i + ": ");
            students.add(scanner.nextLine());
        }

        Supervisor supervisor = null;
        while (supervisor == null) {
            System.out.println("\nAvailable Supervisors:");
            for (Supervisor sup : getAllSupervisors()) {
                System.out.println("- " + sup.getName() + " (" + sup.getEmail() + ")");
            }

            System.out.print("Enter Supervisor Email: ");
            String supervisorEmail = scanner.nextLine();

            for (User user : users) {
                if (user.getEmail().equalsIgnoreCase(supervisorEmail) && user instanceof Supervisor) {
                    supervisor = (Supervisor) user;
                    break;
                }
            }
            if (supervisor == null) {
                System.out.println("Supervisor not found. Please try again.");
            }
        }

        System.out.print("Enter Project Title: ");
        String projectTitle = scanner.nextLine();

        Group group = new Group(groupId, students, supervisor, projectTitle);
        saveGroupToFile(group);
        System.out.println("Group created and saved successfully!");
    }

    private void saveGroupToFile(Group group) {
        try (FileWriter writer = new FileWriter("src/group.txt", true)) {
            writer.write("Group ID: " + group.getGroupId() + "\n");
            writer.write("Students: " + String.join(", ", group.getStudents()) + "\n");
            writer.write("Supervisor: " + group.getSupervisor().getName() + "\n");
            writer.write("Project Title: " + group.getProjectTitle() + "\n");
            writer.write("-----------------------------\n");
        } catch (IOException e) {
            System.out.println("Error saving group: " + e.getMessage());
        }
    }

    public List<Supervisor> getAllSupervisors() {
        List<Supervisor> supervisors = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Supervisor) {
                supervisors.add((Supervisor) user);
            }
        }
        return supervisors;
    }

    private List<User> loadUsersFromFile(String filePath) {
        List<User> userList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return userList;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int userId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String password = parts[3];
                String role = parts[4];

                if (role.equalsIgnoreCase("Student") && parts.length == 6) {
                    userList.add(new Student(userId, name, email, password, role));
                } else if (role.equalsIgnoreCase("Supervisor")) {
                    userList.add(new Supervisor(userId, name, email, password, role, new ArrayList<>()));
                } else {
                    userList.add(new User(userId, name, email, password, role));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users: " + e.getMessage());
        }

        return userList;
    }
}
