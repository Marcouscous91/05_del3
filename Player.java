package cdio3;

class Player {

    private Account account;
    private int age;
    private String animalToken;

    // Constructor for a new Player
    public Player(String animalToken,int age) {
        this.animalToken = animalToken;
        this.age = age;
        createAccount();
    }

    private void createAccount() {
        this.account = new Account();
    }

    
    // Method to get a playerAge and return it
    public int getAge() {
        return age;
    }

    public void setAget(int age){
        this.age = age;
    }

    // Method to get a animalToken and return it
    public int getAnmaltoken(){
        return animalToken;
    }
    
    // Method to set/change an animalToken
    public void setAnimalToken(String animalToken) {
        this.animalToken = animalToken;
    }



    //Don't know if these last ones should be kept, or what to call them, so i won't change them right now
    //Might make sense to keep them as almost the same, but i'm not quite sure
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

    // Method to print Player info
    public void displayPlayerInfo() {
        System.out.println("AnimalToken: " + animalToken);
        System.out.println("Money: " + account.getBalance());
    }
}