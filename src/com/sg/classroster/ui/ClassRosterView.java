package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;

import java.util.List;

public class ClassRosterView {

    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    // Menu.

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
        return io.readInt("Please select from the above choices.");
    }

    // Get student ID.
    public String getStudentIdChoice() {
        return io.readString("Please enter the student ID: ");
    }


    // Create new student.

    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    public Student getNewStudentInfo() {
        Student currentStudent = new Student(io.readString("Please enter student ID: "));
        currentStudent.setFirstName(io.readString("Please enter the student's first name: "));
        currentStudent.setLastName(io.readString("Please enter the student's last name: "));
        currentStudent.setCohort(io.readString("Please enter the student's cohort: "));

        return currentStudent;
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Student successfully created. Please hit enter to continue");
    }


    // Display all students.

    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayStudentList(List<Student> students) {
        for (Student currentStudent : students) {
            String studentInfo = String.format("#%s : %s %s %s",
                    currentStudent.getStudentID(),
                    currentStudent.getFirstName(),
                    currentStudent.getLastName(),
                    currentStudent.getCohort());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    // View a student.

    public void displayDisplayStudentBanner() {
        io.print("=== Display Student ===");
    }

    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentID());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        }
        else {
            io.print("No such student");
        }
        io.readString("Please hit 'enter' to continue.");
    }

    // Remove a student.

    public void displayRemoveStudentBanner() {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveResult(Student student) {
        if (student != null) {
            io.print("Student removed successfully.");
        }
        else {
            io.print("No such student.");
        }
        io.readString("Press 'enter' to continue.");
    }

    // Exit banner.
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    // Unknown command banner.
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    // Error message.
    public void displayErrorMessage (String errorMessage) {
        io.print("=== ERROR ===");
        io.print(errorMessage);
    }

}