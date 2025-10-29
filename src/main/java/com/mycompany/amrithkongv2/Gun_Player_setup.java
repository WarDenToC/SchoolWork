package com.mycompany.amrithkongv2;

import java.util.*;

public class Gun_Player_setup 
{
    ArrayList<Player> internal_players = new ArrayList<>();
    String player1 = "";
    String player2 = "";
    String gunChoice = "";
    RevolverTemplate completedGun;
    
    // Okay so I refactored almost everything, wayyy more simpler than the previous gun player setup, cuz I don't have to much work as much as before
    
    //made a constructor for this class
    public Gun_Player_setup(String gunChoice, String player1, String player2)
    {
        this.gunChoice = gunChoice;
        this.player1 = player1;
        this.player2 = player2;
    }

    // get all the players and then just make a list of the players with their names in it
    public ArrayList<Player> createPlayers() 
    {
        Player Player1 = new Player(player1);
        Player Player2 = new Player(player2);
        
        internal_players.add(Player1);
        internal_players.add(Player2);
        
        return internal_players;
    }

    
    //this will handle the making of a completed revolver, no need to pass the empty gun around, it will return a completed gun with loaded bullet at random. 
    //Essentially I turned this gun into an all in one type of product maker
    public RevolverTemplate gunKeeper() 
    {
        Revolver_Manufacturer gunMaker = new Revolver_Manufacturer();
        completedGun = gunMaker.gunmaker(gunChoice);
        return completedGun;
    }
}
