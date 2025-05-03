import student.studentClass;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private Advisor advisor;
    private List<studentClass> students;

    public Group(String groupId, Advisor advisor) {
        this.groupId = groupId;
        this.advisor = advisor;
        this.students = new ArrayList<>();
    }

    public void addStudent(studentClass student) {
        students.add(student);
    }

    public String getGroupId() {
        return groupId;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public List<studentClass> getStudents() {
        return students;
    }
}

