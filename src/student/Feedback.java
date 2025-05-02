package student;

import java.util.Scanner;

public class Feedback {
    private String message;
    private String givenBy;

    public Feedback() {
        // default constructor for interactive feedback
    }

    public Feedback(String message, String givenBy) {
        this.message = message;
        this.givenBy = givenBy;
    }

    public void collectFeedback(StudentClass student) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your feedback message: ");
        this.message = scanner.nextLine();
        this.givenBy = student.getName();

        System.out.println("Thank you for your feedback, " + givenBy + "!");
    }
}
