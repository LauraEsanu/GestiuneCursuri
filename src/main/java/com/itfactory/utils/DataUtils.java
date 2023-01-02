package com.itfactory.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DataUtils {

    public static final String COURSE_FILE_PATH = "cursuri.csv";
    public static final String STUDENT_FILE_PATH = "studenti.csv";
    public static final String MAPPING_FILE_PATH = "mapari.csv";


    public static List<String> readFile (String filePathStr) throws IOException {
        Path path = Paths.get(filePathStr);
       return Files.readAllLines(path);

    }

    public static void writeFile (String response,String file) throws IOException {
        String fileName = file;
        Path path = Paths.get(fileName);
        Files.write(path,response.getBytes());
    }
}

