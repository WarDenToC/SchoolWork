package com.mycompany.amrithkongv2;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.*;

public class Game 
{
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
    
    
    public Game(Logger log)
    {
        this.logger = log;
    }
    
    public void setUpPlayers(ArrayList<Player> players) 
    {
        this.players = players;
    }

    public void setUpRevolver(RevolverTemplate gun) 
    {
        this.loadedGun = gun;
        // now that we have a gun, we can safely initialize dependent objects
        this.stats = new StatTracker(logger, players, loadedGun, db);
        this.stats.setupStats();
        this.actionhandler = new Action_Handler(loadedGun, stats);
    }
    
    public Action_Handler getActionHandler() 
    {
        return actionhandler;
    }
    
    public void endGame() // when the game end we will call these 3 stuff
    {
        if (stats != null) 
        {
            stats.initDB();
            //stats.PrintStats(); // just logs
            //stats.PrintPreviousGame(); //logs and read
        }
    
    }
}
