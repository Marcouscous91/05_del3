package cdio3;

public class PrintGUI {
    public static void main(String[] args){
        Game game = new Game(4);
        GUI_Board testBoard = new GUI_Board(game);
        testBoard.boardToString("This is a fun little test");
    }
}
