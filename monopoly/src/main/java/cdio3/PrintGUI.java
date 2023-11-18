package cdio3;

import java.io.*;

import org.apache.commons.lang3.StringUtils;

public class PrintGUI {
    public static void main(String[] args){
        GUI_Board tester = new GUI_Board();
        System.out.println(tester.boardToString());
    }
}
