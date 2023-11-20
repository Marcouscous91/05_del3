package cdio3;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
public class MomentTest {
    @Test
    public void momvmentTest(){
        Player playerMovmet = new Player("dog"); //haw to have a name
        for(int i = 1; i<6 ; i++){ //chek all typs of dice role
            int playerStart = playerMovmet.getPosition();
            playerMovmet.move(i);
            assertNotEquals(playerStart, playerMovmet.getPosition());
        }
    }

    

}
