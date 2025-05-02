package student;

import java.util.Scanner;

public class Project {
    private String title;
    private String description;

    public Project() {
        // default constructor for interactive project input
    }

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void startProject(StudentClass student) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter project title: ");
        this.title = scanner.nextLine();

        System.out.print("Enter project description: ");
        this.description = scanner.nextLine();

        System.out.println("Project created successfully by " + student.getName() + ":");
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
