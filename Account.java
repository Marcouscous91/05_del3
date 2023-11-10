class Account {
    
    //Should create a double that is "Balance"
    private double balance = 0;

    public Account(){
        this.balance = 0;
    }

    //Should get the balance from "Player" and then display it to the player
    public double getBalance() {
        return balance;
    }

    //Allow for overwriting/setting the balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //Add a sum "money" to the balance, not return value
    public void addSum(double money) {
        balance += money;
    }

    //Substract a sum "money" from the balance, not return value
    public void subtractSum(double money) {
        balance -= money;
    }
}
