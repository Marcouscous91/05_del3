public class Bank {
// Constructor to pay bank "amount"
    public void payToBank(Player fromPlayer, int amount) {
        int pay = abletoPay(fromPlayer, amount);

    }
    //Constructor to pay from "Player" to "Player"

    // I think this belongs in "Player class"
    public void payToPlayer(Player fromPlayer, Player toPlayer, int amount) {

    }
// Checks if player is abletopay amount
    private int ableToPay(Player player, int amount) {
        if (player.getBalance() <= 0 || amount <= 0) {
            return 0;
        }
        if (player.getBalance() - amount < 0) {

            return player.getBalance();
        }
        return amount;
    }
}
