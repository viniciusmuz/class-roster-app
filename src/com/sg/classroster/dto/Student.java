package com.sg.classroster.dto;

public class Student {
    private String firstName;
    private String lastName;
    private final String studentID;
    // Programming language + cohort month/year
    private String cohort;

    public Student(String studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public String getStudentID() {
        return this.studentID;
    }
}
