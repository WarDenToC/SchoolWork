package com.mycompany.amrithkongv2;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StatTracker 
{
    //Heavily refactored
    HashMap<String, Integer> selfShot = new HashMap<String, Integer>();
    HashMap<String, Integer> turnsSurvived = new HashMap<String, Integer>();
    RevolverTemplate gun;
    private Logger statLogger;
    ArrayList<Player> players;
    Scanner scanner = new Scanner(System.in);
    LogReader reader = new LogReader();
    private DataBaseConn db;
    RevolverTemplate loadedGun;
    
    public StatTracker(Logger log, ArrayList<Player> players, RevolverTemplate loadedGun,DataBaseConn db)
    {
        this.statLogger = log;
        this.players = players;
        this.loadedGun = loadedGun;
        this.db = db;
    }
    
    public void setupStats() // This sets up the: key would be the names, the value would be the amount of turns survived and shooting self. Using hashmap 
    {
        selfShot.put(players.get(0).getName(), 0);
        selfShot.put(players.get(1).getName(), 0);
        turnsSurvived.put(players.get(0).getName(), 0);
        turnsSurvived.put(players.get(1).getName(), 0);
    }
    
    public void setGun(RevolverTemplate gun) // it was hard for me to make a constructor for this. So I just the setter method instead since it was easier
    {
        this.gun = gun;
    }
    
    public void incrementSelfShot(String playerName)
    {
        selfShot.put(playerName, selfShot.getOrDefault(playerName, 0) + 1); // chatGpt helped me with the logic since I didn't know how to update an existed key with value
    }
    
    public void incrementTurnsSurvived(String playerName) //plus one whenever a player survives the turn 
    {
        turnsSurvived.put(playerName, turnsSurvived.getOrDefault(playerName, 0) + 1); 
    }
    
    public HashMap getSelfShots() //returns whatever is in the HashMap of self-shooting
    {
        return selfShot;
    }
    
    public HashMap getTurnsSurvived() //returns whatever is in the HashMap of turnsSurvived 
    {
        return turnsSurvived;
    }
    
    
    public void initDB() //Writes the data to the database with the results and other stuff, 3 other classes will be made to read the result instead from the DB since it seperates logic
    {
        LocalDateTime currentDate = LocalDateTime.now();
        String name1 = players.get(0).getName();
        String name2 = players.get(1).getName();
        
        
        //Logging to Historical Table  
        History_DB_Reader HDB = new History_DB_Reader(db, players.get(0), players.get(1), turnsSurvived, selfShot);
        HDB.StoreHistoryDB();
        
        //Logging to RecentGame
        RecentGame_DB_RW RGDB = new RecentGame_DB_RW(db, players.get(0), players.get(1));
        RGDB.storeRecentGameDB();
        
        //Loggin to memorial
        Player_DB_RW PDB = new Player_DB_RW(db, name1, name2);
        PDB.storePlayersDB();
    }
    
    
    /*
    public void PrintStats() 
    {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = currentDate.format(formattedDate);
        
        StringBuilder sb = new StringBuilder(); // for this I was using println to print it, but then I had to re-code this for both printing and logging so I asked chat to rewrite this, manual work

        
        sb.append("\n=======Endgame=======\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("=======Players=======\n");
        sb.append("Player 1: ").append(players.get(0).getName()).append("\n");
        sb.append("Player 2: ").append(players.get(1).getName()).append("\n");
        sb.append("=======Outcome=======\n");
        if(!players.get(0).getAlive()) 
        {
            sb.append("Winner: ").append(players.get(1).getName()).append("\n");
            sb.append("Loser: ").append(players.get(0).getName()).append("\n");
    
        } 
        else 
        {
            sb.append("Winner: ").append(players.get(0).getName()).append("\n");
            sb.append("Loser: ").append(players.get(1).getName()).append("\n");
        }

        sb.append("=============Technical Stats=============\n");
        sb.append("Amount of time each player shot themselves:\n").append(selfShot).append("\n");
        sb.append("Amount of time each player survives a turn:\n").append(turnsSurvived).append("\n");
        sb.append("Gun chosen: ").append(gun.gunModel).append("\n");

        
    
        System.out.println(sb.toString()); // print the string builder

        // Log to file
        if(statLogger != null) // Since I was passing our logger through multiple classes, I had to use this to test for null. 
            //Basically, hides the null error, if the files has been written that mean it works, if it's empty that means this method was never aclled
        {
            statLogger.LogResult(sb.toString());
        }
    }
    
    public void PrintPreviousGame() // same as above but I am cutting out the technical part 
    {
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = currentDate.format(formattedDate);
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("=======Endgame=======\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("=======Players=======\n");
        sb.append("Player 1: ").append(players.get(0).getName()).append("\n");
        sb.append("Player 2: ").append(players.get(1).getName()).append("\n");
        sb.append("=======Outcome=======\n");
        if(!players.get(0).getAlive()) 
        {
            sb.append("Winner: ").append(players.get(1).getName()).append("\n");
            sb.append("Loser: ").append(players.get(0).getName()).append("\n");
    
        } 
        else 
        {
            sb.append("Winner: ").append(players.get(0).getName()).append("\n");
            sb.append("Loser: ").append(players.get(1).getName()).append("\n");
        }
        
    }*/
    
}
