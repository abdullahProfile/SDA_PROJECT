import java.util.*;
import java.io.*;
import java.time.LocalDate;

// Main class to run the system
public class FYPMain {
    public static void main(String[] args) {
        FYPManagementSystem system = new FYPManagementSystem();
        system.initializeSampleData();
        system.start();
    }
}

// Core system class
class FYPManagementSystem {
    private List<Admin> admins;
    private List<Student> students;
    private List<Supervisor> supervisors;
    private List<Project> projects;
    private SystemConfig config;
    private Admin currentAdmin;
    private Scanner scanner;

    public FYPManagementSystem() {
        admins = new ArrayList<>();
        students = new ArrayList<>();
        supervisors = new ArrayList<>();
        projects = new ArrayList<>();
        config = new SystemConfig();
        scanner = new Scanner(System.in);
    }

    public void initializeSampleData() {
        // Add sample admin
        admins.add(new Admin("admin@university.edu", "admin123", "System Admin"));
        
        // Add sample students
        students.add(new Student("S1001", "Malik Zaman", "mz@student.edu", "Computer Science"));
        students.add(new Student("S1002", "Farhan Khan", "farhan@student.edu", "Electrical Engineering"));
        
        // Add sample supervisors
        supervisors.add(new Supervisor("CS101", "Dr.Mukhtiar Zamin", "Computer Science", 5));
        supervisors.add(new Supervisor("EE201", "Dr.Fizza Seemab", "Electrical Engineering", 4));
        
        // Add sample projects
        Project p1 = new Project("P001", "AI Research", "Advanced AI algorithms", LocalDate.now().plusMonths(6));
        projects.add(p1);
    }

    public void start() {
        System.out.println("=== FYP Management System ===");
        login();
    }

    private void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentAdmin = authenticateAdmin(email, password);
        if (currentAdmin != null) {
            System.out.println("Login successful! Welcome, " + currentAdmin.getName());
            showMainMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            login();
        }
    }

    private Admin authenticateAdmin(String email, String password) {
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Manage Students");
            System.out.println("2. Manage Supervisors");
            System.out.println("3. Manage Projects");
            System.out.println("4. Generate Reports");
            System.out.println("5. System Configuration");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    manageSupervisors();
                    break;
                case 3:
                    manageProjects();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    systemConfiguration();
                    break;
                case 6:
                    currentAdmin = null;
                    System.out.println("Logged out successfully.");
                    start();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void manageStudents() {
        System.out.println("\n--- Manage Students ---");
        System.out.println("1. Add Student");
        System.out.println("2. Edit Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View All Students");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                editStudent();
                break;
            case 3:
                deleteStudent();
                break;
            case 4:
                viewAllStudents();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid option.");
        }
        manageStudents();
    }

    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Student ID: ");
        String id = scanner.nextLine();
        
        // Check if student already exists
        if (getStudentById(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists.");
            return;
        }
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Program: ");
        String program = scanner.nextLine();
        
        students.add(new Student(id, name, email, program));
        System.out.println("Student added successfully!");
    }

    private void editStudent() {
        System.out.println("\n--- Edit Student ---");
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();
        
        Student student = getStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        System.out.println("Current details:");
        System.out.println(student);
        
        System.out.print("New Name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);
        
        System.out.print("New Email (leave blank to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) student.setEmail(email);
        
        System.out.print("New Program (leave blank to keep current): ");
        String program = scanner.nextLine();
        if (!program.isEmpty()) student.setProgram(program);
        
        System.out.println("Student updated successfully!");
    }

    private void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        
        Student student = getStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        // Check if student is assigned to any project
        for (Project project : projects) {
            if (project.getStudent() != null && project.getStudent().getId().equals(id)) {
                System.out.println("Cannot delete student. They are assigned to project: " + project.getTitle());
                return;
            }
        }
        
        students.remove(student);
        System.out.println("Student deleted successfully!");
    }

    private void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private void manageSupervisors() {
        System.out.println("\n--- Manage Supervisors ---");
        System.out.println("1. Add Supervisor");
        System.out.println("2. Assign Supervisor to Project");
        System.out.println("3. View All Supervisors");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addSupervisor();
                break;
            case 2:
                assignSupervisor();
                break;
            case 3:
                viewAllSupervisors();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option.");
        }
        manageSupervisors();
    }

    private void addSupervisor() {
        System.out.println("\n--- Add New Supervisor ---");
        System.out.print("Supervisor ID: ");
        String id = scanner.nextLine();
        
        // Check if supervisor already exists
        if (getSupervisorById(id) != null) {
            System.out.println("Error: Supervisor with ID " + id + " already exists.");
            return;
        }
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();
        System.out.print("Max Projects: ");
        int maxProjects = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        supervisors.add(new Supervisor(id, name, department, maxProjects));
        System.out.println("Supervisor added successfully!");
    }

    private void assignSupervisor() {
        System.out.println("\n--- Assign Supervisor to Project ---");
        
        // List all projects
        System.out.println("\nAvailable Projects:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i+1) + ". " + projects.get(i).getTitle() + 
                (projects.get(i).getSupervisor() == null ? " (No supervisor)" : 
                " (Supervisor: " + projects.get(i).getSupervisor().getName() + ")"));
        }
        
        System.out.print("Select project number: ");
        int projectIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        
        if (projectIndex < 0 || projectIndex >= projects.size()) {
            System.out.println("Invalid project selection.");
            return;
        }
        
        Project project = projects.get(projectIndex);
        
        // List all supervisors
        System.out.println("\nAvailable Supervisors:");
        for (int i = 0; i < supervisors.size(); i++) {
            Supervisor s = supervisors.get(i);
            int currentLoad = getSupervisorProjectCount(s.getId());
            System.out.println((i+1) + ". " + s.getName() + " (" + s.getDepartment() + 
                ") - Load: " + currentLoad + "/" + s.getMaxProjects());
        }
        
        System.out.print("Select supervisor number: ");
        int supervisorIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        
        if (supervisorIndex < 0 || supervisorIndex >= supervisors.size()) {
            System.out.println("Invalid supervisor selection.");
            return;
        }
        
        Supervisor supervisor = supervisors.get(supervisorIndex);
        
        // Check supervisor's current load
        if (getSupervisorProjectCount(supervisor.getId()) >= supervisor.getMaxProjects()) {
            System.out.println("Error: This supervisor has reached their maximum project limit.");
            return;
        }
        
        // Assign supervisor to project
        project.setSupervisor(supervisor);
        System.out.println("Supervisor " + supervisor.getName() + " assigned to project " + project.getTitle());
    }

    private int getSupervisorProjectCount(String supervisorId) {
        int count = 0;
        for (Project project : projects) {
            if (project.getSupervisor() != null && project.getSupervisor().getId().equals(supervisorId)) {
                count++;
            }
        }
        return count;
    }

    private void viewAllSupervisors() {
        System.out.println("\n--- All Supervisors ---");
        if (supervisors.isEmpty()) {
            System.out.println("No supervisors found.");
            return;
        }
        
        for (Supervisor supervisor : supervisors) {
            System.out.println(supervisor + " - Current Load: " + 
                getSupervisorProjectCount(supervisor.getId()) + "/" + supervisor.getMaxProjects());
        }
    }

    private Supervisor getSupervisorById(String id) {
        for (Supervisor supervisor : supervisors) {
            if (supervisor.getId().equals(id)) {
                return supervisor;
            }
        }
        return null;
    }

    private void manageProjects() {
        System.out.println("\n--- Manage Projects ---");
        System.out.println("1. Create Project");
        System.out.println("2. Assign Student to Project");
        System.out.println("3. View All Projects");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createProject();
                break;
            case 2:
                assignStudent();
                break;
            case 3:
                viewAllProjects();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option.");
        }
        manageProjects();
    }

    private void createProject() {
        System.out.println("\n--- Create New Project ---");
        System.out.print("Project ID: ");
        String id = scanner.nextLine();
        
        // Check if project already exists
        if (getProjectById(id) != null) {
            System.out.println("Error: Project with ID " + id + " already exists.");
            return;
        }
        
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(scanner.nextLine());
        
        projects.add(new Project(id, title, description, deadline));
        System.out.println("Project created successfully!");
    }

    private void assignStudent() {
        System.out.println("\n--- Assign Student to Project ---");
        
        // List all projects
        System.out.println("\nAvailable Projects:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i+1) + ". " + projects.get(i).getTitle() + 
                (projects.get(i).getStudent() == null ? " (No student)" : 
                " (Student: " + projects.get(i).getStudent().getName() + ")"));
        }
        
        System.out.print("Select project number: ");
        int projectIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        
        if (projectIndex < 0 || projectIndex >= projects.size()) {
            System.out.println("Invalid project selection.");
            return;
        }
        
        Project project = projects.get(projectIndex);
        
        // Check if project already has a student
        if (project.getStudent() != null) {
            System.out.println("Error: This project already has a student assigned.");
            return;
        }
        
        // List all students
        System.out.println("\nAvailable Students:");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            boolean hasProject = false;
            for (Project p : projects) {
                if (p.getStudent() != null && p.getStudent().getId().equals(s.getId())) {
                    hasProject = true;
                    break;
                }
            }
            if (!hasProject) {
                System.out.println((i+1) + ". " + s.getName() + " (" + s.getId() + ")");
            }
        }
        
        System.out.print("Select student number: ");
        int studentIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline
        
        if (studentIndex < 0 || studentIndex >= students.size()) {
            System.out.println("Invalid student selection.");
            return;
        }
        
        Student student = students.get(studentIndex);
        
        // Check if student already has a project
        for (Project p : projects) {
            if (p.getStudent() != null && p.getStudent().getId().equals(student.getId())) {
                System.out.println("Error: This student is already assigned to a project.");
                return;
            }
        }
        
        // Assign student to project
        project.setStudent(student);
        System.out.println("Student " + student.getName() + " assigned to project " + project.getTitle());
    }

    private void viewAllProjects() {
        System.out.println("\n--- All Projects ---");
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
            return;
        }
        
        for (Project project : projects) {
            System.out.println(project);
            System.out.println("  Student: " + 
                (project.getStudent() != null ? project.getStudent().getName() : "Not assigned"));
            System.out.println("  Supervisor: " + 
                (project.getSupervisor() != null ? project.getSupervisor().getName() : "Not assigned"));
        }
    }

    private Project getProjectById(String id) {
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                return project;
            }
        }
        return null;
    }

    private void generateReports() {
        System.out.println("\n--- Generate Reports ---");
        System.out.println("1. Progress Report");
        System.out.println("2. Evaluation Report");
        System.out.println("3. Back to Main Menu");
        System.out.print("Select report type: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                generateProgressReport();
                break;
            case 2:
                generateEvaluationReport();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void generateProgressReport() {
        System.out.println("\n--- Progress Report ---");
        System.out.println("Generating report for all projects...");
        
        System.out.println("\n=== FYP Progress Report ===");
        System.out.println("Generated on: " + LocalDate.now());
        System.out.println("Total Projects: " + projects.size());
        
        int completed = 0;
        int inProgress = 0;
        int notStarted = 0;
        
        for (Project project : projects) {
            if (project.getStudent() == null) {
                notStarted++;
            } else if (project.isCompleted()) {
                completed++;
            } else {
                inProgress++;
            }
        }
        
        System.out.println("\nStatus Summary:");
        System.out.println("- Completed: " + completed);
        System.out.println("- In Progress: " + inProgress);
        System.out.println("- Not Started: " + notStarted);
        
        System.out.println("\nProject Details:");
        for (Project project : projects) {
            System.out.println("\nProject: " + project.getTitle());
            System.out.println("Student: " + 
                (project.getStudent() != null ? project.getStudent().getName() : "Not assigned"));
            System.out.println("Supervisor: " + 
                (project.getSupervisor() != null ? project.getSupervisor().getName() : "Not assigned"));
            System.out.println("Deadline: " + project.getDeadline());
            System.out.println("Status: " + 
                (project.getStudent() == null ? "Not Started" : 
                (project.isCompleted() ? "Completed" : "In Progress")));
        }
        
        System.out.println("\nReport generated successfully!");
        System.out.println("Would you like to save to file? (Y/N)");
        String saveChoice = scanner.nextLine();
        if (saveChoice.equalsIgnoreCase("Y")) {
            saveReportToFile("Progress_Report_" + LocalDate.now() + ".txt", generateReportContent());
        }
    }

    private void generateEvaluationReport() {
        System.out.println("\n--- Evaluation Report ---");
        System.out.println("Generating evaluation summary...");
        
        System.out.println("\n=== FYP Evaluation Report ===");
        System.out.println("Generated on: " + LocalDate.now());
        
        System.out.println("\nSupervisor Evaluation Summary:");
        for (Supervisor supervisor : supervisors) {
            int assignedProjects = getSupervisorProjectCount(supervisor.getId());
            System.out.println("\nSupervisor: " + supervisor.getName());
            System.out.println("Department: " + supervisor.getDepartment());
            System.out.println("Projects Assigned: " + assignedProjects + "/" + supervisor.getMaxProjects());
            
            int completed = 0;
            for (Project project : projects) {
                if (project.getSupervisor() != null && 
                    project.getSupervisor().getId().equals(supervisor.getId()) && 
                    project.isCompleted()) {
                    completed++;
                }
            }
            System.out.println("Projects Completed: " + completed);
        }
        
        System.out.println("\nReport generated successfully!");
        System.out.println("Would you like to save to file? (Y/N)");
        String saveChoice = scanner.nextLine();
        if (saveChoice.equalsIgnoreCase("Y")) {
            saveReportToFile("Evaluation_Report_" + LocalDate.now() + ".txt", generateReportContent());
        }
    }

    private String generateReportContent() {
        // This would generate the actual content to be saved to file
        // Simplified for this example
        return "Report content generated on " + LocalDate.now();
    }

    private void saveReportToFile(String filename, String content) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.write(content);
            System.out.println("Report saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving report: " + e.getMessage());
        }
    }

    private void systemConfiguration() {
        System.out.println("\n--- System Configuration ---");
        System.out.println("1. Set Submission Deadlines");
        System.out.println("2. Set Grading Criteria");
        System.out.println("3. View Current Configuration");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                setDeadlines();
                break;
            case 2:
                setGradingCriteria();
                break;
            case 3:
                viewConfiguration();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option.");
        }
        systemConfiguration();
    }

    private void setDeadlines() {
        System.out.println("\n--- Set Submission Deadlines ---");
        System.out.print("Proposal Deadline (YYYY-MM-DD): ");
        LocalDate proposalDeadline = LocalDate.parse(scanner.nextLine());
        
        if (proposalDeadline.isBefore(LocalDate.now())) {
            System.out.println("Error: Deadline cannot be in the past.");
            return;
        }
        
        System.out.print("Final Report Deadline (YYYY-MM-DD): ");
        LocalDate finalReportDeadline = LocalDate.parse(scanner.nextLine());
        
        if (finalReportDeadline.isBefore(proposalDeadline)) {
            System.out.println("Error: Final report deadline must be after proposal deadline.");
            return;
        }
        
        config.setProposalDeadline(proposalDeadline);
        config.setFinalReportDeadline(finalReportDeadline);
        System.out.println("Deadlines updated successfully!");
    }

    private void setGradingCriteria() {
        System.out.println("\n--- Set Grading Criteria ---");
        System.out.print("Proposal Weight (%): ");
        int proposalWeight = scanner.nextInt();
        System.out.print("Progress Report Weight (%): ");
        int progressWeight = scanner.nextInt();
        System.out.print("Final Report Weight (%): ");
        int finalWeight = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (proposalWeight + progressWeight + finalWeight != 100) {
            System.out.println("Error: Weights must sum to 100%.");
            return;
        }
        
        config.setProposalWeight(proposalWeight);
        config.setProgressWeight(progressWeight);
        config.setFinalWeight(finalWeight);
        System.out.println("Grading criteria updated successfully!");
    }

    private void viewConfiguration() {
        System.out.println("\n--- Current System Configuration ---");
        System.out.println(config);
    }
}

// Entity classes
class Admin {
    private String email;
    private String password;
    private String name;

    public Admin(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters and setters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
}

class Student {
    private String id;
    private String name;
    private String email;
    private String program;

    public Student(String id, String name, String email, String program) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.program = program;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getProgram() { return program; }
    
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setProgram(String program) { this.program = program; }

    @Override
    public String toString() {
        return "Student [ID: " + id + ", Name: " + name + ", Email: " + email + ", Program: " + program + "]";
    }
}

class Supervisor {
    private String id;
    private String name;
    private String department;
    private int maxProjects;

    public Supervisor(String id, String name, String department, int maxProjects) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.maxProjects = maxProjects;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getMaxProjects() { return maxProjects; }

    @Override
    public String toString() {
        return "Supervisor [ID: " + id + ", Name: " + name + ", Department: " + department + "]";
    }
}

class Project {
    private String id;
    private String title;
    private String description;
    private LocalDate deadline;
    private Student student;
    private Supervisor supervisor;
    private boolean completed;

    public Project(String id, String title, String description, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.completed = false;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDeadline() { return deadline; }
    public Student getStudent() { return student; }
    public Supervisor getSupervisor() { return supervisor; }
    public boolean isCompleted() { return completed; }
    
    public void setStudent(Student student) { this.student = student; }
    public void setSupervisor(Supervisor supervisor) { this.supervisor = supervisor; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        return "Project [ID: " + id + ", Title: " + title + ", Deadline: " + deadline + "]";
    }
}

class SystemConfig {
    private LocalDate proposalDeadline;
    private LocalDate finalReportDeadline;
    private int proposalWeight;
    private int progressWeight;
    private int finalWeight;

    public SystemConfig() {
        // Default values
        proposalDeadline = LocalDate.now().plusMonths(1);
        finalReportDeadline = LocalDate.now().plusMonths(6);
        proposalWeight = 20;
        progressWeight = 30;
        finalWeight = 50;
    }

    // Getters and setters
    public LocalDate getProposalDeadline() { return proposalDeadline; }
    public LocalDate getFinalReportDeadline() { return finalReportDeadline; }
    public int getProposalWeight() { return proposalWeight; }
    public int getProgressWeight() { return progressWeight; }
    public int getFinalWeight() { return finalWeight; }
    
    public void setProposalDeadline(LocalDate deadline) { this.proposalDeadline = deadline; }
    public void setFinalReportDeadline(LocalDate deadline) { this.finalReportDeadline = deadline; }
    public void setProposalWeight(int weight) { this.proposalWeight = weight; }
    public void setProgressWeight(int weight) { this.progressWeight = weight; }
    public void setFinalWeight(int weight) { this.finalWeight = weight; }

    @Override
    public String toString() {
        return "System Configuration:\n" +
               "- Proposal Deadline: " + proposalDeadline + "\n" +
               "- Final Report Deadline: " + finalReportDeadline + "\n" +
               "- Grading Weights: Proposal=" + proposalWeight + "%, " +
               "Progress=" + progressWeight + "%, Final=" + finalWeight + "%";
    }
}