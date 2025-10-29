
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
        
        if(player1 != null && player2 != null)
        {
            logHistory(player1.getName(), currentDate, turnsSurvived.get(player1.getName()), selfShot.get(player1.getName()), player1.getAlive());
            logHistory(player2.getName(), currentDate, turnsSurvived.get(player2.getName()), selfShot.get(player2.getName()), player2.getAlive());
        }
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
    
    public StringBuilder readFullHistoryDB() 
    {
        StringBuilder sb = new StringBuilder();
        String query = "SELECT * FROM HistoricalTable ORDER BY gameDate DESC"; // newest first

        try (Statement smt = db.getConnection().createStatement();
             ResultSet rs = db.getConnection().prepareStatement(query).executeQuery()) 
        {
            sb.append("=== Full Game History ===\n");

            while (rs.next()) 
            {
                String playerName = rs.getString("PlayerName");
                Timestamp gameDate = rs.getTimestamp("gameDate");
                int selfShotCount = rs.getInt("SelfShot");
                int turns = rs.getInt("TurnsSurvived");
                boolean status = rs.getBoolean("Status");

                sb.append("Player: ").append(playerName).append("\n");
                sb.append("Game Date: ").append(gameDate.toLocalDateTime()).append("\n");
                sb.append("Self Shot: ").append(selfShotCount).append("\n");
                sb.append("Turns Survived: ").append(turns).append("\n");
                sb.append("Alive: ").append(status ? "Yes" : "No").append("\n");
                sb.append("-------------------------\n");
            }

        } 
        catch (SQLException e) 
        {
            sb.append("Failed to read from HistoricalTable.\n");
            e.printStackTrace();
        }

        return sb;
    }
    
    
}
