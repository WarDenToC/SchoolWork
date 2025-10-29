
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
        this.game = game;
        this.gun = gun;
        this.playerList = playerList;
        
        this.gameView = new GameGUI_MVC();
        this.gameView.addGameListener(this);
        gameView.setVisible(true);
        
        game.turn.setPlayers(playerList);
        updateTurnDisplay();
    }
    
    private void updateTurnDisplay() {
        Player currentPlayer = game.turn.getCurrentPlayer();
        gameView.setPlayerTurn(currentPlayer.getName());

        
        gameView.setShootOtherVisible(currentPlayer.getSelfShot() >= 3);
        gameView.setPeekVisible(currentPlayer.getSelfShot() >= 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object src = e.getSource();
        Player current = game.turn.getCurrentPlayer();
        Player other = game.turn.getOtherPlayer();
        DecisionMaker choice = new DecisionMaker();
        String currentPlayerChoice = choice.getMenuChoice(src, gameView);
        
        if (currentPlayerChoice.equals("F")) {
            game.forfeit = true;
            JOptionPane.showMessageDialog(gameView, current.getName() + " has forfeited!");
            endGame(current, other);
            return;
        }
        

        
        String result = game.getActionHandler().handle_action(current, other, currentPlayerChoice);
        gameView.setRevolverStatus(result);

        if (!current.getAlive() || !other.getAlive()) 
        {
            Player loser = !current.getAlive() ? current : other;
            Player winner = current == loser ? other : current;
            endGame(loser, winner);
            return;
        }
        
        game.turn.getNextPlayer();
        updateTurnDisplay();
    }
    
    private void endGame(Player loser, Player winner) 
    {
        game.endGame();
        JOptionPane.showMessageDialog
        (
                gameView,
                "Game Over!\nWinner: " + (winner.getAlive() ? winner.getName() : "None")
                + "\nLoser: " + loser.getName(),
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
        );

        gameView.dispose();
        // Optionally, go back to menu
        MenuGUI_MVC menu = new MenuGUI_MVC();
        new MenuController_MVC(menu);
        menu.setVisible(true);
    }
}
