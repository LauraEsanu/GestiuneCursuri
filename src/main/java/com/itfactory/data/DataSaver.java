package com.itfactory.data;

import com.itfactory.model.Course;
import com.itfactory.model.Student;
import com.itfactory.utils.DataUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataSaver  {


    private void saveCourses(Map<Course, List<Student>> map) throws IOException {
        Set<Course> set = map.keySet();
        String dateCursuri = "";
        for (Course course : set) {
            dateCursuri = dateCursuri.concat(course.toString().concat("\n"));

        } DataUtils.writeFile(dateCursuri,DataUtils.COURSE_FILE_PATH);


    }

    private void saveStudents(Map<Course, List<Student>> map) throws IOException {
        String dateStudenti = "";
        for (List<Student> valoriDinMap : map.values()) {
            for (Student student : valoriDinMap){
                dateStudenti = dateStudenti.concat(student.toString().concat("\n"));
            } DataUtils.writeFile(dateStudenti,DataUtils.STUDENT_FILE_PATH);
        }
    }

    private void saveMappings(Map<Course, List<Student>> map) throws IOException {
        String mapari = "";
        for (Map.Entry<Course, List<Student>> entry : map.entrySet()){
            Integer courseId = entry.getKey().getCourseId();
            for (Student student : entry.getValue()){
                Integer studentId = student.getStudentId();
                mapari = mapari.concat(studentId.toString().concat(",").concat(courseId.toString().concat("\n")));
            }
        } DataUtils.writeFile(mapari,DataUtils.MAPPING_FILE_PATH);
    }
    public void saveData (Map<Course, List<Student>> map) throws IOException {
        saveCourses(map);
        saveStudents(map);
        saveMappings(map);
    }
}
