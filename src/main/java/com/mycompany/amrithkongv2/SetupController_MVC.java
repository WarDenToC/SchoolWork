
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
            //get the gun, and players 
            player1 = setupView.getPlayer1Name(); 
            player2 = setupView.getPlayer2Name();
            gunChosen = setupView.getSelectedGun().trim();

            if (gunChosen != null && !player1.isEmpty() && !player2.isEmpty() && !(player1.equalsIgnoreCase(player2))) //need to make sure they aren't null since I had a lot of problems at this part
            {
                Gun_Player_setup setter = new Gun_Player_setup(gunChosen, player1, player2); // pass this to the class that helps us set stuff up 
                playerList.addAll(setter.createPlayers()); //use the method from setter to get an ArrayList of players
                gun = setter.gunKeeper(); // the gunKeeper() methods help us make a completed gun ready for gaming
                
                assert gun != null : "Gun is null — something went wrong in setup!";

                
                Logger log = new Logger(); // not really needed but adding it since it you can still read from the txt
                Game game = new Game(log);
                
                game.setUpPlayers(playerList); // passing this list to to game class
                game.setUpRevolver(gun);   // passing this gun to to game class
                
                setupView.dispose();
                GameController_MVC control = new GameController_MVC(game, gun, playerList); //passing this ot the set up controller
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
    
    /*
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            SetupGUI_MVC setup = new SetupGUI_MVC();
            new SetupController_MVC(setup);
            setup.setVisible(true);
        });
    }*/



    
    
}
