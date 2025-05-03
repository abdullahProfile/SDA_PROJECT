package Model;

public class Student extends User {
    private int groupId;

    public Student(int userId, String name, String email, String password, String role) {
        super(userId, name, email, password, role);
    }

}
