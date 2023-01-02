package com.itfactory.model;

import java.time.LocalDate;
import java.util.*;

public class Course {
    private int courseId;
    private String courseName;
    private double price;
    private LocalDate startDate;

    public Course(int courseId, String courseName, double price, LocalDate startDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.price = price;
        this.startDate = startDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId && Double.compare(course.price, price) == 0 && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, price, startDate);
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return courseId + "," + courseName + "," + price + "," + startDate;
    }

    public static Course creeazaCurs(Map<Course, List<Student>> mapCursId) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Care este numele cursului?");
        String nume = scanner.nextLine();
        System.out.println("Care este pretul cursului?");
        double bani = Double.parseDouble(scanner.nextLine());
        System.out.println("Care este data de start a cursului(YYYY-MM-DD)?");
        LocalDate dataCurs = LocalDate.parse(scanner.nextLine());
        Set<Course> listaCursId = mapCursId.keySet();
        int id = 100;
        for (Course c : listaCursId) {
            int cursId = c.getCourseId();
            id = Integer.max(cursId, id);
        }
        id += 100;

        return new Course(id, nume, bani, dataCurs);


    }

//    public static boolean checkDate (Map<Course, List<Student>> mapDateStart, String course){
//        LocalDate now = LocalDate.now();
//        Set<Course> set = mapDateStart.keySet();
//        for (Course c2 : set) {
//            if (now.isAfter(c2.getStartDate())){
//                System.out.println("Data de curs este depasita!");
//
//
//            }else{
//                System.out.println("Curs disponibil este: " + c2.getCourseName() );
//            }
//        }
//
//        return false;
//    }
//}

    public static boolean checkDate(Map<Course, List<Student>> mapDateStart, String course) {
        LocalDate now = LocalDate.now();
        Set<Course> set = mapDateStart.keySet();
        for (Course c2 : set) {
            if (c2.getCourseName().equalsIgnoreCase(course)) {
                if (now.isAfter(c2.getStartDate())) {
                    System.out.println("Data de curs este depasita!");
                    int count = 0;
                    for (Course c3 : set) {
                        if (now.isBefore(c3.getStartDate())) {
                            System.out.println("Curs disponibil: " + c3.getCourseName());
                        } else {
                            count++;
                        }
                        if (count == set.size()) {
                            System.out.println("Nu este nici un curs disponibil. ");
                        }

                    }
                    return false;
                } else {
                    break;

                }

            }

        }
        return true;
    }
}


