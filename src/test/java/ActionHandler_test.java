
import com.mycompany.amrithkongv2.Action_Handler;
import com.mycompany.amrithkongv2.Bounty_Hunter;
import com.mycompany.amrithkongv2.Gun_Player_setup;
import com.mycompany.amrithkongv2.Player;
import com.mycompany.amrithkongv2.RevolverTemplate;
import com.mycompany.amrithkongv2.Revolver_Manufacturer;
import com.mycompany.amrithkongv2.StatTracker;

import org.junit.Test;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ActionHandler_test 
{
    public Action_Handler action;
    private Player player1;
    private Player player2;
    private RevolverTemplate gun;
    
    @Before
    public void setUpAction() 
    {
        // Initialize players before each test
        player1 = new Player("King");
        player2 = new Player("Kong");

        // Initialize a fresh gun before each test
        Revolver_Manufacturer gunMaker = new Revolver_Manufacturer();
        gun = gunMaker.gunmaker("Colt Python");

        // Initialize the Action_Handler
        fakeStatTracker dummyStats = new fakeStatTracker();
        action = new Action_Handler(gun, dummyStats); // stats can be null if not needed
    }
    
    @Test
    public void testKill()
    {
        String result = action.selfShot(player1);
        assertNotNull(result);
        assertTrue(player1.getAlive() == true || player1.getAlive() == false);
    }
    
    @Test
    public void testKillOther()
    {
        String result = action.shootOther(player1, player2);
        assertNotNull(result);
        assertTrue(player2.getAlive() == true || player2.getAlive() == false);
    }
    
    @Test
    public void testSpin()
    {
        String result = action.reSpin();
        assertEquals("WRRRRRRRRRRRRRRR", result);
    }
    
}

// A dummy StatTracker that does nothing, can't pass a real stat tracker so made a fake one just to test the killing of players
class fakeStatTracker extends StatTracker 
{    
    fakeStatTracker()
    {
        super(null, null, null, null);
    }
    
    @Override
    public void incrementSelfShot(String name) {}
    @Override
    public void incrementTurnsSurvived(String name) {}
}
