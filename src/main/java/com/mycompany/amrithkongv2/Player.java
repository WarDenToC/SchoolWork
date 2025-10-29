
package com.mycompany.amrithkongv2;

public class Player
{
    private String name;
    private boolean alive =  true;
    private int self_shot = 0;
    
    
    public Player(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
        
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public boolean getAlive()
    {
        return alive;
    }
    
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
    
    public void kill()
    {
        alive = false;
    }
    
    public void self_shot_counter()
    {
        self_shot++;
    }
    
    public int getSelfShot()
    {
        return self_shot;
    }
    
    
    
}