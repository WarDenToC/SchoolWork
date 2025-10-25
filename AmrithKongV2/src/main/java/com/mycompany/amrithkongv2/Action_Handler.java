package com.mycompany.amrithkongv2;
import java.util.*;

public class Action_Handler 
{
    RevolverTemplate gunInternal;
    private StatTracker stats;
    
    public Action_Handler(StatTracker stats)
    {
        this.stats = stats;
    }

    public void handle_action(Player currentPlayer, Player otherPlayer, RevolverTemplate gun, String choice)
    {
        this.gunInternal = gun;
        switch(choice) //based on the choice, calls in the method and break,
        {
            case "S":
                selfShot(currentPlayer);
                break;
                
            case "K":
                shootOther(currentPlayer, otherPlayer);
                break;
                
            case "R":
                reSpin(gun);
                break;
                
            case "P":
                peekNextChamber(gun);
                break;
        }
        
    }
    
    public void selfShot(Player ownself) // ask for the current player and calls in the pullHammer function from revolver template 
    {
        boolean fired = gunInternal.pullHammer();

        stats.incrementSelfShot(ownself.getName());
        if(fired)
        {
            ownself.kill(); //if gun goes off, the player calls in the kill method which means alive == false
            System.out.println("BANG!");
            System.out.println(ownself.getName() + " has been killed");
        }
        else
        {
            stats.incrementTurnsSurvived(ownself.getName()); //if it did not shoot, our player self shoot counter goes up by 1
            ownself.self_shot_counter();
            System.out.println("CLICK!!...");
        }
    }
    
    public void shootOther(Player shooter, Player target)
    {
        boolean fired = gunInternal.pullHammer();
        
        if(fired)
        {
            target.kill();
            System.out.println(shooter.getName() + " has shot and killed " + target.getName());
        }
        else
        {
            stats.incrementTurnsSurvived(target.getName());
            System.out.println(target.getName() + " did not get shot");
        }
    }
    
    public void peekNextChamber(RevolverTemplate gun) //peek the next chamber of the gun to see if it has a bullet or not
    {
        int nextChamber = (gun.hammerIndex + 1) % gun.chamberSize;
        System.out.println(gun.chamberedBullet.get(nextChamber));
    }
    
    public void reSpin(RevolverTemplate gun)
    {
        gun.respinGun();
    }
    
}
