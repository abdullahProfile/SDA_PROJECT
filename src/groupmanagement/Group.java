import student.StudentClass;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private Advisor advisor;
    private List<StudentClass> students;

    public Group(String groupId, Advisor advisor) {
        this.groupId = groupId;
        this.advisor = advisor;
        this.students = new ArrayList<>();
    }

    public void addStudent(StudentClass student) {
        students.add(student);
    }

    public String getGroupId() {
        return groupId;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public List<StudentClass> getStudents() {
        return students;
    }
}

