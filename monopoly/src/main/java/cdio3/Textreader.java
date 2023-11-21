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
                String l = scanner.nextLine(); // this is not used for other purpose than movin to the next line in the
                try {
                    Integer.parseInt(l.toCharArray()[0]+"");
                    lines++;
                } catch (IllegalArgumentException e) {
                    continue;
                }                          
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }

        String[] fullFile = new String[lines];
            try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; scanner.hasNextLine(); i++) {
                int index;
                String l = scanner.nextLine();
                try{
                    String[] lineSplitted = l.split(":");
                    index = Integer.parseInt(lineSplitted[0]);
                    fullFile[index] = lineSplitted[1];
                }catch (IllegalArgumentException e){
                    continue;
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }


    }
}
