package studentsystem;

import java.io.Serializable;

public class Student implements Serializable { private static final long serialVersionUID = 1L; private String name; private String rollNumber; private String grade;

public Student(String name, String rollNumber, String grade) {
    this.name = name;
    this.rollNumber = rollNumber;
    this.grade = grade;
}

public String getName() {
    return name;
}

public String getRollNumber() {
    return rollNumber;
}

public String getGrade() {
    return grade;
}

@Override
public String toString() {
    return "Student{" +
            "name='" + name + '\'' +
            ", rollNumber='" + rollNumber + '\'' +
            ", grade='" + grade + '\'' +
            '}';
}
}

/Class2/

package studentsystem;

import java.io.; import java.util.;

public class StudentManagementSystem { private List students; private final String fileName = "students.dat";

public StudentManagementSystem() {
    students = new ArrayList<>();
    loadStudents();
}

public void addStudent(Student student) {
    students.add(student);
    saveStudents();
}

public void removeStudent(String rollNumber) {
    students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    saveStudents();
}

public Student searchStudent(String rollNumber) {
    for (Student student : students) {
        if (student.getRollNumber().equals(rollNumber)) {
            return student;
        }
    }
    return null;
}

public void displayAllStudents() {
    for (Student student : students) {
        System.out.println(student);
    }
}

private void saveStudents() {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
        out.writeObject(students);
    } catch (IOException e) {
        System.out.println("Error saving students: " + e.getMessage());
    }
}

private void loadStudents() {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
        students = (List<Student>) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error loading students: " + e.getMessage());
    }
}

public static void main(String[] args) {
    StudentManagementSystem sms = new StudentManagementSystem();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;

    while (!exit) {
        System.out.println("Student Management System:");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Search Student");
        System.out.println("4. Display All Students");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter roll number: ");
                String rollNumber = scanner.nextLine();
                System.out.print("Enter grade: ");
                String grade = scanner.nextLine();
                sms.addStudent(new Student(name, rollNumber, grade));
                break;
            case 2:
                System.out.print("Enter roll number of the student to remove: ");
                String rollToRemove = scanner.nextLine();
                sms.removeStudent(rollToRemove);
                break;
            case 3:
                System.out.print("Enter roll number of the student to search: ");
                String rollToSearch = scanner.nextLine();
                Student foundStudent = sms.searchStudent(rollToSearch);
                if (foundStudent != null) {
                    System.out.println("Student found: " + foundStudent);
                } else {
                    System.out.println("Student not found.");
                }
                break;
            case 4:
                sms.displayAllStudents();
                break;
            case 5:
                exit = true;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    scanner.close();
}
}