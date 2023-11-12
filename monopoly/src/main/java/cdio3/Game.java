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

    public void play(){
        Scanner pressEnter = new Scanner(System.in);
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.println("\n" + players[i].getName() + ", please press enter to roll dice!");
            pressEnter.nextLine();
            boolean continueGame = roll(players[i]);
            if(i == numberOfPlayers - 1){
                i = -1;
            }
        }
    }

    public Player getCurrentPlayer(){
        return currenPlayer;
    }
    
    public boolean movePlayer(int dieSum){
        boolean continueGame;
        currenPlayer.move(dieSum);
        String nameOfField = board.getField(currenPlayer.getPosition()).getName();
        System.out.println("\nYou landed on " + nameOfField);
        continueGame = board.movePlayer(currenPlayer);
        return continueGame;
    }

    private void createPlayers(int numberOfPlayers){
        Scanner input = new Scanner(System.in);
        this.players = new Player[numberOfPlayers];

        for(int i = 0; i < players.length; i++){
            System.out.println("Enter name of Player " + (i + 1));
            String playerName = input.nextLine();
            this.players[i] = new Player(playerName);
            bank.transferMoney(this.players[i], 15);  
        }
    }

    private boolean roll(Player player){
        boolean continueGame;
        currenPlayer = player;
        die.roll();
        int dieSum = die.getFaceValue();
        continueGame = movePlayer(dieSum);
        return continueGame;
    }
}
