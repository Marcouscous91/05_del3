package cdio3;

abstract class Field {
    protected String name;

    public Field(String name){
        this.name = name;
    }

    abstract public boolean doAction(Game game);

    public String getName(){
        return name;
    }
}

class Property extends Field{
    private int cost;
    private int rent;
    private Actor owner;
    private Color color;


    public Property(String name, int cost, Actor bank, Color color){
        super(name);
        this.cost = cost;
        this.rent = cost;
        this.owner = bank;
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }

    private boolean buyProperty(Player player){
        boolean canPay;
        canPay = player.transferMoney(owner, cost);
        if(canPay){
            owner = player;
            System.out.println("You bought " + name + " for " + cost + "M");
        } else if(!canPay) {
            System.out.println("You don't have enough money to buy " + this.name);
        }
        return canPay;
    }

    private boolean payRent(Player player){
        boolean canPay;
        canPay = player.transferMoney(owner, rent);
        if(canPay){
            System.out.println("You payed " + cost + " M to " + owner.getName() + " in rent");
        }
        return canPay;
    }

    @Override
    public boolean doAction(Game game){
        boolean canPay = false;
        if(owner.getName().equals("Bank")){
            canPay = buyProperty(game.getCurrentPlayer());
        } else if(owner instanceof Player && owner != game.getCurrentPlayer()){
            canPay = payRent(game.getCurrentPlayer());
        }
        return canPay;
    }

}

class Prison extends Field{

    public Prison(String name){
        super(name);
    }

    @Override
    public boolean doAction(Game game){
        game.getCurrentPlayer().ToPrison();
        System.out.println("You done gone to prison");
        return true;
    }
    
}

class Start extends Field{
    public Start(String name){
        super(name);
    }

    @Override
    public boolean doAction(Game game){
        game.getCurrentPlayer().addSum(2);
        System.out.println("You land on start. Well done");
        return true;
    }
}

enum Color{
    BROWN,
    LIGHTBLUE,
    PURPLE,
    ORANGE,
    RED,
    YELLOW,
    GREEN,
    DARKBLUE,
}
