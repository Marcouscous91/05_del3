public class Bank {

    public void payToBank(Player fromPlayer, int amount) {
        int pay = abletoPay(fromPlayer, amount);

        /*
         * if (player.getBalance() <= 0 || amount <= 0) {
         * return 0;
         * }
         * if (player.getBalance() - amount < 0) {
         * return player.getBalance();
         * }
         * return amount;
         */
    }

    // I think this belongs in "Player class"
    public void payToPlayer(Player fromPlayer, Player toPlayer, int amount) {

    }

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
