package cdio3;

public class GUI_Board {
    
}

class GUI_Field{
    String name;

    public GUI_Field(Field field){
        this.name = field.getName();
    }

    public String outpotBox(){
        String output = "";
        return output;
    }
}

class GUI_Property extends GUI_Field{
    String owner;
    double cost;
    Color color;

    public GUI_Property(Property property){
        this.owner = property
    }
}

