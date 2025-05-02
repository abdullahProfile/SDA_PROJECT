
import Login.LoginService;
import student.StudentClass;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GroupService groupService = new GroupService();
        groupService.createGroup("G1", new Advisor("Dr. Smith"));

        LoginService loginService = new LoginService();
        StudentClass currentStudent = loginService.handleStudentLogin();

        if (currentStudent == null) {
            System.out.println("Exiting due to failed login/registration.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to join a group? (yes/no): ");
        String joinGroup = scanner.nextLine();

        if (joinGroup.equalsIgnoreCase("yes")) {
            System.out.print("Enter group ID to join: ");
            String groupId = scanner.nextLine();

            boolean groupExists = false;
            for (Group g : groupService.getGroups()) {
                if (g.getGroupId().equals(groupId)) {
                    groupExists = true;
                    break;
                }
            }

            if (!groupExists) {
                System.out.println("Group not found. Creating group...");
                groupService.createGroup(groupId, new Advisor("Dr. Smith"));
            }

            groupService.addStudentToGroup(groupId, currentStudent);
        }

        scanner.close();
    }
}
