package com.mycompany.amrithkongv2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController_MVC implements ActionListener 
{
    private MenuGUI_MVC menuView;

    public MenuController_MVC(MenuGUI_MVC menuView) 
    {
        this.menuView = menuView;
        menuView.addMenuListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object src = e.getSource();

        if (src == menuView.getStartButton()) 
        {
            System.out.println("Start Game clicked");
            // TODO: open SetupGUI once it's ready
            SetupGUI_MVC setupGUI = new SetupGUI_MVC();
            new SetupController_MVC(setupGUI);
            setupGUI.setVisible(true);
            menuView.dispose();
        }
        else if (src == menuView.getStatsButton()) 
        {
            System.out.println("Stats clicked");
            // TODO: open StatsGUI in future
        }
        else if (src == menuView.getExitButton()) 
        {
            System.out.println("Exiting game...");
            System.exit(0);
        }
    }

    // Main test
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MenuGUI_MVC menu = new MenuGUI_MVC();
            new MenuController_MVC(menu);
            menu.setVisible(true);
        });
    }
}