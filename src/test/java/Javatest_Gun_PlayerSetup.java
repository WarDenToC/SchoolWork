
import com.mycompany.amrithkongv2.Gun_Player_setup;
import com.mycompany.amrithkongv2.Player;
import com.mycompany.amrithkongv2.RevolverTemplate;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class Javatest_Gun_PlayerSetup 
{
    // this is used to test the core setup of the game, it will test if guns have been made and returned right along with the players
    // this test should test about 6 classes: Revolver Manufacturer, All types of revolver class (3 total), the setup class, the bullet loader class 
    public Gun_Player_setup setter;
    String gunChoice1 = "GP100";
    String gunChoice2 = "Colt Python";
    String gunChoice3 = "Bounty Hunter";
    String player1 = "Mike";
    String player2 = "Osbore";
    
    /* reminder for myself
    assertEquals(String message, expected, actual);
    assertNotNull(String message, object);
    assertTrue(String message, condition);
    assertFalse(String message, condition);
    */
    
    @Test
    public void gunTest()
    {
        //create 3 different guns
        //each gun should be a completed gun with a set of amount of bullets loaded in randomly to the cyclinder
        Gun_Player_setup setup1 = new Gun_Player_setup(gunChoice1, player1, player2);
        Gun_Player_setup setup2 = new Gun_Player_setup(gunChoice2, player1, player2);
        Gun_Player_setup setup3 = new Gun_Player_setup(gunChoice3, player1, player2);
        
        //Testing the revolver manufacturer class 
        RevolverTemplate gun1 = setup1.gunKeeper();
        RevolverTemplate gun2 = setup2.gunKeeper();
        RevolverTemplate gun3 = setup3.gunKeeper();
        
        //test gun GP100 to see if the bullet amount is correct
        assertNotNull("Gun 1 error", gun1); 
        assertEquals("Gun 1 not loaded", 1, gun1.getBullets());
        //test randomization to see if the bullet slots are randomly chambered, might need to run a few times
        System.out.println(gun1.chamberedBullet+"\n");
   
        
        //test gun Colt Python to see if the bullet amount is correct
        assertNotNull("Gun 2 error", gun2);
        assertEquals("Gun 2 not loaded", 1, gun2.getBullets());
        //test randomization to see if the bullet slots are randomly chambered, might need to run a few times
        System.out.println(gun2.chamberedBullet+"\n");

        
        //test gun Bounty Hunter to see if the bullet amount is correct
        assertNotNull("Gun 3 error", gun3);
        assertEquals("Gun 3 not loaded", 2, gun3.getBullets());
        //test randomization to see if the bullet slots are randomly chambered, might need to run a few times
        System.out.println(gun3.chamberedBullet+"\n"); 

    }
    @Test
    public void playerTest()
    {
        Gun_Player_setup setup1 = new Gun_Player_setup(gunChoice1, player1, player2);
        
        ArrayList<Player> testList = setup1.createPlayers();
        
        // test size of players and then names
        assertEquals("Should create exactly 2 players", 2, testList.size());
        assertEquals("Should be Mike", "Mike", testList.get(0).getName());
        assertEquals("Should be Osbore", "Osbore", testList.get(1).getName());
        
    }
}
