package com.mycompany.amrithkongv2;

import java.sql.*;
import java.time.LocalDateTime;


public final class  DataBaseConn
{


    private static final String USER_NAME = ""; //your DB username
    private static final String PASSWORD = ""; //your DB password
    private static final String URL = "jdbc:derby:RussianRDB;create=true";  //url of the DB host

    Connection conn;

    public DataBaseConn() 
    {
        //establishConnection();
        createMemorialTable();
        createHistoryTable();
        createRecentGameTable();
    }

    public Connection getConnection() 
    {
        return this.conn;
    }

    //Establish connection
    /*
    public void establishConnection() 
    {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnections() 
    {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }*/
    
    
    ///////////////
    public void createMemorialTable() // Creates a table that logs new players, does not update existing player or will add old players
    {
        try(Connection conn = DriverManager.getConnection(URL);Statement smt = conn.createStatement())
        {
            smt.executeUpdate("CREATE TABLE MemorialTable (PlayerName VARCHAR(50) PRIMARY KEY)");
        }
        catch(SQLException e)
        {
            System.out.println("Something wrong with the memorial table");
        }
    }
    
    public void addNewPlayers(String name) //method for memorial table
    {
        String insertion = "INSERT INTO MemorialTable (PlayerName) VALUES (?)";
        try(Connection conn = DriverManager.getConnection(URL); PreparedStatement ps = conn.prepareStatement(insertion))
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
    //////////////////
    
    
    /////////
    public void createHistoryTable()
    {
        try(Connection conn = DriverManager.getConnection(URL);Statement smt = conn.createStatement())
        {
            smt.executeUpdate("""
                CREATE TABLE HistoricalTable (
                              PlayerName VARCHAR(50) PRIMARY KEY,
                              GameDate TIMESTAMP,
                              SelfShot INT,
                              TurnsSurvived INT
                                            )
                              """);
        }
        catch(SQLException e)
        {
            System.out.println("Something wrong with the Historical Table");
        }
        
    }
    
    public void logHistory(String name, LocalDateTime gameDate, int SelfShot, int TurnsSurvived) //method for memorial table
    {
        String insertion = "INSERT INTO HistoricalTable (PlayerName, gameDate, SelfShot, TurnsSurvived) VALUES (?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(URL); PreparedStatement ps = conn.prepareStatement(insertion))
        {
            ps.setString(1, name);
            ps.setTimestamp(2, Timestamp.valueOf(gameDate));
            ps.setInt(3, SelfShot);
            ps.setInt(4, TurnsSurvived);
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
        try(Connection conn = DriverManager.getConnection(URL);Statement smt = conn.createStatement())
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
    
    public void logRecentGame(String name, LocalDateTime gameDate, boolean survived) 
    {
        String rewrite = "DELETE FROM RecentGameTable";
        String insertion = "INSERT INTO RecentGameTable (PlayerName, gameDate, survived) VALUES (?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(URL); PreparedStatement ps = conn.prepareStatement(insertion); Statement smt = conn.createStatement();)
        {
            smt.executeUpdate(rewrite); //rewrite the whole table so we can only include the previous game
            ps.setString(1, name);
            ps.setTimestamp(2, Timestamp.valueOf(gameDate));
            ps.setBoolean(3, survived);
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Something went wrong with the LogHistory, check it out"); 
        }
        
    }
    ///////////
    
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
    }

}
