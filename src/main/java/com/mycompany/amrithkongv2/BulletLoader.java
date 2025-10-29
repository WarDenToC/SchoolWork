package com.mycompany.amrithkongv2;
import java.util.*;
public class BulletLoader 
{
    public ArrayList<Boolean> loadbullet(int chamberSize, int bulletCount)
    {
        boolean[] chamber = new boolean[chamberSize]; //set the chamber of the current reovler and all of them are empty
        Random random =  new Random();
        
        while(bulletCount != 0)
        {
            int x = random.nextInt(chamberSize);
            if(chamber[x] == false) //if said random chamber is empty, that chamber will have a bullet
            {
                chamber[x] = true;
                bulletCount--;
            }
        }
        ArrayList<Boolean> list = new ArrayList<>(); //create a true and false arraylist of the chamber of the gun
        for(int i = 0; i < chamber.length; i++)
        {
            list.add(chamber[i]);
            
        }
        return list;
    }
    
}
