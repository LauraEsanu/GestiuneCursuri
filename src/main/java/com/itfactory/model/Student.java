package com.itfactory.model;

import java.util.*;

public class Student {
    private int studentId;
    private String studentName;
    private double budget;

    public Student(int studentId, String studentName, double budget) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.budget = budget;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId && Double.compare(student.budget, budget) == 0 && studentName.equals(student.studentName);
    }

    public static Student creeazaStudent(Map<Course, List<Student>> mapDateStart) {
        Scanner scanner = new Scanner(System.in);
        int id = 1000;
        Set<Course> set = mapDateStart.keySet();
        for (Course c : set) {
            List<Student> studenti = mapDateStart.get(c);
            for (Student s : studenti) {
                int studentId = s.getStudentId();
                id = Integer.max(studentId, id);
            }
        }

        id += 100;

        System.out.println("Care este numele studentului?");
        String nume = scanner.nextLine();
        System.out.println("Care este bugetul studentului?");
        double bani = Double.parseDouble(scanner.nextLine());
        return new Student(id, nume, bani);
    }




    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, budget);
    }

    @Override
    public String toString() {
        return studentId + "," + studentName + "," + budget;
    }
}
