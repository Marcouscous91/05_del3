package cdio3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VerifyWin {
    @Test
    public void havenLostWhen0Test(){
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        p1.setBalance(1);
        p2.setBalance(8);
        boolean money = p1.transferMoney(p2, 1);
        assertEquals(money, true);
    }

    @Test
    public void havenLostWhenLesThen0Test(){
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        p1.setBalance(1);
        p2.setBalance(8);
        boolean money = p1.transferMoney(p2, 2);
        assertEquals(money, false);
    }

    @Test
    public void haveNotLostWhen1Test(){
        Player p1 = new Player("Player 1");
        Player p2 = new Player("Player 2");
        p1.setBalance(2);
        p2.setBalance(8);
        boolean money = p1.transferMoney(p2, 1);
        assertEquals(money, true);
    }
}
