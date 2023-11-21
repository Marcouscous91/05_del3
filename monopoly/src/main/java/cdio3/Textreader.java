package cdio3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.net.*;

class Textreader {
    private static String[] fullFile;

    public static void loadFile(String filename){
        URL path = Textreader.class.getResource(filename);
        String pathName = path.getFile();
        File file = new File(pathName);
        int lines = 0;
        
        // Counts the number of lines of text to be printed out, without counting empty spaces and headlines
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String l = scanner.nextLine(); // this is not used for other purpose than movin to the next line in the
                try {
                    if(l != ""){
                        Integer.parseInt(l.toCharArray()[0]+"");
                        lines++;
                    }
                } catch (IllegalArgumentException e) {
                    continue;
                }                          
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
        }

        fullFile = new String[lines]; //Array of length equal to number of lines of text from file
        
        // Puts te text to be printed out, into array, according to their indexed number
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

    public static String getTextLineToPrint(int lineNumber) {
        String getTextLineToPrint = fullFile[lineNumber];
        return getTextLineToPrint;
    }
}
