package cdio3;

import java.util.Scanner;

class Monopoly {
    public static void main(String[] args) {
        System.out.println("Enter desired language / indtast foretrukne sprog [DK / EN]:");
        initializeGame();
        play();
    }
        
    public static void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        String language = scanner.nextLine();
        language = language.toLowerCase();
        try{
        if (language.equals("en")) {
            Textreader.loadFile("GametextEN.txt");
        } else if (language.equals("dk")) {
            Textreader.loadFile("GametextDK.txt");
        } else {
            throw new IllegalArgumentException();
        }
        } catch (IllegalArgumentException a) {
            System.out.println("\nEnter EN / indtast DK");
            initializeGame();
        }
    }

    public static void play() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(Textreader.getTextLineToPrint(35));// 35
            String command = scanner.nextLine();
            int numberOfPlayers = Integer.parseInt(command);
            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                throw new IllegalArgumentException();
            }
            Game monopoly = new Game(numberOfPlayers);
            monopoly.play();
        } catch (IllegalArgumentException a) {
            System.out.println("\n"+Textreader.getTextLineToPrint(34));// 34
            play();
        }
    }
}
