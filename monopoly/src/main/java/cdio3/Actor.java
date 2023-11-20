package cdio3;

abstract class Actor {
    protected String name;
    protected Account account;

    public Actor(){
        this.account = new Account();
    }

    public String getName(){
        return name;
    }

    public void setBalance(double money){
        account.setBalance(money);
    }

    // Method to get the players balance and return it
    public double getBalance() {
        return account.getBalance();

    }

    // Method to add money/points to Player accoutn
    public void addSum(double money) {
        account.addSum(money);

    }

    // Method to subtract money/sum from Players account
    public void subtractSum(double money) {
        account.subtractSum(money);

    }

    abstract public boolean transferMoney(Actor receiver, double amount);
}

class Player extends Actor{
    private int position;
    private boolean inDebt;
    private double debt;
    private boolean inPrison;

    public Player(String name){
        super();
        this.position = 0;
        this.inDebt = false;
        this.name = name;
    }


    /*
     * Updates the position of the player, depending on die sum.
     * Makes sure that the player position is within the number of fields on board.
     * Param:   dieSum: the face value of the rolled die.
     */
    public void move(int dieSum){
        position += dieSum;
        if(position > 23){
            position -= 23;
        }
    }

    public void ToPrison(){
        inPrison = true;
    }

    public int getPosition(){
        return position;
    }

    public void outOfPrison(){
        inPrison = false;
    }

    public boolean inDebt(){
        return inDebt;
    }

    public void setPosition(int newPosition){
        position = newPosition;
    }

    /*
     * Subtracts money from the player account.
     * If account falls below 0, the player is in debt, and debt is tracked, while balance is set to 0.
     * 
     * Param:   money: the amount to be subtracted from account
     */
    @Override
    public void subtractSum(double money){
        account.subtractSum(money);
        if(getBalance() < 0){
            inDebt = true;
            debt = Math.abs(getBalance());
            setBalance(0);
        }
        
    }

    /*
     * Transfers money from the players account to the receivers account.
     * Keeps track if the player has enough money to pay amount, and if the player goes in debt.
     * 
     * Param:   receiver: the player, the money is transferred to
     *          amount: the total amount to be transferred
     * 
     * Return:  canPay: boolean to tell if enough money or not, to complete transfer.
     */
    @Override
    public boolean transferMoney(Actor receiver, double amount){
        boolean canPay;
        if(this.getBalance() >= amount){
            receiver.addSum(amount);
            this.subtractSum(amount);
            canPay = true;
        } else {
            receiver.addSum(this.getBalance());
            debt = Math.abs(this.getBalance() - amount);
            this.setBalance(0);
            canPay = false;
        }
        return canPay;
    }
}

class Bank extends Actor{

    public Bank(){
        super();
        this.setBalance(90);
        this.name = "Bank";
    }

    @Override
    public boolean transferMoney(Actor receiver, double amount) {
        this.subtractSum(amount);
        receiver.addSum(amount);
        return true;
    }
}
