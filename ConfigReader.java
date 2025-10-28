package com.mycompany.amrithkongv2;
import java.awt.BorderLayout;
import java.io.*;
import java.util.*;
public class ConfigReader 
{
    ArrayList<Player> configPlayers = new ArrayList<>();
    String filepath = "./config.txt"; // this file needs to be manually since I expect players to make this by themselves
    String gunType = "";
    
    public ArrayList<Player> readConfigPlayers()//called readConfig player but we also note in the gun type as well, this method returns the ArrayList of players
    {
        String name1, name2;
        
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath)))
        {
            name1 = reader.readLine();
            name2 = reader.readLine();
            gunType = reader.readLine();//note the gun on the 3rd line
            
            
            Player player1_config = new Player(name1);
            Player player2_config = new Player(name2);
            
            configPlayers.add(player2_config);
            configPlayers.add(player1_config);
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong with the File Reading");
        }
        return configPlayers;
    }
    
    public String readConfigGun()//get the gun and return the string name of the gun
    {
        if(gunType == null || gunType.isBlank())
        {
            System.out.println("Error from config file");
            gunType = "Q";
        }
        else
        {
            if(gunType.equalsIgnoreCase("Bounty Hunter"))
            {
                gunType = "3";
            }
            else if(gunType.equalsIgnoreCase("GP100"))
            {
                gunType = "2";
            }
            else if(gunType.equalsIgnoreCase("Colt Python"))
            {
                gunType = "1";
            }
        }
        
        return gunType;
    }
    
}
