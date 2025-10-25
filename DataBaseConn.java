package com.mycompany.amrithkongv2;

/*
 * The programs are designed for PDC paper
 */


import java.sql.*;


public final class  DataBaseConn{


    private static final String USER_NAME = ""; //your DB username
    private static final String PASSWORD = ""; //your DB password
    private static final String URL = "jdbc:derby:RussianRDB;create=true";  //url of the DB host

    Connection conn;

    public DataBaseConn() 
    {
        establishConnection();
    }

    public static void main(String[] args) 
    {
        DataBaseConn dbManager = new DataBaseConn();
        System.out.println(dbManager.getConnection());
    }

    public Connection getConnection() 
    {
        return this.conn;
    }

    //Establish connection
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
    }

}
