package com.mycompany.amrithkongv2;
import java.util.*;
public abstract class RevolverTemplate implements Revolver 
{
    String gunModel;
    int chamberSize;
    int hammerIndex;
    int bullets;
    boolean canRespin;
    ArrayList<Boolean> chamberedBullet;  
    Random random = new Random();
    
    public RevolverTemplate(String gunModel, int chamberSize)
    {
        this.gunModel = gunModel;
        this.chamberSize = chamberSize;
        this.chamberedBullet = new ArrayList<Boolean>();
    }
    
    
    public void loadGun(ArrayList<Boolean> bullets)
    {
        chamberedBullet.clear();
        chamberedBullet.addAll(bullets);
        hammerIndex = random.nextInt(chamberSize); // So chamber is random in terms of it has bullet or not, and our hammerIndex is also random since the hammer of the gun can sit anywhere, of this as a spin 
    }
    
    public boolean pullHammer() // so how it works is, pull hammer which will spin the chamber by one THEN WE SHOOT
    {
        hammerIndex = (hammerIndex + 1) % chamberSize; // if hammer index is rotating until the end, it will reset to the first chamber until a bullet is fired.
        return shoot(); 
    }
    
    public boolean shoot() //if the chamber in which our bullet sits in has a bullet we fire
    {
        if(chamberedBullet.get(hammerIndex) == true)
        {
           chamberedBullet.set(hammerIndex, false);
           return true;
        }
        else
        {
            return false;
        }
    }
    
    public int getBullets()
    {
        return bullets;
    }

    public abstract void respinGun();
}
