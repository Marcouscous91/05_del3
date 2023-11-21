package cdio3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

class Textreader {
    private static String[] fullFile;

    public static void loadFile(String filename){
        // Creates the absolute path name of the file
        Path currentRelPath = Paths.get("src\\main\\java\\cdio3\\" + filename);
        String pathName = currentRelPath.toAbsolutePath().toString();
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
                /*
                 * If the first character of the curent line is not an int, and thus can't be parsed
                 * as an integer, Exception e will be thrown, and caught, and then the loop will
                 * continue. This means the loop will only count amount of lines, of text which will 
                 * be printed
                 */
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
                /*
                 * Splits the current line of text for every ':', and creates an array of Strings,
                 * seperated by each ':'. First element of this array will be parsed as Integer in 
                 * 'index', and the second element of the array, will be stored in the array 
                 * 'fullFile' at 'index' of the first element.
                 * 
                 * If the first element is not a number and thus can't be parsed, an exception will
                 * be thrown and caught, and then the loop continues.
                 * 
                 * This way all lines to be printed will be stored in the array 'fullFile', at the index
                 * indicated in the .txt file
                 */
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

    /*
     * Returns the string to be printed
     * 
     * Param:   lineNumber: the line from the .txt file to be printed
     * 
     * Return:  textLineToPrint:    the String at position 'lineNumer'
     */
    public static String getTextLineToPrint(int lineNumber) {
        String textLineToPrint = fullFile[lineNumber];
        return textLineToPrint;
    }
}
