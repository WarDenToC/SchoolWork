
package com.mycompany.amrithkongv2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class History_DB_Reader 
{
    DataBaseConn db;
    Player player1;
    Player player2;
    HashMap<String, Integer> selfShot;
    HashMap<String, Integer> turnsSurvived;
    
    public History_DB_Reader(DataBaseConn db, Player player1, Player player2, HashMap<String, Integer> turnsSurvived, HashMap<String, Integer> selfShot)
    {
        this.db = db;
        this.player1 = player1;
        this.player2 = player2;
        this.turnsSurvived = turnsSurvived;
        this.selfShot = selfShot;
    }
    
    public void StoreHistoryDB()
    {
        LocalDateTime currentDate = LocalDateTime.now();
        
        logHistory(player1.getName(), currentDate, turnsSurvived.get(player1), selfShot.get(player1), player1.getAlive());
        logHistory(player2.getName(), currentDate, turnsSurvived.get(player2), selfShot.get(player2), player2.getAlive());
    }
    
    
    public void logHistory(String name, LocalDateTime gameDate, int TurnsSurvived, int SelfShot, boolean Status ) 
    {
        String insertion = "INSERT INTO HistoricalTable (PlayerName, gameDate, SelfShot, TurnsSurvived, Status) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement ps = db.getConnection().prepareStatement(insertion))
        {
            ps.setString(1, name);
            ps.setTimestamp(2, Timestamp.valueOf(gameDate));
            ps.setInt(3, SelfShot);
            ps.setInt(4, TurnsSurvived);
            ps.setBoolean(5, Status);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Something went wrong with the LogHistory, check it out"); 
        }
        
    }
    
    
}
