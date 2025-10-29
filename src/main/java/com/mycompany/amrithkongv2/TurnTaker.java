package com.mycompany.amrithkongv2;
import java.util.*;
public class TurnTaker 
{
    ArrayList<Player> list_of_players = new ArrayList<>();
    int currentTurn;
    Player otherPlayer;

    Random random = new Random();
    
    public void setPlayers(List<Player> players)
    {
        list_of_players.clear();
        list_of_players.addAll(players);
        currentTurn = random.nextInt(2); //in charge of who goes first 
    }
    
    public void getNextPlayer() // this is called at the end of while loop so that we set the current player 
    {
        currentTurn = (currentTurn + 1) % 2; 
    }
    
    public Player getCurrentPlayer() //get current player
    {
        return list_of_players.get(currentTurn);
    }
    public Player getOtherPlayer() // get the other player of the current player, useful for when we're shooting the other player
    {
        
        if(currentTurn == 0)
        {
            otherPlayer = list_of_players.get(1);
        }
        else if (currentTurn == 1)
        {
            otherPlayer = list_of_players.get(0);
        }
        
        return otherPlayer;
    }
    
}