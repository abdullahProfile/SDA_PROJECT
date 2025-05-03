package Model;

import java.util.List;

public class Group {
    private int groupId;
    private List<String> students;
    private Supervisor supervisor;
    private String projectTitle;

    public Group(int groupId, List<String> students, Supervisor supervisor, String projectTitle) {
        this.groupId = groupId;
        this.students = students;
        this.supervisor = supervisor;
        this.projectTitle = projectTitle;
    }
    public int getGroupId() {
        return groupId;
    }
    public List<String> getStudents() {
        return students;
    }
    public Supervisor getSupervisor() {
        return supervisor;
    }
    public String getProjectTitle() {
        return projectTitle;
    }
}
