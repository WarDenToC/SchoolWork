
package com.mycompany.amrithkongv2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Player_DB_RW 
{
    String nameOfP1;
    String nameOfP2;
    ArrayList<String> nameList = new ArrayList<>();
    DataBaseConn db;
    
    
    Player_DB_RW(DataBaseConn db, String nameOfP1, String nameOfP2)
    {
        this.db = db;
        this.nameOfP1 = nameOfP1;
        this.nameOfP2 = nameOfP2;
    }
    
    public void storePlayersDB()
    {
        addNewPlayers(nameOfP1);
        addNewPlayers(nameOfP2);
    }
    
    public void addNewPlayers(String name) //method for memorial table
    {
        String insertion = "INSERT INTO MemorialTable (PlayerName) VALUES (?)";
        try(PreparedStatement ps = db.getConnection().prepareStatement(insertion))
            
        {   
            ps.setString(1, name);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            if ("23505".equals(e.getSQLState())) //handles duplicate keys in the exception 
            {
                System.out.println("Player already exists: " + name);
            } 
        }
    }
    
    public List<String> getAllPlayers()
    {
        String retrieveQuery = "Select PlayerName FROM MemorialTable";
        try(ResultSet rs = db.getConnection().prepareStatement(retrieveQuery).executeQuery())
        {
            while(rs.next())
            {
                nameList.add(rs.getString("PlayerName"));
            }
        }
        catch(SQLException e)
        {
            System.out.println("Something wrong with the DB getPlayers");
        }   
        return nameList;
    }
    
}
