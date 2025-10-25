package com.mycompany.amrithkongv2;
import java.io.*;
import java.util.*;

public class Logger 
{
    public void LogResult(String result) // handles the logging history of all games being played, no reading, you have to manually read the text file 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./log.txt", true)))
        {
            System.out.println("Writing log to: " + new File("log.txt").getAbsolutePath());
            bw.write(result);
            bw.newLine();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void LogRecentGame(String result) //logs the previous game, will overwrite current one. Get the string from StatTracker
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./recentGameLog.txt")))
        {
            System.out.println("Writing log to: " + new File("recentGameLog.txt").getAbsolutePath());
            bw.write(result);
            bw.newLine();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void LogPlayersName(Player players) //we will get the player from LogReader, writes one by one
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./nameList.txt", true)))
        {
            System.out.println("Writing log to: " + new File("nameList.txt").getAbsolutePath());
            bw.write(players.getName());
            bw.newLine();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
}


