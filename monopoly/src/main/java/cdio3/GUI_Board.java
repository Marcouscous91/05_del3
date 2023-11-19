package cdio3;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class GUI_Board {
    GUI_Box[][] GUI_board;
    PositionTracker positionTracker;
    GUI_Box[] fields;
    Player[] players;

    public GUI_Board(Game game){
        players = game.getPlayers();
        createFields(game.getBoard());
        createBoard();
        //positionTracker = new PositionTracker(fields, players);

    }

    public void createFields(Board board){
        fields = new GUI_Box[]{
            new GUI_Field(board.getField(0)),
            new GUI_Property(board.getProperty(1)),
            new GUI_Property(board.getProperty(2)),
            new GUI_Field(board.getField(3)),
            new GUI_Property(board.getProperty(4)),
            new GUI_Property(board.getProperty(5)),
            new GUI_Field(board.getField(6)),
            new GUI_Property(board.getProperty(7)),
            new GUI_Property(board.getProperty(8)),
            new GUI_Field(board.getField(9)),
            new GUI_Property(board.getProperty(10)),
            new GUI_Property(board.getProperty(11)),
            new GUI_Field(board.getField(12)),
            new GUI_Property(board.getProperty(13)),
            new GUI_Property(board.getProperty(14)),
            new GUI_Field(board.getField(15)),
            new GUI_Property(board.getProperty(16)),
            new GUI_Property(board.getProperty(17)),
            new GUI_Field(board.getField(18)),
            new GUI_Property(board.getProperty(19)),
            new GUI_Property(board.getProperty(20)),
            new GUI_Field(board.getField(21)),
            new GUI_Property(board.getProperty(22)),
            new GUI_Property(board.getProperty(23))
        };
    }

    public String boardToString(){
        String output = "";
        resetPosition();
        updatePosition();
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

    public void resetPosition(){
        for(GUI_Box field: fields){
            field.resetplayerTokens();
        }
    }

    public void updatePosition(){
        for(Player player: players){
            fields[player.getPosition()].addToplayerTokens(player.getToken());
        }
    }

    private void createBoard(){
        GUI_board = new GUI_Box[][]{
            topRow(),
            secondRow(),
            thirdRow(),
            fourthRow(),
            fifthRow(),
            sixthRow(),
            bottomRow()
        };
    }

    private GUI_Box[] topRow(){

        GUI_Box[] row = new GUI_Box[] {
            fields[0],
            fields[1],
            fields[2],
            fields[3],
            fields[4],
            fields[5],
            fields[6],
        };
        return row;
    }
    
    private GUI_Box[] secondRow(){
        GUI_Box[] row = new GUI_Box[] {
            fields[23],
            new GUI_PlayerInfo(players[0]),
            null,
            null,
            null,
            new GUI_PlayerInfo(players[1]),
            fields[7]
        };
        return row;
    }

    private GUI_Box[] thirdRow(){
        GUI_Box[] row = new GUI_Box[] {
            fields[22],
            null,
            null,
            null,
            null,
            null,
            fields[8],
        };
        return row;
    }

    private GUI_Box[] fourthRow(){
        GUI_Box[] row = new GUI_Box[] {
            fields[21],
            null,
            null,
            null,
            null,
            null,
            fields[9]
        };
        return row;
    }

    private GUI_Box[] fifthRow(){
        GUI_Box[] row = new GUI_Box[] {
            fields[20],
            null,
            null,
            null,
            null,
            null,
            fields[10]
        };
        return row;
    }

    private GUI_Box[] sixthRow(){
        GUI_Box[] row = new GUI_Box[] {
            fields[19],
            new GUI_PlayerInfo(players[2]),
            null,
            null,
            null,
            new GUI_PlayerInfo(players[3]),
            fields[11]
        };
        return row;
    }

    private GUI_Box[] bottomRow(){
        GUI_Box[] row = new GUI_Box[] {
            fields[18],
            fields[17],
            fields[16],
            fields[15],
            fields[14],
            fields[13],
            fields[12]
        };
        return row;
    }



}

class PositionTracker{
    GUI_Box[] fields;
    Player[] players;

    public PositionTracker(GUI_Box[] fields, Player[] players){
        this.fields = fields;
        this.players = players;
    }

    public void resetPosition(){
        for(GUI_Box field: fields){
            field.resetplayerTokens();
        }
    }

    public void updatePosition(){
        for(Player player: players){
            fields[player.getPosition()].addToplayerTokens(player.getToken());
        }
    }


}

abstract class GUI_Box{
    protected String[] playerTokens;
    protected String horizontalEdge;
    abstract public String line(int row);

    public void resetplayerTokens(){
        playerTokens = new String[4];
    }

    public void addToplayerTokens(String playerSymbol){
        for(int i = 0; i < playerTokens.length; i++){
            if(playerTokens[i] == null){
                playerTokens[i] = playerSymbol;
                break;
            }
        }
    }

    public String playerTokensToString(){
        String playerTokensToString = "";
        for(String str: playerTokens){
            if(str != null){
                playerTokensToString += " " + str;
            } else if(str == null){
                break;
            }
        }
        return playerTokensToString;
    }
}

class GUI_Field extends GUI_Box{
    protected String name;
    protected String horizontalEdge = "-".repeat(15);
    protected int rows = 7;

    public GUI_Field(Field field){
        this.name = field.getName();
        this.playerTokens = new String[4];
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
            case 3:
                output = row(playerTokensToString());
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
    Player player;
    //String playerName;
    //double balance;

    public GUI_PlayerInfo(Player player){
        //playerName = player.getName();
        //balance = player.getBalance();
        this.player = player;
    }

    @Override
    public String line(int row) {
        String output;
        switch (row) {
            case 2:
                output = row(player.getName());
                break;
            case 3:
                output = row(player.getToken());
                break;
            case 5:
                output = row("Balance:");
                break;
            case 6:
                output = row(player.getBalance() + "M");
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
            case 3:
                output = row(playerTokensToString());
                break;
            case 5:
                output = row("Owner: " + property.getOwner().getToken());
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


