package cdio3;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class GUI_Board {
    GUI_Box[][] GUI_board;

    public GUI_Board(Game game){
        createBoard(game);
    }

    public String boardToString(){
        String output = "";
        for(GUI_Box[] row: GUI_board){
            for(int i = 1; i < 8; i++){
                for(GUI_Box box: row){
                    if(box == null){
                        output += StringUtils.center("", 15);
                    } else{
                        output += box.line(i);
                    }
                }
                output += "\n";
            }
        }
        return output;
    }

    private GUI_Box[] topRow(Board board){

        GUI_Box[] row = new GUI_Box[] {
            new GUI_Field(board.getField(0)),
            new GUI_Property(board.getProperty(1)),
            new GUI_Property(board.getProperty(2)),
            new GUI_Field(board.getField(3)),
            new GUI_Property(board.getProperty(4)),
            new GUI_Property(board.getProperty(5)),
            new GUI_Field(board.getField(6))
        };
        return row;
    }
    
    private GUI_Box[] secondRow(Board board, Player p1, Player p2){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Property(board.getProperty(23)),
            new GUI_PlayerInfo(p1),
            null,
            null,
            null,
            new GUI_PlayerInfo(p2),
            new GUI_Property(board.getProperty(7))
        };
        return row;
    }

    private GUI_Box[] thirdRow(Board board){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Property(board.getProperty(22)),
            null,
            null,
            null,
            null,
            null,
            new GUI_Property(board.getProperty(8))
        };
        return row;
    }

    private GUI_Box[] fourthRow(Board board){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Field(board.getField(21)),
            null,
            null,
            null,
            null,
            null,
            new GUI_Field(board.getField(9))
        };
        return row;
    }

    private GUI_Box[] fifthRow(Board board){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Property(board.getProperty(20)),
            null,
            null,
            null,
            null,
            null,
            new GUI_Property(board.getProperty(10))
        };
        return row;
    }

    private GUI_Box[] sixthRow(Board board, Player p3, Player p4){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Property(board.getProperty(19)),
            new GUI_PlayerInfo(p3),
            null,
            null,
            null,
            new GUI_PlayerInfo(p4),
            new GUI_Property(board.getProperty(11))
        };
        return row;
    }

    private GUI_Box[] bottomRow(Board board){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Field(board.getField(12)),
            new GUI_Property(board.getProperty(13)),
            new GUI_Property(board.getProperty(14)),
            new GUI_Field(board.getField(15)),
            new GUI_Property(board.getProperty(16)),
            new GUI_Property(board.getProperty(17)),
            new GUI_Field(board.getField(18))
        };
        return row;
    }



    private GUI_Box[] creatRow(){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Property(new Property("House", 20, new Player("Tester"), Color.RED)),
            new GUI_Field(new Start("Start")),
            new GUI_Field(new Prison("Prison")),
            new GUI_Field(new Start("Start")),
            new GUI_Field(new Prison("Prison")),
            new GUI_Field(new Start("Start")),
            new GUI_Field(new Prison("Prison"))
        };
        return row;
    }

    private GUI_Box[] creatMiddleRow(){
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Field(new Prison("Prison")),
            null,
            null,
            null,
            null,
            null,
            new GUI_Property(new Property("Bank", 0, new Player("Tester"), Color.PURPLE))
        };
        return row;
    }

    private GUI_Box[] createTopRow(){
        Player player1 = new Player("Soeren");
        player1.setBalance(10);
        Player player2 = new Player("Bjoern");
        player2.setBalance(13);
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Field(new Prison("Prison")),
            new GUI_PlayerInfo(player2),
            null,
            null,
            null,
            new GUI_PlayerInfo(player2),
            new GUI_Property(new Property("Candy store", 3, new Player("Tester"), Color.PURPLE))
        };
        return row;
    }

    private GUI_Box[] createBottomRow(){
        Player player3 = new Player("Hjalte");
        player3.setBalance(3);
        Player player4 = new Player("Gustav");
        player4.setBalance(7);
        GUI_Box[] row = new GUI_Box[] {
            new GUI_Field(new Prison("Prison")),
            new GUI_PlayerInfo(player3),
            null,
            null,
            null,
            new GUI_PlayerInfo(player4),
            new GUI_Property(new Property("Bank", 10, new Player("Tester"), Color.PURPLE))
        };
        return row;
    }

    private void createBoard(Game game){
        GUI_board = new GUI_Box[][]{
            topRow(game.getBoard()),
            secondRow(game.getBoard(), game.getPlayer(1), game.getPlayer(2)),
            thirdRow(game.getBoard()),
            fourthRow(game.getBoard()),
            fifthRow(game.getBoard()),
            sixthRow(game.getBoard(), game.getPlayer(3), game.getPlayer(4)),
            bottomRow(game.getBoard()),
        };
    }
}

abstract class GUI_Box{
    protected String horizontalEdge;
    abstract public String line(int row);
}

class GUI_Field extends GUI_Box{
    protected String name;
    protected String horizontalEdge = "-".repeat(15);
    protected int rows = 7;

    public GUI_Field(Field field){
        this.name = field.getName();
    }

    public GUI_Field(){
        this.name = "";
    }

    public String line(int row){
        String output;
        switch (row) {
            case 1:
                output = horizontalEdge;
                break;
            case 2:
                output = row(name);
                break;
            case 7:
                output = horizontalEdge;
                break;
            default:
                output = row("");
                break;
        }
        return output;
    }

    protected String row(String text){
        return "|" + StringUtils.center(text, 13) + "|";
    }
}

class GUI_PlayerInfo extends GUI_Box{
    String playerName;
    double balance;

    public GUI_PlayerInfo(Player player){
        playerName = player.getName();
        balance = player.getBalance();
    }

    @Override
    public String line(int row) {
        String output;
        switch (row) {
            case 2:
                output = row(playerName);
                break;
            case 4:
                output = row("Balance:");
                break;
            case 5:
                output = row(balance + "M");
                break;
            default:
                output = row("");
                break;
        }
        return output;
    }

    protected String row(String text){
        return StringUtils.center(text, 15);
    }
}

class EmptyBox{

}

class GUI_Property extends GUI_Field{
    Property property;
    String owner;
    double cost;
    Color color;
    Map<Color, String> colors = new HashMap<>();
    String ANSI_RESET = "\u001B[0m";

    public GUI_Property(Property property){
        super(property);
        this.property = property;
        this.owner = property.getOwner().getName();
        this.cost = property.getCost();
        this.color = property.getColor();
        createColorMap();
        this.horizontalEdge = colors.get(color) + horizontalEdge + ANSI_RESET;

    }

    private void createColorMap(){
        colors.put(Color.RED, "\u001B[31m");
        colors.put(Color.DARKBLUE, "\u001B[34m");
        colors.put(Color.PURPLE, "\u001B[35m");
        colors.put(Color.LIGHTBLUE, "\u001B[36m");
        colors.put(Color.ORANGE, "\u001b[38;5;202m");
        colors.put(Color.BROWN, "\u001b[38;5;94m");
        colors.put(Color.YELLOW, "\u001B[33m");
        colors.put(Color.GREEN, "\u001B[32m");
    }

    public String line(int row){
        String output;

        switch (row) {
            case 1:
                output = horizontalEdge;
                break;
            case 2:
                output = row(name);
                break;
            case 6:
                output = row(cost + "M");
                break;
            case 7:
                output = horizontalEdge;
                break;
            default:
                output = row("");
                break;
        }
        return output;
    }

    protected String row(String text){
        return colors.get(color) + "|" + ANSI_RESET + StringUtils.center(text, 13) + colors.get(color) + "|" + ANSI_RESET;
    }

}


