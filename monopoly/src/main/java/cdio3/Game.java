package cdio3;

import java.util.Scanner;

class Game {
    private Player[] players;
    private Die die;
    private Board board;
    private Player currentPlayer;
    private Bank bank;
    private int numberOfPlayers;

    public Game(int numberOfPlayers) {
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
    public void play() {
        Scanner pressEnter = new Scanner(System.in);
        // Loop to keep game going
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("\n" + players[i].getName() + Textreader.getTextLineToPrint(27));// 27
            pressEnter.nextLine();
            boolean continueGame = roll(players[i]);
            // Searches for winning player, if a player loses
            if (!continueGame) {
                Player winner = checkWinner();
                System.out.println(Textreader.getTextLineToPrint(28) + "\n" + // 28
                                winner.getName() + Textreader.getTextLineToPrint(29) // 29
                                + winner.getBalance() + Textreader.getTextLineToPrint(30)// 30
                );
                System.out.println("\n" + Textreader.getTextLineToPrint(31));//31
                break;
            }
            // Resets the for-loop, according to number of players.
            if (i == numberOfPlayers - 1) {
                i = -1;
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer(int playerNumber) {
        return players[playerNumber - 1];
    }

    /*
     * (!) Important function (!)
     * 
     * Updates the board position of the player, corresponding to the die roll, and
     * fetches the
     * corresponding field.
     * Calls on the movePlayer() action of the board, which will initiate the action
     * according,
     * to the field the player lands on.
     * Checks if player is in prison, and makes them pay accordingly.
     * Checks if player lands on, or passes start, and receives accordingly.
     * 
     * Param: dieSum: the face value of the die rolled.
     * 
     * Return: continueGame: boolean depending og whether the action performed on
     * the field
     * is succesfully completed or not
     */
    public boolean movePlayer(int dieSum) {
        boolean continueGame;
        if (currentPlayer.inPrison()) {
            boolean canPay = currentPlayer.transferMoney(bank, 1);
            if (!canPay) {
                return canPay;
            }
            currentPlayer.outOfPrison();
        }
        boolean passStart = currentPlayer.move(dieSum);
        if (passStart) {
            bank.transferMoney(currentPlayer, 2);
            System.out.println(Textreader.getTextLineToPrint(36));
        }
        String nameOfField = board.getField(currentPlayer.getPosition()).getName();
        System.out.println("\n" + Textreader.getTextLineToPrint(32) + nameOfField);// 32
        continueGame = board.movePlayer(currentPlayer);
        return continueGame;
    }

    /*
     * Creates the players according to number of players, and stores them in array
     * of type Players.
     * Transfers the start amount of money to each player, from bank, according to
     * number of players.
     * 
     * Param: numberOfPlayers: the desired number of players, playing the game.
     */
    private void createPlayers(int numberOfPlayers) {
        Scanner input = new Scanner(System.in);
        this.players = new Player[numberOfPlayers];

        for (int i = 0; i < players.length; i++) {
            System.out.println(Textreader.getTextLineToPrint(33) + (i + 1));// 33
            String playerName = input.nextLine();
            this.players[i] = new Player(playerName);
            if (numberOfPlayers == 2) {
                bank.transferMoney(this.players[i], 20);
            } else if (numberOfPlayers == 3) {
                bank.transferMoney(this.players[i], 18);
            } else if (numberOfPlayers == 4) {
                bank.transferMoney(this.players[i], 16);
            }

            switch (i) {
                case 0:
                    players[i].setToken("#");
                    break;
                case 1:
                    players[i].setToken("@");
                    break;
                case 2:
                    players[i].setToken("$");
                    break;
                case 3:
                    players[i].setToken("%");
                    break;
                default:
                    break;
            }
        }
    }

    /*
     * Searches for the player with the highest amount on their balance - in case of
     * tie,
     * searches for the player with highest over all score, according to amount of
     * properties and
     * their cost.
     * 
     * Return: winner: the player with highest score
     */
    private Player checkWinner() {
        boolean isTie = false;
        double winnerBalance = 0;
        Player winner = null;
        int tieCounter = 0;
        Player[] playersInTie = new Player[numberOfPlayers - 1];
        // Searches for the highest balance and notates the player
        for (int i = 0; i < numberOfPlayers; i++) {
            if (!players[i].inDebt()) {
                double playerBalance = players[i].getBalance();
                if (winnerBalance < playerBalance) {
                    isTie = false;
                    winner = players[i];
                    winnerBalance = playerBalance;
                    // Checks to see if there is a tie between one or more players.
                } else if ((winnerBalance == playerBalance) && (winnerBalance != 0)) {
                    isTie = true;
                    playersInTie[tieCounter] = players[i];
                    tieCounter++;
                }
            }
        }

        if (isTie) {
            winner = resolveTie(playersInTie);
        }

        return winner;
    }

    /*
     * Searches through max total score between players, if there is a tie between
     * max balance.
     * 
     * Param: playersInTie: array of players in tie
     * 
     * Return: winner: the player who has the highest overall score.
     */
    private Player resolveTie(Player[] playersInTie) {
        Player winner = null;
        double winnerBalance = 0;

        for (Player player : playersInTie) {
            if (player == null) {
                continue;
            } else if (winnerBalance < player.getTotalScore()) {
                winner = player;
                winnerBalance = player.getTotalScore();
            }
        }

        return winner;
    }

    /*
     * Determines the current player, who's turn it is.
     * Rolls the dice, and gets the face value.
     * Calls on the movePlayer() function, of this object, to initiate player
     * movement on the board.
     * 
     * Param: player: the player who's turn it is.
     * 
     * Return: continueGame: boolean depending, if the doAction() of fields where
     * performed
     * succesfully
     */
    private boolean roll(Player player) {
        boolean continueGame;
        currentPlayer = player;
        die.roll();
        int dieSum = die.getFaceValue();
        continueGame = movePlayer(dieSum);
        
        System.out.println("\n"+ Textreader.getTextLineToPrint(37) + player.getBalance() + "\n");
        return continueGame;
    }
}
