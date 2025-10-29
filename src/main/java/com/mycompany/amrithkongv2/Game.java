package com.mycompany.amrithkongv2;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.*;

public class Game 
{
    //this entire class, I had to get rid of a lot of code since we're moving to GUI.
    
    private Logger logger;
    boolean forfeit = false;
    boolean properSetUp = true;
    RevolverTemplate loadedGun;
    TurnTaker turn = new TurnTaker();
    ArrayList<Player> players =  new ArrayList<>();
    DataBaseConn db = DataBaseConn.getInstance();
    Connection conn = db.getConnection();
    public StatTracker stats;
    public Action_Handler actionhandler;
    
    
    public Game(Logger log) //not really needed, but if player want to read txt file instead
    {
        this.logger = log;
    }
    
    public void setUpPlayers(ArrayList<Player> players) //getting this from the setup controller 
    {
        this.players = players;
    }

    public void setUpRevolver(RevolverTemplate gun) //getting this from setup controller, had to create instance within this class since my stats and actionhandler rely on each other very closely
    {
        this.loadedGun = gun;
        this.stats = new StatTracker(logger, players, loadedGun, db);
        this.stats.setupStats(); // set up the players and guns first before doing anything else. Can be found in stat tracker class
        this.actionhandler = new Action_Handler(loadedGun, stats);
    }
    
    public Action_Handler getActionHandler() 
    {
        //for our game loop
        return actionhandler;
    }
    
    public void endGame() // when the game end we will call these 3 stuff
    {
        if (stats != null) 
        {
            stats.initDB();
            //stats.PrintStats(); // just logs, no longer used since we're moving to GUI
            //stats.PrintPreviousGame(); //logs and read, same as above
        }
    
    }
}
