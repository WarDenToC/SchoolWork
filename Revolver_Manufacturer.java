package com.mycompany.amrithkongv2;
import java.util.*;

public class Revolver_Manufacturer 
{
    RevolverTemplate gun;
    BulletLoader loader = new BulletLoader();
    ArrayList<Boolean> cylinder;

    
    public RevolverTemplate gunmaker(String choice) // we get the choice from the Game Class
    {
        if(choice.equalsIgnoreCase("Colt Python"))
        {
            gun = new Colt_Python();
            cylinder = loader.loadbullet(gun.chamberSize, gun.getBullets()); //loadbullet method will ask for the gunchamber size and amount of bullets per gun has then return an arrayList of true and false
            //once done cycliner is an arraylist of true of false,
            gun.loadGun(cylinder); //cyclider will be passed to the gun template and be added to the arrayList of boolean there as well 
            System.out.println("You have chosen the Colt Python");
            System.out.println("Amount of bullets in this gun: ⌯⁍\n");
           
        }
        
        else if(choice.equalsIgnoreCase("GP100"))
        {
            gun = new GP100();
            cylinder = loader.loadbullet(gun.chamberSize, gun.getBullets());
            gun.loadGun(cylinder);
            System.out.println("You have chosen the GP100");
            System.out.println("Amount of bullets in this gun: ⌯⁍ \n");
        }
        
        else if(choice.equalsIgnoreCase("Bounty Hunter"))
        {
            gun = new Bounty_Hunter();
            cylinder = loader.loadbullet(gun.chamberSize, gun.getBullets()); 
            gun.loadGun(cylinder);
            System.out.println("You have chosen the Bounty Hunter");
            System.out.println("Amount of bullets in this gun: ⌯⁍ ⌯⁍\n");
            
        }
        
        return gun;
    }
    
}
