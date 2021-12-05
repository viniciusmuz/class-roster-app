package com.sg.classroster.controller;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoException;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;

public class ClassRosterController {
    private ClassRosterView view;
    private ClassRosterDao dao;

    public ClassRosterController(ClassRosterView view, ClassRosterDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() throws ClassRosterDaoException {
        boolean keepGoing = true;
        int selection = 0;

        while (keepGoing) {
            selection = getMenuSelection();

            switch (selection) {
                case 1 -> this.listStudents();
                case 2 -> this.createStudent();
                case 3 -> this.viewStudent();
                case 4 -> this.removeStudent();
                case 5 -> keepGoing = false;
                default -> this.unknownCommand();
            }
        }
        this.exitMessage();
    }

    private void createStudent() throws ClassRosterDaoException {
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentID(), newStudent);
        view.displayCreateSuccessBanner();
    }

    private void listStudents() throws ClassRosterDaoException {
        view.displayDisplayAllBanner();
        view.displayStudentList(dao.getAllStudents());
    }

    private void viewStudent() throws ClassRosterDaoException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterDaoException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.removeStudent(studentId);
        view.displayRemoveResult(student);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void exitMessage() {
        view.displayExitBanner();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
}
