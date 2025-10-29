
package com.mycompany.amrithkongv2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JOptionPane;

public class GameController_MVC implements ActionListener
{
    public Game game;
    public ArrayList<Player> playerList;
    public RevolverTemplate gun;
    private GameGUI_MVC gameView;
    
    public GameController_MVC(Game game, RevolverTemplate gun, ArrayList<Player> playerList)
    {
        //all of these are from the setup controller 
        this.game = game;
        this.gun = gun;
        this.playerList = playerList;
        
        this.gameView = new GameGUI_MVC();
        this.gameView.addGameListener(this);
        gameView.setVisible(true);
        
        game.turn.setPlayers(playerList);
        updateTurnDisplay();
    }
    
    private void updateTurnDisplay() 
    {
        Player currentPlayer = game.turn.getCurrentPlayer(); //reusing my old turn taker class for switching turn
        
        gameView.setPlayerTurn(currentPlayer.getName());    //update the current player turns
        
        //make these shoot other and peek button visible once the players tries to kill themselves 2 or 3 time.
        gameView.setShootOtherVisible(currentPlayer.getSelfShot() >= 3);
        gameView.setPeekVisible(currentPlayer.getSelfShot() >= 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //Withint this game loop, the quit is forfeiting and restart is basically respinning the gun since you are resetting the index of the hammer onto the chamber 
        Object src = e.getSource();
        Player current = game.turn.getCurrentPlayer();
        Player other = game.turn.getOtherPlayer();
        DecisionMaker choice = new DecisionMaker();
        String currentPlayerChoice = choice.getMenuChoice(src, gameView);
        
        if (currentPlayerChoice.equals("F")) //if any player choose to quit/forfeit, the game will end
        {
            game.forfeit = true;
            JOptionPane.showMessageDialog(gameView, current.getName() + " has forfeited!");
            endGame(current, other);
            return;
        }
        
        String result = game.getActionHandler().handle_action(current, other, currentPlayerChoice);
        gameView.setRevolverStatus(result);

        if (!current.getAlive() || !other.getAlive()) // if any player dies, game will end
        {
            Player loser = !current.getAlive() ? current : other;
            Player winner = current == loser ? other : current;
            endGame(loser, winner);
            return;
        }
        
        // if no one dies, then just continues
        game.turn.getNextPlayer();
        updateTurnDisplay();
    }
    
    private void endGame(Player loser, Player winner) 
    {
        game.endGame(); //call the endGame from game class to start logging all the details to the DB
        JOptionPane.showMessageDialog
        (
                gameView,
                "Game Over!\nWinner: " + (winner.getAlive() ? winner.getName() : "None")
                + "\nLoser: " + loser.getName(),
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
        );

        gameView.dispose();
        // Go back to the main menu, you can check the stats from the stat menu
        MenuGUI_MVC menu = new MenuGUI_MVC();
        new MenuController_MVC(menu);
        menu.setVisible(true);
    }
}
