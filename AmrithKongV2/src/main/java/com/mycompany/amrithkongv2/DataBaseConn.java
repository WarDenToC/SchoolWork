package com.mycompany.amrithkongv2;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class  DataBaseConn
{
    private static final String URL = "jdbc:derby:RussianRDB;create=true";  //url of the DB host

    private static DataBaseConn instance; // single instance
    public Connection conn; // shared connection

    public DataBaseConn() 
    {
        try 
        {
            conn = DriverManager.getConnection(URL);
            //establishConnection();
            createMemorialTable();
            createHistoryTable();
            createRecentGameTable();
        } 
        catch (SQLException ex) 
        {
            System.out.println("Did not setup right");
        }
        
    }
    
    public static synchronized DataBaseConn getInstance() 
    {
        if (instance == null) 
        {
            instance = new DataBaseConn();
        }
        return instance;
    }

    public Connection getConnection() 
    {
        return this.conn;
    }
    
    
    ///////////////
    public void createMemorialTable() // Creates a table that logs new players, does not update existing player or will add old players
    {
        try(Statement smt = conn.createStatement())
        {
            smt.executeUpdate("CREATE TABLE MemorialTable (PlayerName VARCHAR(50) PRIMARY KEY)");
        }
        catch(SQLException e)
        {
            System.out.println("Something wrong with the memorial table");
        }
    }
    
    //////////////////

    public void createHistoryTable()
    {
        try(Statement smt = conn.createStatement())
        {
            smt.executeUpdate("""
                CREATE TABLE HistoricalTable (
                              PlayerName VARCHAR(50) PRIMARY KEY,
                              GameDate TIMESTAMP,
                              SelfShot INT,
                              TurnsSurvived INT,
                              Status BOOLEAN
                                            )
                              """);
        }
        catch(SQLException e)
        {
            System.out.println("Something wrong with the Historical Table");
        }
        
    }
    
    public void logHistory(String name, LocalDateTime gameDate, int SelfShot, int TurnsSurvived, boolean Status) //method for memorial table
    {
        String insertion = "INSERT INTO HistoricalTable (PlayerName, gameDate, SelfShot, TurnsSurvived, Status) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(insertion))
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
    
    
    ///////////
    
    public void createRecentGameTable()
    {
        try(Statement smt = conn.createStatement())
        {
            smt.executeUpdate("""
                CREATE TABLE RecentGameTable (
                              PlayerName VARCHAR(50) PRIMARY KEY,
                              GameDate TIMESTAMP,
                              Status BOOLEAN
                                            )
                              """);
        }
        catch(SQLException e)
        {
            System.out.println("Something wrong with the RecentGameTable");
        }
    }
    
    
    
    
    ///////////
    
    /*
    public void closeConnections() 
    {
        if (conn != null) 
        {
            try 
            {
                conn.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
    }*/

}
