package com.mycompany.amrithkongv2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    public void storeRecentGameDB() //delete first before we start storing anything at all. Still storing stuff one by one since I don't know how to store stuff 2 things at a time
    {
        try (Statement smt = db.getConnection().createStatement()) 
        {
            smt.executeUpdate("DELETE FROM RecentGameTable"); // delete any data from the previous game, since all these methods are for reading stuff for previous game only
        } 
        catch (SQLException e) 
        {
            System.out.println("Failed to clear RecentGameTable"); //incase it doesn't work 
        }
        
        LocalDateTime currentDate = LocalDateTime.now();
        logRecentGame(player1.getName(), currentDate, player1.getAlive());
        logRecentGame(player2.getName(), currentDate, player2.getAlive());
    }
    
    public void logRecentGame(String name, LocalDateTime gameDate, boolean Status) 
    {
        
        String insertion = "INSERT INTO RecentGameTable (PlayerName, gameDate, Status) VALUES (?, ?, ?)";
        try(PreparedStatement ps = db.getConnection().prepareStatement(insertion); Statement smt = db.getConnection().createStatement();)
        {
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
    
    public StringBuilder readRecentGameDBToString() 
    {
        StringBuilder sb = new StringBuilder();
        String query = "SELECT * FROM RecentGameTable";

        try (Statement smt = db.getConnection().createStatement(); ResultSet rs = db.getConnection().prepareStatement(query).executeQuery()) 
        {
            sb.append("=== Recent Game Records ===\n");
            while (rs.next()) 
            {
                String playerName = rs.getString("PlayerName");
                Timestamp gameDate = rs.getTimestamp("gameDate");
                boolean status = rs.getBoolean("Status");

                sb.append("Player: ").append(playerName).append("\n");
                sb.append("Game Date: ").append(gameDate.toLocalDateTime()).append("\n");
                sb.append("Alive: ").append(status ? "Yes" : "No").append("\n");
                sb.append("-------------------------\n");
            }
        }
        catch (SQLException e) 
        {
            sb.append("Failed to read from RecentGameTable.\n");
            e.printStackTrace();
        }
        return sb; // pass this back to the statController/GUI
    }
    
}
