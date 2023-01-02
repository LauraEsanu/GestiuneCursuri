package com.itfactory.data;

import com.itfactory.model.Course;
import com.itfactory.model.Student;
import com.itfactory.utils.DataUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class DataLoader {

    private Map<Course, List<Student>> dataMap = new HashMap();
    private List<Student> students = new ArrayList();

    public DataLoader() {
    }

    public void loadData() throws IOException {
        this.loadCourses();
        this.loadStudents();
        this.mapStudentsToCourses();
    }

    private void loadCourses() throws IOException {
        List<String> courseInputData = DataUtils.readFile(DataUtils.COURSE_FILE_PATH);

        for (String var1 : courseInputData) {
            String[] data = var1.split(",");
            this.dataMap.put(this.createCourse(data), new ArrayList<>());

        }

    }

    private Course createCourse(String[] data) {
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        double price = Double.parseDouble(data[2]);
        LocalDate courseStart = LocalDate.parse(data[3]);
        return new Course(id, name, price, courseStart);
    }

    private void loadStudents() throws IOException {
        List<String> studentsInputData = DataUtils.readFile(DataUtils.STUDENT_FILE_PATH);

        for (String var2 : studentsInputData) {
            String[] data2 = var2.split(",");
            this.students.add(this.createStudent(data2));
        }

    }

    private Student createStudent(String[] data2) {
        int idStudent = Integer.parseInt(data2[0]);
        String nameStudent = data2[1];
        double budget = Double.parseDouble(data2[2]);
        return new Student(idStudent, nameStudent, budget);

    }

    private void mapStudentsToCourses() throws IOException {
        List<String> mapInputData = DataUtils.readFile(DataUtils.MAPPING_FILE_PATH);
        Course curs;
        Student stu;

        for (String var3 : mapInputData) {
            String[] data3 = var3.split(",");
            int valIdStudent = Integer.parseInt(data3[0]);
            int valIdCurs = Integer.parseInt(data3[1]);
            Set<Course> cursuri = getDataMap().keySet();
            for (Course c : cursuri) {
                if (c.getCourseId() == valIdCurs) {


                    for (Student s : students) {
                        if (s.getStudentId() == valIdStudent) {

                            dataMap.get(c).add(s);
                            break;
                        }
                    }
                }
            }
        }
    }


    private String valoare1(String[] data3) {
        return data3[0];
    }

    private String valoare2(String[] data3) {
        return data3[1];
    }


    public Map<Course, List<Student>> getDataMap() {
        return this.dataMap;
    }


}


