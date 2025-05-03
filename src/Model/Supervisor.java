package Model;

import java.util.List;

public class Supervisor extends User {
    private List<Project> assignedProjects;

    public Supervisor(int userId, String name, String email, String password, String role, List<Project> assignedProjects) {
        super(userId, name, email, password, role);
        this.assignedProjects = assignedProjects;
    }

    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }
}
