package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;

import java.io.*;
import java.util.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

    final Map<String, Student> students = new HashMap<>();
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterDaoException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDaoException {
        loadRoster();
        return new ArrayList<>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterDaoException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }

    /*
    Creates a student object from a String in the following format:
    ID<delimiter>FirstName<delimiter>LastName<delimiter>Cohort
     */
    private Student unmarshallStudent(String studentAsText) {
        String[] studentTokens = studentAsText.split(DELIMITER);
        Student studentFromFile = new Student(studentTokens[0]);
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);

        return studentFromFile;
    }

    /*
    Reads the roster from a file.
     */
    private void loadRoster() throws ClassRosterDaoException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));

            while (scanner.hasNext()) {
                Student newStudent = unmarshallStudent(scanner.nextLine());
                students.put(newStudent.getStudentID(), newStudent);
            }
        }
        catch (FileNotFoundException e) {
            throw new ClassRosterDaoException("Couldn't load roster data into memory", e);
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public String marshalStudent(Student student) {
        StringBuilder studentAsText = new StringBuilder();

        studentAsText.append(student.getStudentID()).append(DELIMITER);
        studentAsText.append(student.getFirstName()).append(DELIMITER);
        studentAsText.append(student.getLastName()).append(DELIMITER);
        studentAsText.append(student.getCohort());

        return studentAsText.toString();
    }

    private void writeRoster() throws ClassRosterDaoException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(ROSTER_FILE));
            for (String key : students.keySet()) {
                writer.println(marshalStudent(students.get(key)));
                writer.flush();
            }
        }
        catch (IOException e) {
            throw new ClassRosterDaoException("Couldn't save roster data.", e);
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
