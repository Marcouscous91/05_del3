package cdio3;

class Game {
    private Player[] players;
    private Die die;
    private Board board;
    private Player currenPlayer;
    private Bank bank;
    private int numberOfPlayers;

    public Game(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        bank = new Bank();
        die = new Die(1, 6);
        createPlayers(numberOfPlayers);
        board = new Board(bank);

    }

    public Player getCurrentPlayer(){
        return currenPlayer;
    }
    
    public void createPlayers(int numberOfPlayers){
        players = new Player[numberOfPlayers];
    }
}
