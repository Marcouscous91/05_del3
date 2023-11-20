package cdio3;

import java.util.Scanner;

class Monopoly {
    public static void main(String[] args){
        play();
    }

    public static void play(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter number of players:");
            String command = scanner.nextLine();
            int numberOfPlayers = Integer.parseInt(command);
            if(numberOfPlayers < 2 || numberOfPlayers > 4){
                throw new IllegalArgumentException();
            }
            Game monopoly = new Game(numberOfPlayers);
            monopoly.play();
        } catch (IllegalArgumentException a){
            System.out.println("\nNumber of Players, must be a number between 2 and 4.");
            play();
        }
    }
}
