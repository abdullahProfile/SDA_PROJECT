package student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStorage {
    private static final String FILE_NAME = "students.txt";

    // Save student to the file
    public static void saveStudent(StudentClass student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Save the student's details, including the password
            writer.write("Student data: " +"Student Id: " +student.getStudentId() + "Student Name: "+ student.getName() );
            writer.newLine(); // Add a new line after each student's data
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }

    // Load students from the file
    public static List<StudentClass> loadStudents() {
        List<StudentClass> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split by commas to get student info
                if (parts.length == 3) {
                    // Create a new student object and add to the list
                    students.add(new StudentClass(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing students found or error reading file.");
        }
        return students;
    }
}
