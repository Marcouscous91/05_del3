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
        die = new Die(1, 6);
        createPlayers(numberOfPlayers);
        board = new Board(bank);
        bank.setBalance(90);

    }

    public void play(){
        for(int i = 1; i <= numberOfPlayers; i++){
            roll(players[i]);
            if(i == numberOfPlayers){
                i = 1;
            }
        }
    }

    public Player getCurrentPlayer(){
        return currenPlayer;
    }
    
    public void movePlayer(int sum){
        currenPlayer.move(sum);
        board.movePlayer(this);
    }

    private void createPlayers(int numberOfPlayers){
        Scanner input = new Scanner(System.in);
        players = new Player[numberOfPlayers];

        for(int i = 0; i < players.length; i++){
            System.out.println("Enter name of Player " + (i + 1));
            String playerName = input.nextLine();
            players[i] = new Player(playerName);
        }
    }

    private void roll(Player player){
        currenPlayer = player;
        die.roll();
        int sum = die.getFaceValue();
        movePlayer(sum);
    }
}
