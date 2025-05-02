package Login;

public class Student extends User {
    private String studentId;
    private String projectTitle;

    public Student(String studentId, String username, String password) {
        super(username, password);
        this.studentId = studentId;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getProjectTitle() { return projectTitle; }

    // Setters
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public void showMenu() {
        System.out.println("\nStudent Menu:");
        System.out.println("1. View Profile");
        System.out.println("2. Set Project Title");
        System.out.println("3. Logout");
    }

    @Override
    public void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Student ID: " + studentId);
                System.out.println("Project: " + (projectTitle != null ? projectTitle : "Not set"));
                break;
            case 2:
                System.out.print("Enter project title: ");
                // Implementation would need a Scanner
                break;
            case 3:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}