import student.StudentClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GroupService groupService = new GroupService();
        List<StudentClass> studentList = new ArrayList<>();

        // Initial default group
        groupService.createGroup("G1", new Advisor("Dr. Smith"));

        System.out.print("Are you a new student? (yes/no): ");
        String isNew = scanner.nextLine();

        StudentClass currentStudent = null;

        if (isNew.equalsIgnoreCase("yes")) {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            currentStudent = new StudentClass(id, name, password);
            studentList.add(currentStudent);

            System.out.println("Registration successful!");

            System.out.print("Please login. Enter your password: ");
            String enteredPassword = scanner.nextLine();

            if (!currentStudent.login(enteredPassword)) {
                System.out.println("Login failed. Exiting...");
                return;
            }
        } else {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            boolean found = false;
            for (StudentClass student : studentList) {
                if (student.getStudentId().equals(id)) {
                    if (student.login(enteredPassword)) {
                        currentStudent = student;
                        found = true;
                        break;
                    } else {
                        System.out.println("Incorrect password.");
                        return;
                    }
                }
            }

            if (!found) {
                System.out.println("Student not found. Please register first.");
                return;
            }
        }

        // Group joining part
        System.out.print("Do you want to join a group? (yes/no): ");
        String joinGroup = scanner.nextLine();

        if (joinGroup.equalsIgnoreCase("yes")) {
            System.out.print("Enter group ID to join (existing is 'G1', or type new ID to create): ");
            String groupId = scanner.nextLine();

            Group foundGroup = null;
            for (Group g : groupService.getGroups()) {
                if (g.getGroupId().equals(groupId)) {
                    foundGroup = g;
                    break;
                }
            }

            if (foundGroup == null) {
                System.out.println("Group not found. Creating new group with Advisor 'Dr. Smith'...");
                groupService.createGroup(groupId, new Advisor("Dr. Smith"));
            }

            groupService.addStudentToGroup(groupId, currentStudent);
        }

        scanner.close();
    }
}
