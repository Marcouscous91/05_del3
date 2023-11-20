package main.java.cdio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Textreader {

    public List<String> readFieldNames(String filePath) {
        List<String> fieldNames = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("board.field.")) {
                    String fieldName = line.substring("board.field.".length(), line.length() - 1);
                    fieldNames.add(fieldName);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        return fieldNames;
    }
}
