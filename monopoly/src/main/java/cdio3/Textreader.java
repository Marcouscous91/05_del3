package main.java.cdio3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Textreader {

    public static void main(String[] args) {
        String filePath = "Gametekst.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("board.field.")) {
                    String fieldName = line.substring("board.field.".length(), line.length() - 1);
                    System.out.println("Field: " + fieldName);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
