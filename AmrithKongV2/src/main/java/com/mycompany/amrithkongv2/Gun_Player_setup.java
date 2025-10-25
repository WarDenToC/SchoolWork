package com.mycompany.amrithkongv2;

import java.util.*;

public class Gun_Player_setup {

    ArrayList<Player> internal_players = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    String gunChoice = "";
    boolean autoSetup = false;
    ConfigReader configger = new ConfigReader();

    public ArrayList<Player> createPlayers() //A lot of mess since I am adding this as I go, it wasn't planned. This method will create players either manually or automatically from the config file
    {
        System.out.println("Do you prefer a manual setup or an auto setup?");
        System.out.println("(M) Manual");
        System.out.println("(A) Auto");
        System.out.println("(Q) Quit");
        String configure = scan.nextLine();
        
        while(!configure.equalsIgnoreCase("A") && !configure.equalsIgnoreCase("M") && !configure.equalsIgnoreCase("Q"))
        {
            System.out.println("Please choose the correct options");
            configure = scan.nextLine();
        }

        if (configure.equalsIgnoreCase("A")) //if they choose auto, then auotSetUp turns true
        {
            autoSetup = true;
        }
        else if(configure.equalsIgnoreCase("M"))
        {
            autoSetup = false;
        }
        else //if they choose Q, it returns nothing, ending the game
        {
            return internal_players;
        }
        
        if (!autoSetup) 
        {
            System.out.print("Hello, what would be Player 1's name?(Q to quit game): "); //Q returns nothing, ending the game
            String name1 = scan.nextLine();

            if (name1.equalsIgnoreCase("Q")) 
            {
                return internal_players;
            } 
            else if (name1.isBlank()) 
            {
                while (name1.isBlank() || name1.equalsIgnoreCase("Q")) 
                {
                    System.out.println("Player 1's name is blank, please enter a name");
                    name1 = scan.nextLine();
                    if (name1.equalsIgnoreCase("Q")) 
                    {
                        return internal_players;
                    }
                }
            }

            System.out.print("What about Player 2's name?(Q to quit game): ");
            String name2 = scan.nextLine();

            if (name2.equalsIgnoreCase("Q")) 
            {
                return internal_players;
            } 
            else if (name2.equalsIgnoreCase(name1) || name2.isBlank()) 
            {
                while (name2.equalsIgnoreCase(name1) || name2.equalsIgnoreCase("Q") || name2.isBlank()) 
                {
                    System.out.print("Please use a different name from Player 1, and not blank(Q to quit): ");
                    name2 = scan.nextLine();
                    if (name2.equalsIgnoreCase("Q")) 
                    {
                        return internal_players;
                    }
                }

            }

            Player player1_internal = new Player(name1);
            Player player2_internal = new Player(name2);

            internal_players.add(player1_internal);
            internal_players.add(player2_internal);

            return internal_players;
        } 
        else //IF AUTOSETUP
        {
            internal_players.clear(); //clear anything previously if it does exist, just to be safe
            internal_players.addAll(configger.readConfigPlayers()); //read the config text by calling in the readConfigPlayers
            return internal_players;
        }
    }

    public String gunKeeper() //gunKeeper is in charge of handling gun choice to use during setup
    {
        if (!autoSetup) 
        {
            System.out.println("Please choose the gun below:");
            System.out.println("(1) Colt Python");
            System.out.println("(2) GP100");
            System.out.println("(3) Bounty Hunter");
            System.out.println("(Q) to quit the game");
            gunChoice = scan.nextLine();

            while (!gunChoice.equalsIgnoreCase("1") && !gunChoice.equalsIgnoreCase("2") && !gunChoice.equalsIgnoreCase("3") && !gunChoice.equalsIgnoreCase("Q")) 
            {
                System.out.println("Please choose the appropriate options");
                System.out.println("(1) Colt Python");
                System.out.println("(2) GP100");
                System.out.println("(3) Bounty Hunter");
                System.out.println("(Q) to quit the game");
                gunChoice = scan.nextLine();
            }

            return gunChoice;
        } 
        
        else //IF AUTOSETUP, use the gun from the config reader
        {
            gunChoice = configger.readConfigGun();
            return gunChoice;
        }
    }
}
