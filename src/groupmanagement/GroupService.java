import student.StudentClass;
import java.util.ArrayList;
import java.util.List;

public class GroupService {
    private List<Group> groups;

    public GroupService() {
        this.groups = new ArrayList<>();
    }

    public void createGroup(String groupId, Advisor advisor) {
        groups.add(new Group(groupId, advisor));
    }

    public void addStudentToGroup(String groupId, StudentClass student) {
        for (Group group : groups) {
            if (group.getGroupId().equals(groupId)) {
                group.addStudent(student);
                System.out.println("Student " + student.getName() + " added to group " + groupId);
                return;
            }
        }
        System.out.println("Group not found.");
    }

    public List<Group> getGroups() {
        return groups;
    }
}
