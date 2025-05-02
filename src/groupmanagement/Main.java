import student.StudentClass;

public class Main {
    public static void main(String[] args) {
        GroupService groupService = new GroupService();

        Advisor advisor = new Advisor("Dr. Smith");
        groupService.createGroup("G1", advisor);

        StudentClass s1 = new StudentClass("S001", "Abdullah", "1234");
        StudentClass s2 = new StudentClass("S002", "Ali", "abcd");

        s1.login("1234");
        s2.login("abcd");

        groupService.addStudentToGroup("G1", s1);
        groupService.addStudentToGroup("G1", s2);
    }
}
