
package com.mycompany.amrithkongv2;
import java.io.*;
import java.util.*;

public class LogReader 
{
    Logger log = new Logger();
    String filepath = "./recentGameLog.txt";
    String filepath2 = "./nameList.txt";
    
    public void readPreviousGame() // just reads the previous game in recentGameLog
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath)))
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                System.out.println(line);
            }
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong with the previous game");
        }
        
    }
    
    public void readParticipatedPlayer(ArrayList<Player> players, boolean read) // so this is a b
    {
        //Start of chat code since I did not know how to check and write a file if it does not exist, usually filewriter handles that part but I am using it somewhat concurrently as well
        try 
        {
            File file = new File(filepath2);
            if (!file.exists()) 
            {
                try 
                {
                    if (file.createNewFile()) 
                    {
                        System.out.println("File created: " + filepath2);
                    } else 
                    {
                        System.out.println("Failed to create file.");
                        return;
                    }
                } 
                catch (IOException e) 
                {
                    System.out.println("Error creating file: " + e.getMessage());
                    return;
                }
            }
            //end of chat code 

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) //this will read our text nameList, if the names never existed, then we add it 
            {
                ArrayList<String> names = new ArrayList<>(); //creates an arrayList of names
                String line2;
                while ((line2 = reader.readLine()) != null) 
                {
                    names.add(line2);
                }
                if (!names.contains(players.get(0).getName())) //if the arrayList does not contain our players name then we add it or log in 
                {
                    log.LogPlayersName(players.get(0));
                }
                if (!names.contains(players.get(1).getName())) 
                {
                    log.LogPlayersName(players.get(1));
                }

                if(read)// if they want to read we will call our method below 
                {
                    Readnames();
                }
                else
                {
                    System.out.println("Names Logged");
                }
                
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Something went wrong with reading the names: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
    public void Readnames()
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filepath2)))
        {
            String line3;
            System.out.print("{");
            while ((line3 = reader.readLine()) != null) 
            {
                System.out.print(line3 + " ");
            }
            System.out.print("}");
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong with reading the names2");
        }
        
    }
}
