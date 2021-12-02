package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;

import java.util.List;

public class ClassRosterView {

    private UserIO io = new UserIOConsoleImpl();
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");
        return io.readInt("Please select from the above choices.");
    }

    public Student getNewStudentInfo() {
        Student currentStudent = new Student(io.readString("Please enter student ID: "));
        currentStudent.setFirstName(io.readString("Please enter the student's first name: "));
        currentStudent.setLastName(io.readString("Please enter the student's last name: "));
        currentStudent.setCohort(io.readString("Please enter the student's cohort: "));

        return currentStudent;
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

    public void print(String toPrint) {
        io.print(toPrint);
    }

    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "Student successfully created. Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }
}