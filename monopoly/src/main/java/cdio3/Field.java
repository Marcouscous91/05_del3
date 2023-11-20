package cdio3;

public abstract class Field {
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
    private double cost;
    private double rent;
    private Actor owner;
    private Color color;


    public Property(String name, double cost, Actor bank, Color color){
        super(name);
        this.cost = cost;
        this.rent = cost;
        this.owner = bank;
        this.color = color;
    }
    
    public Color getColor(){
        return color;
    }

    public double getCost(){
        return cost;
    }


    public Actor getOwner(){
        return owner;
    }

    public double getRent(){
        return rent;
    }


    public void doubleRent(){
        this.rent = cost * 2;
    }

    public double getCost(){
        return cost;

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
            player.acquireProperty(this);
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
            System.out.println("\nYou payed " + rent + " M to " + owner.getName() + " in rent");
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
        if(!(owner instanceof Player)){
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

class InertField extends Field{
    public InertField(String name){
        super(name);
    }

    @Override
    public boolean doAction(Player player){
        if(player.getPosition() == 6){
            System.out.println("You're visiting prison");
        } else if (player.getPosition() == 12) {
            System.out.println("Great free parking");
        } else if(player.getPosition() == 3 || player.getPosition() == 9 || player.getPosition() == 15 || player.getPosition() == 21){
            System.out.println("This is a chance field, though it doesn't do anything");
        }
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
