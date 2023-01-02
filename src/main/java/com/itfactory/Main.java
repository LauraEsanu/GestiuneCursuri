package com.itfactory;

import com.itfactory.data.DataLoader;
import com.itfactory.data.DataSaver;
import com.itfactory.model.Course;
import com.itfactory.model.Student;


import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<Course, List<Student>> data;
        DataLoader d = new DataLoader();
        d.loadData();
        data = d.getDataMap();
        System.out.println("Buna ziua!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Puteti introduce urmatoarele optiuni:\s
                 0 - Ies din program
                 1 - Afiseaza cursuri
                 2 - Introduceti un curs nou
                 3 - Introduceti un student nou si inrolati-l la curs""");
        while (true) {
            System.out.println("Introdu o optiune: ");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 0:
                    DataSaver save = new DataSaver();
                    save.saveData(data);
                    System.exit(0);
                    break;
                case 1: {
                    afiseazaCursuri(data);

                }
                break;
                case 2: {

                    Course newCourse = Course.creeazaCurs(data);
                    data.put(newCourse, new ArrayList<>());
                }
                break;

                case 3: {
                    LocalDate now = LocalDate.now();
                    System.out.println("La ce curs doriti sa va inscrieti?");
                    String course = scanner.nextLine();
                    List<Student> a = cautaCurs(data, course);

                    if (a.size() >= 9) {
                        System.out.println("Cursul introdus nu este diponibil. Avem urmatoarele cursuri disponibile: ");
                        int cursuriDisponibile = 0;
                        for (Map.Entry<Course, List<Student>> entry : data.entrySet()) {
                            if (entry.getValue().size() < 9) {
                                cursuriDisponibile++;
                                System.out.println("-Curs disponibil: " + entry.getKey().getCourseName() + ".....Pret: " + entry.getKey().getPrice());
                            }

                        }
                        if (cursuriDisponibile == 0) {
                            System.out.println("Nu este niciun curs disponibil");
                        }

                    } else if (!Course.checkDate(data, course)) {
                        break;

                    } else {
                        Student nouStudent = Student.creeazaStudent(data);
                        if (!(a.contains(nouStudent))) {

                            a.add(nouStudent);

                            Set<Course> s2 = data.keySet();
                            for (Course c2 : s2) {
                                if (c2.getCourseName().equalsIgnoreCase(course)) {
                                    nouStudent.setBudget(nouStudent.getBudget() - c2.getPrice());
                                    if (nouStudent.getBudget() < 0) {
                                        System.out.println("Budgetul este depasit!");
                                        break;
                                    } else {
                                        data.put(c2, a);
                                        System.out.println("Felicitari v-ati inscris la cursul " + c2.getCourseName() + "!");

                                        System.out.println(data.values());
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                default:
                    System.out.println("Nu ati introdus o optiune viabila!");
            }
        }

    }

    public static void afiseazaCursuri(Map<Course, List<Student>> map) {
        Set<Course> listaDeCursuri = map.keySet();
        for (Course courseList : listaDeCursuri) {
            System.out.println(courseList.getCourseName() + " --> Pret: " + courseList.getPrice());

        }


    }

    public static List<Student> cautaCurs(Map<Course, List<Student>> map, String curs) throws IOException {
        Set<Course> s1 = map.keySet();
        List<Student> lista;
        for (Course course : s1) {

            if (course.getCourseName().equalsIgnoreCase(curs)) {
                lista = map.get(course);
                return lista;
            }
        }
        throw new IOException("Course not found!");


    }
}




