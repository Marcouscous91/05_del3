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
    }

    public void move(int dieSum){
        position += dieSum;
        if(position > 24){
            position -= 24;
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

    @Override
    public void subtractSum(double money){
        account.subtractSum(money);
        if(getBalance() < 0){
            inDebt = true;
        }
    }

    @Override
    public boolean transferMoney(Actor receiver, double amount){
        boolean canPay;
        if(this.getBalance() > amount){
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
    }

    @Override
    public boolean transferMoney(Actor receiver, double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferMoney'");
    }
}
