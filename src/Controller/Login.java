package Controller;

import java.util.Scanner;

public class Login {
    public static void proceedAfterLogin(Scanner scanner, String role) {
        System.out.println("Login successful!");

        if (role.equals("Student")) {
            studentController studentController = new studentController();
            System.out.println("Do you want to make a new group yes or no: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes")) {
                studentController.createGroup();
            } else if (input.equals("no")) {
                System.out.println("You are not in a group");
            } else {
                System.out.println("Invalid input.");
            }
            while (true) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Submit Proposal");
                System.out.println("2. Update Document");
                System.out.println("3. View Feedback");
                System.out.println("4. Track Progress");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        studentController.submitProposal();
                        break;
                    case "2":
                        studentController.updateDocument();
                        break;
                    case "3":
                        studentController.viewFeedback();
                        break;
                    case "4":
                        studentController.trackProgress();
                        break;
                    case "5":
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } else if (role.equals("Supervisor")) {
            supervisorController supervisorController = new supervisorController();
            while (true) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Review Proposal");
                System.out.println("2. Provide Feedback");
                System.out.println("3. Grade Submission");
                System.out.println("4. Define Milestone");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        supervisorController.reviewProposal();
                        break;
                    case "2":
                        supervisorController.provideFeedback();
                        break;
                    case "3":
                        supervisorController.gradeSubmission();
                        break;
                    case "4":
                        supervisorController.defineMilestone();
                        break;
                    case "5":
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("Invalid input.");
        }
    }
}
