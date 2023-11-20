package main.java.cdio3;

import java.io.IOException;
import java.utils.Scanner;

class Textreader {
    public static void main(String[] args) {
        var filename = "Gametext.txt";
        var file = new java.io.file(filename);
        var lines = 0;

        try {
            var Scanner = new java.util.Scanner(file);
            while (Scanner.hasNextLine()) {
                var j = Scanner.nextLine(); // this is not used for other purpose than movin to the next line in the
                                            // while loop
                lines++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        String[] fullFile;
        fullFile = new String[lines];
        try {
            var Scanner = new java.util.Scanner(file);
            for (i = 0; Scanner.hasNextLine(); i++) {
                var l = Scanner.nextLine();
                fullFile[i] = l;
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }
}
