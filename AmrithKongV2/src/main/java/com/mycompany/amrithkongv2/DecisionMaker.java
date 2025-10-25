package com.mycompany.amrithkongv2;
import java.util.*;
public class DecisionMaker 
{
    private final Scanner scan_input = new Scanner(System.in); //chat told me to do this since scanner keeps double asking 
            
    public String getMenuChoice(Player player, RevolverTemplate gun)
    {
        String choice;
        
        while(true)
        {
            System.out.println("\n--- " + player.getName() + "'s turn ---");
            System.out.println("Options:");
            
            System.out.println("[S] Shoot self "
                    + "\n[Q] Quit game");
            if(gun.canRespin)
            {
                System.out.println("[R] ReSpin chamber");
            }
            if(player.getSelfShot() >= 3) //only prompts this if the player tries to shoot themselves 3 times or more
            {
                System.out.println("[K] Kill other player"); 
            }
            if(player.getSelfShot() >=2) //same as above but 2 times and more 
            {
                System.out.println("[P] Peek the next chamber");
            }
            
            
            System.out.print("Enter your choice here:");
            
            choice = scan_input.nextLine().trim().toUpperCase();
            
            if (choice.equals("S") 
                    || ( choice.equals("K") && player.getSelfShot() >= 3) 
                    || choice.equals("Q") 
                    || (gun.canRespin && choice.equals("R")) 
                    || (choice.equals("P") && player.getSelfShot() >= 2) ) 
            {
                return choice; 
            }
            
            System.out.println("Invalid choice, try again");
        }        
    }
}