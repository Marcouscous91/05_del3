package cdio3;

abstract class Field {
    protected String name;

    public Field(String name){
        this.name = name;
    }

    abstract public boolean doAction(Player player);

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
            System.out.println("\nYou bought " + name + " for " + cost + "M");
        } else if(!canPay) {
            System.out.println("\nYou don't have enough money to buy " + this.name);
        }
        return canPay;
    }

    private boolean payRent(Player player){
        boolean canPay;
        canPay = player.transferMoney(owner, rent);
        if(canPay){
            System.out.println("\nYou payed " + cost + " M to " + owner.getName() + " in rent");
        }
        return canPay;
    }

    @Override
    public boolean doAction(Player player){
        boolean canPay;
        if(owner.getName().equals("Bank")){
            canPay = buyProperty(player);
        } else if(owner instanceof Player && owner != player){
            canPay = payRent(player);
        } else {
            return true;
        }
        return canPay;
    }

}

class Prison extends Field{

    public Prison(String name){
        super(name);
    }

    @Override
    public boolean doAction(Player player){
        player.ToPrison();
        System.out.println("You done gone to prison");
        return true;
    }
    
}

class Start extends Field{
    public Start(String name){
        super(name);
    }

    @Override
    public boolean doAction(Player player){
        player.addSum(2);
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
