package com.mycompany.amrithkongv2;
import java.util.*;

public class Bounty_Hunter extends RevolverTemplate 
{
    
    Random random = new Random();
    public Bounty_Hunter() 
    {
        super("Bounty Hunter", 8);
        this.canRespin = false;
        this.bullets = 2;
    }
    
    public void respinGun() 
    {    
        if(canRespin == true)
        {
            hammerIndex = random.nextInt(this.chamberSize);
        }
    }
    
    public int getBullets() // also exist in revolver template but incase we want the bullet from the class itself
    {
        return this.bullets;
    }
    
}
