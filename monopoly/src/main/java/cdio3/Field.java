package cdio3;

abstract class Field {
    protected String name;

    public Field(String name){
        this.name = name;
    }

    /*
     * Action to be performed, when a player lands on field
     */
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

    /*
     * Initiates the purchase of the property.
     * Keeps track if the player has enough money to purchase or not. Sets the owner accordingly
     * 
     * Param:   player: the player who is making the purchase.
     * 
     * Return:  canPay: boolean depending if player can perform purchase.
     */
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

    /*
     * Initiates the payment of rent.
     * Keeps track if the player has enough money to pay rent or not. Sets the owner accordingly
     * 
     * Param:   player: the player who is paying rent.
     * 
     * Return:  canPay: boolean depending if player can pay.
     */
    private boolean payRent(Player player){
        boolean canPay;
        canPay = player.transferMoney(owner, rent);
        if(canPay){
            System.out.println("\nYou payed " + cost + " M to " + owner.getName() + " in rent");
        }
        return canPay;
    }

    /*
     * Action performed when player lands on property
     * Checks if the player is the owner or not. 3 scenarios:
     * 1) Property is free -> player buys property from bank
     * 2) Property owned by other player -> player pays rent to owner
     * 3) Property owned by player -> nothing happens.
     * 
     * Param:   player: the player who is paying rent.
     * 
     * Return:  canPay: boolean depending if player can pay according to scenario.
     */
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
            player.setPosition(6);
            player.subtractSum(2);
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

class IntertField extends Field{
    public IntertField(String name){
        super(name);
    }

    @Override
    public boolean doAction(Player player){
        return true;
    }
}

class ChanceField extends Field{
    public ChanceField(String name){
        super(name);
    }

    @Override
    public boolean doAction(Player player){
        System.out.println("nothing here yet! more to come later");
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
