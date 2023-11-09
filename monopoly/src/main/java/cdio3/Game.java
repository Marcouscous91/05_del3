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
        bank.setBalance(90);

    }

    public void play(){
        Scanner pressEnter = new Scanner(System.in);
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.println(players[i].getName() + ", please press enter to roll dice!");
            pressEnter.nextLine();
            roll(players[i]);
            if(i == numberOfPlayers - 1){
                i = -1;
            }
        }
    }

    public Player getCurrentPlayer(){
        return currenPlayer;
    }
    
    public void movePlayer(int sum){
        currenPlayer.move(sum);
        String nameOfField = board.getField(currenPlayer.getPosition()).getName();
        System.out.println("You landed on " + nameOfField);
        board.movePlayer(this);
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

    private void roll(Player player){
        currenPlayer = player;
        die.roll();
        int sum = die.getFaceValue();
        movePlayer(sum);
    }
}
