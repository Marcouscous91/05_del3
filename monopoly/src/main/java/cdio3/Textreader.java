package cdio3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Textreader {
    public static void main(String[] args) {
        String filename = "Gametext.txt";
        File file = new File(filename);
        int lines = 0;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                scanner.nextLine(); // this is not used for other purpose than movin to the next line in the
                                            // while loop
                lines++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }

        String[] fullFile = new String[lines];
        
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; scanner.hasNextLine(); i++) {
                String l = scanner.nextLine();
                fullFile[i] = l;
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }
    }
}
