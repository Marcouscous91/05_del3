package cdio3;

import java.util.Scanner;

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
        bank.setBalance(90);
        die = new Die(1, 6);
        createPlayers(numberOfPlayers);
        board = new Board(bank);
    }

    /* 
     * (!) Important function (!)
     * 
     * Main function:
     * 
     * Initiates the game, with the subsequent methods.
     */
    public void play(){
        Scanner pressEnter = new Scanner(System.in);
        // Loop to keep game going
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.println("\n" + players[i].getName() + ", please press enter to roll dice!");
            pressEnter.nextLine();
            boolean continueGame = roll(players[i]);
            // Resets the for-loop, according to number of players.
            if(i == numberOfPlayers - 1){
                i = -1;
            }
        }
    }

    public Board getBoard(){
        return board;
    }

    public Player getCurrentPlayer(){
        return currenPlayer;
    }

    public Player getPlayer(int playerNumber){
        return players[playerNumber - 1];
    }
    
    /*
     * (!) Important function (!)
     * 
     * Updates the board position of the player, corresponding to the die roll, and fetches the 
     * corresponding field.
     * Calls on the movePlayer() action of the board, which will initiate the action according,
     * to the field the player lands on.
     * 
     * Param:   dieSum: the face value of the die rolled.
     * 
     * Return:  continueGame:   boolean depending og whether the action performed on the field
     *                          is succesfully completed or not
     */
    public boolean movePlayer(int dieSum){
        boolean continueGame;
        currenPlayer.move(dieSum);
        String nameOfField = board.getField(currenPlayer.getPosition()).getName();
        System.out.println("\nYou landed on " + nameOfField);
        continueGame = board.movePlayer(currenPlayer);
        return continueGame;
    }

    /*
     * Creates the players according to number of players, and stores them in array of type Players.
     * Transfers the start amount of money to each player, from bank, according to number of players.
     * 
     * Param:   numberOfPlayers: the desired number of players, playing the game.
     */
    private void createPlayers(int numberOfPlayers){
        Scanner input = new Scanner(System.in);
        this.players = new Player[numberOfPlayers];

        for(int i = 0; i < players.length; i++){
            System.out.println("Enter name of Player " + (i + 1));
            String playerName = input.nextLine();
            this.players[i] = new Player(playerName);
            if (numberOfPlayers == 2){
                bank.transferMoney(this.players[i], 20); 
            } else if (numberOfPlayers == 3){
                bank.transferMoney(this.players[i], 18); 
            } else if (numberOfPlayers == 4){
                bank.transferMoney(this.players[i], 16); 
            }  
        }
    }

    /*
     * Determines the current player, who's turn it is.
     * Rolls the dice, and gets the face value.
     * Calls on the movePlayer() function, of this object, to initiate player movement on the board.
     * 
     * Param:   player: the player who's turn it is.
     * 
     * Return: continueGame:    boolean depending, if the doAction() of fields where performed
     *                          succesfully
     */
    private boolean roll(Player player){
        boolean continueGame;
        currenPlayer = player;
        die.roll();
        int dieSum = die.getFaceValue();
        continueGame = movePlayer(dieSum);
        return continueGame;
    }
}
