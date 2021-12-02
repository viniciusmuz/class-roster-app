package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;

public class ClassRosterController {
    private ClassRosterView view = new ClassRosterView();
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    public void run() {
        boolean keepGoing = true;
        int selection = 0;

        while (keepGoing) {
            selection = getMenuSelection();

            switch (selection) {
                case 1:
                    this.listStudents();
                    break;
                case 2:
                    this.createStudent();
                    break;
                case 3:
                    view.print("VIEW A STUDENT");
                    break;
                case 4:
                    view.print("REMOVE A STUDENT");
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    view.print("UNKNOWN COMMAND");
                    break;
            }
        }
        view.print("Goodbye!");
    }

    private void createStudent() {
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentID(), newStudent);
        view.displayCreateSuccessBanner();
    }

    private void listStudents() {
        view.displayDisplayAllBanner();
        view.displayStudentList(dao.getAllStudents());
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
}
