package cdio3;

import org.apache.commons.lang3.StringUtils;

public class GUI_Board {
    GUI_Field[][] board;

    public GUI_Board(){
        createBoard();
    }

    public String boardToString(){
        String output = "";
        for(GUI_Field[] row: board){
            for(int i = 1; i < 8; i++){
                for(GUI_Field field: row){
                    if(field == null){
                        output += StringUtils.center("", 14);
                    } else{
                        output += field.line(i);
                    }
                }
                output += "\n";
            }
        }
        return output;
    }

    private GUI_Field[] creatRow(){
        GUI_Field[] row = new GUI_Field[] {
            new GUI_Field(new Prison("Prison")),
            new GUI_Field(new Start("Start")),
            new GUI_Field(new Prison("Prison")),
            new GUI_Field(new Start("Start")),
            new GUI_Field(new Prison("Prison")),
            new GUI_Field(new Start("Start")),
            new GUI_Field(new Prison("Prison")),
            new GUI_Field(new Start("Start"))
        };
        return row;
    }

    private GUI_Field[] creatMiddleRow(){
        GUI_Field[] row = new GUI_Field[] {
            new GUI_Field(new Prison("Prison")),
            null,
            null,
            null,
            null,
            null,
            null,
            new GUI_Field(new Start("Start"))
        };
        return row;
    }

    private void createBoard(){
        board = new GUI_Field[][]{
            creatRow(),
            creatMiddleRow(),
            creatMiddleRow(),
            creatMiddleRow(),
            creatMiddleRow(),
            creatRow()
        };
    }
}

class GUI_Field{
    protected String name;
    protected String horizontalEdge = "-".repeat(14);
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
        return "|" + StringUtils.center(text, 12) + "|";
    }
}

class EmptyBox{

}

class GUI_Property extends GUI_Field{
    String owner;
    double cost;
    Color color;

    public GUI_Property(Property property){
        super(property);
        this.owner = property.getOwner().getName();
        this.cost = property.getCost();
        this.color = property.getColor();
    }


}


