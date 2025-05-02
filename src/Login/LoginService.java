// LoginService.java
package Login;

import student.StudentClass;
import student.StudentStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginService {
    private List<StudentClass> studentList;
    private Scanner scanner = new Scanner(System.in);

    public LoginService() {
        // Load students from file
        studentList = StudentStorage.loadStudents();
    }

    public StudentClass handleStudentLogin() {
        System.out.print("Are you a new student? (yes/no): ");
        String isNew = scanner.nextLine();

        StudentClass currentStudent = null;

        if (isNew.equalsIgnoreCase("yes")) {
            currentStudent = registerStudent();
            if (currentStudent != null) {
                studentList.add(currentStudent);
                StudentStorage.saveStudent(currentStudent); // Save to file
            }
        } else {
            currentStudent = loginExistingStudent();
        }

        return currentStudent;
    }

    private StudentClass registerStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        StudentClass newStudent = new StudentClass(id, name, password);
        System.out.println("Registration successful!");

        System.out.print("Please login. Enter your password: ");
        String enteredPassword = scanner.nextLine();

        if (newStudent.login(enteredPassword)) {
            return newStudent;
        } else {
            System.out.println("Login failed after registration.");
            return null;
        }
    }

    private StudentClass loginExistingStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (StudentClass student : studentList) {
            if (student.getStudentId().equals(id)) {
                if (student.login(password)) {
                    return student;
                } else {
                    System.out.println("Incorrect password.");
                    return null;
                }
            }
        }

        System.out.println("Student not found. Please register first.");
        return null;
    }
}
