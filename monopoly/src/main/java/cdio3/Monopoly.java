package cdio3;

import java.util.Scanner;

class Monopoly {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players:");
        String command = scanner.nextLine();
        int numberOfPlayers = Integer.parseInt(command);
        Game monopoly = new Game(numberOfPlayers);
    }
}
