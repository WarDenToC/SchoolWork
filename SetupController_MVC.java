
package com.mycompany.amrithkongv2;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class SetupController_MVC implements ActionListener
{
    private SetupGUI_MVC setupView;
    String player1 = "";
    String player2 = "";
    String gunChosen = "";
    ArrayList<Player> playerList = new ArrayList<>();
    RevolverTemplate gun = null;
    
    public SetupController_MVC(SetupGUI_MVC setupView)
    {
        this.setupView = setupView;
        setupView.addSetupListener(this);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        
        if (src == setupView.getStartButton()) 
        {
            player1 = setupView.getPlayer1Name();
            player2 = setupView.getPlayer2Name();
            gunChosen = setupView.getSelectedGun().trim();

            if (gunChosen != null && !player1.isEmpty() && !player2.isEmpty() && !(player1.equalsIgnoreCase(player2))) 
            {
                Gun_Player_setup setter = new Gun_Player_setup(gunChosen, player1, player2);
                playerList.addAll(setter.createPlayers());
                gun = setter.gunKeeper();
                
                assert gun != null : "Gun is null — something went wrong in setup!";

                
                Logger log = new Logger();
                Game game = new Game(log);
                game.setUpPlayers(playerList);
                game.setUpRevolver(gun);   
                setupView.dispose();
                GameController_MVC control = new GameController_MVC(game, gun, playerList);
            }
            else 
            {
                JOptionPane.showMessageDialog(setupView, "Please make sure all fields are filled and there is no duplicate names.", "Selection Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            System.out.println("Starting game: " + player1 + " vs " + player2 + " using " + gunChosen);
        }
        
        else if (src == setupView.getBackButton()) 
        {
            setupView.dispose();
            MenuGUI_MVC menu = new MenuGUI_MVC();
            new MenuController_MVC(menu);
            menu.setVisible(true);
        }
          
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            SetupGUI_MVC setup = new SetupGUI_MVC();
            new SetupController_MVC(setup);
            setup.setVisible(true);
        });
    }


    
    
}
