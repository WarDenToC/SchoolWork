package com.mycompany.amrithkongv2;
import java.awt.BorderLayout;
import java.util.*;

public class Game 
{
    private Logger logger;
    boolean forfeit = false;
    boolean properSetUp = true;
    RevolverTemplate loadedGun;
    TurnTaker turn = new TurnTaker();
    DecisionMaker input = new DecisionMaker();
    ArrayList<Player> players =  new ArrayList<>();
    Revolver_Manufacturer gunsmith = new Revolver_Manufacturer();
    Gun_Player_setup setups = new Gun_Player_setup();
    private StatTracker stats; 
    private Action_Handler actionhandler;
    
    
    public Game(Logger log)
    {
        this.logger = log;
    }
    
    public void start() //most important method of the game since it will call other methods to do their own work 
    {
        System.out.println("Hello this is Russian Roulette, we are setting up the game for you....\n\n");
        setUpPlayers(); // calls this method, it handles the player setup like names
        if(properSetUp) // if no one presses quit or Q, then we will start our revolver setup
            setUpRevolver(); //sets up the revolver
        actionhandler = new Action_Handler(stats); //was having problem with instances and objects not being passed so ChatGPT suggested I move it here, it was at the top
        
        if(properSetUp) //if this boolean is still true, core gameplay will begin 
        {
            game();
            endGame();
        }
        else
        {
            System.out.println("Game has been cancelled.");
        }
    }
    
    public void setUpPlayers() //this method will handle the creation of the players, clear leftover and setup new ones, once done it will print out the created players 
    {
        players.clear();
        players.addAll(setups.createPlayers()); //handles the players and GUN at the same time, but the gun part will be used below
        if(!players.isEmpty())
        {
            System.out.println(players.get(0).getName() + " has entered the game");
            System.out.println(players.get(1).getName() + " has entered the game\n");
            stats = new StatTracker(logger, players); //pass in the logger instance and players ArrayList we can start tracking who's dead
            stats.setupStats();
        }
        else
        {
            properSetUp = false;
        }
    }
    
    public void setUpRevolver() //ask for gun type and set up the gun type with the bullets already loaded into the gun 
    {
        String choice = setups.gunKeeper();// we got this from the createPlayer method from Gun_Player_setup Class
        
        if(choice.equalsIgnoreCase("Q"))
        {
            properSetUp = false;
        }
        else
        {
            loadedGun = gunsmith.gunmaker(choice); //our gun instance will be passed to the Revolver_Manufacturer
            stats.setGun(loadedGun); //send this gun to the StatTracker so we can note the name of said gun
        }
        
        //System.out.println(loadedGun.chamberedBullet+"\n"); //Uncomment this to know where the bullets are in the chamber
    }
    
    public void game() //the loop of the game where player will enter the choice when it's their turn and 
    {
        System.out.println("Tossing coin....");
        turn.setPlayers(players); // sets which players goes first 
        while( (players.get(0).getAlive() && players.get(1).getAlive()) && !forfeit) //game is still going as long as 2 players are alive and none has quit
        {
            Player currentPlayer = turn.getCurrentPlayer();
            Player otherPlayer = turn.getOtherPlayer();
            String playerchoice = input.getMenuChoice(currentPlayer, loadedGun); //prompt the current player for their choice
            if(playerchoice.equalsIgnoreCase("Q"))
            {
                forfeit = true;
                break;   
            }
            actionhandler.handle_action(currentPlayer, otherPlayer, loadedGun, playerchoice.toUpperCase()); //pass the choice, current and other player, and the gun 
            turn.getNextPlayer();
        }
    }
    
    public void endGame() // when the game end we will call these 3 stuff
    {   
        stats.PrintStats(); // just logs
        stats.PrintPreviousGame(); //logs and read
        stats.printPreviousPlayer(); //logs and read
    }
}
