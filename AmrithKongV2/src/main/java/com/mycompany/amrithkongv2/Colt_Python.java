package com.mycompany.amrithkongv2;
import java.util.*;

public class Colt_Python extends RevolverTemplate 
{
    Random random = new Random();
    public Colt_Python() 
    {
        super("Colt Python", 6);
        this.bullets = 1;
        this.canRespin = false;
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
