package cdio3;

import java.io.*;

import org.apache.commons.lang3.StringUtils;

public class PrintGUI {
    public static void main(String[] args){
        Game board = new Game(4);
        GUI_Board tester = new GUI_Board(board);
        // System.out.println(tester.boardToString());

    }
}
