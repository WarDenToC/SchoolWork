package com.mycompany.amrithkongv2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class RecentGame_DB_RW 
{
    Player player1;
    Player player2;
    DataBaseConn db;
    
    public RecentGame_DB_RW(DataBaseConn db, Player player1, Player player2)
    {
        this.db = db;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void storeRecentGameDB()
    {
        LocalDateTime currentDate = LocalDateTime.now();
        logRecentGame(player1.getName(), currentDate, player1.getAlive());
        logRecentGame(player2.getName(), currentDate, player2.getAlive());
    }
    
    public void logRecentGame(String name, LocalDateTime gameDate, boolean Status) 
    {
        String rewrite = "DELETE FROM RecentGameTable";
        String insertion = "INSERT INTO RecentGameTable (PlayerName, gameDate, Status) VALUES (?, ?, ?)";
        try(PreparedStatement ps = db.getConnection().prepareStatement(insertion); Statement smt = db.getConnection().createStatement();)
        {
            smt.executeUpdate(rewrite); //rewrite the whole table so we can only include the previous game
            ps.setString(1, name);
            ps.setTimestamp(2, Timestamp.valueOf(gameDate));
            ps.setBoolean(3, Status);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Something went wrong with the LogHistory, check it out"); 
        }
        
    }
    
}
