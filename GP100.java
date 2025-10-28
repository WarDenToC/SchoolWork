package com.mycompany.amrithkongv2;
import java.util.*;

public class GP100 extends RevolverTemplate 
{
    Random random = new Random();
    public GP100() 
    {
        super("GP100", 7);
        this.bullets = 1;
        this.canRespin = true;
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
