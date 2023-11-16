package cdio3;

import java.util.Scanner;

class Monopoly {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players:");
        String command = scanner.nextLine();
        int numberOfPlayers = Integer.parseInt(command);
        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.println("you have to write a number between 2 and 4");
            command = scanner.nextLine();
            numberOfPlayers = Integer.parseInt(command);
        }
        Game monopoly = new Game(numberOfPlayers);
        monopoly.play();
    }
}
