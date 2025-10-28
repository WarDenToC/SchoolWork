package com.mycompany.amrithkongv2;
import java.util.*;

public class Action_Handler 
{
    RevolverTemplate gunInternal;
    private StatTracker stats;
    
    public Action_Handler(RevolverTemplate gunInternal, StatTracker stats)
    {
        this.gunInternal = gunInternal;
        this.stats = stats;
    }

    public String handle_action(Player currentPlayer, Player otherPlayer, String choice)
    {
        String result = "";
        switch(choice) //based on the choice, calls in the method and break,
        {
            case "S":
                result = selfShot(currentPlayer);
                break;
                
            case "K":
                result = shootOther(currentPlayer, otherPlayer);
                break;
                
            case "R":
                result = reSpin();
                break;
                
            case "P":
                result = peekNextChamber();
                break;
        }
        return result;
        
    }
    
    public String selfShot(Player ownself) // ask for the current player and calls in the pullHammer function from revolver template 
    {
        boolean fired = gunInternal.pullHammer();

        stats.incrementSelfShot(ownself.getName());
        if(fired)
        {
            
            ownself.kill(); //if gun goes off, the player calls in the kill method which means alive == false
            System.out.println("DEBUG: Current alive = " + ownself.getAlive());
            return "💥 BANG! " + ownself.getName() + " has been killed.";
        }
        else
        {
            stats.incrementTurnsSurvived(ownself.getName()); //if it did not shoot, our player self shoot counter goes up by 1
            ownself.self_shot_counter();
            return "Click... " + ownself.getName() + " survived!";
        }
    }
    
    public String shootOther(Player shooter, Player target)
    {
        boolean fired = gunInternal.pullHammer();
        
        if(fired)
        {
            target.kill();
            System.out.println("DEBUG: " + target.getName() + " alive = " + target.getAlive());

            return "💥 BANG! " + target.getName() + " has been killed.";
        }
        else
        {
            stats.incrementTurnsSurvived(target.getName());
            return "CLICK! " + target.getName() + " is alive";
        }
    }
    
    public String peekNextChamber() //peek the next chamber of the gun to see if it has a bullet or not
    {
        int nextChamber = (gunInternal.hammerIndex + 1) % gunInternal.chamberSize;
        return gunInternal.chamberedBullet.get(nextChamber).toString();
    }
    
    public String reSpin()
    {
        gunInternal.respinGun();
        return "WRRRRRRRRRRRRRRR";
    }
    
}
