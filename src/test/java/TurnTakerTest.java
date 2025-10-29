import com.mycompany.amrithkongv2.*;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
public class TurnTakerTest 
{
    // simple test to make sure turn switching works, probably the most imporatnt part of this class
    
    
    @Test
    public void testTurnSwitching() 
    {
        
        TurnTaker turnTaker = new TurnTaker();
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");

        //set the turn for the players
        turnTaker.setPlayers(Arrays.asList(player1, player2));

        //First person should be Alice
        Player first = turnTaker.getCurrentPlayer();
        System.out.println("Initial turn: " + first.getName());

        // Move to next player Bob
        turnTaker.getNextPlayer();
        Player next = turnTaker.getCurrentPlayer();
        System.out.println("After switching: " + next.getName());

        // Move again (should go back to Alice)
        turnTaker.getNextPlayer();
        Player back = turnTaker.getCurrentPlayer();
        System.out.println("After second switch: " + back.getName());

        //test to see if this works as intended
        assertNotEquals("Next player should not be the same as first", first, next);
        //first player now should be bob and the next one alice
        System.out.println(first.getName() + " " + next.getName());

    }
    
}
