package com.mycompany.amrithkongv2;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DataBaseConn {

    private static final String URL = "jdbc:derby:RussianRDB;create=true";  //url of the DB host

    private static DataBaseConn instance; // single instance
    private Connection conn; // shared connection

    public DataBaseConn() {
        try 
        {
            conn = DriverManager.getConnection(URL);
            //establishConnection();
            createMemorialTable();
            createHistoryTable();
            createRecentGameTable();
        } 
        catch (SQLException ex) {
            System.out.println("DB setup error: " + ex.getMessage() + " / SQLState: " + ex.getSQLState());
        }

    }

    public static synchronized DataBaseConn getInstance() 
    {
        if (instance == null) {
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
        try (Statement smt = conn.createStatement()) 
        {
            smt.executeUpdate("CREATE TABLE MemorialTable (PlayerName VARCHAR(50) PRIMARY KEY)");
        } 
        catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) {
                System.out.println("Error creating MemorialTable: " + e.getMessage());
            }
        }
    }

    //////////////////
    public void createHistoryTable() 
    {
        try (Statement smt = conn.createStatement()) 
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
        catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) {
                System.out.println("Error creating MemorialTable: " + e.getMessage());
            }
        }

    }

    ///////////
    public void createRecentGameTable() 
    {
        try (Statement smt = conn.createStatement()) 
        {
            smt.executeUpdate("""
                CREATE TABLE RecentGameTable (
                              PlayerName VARCHAR(50) PRIMARY KEY,
                              GameDate TIMESTAMP,
                              Status BOOLEAN
                                            )
                              """);
        } 
        catch (SQLException e) 
        {
            if (!"X0Y32".equals(e.getSQLState())) {
                System.out.println("Error creating MemorialTable: " + e.getMessage());
            }
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
