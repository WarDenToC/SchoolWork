
package com.mycompany.amrithkongv2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class GameGUI_MVC extends JFrame
{
    private JLabel playerTurnLabel;
    private JLabel revolverStatusLabel;
    private JLabel revolverBullets;
    private JButton shootButton;
    private JButton spinButton;
    private JButton forfeitButton;
    private JButton shootOtherButton;
    private JButton peekButton;
    private Font customFont;
    
    public GameGUI_MVC()
    {
        try
        {
            File font = new File("Molot.otf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, font);
            customFont = customFont.deriveFont(24f);
            this.customFont = customFont;
            
            setTitle("Russian Roulette Game");
            setSize(1000, 800);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setBackground(Color.BLACK);
            setLocationRelativeTo(null);

            
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(false);
            
            playerTurnLabel = new JLabel("Player Turn: ");
            revolverStatusLabel = new JLabel("Messager: ");
            //revolverBullets = new JLabel("");
            
            shootButton = new JButton("Shoot");
            spinButton = new JButton("Spin Cylinder");
            forfeitButton = new JButton("Forfeit");
            shootOtherButton = new JButton("Shoot Other Player");
            peekButton = new JButton("Peek Chamber");
            
            //these button visibility will only show if a player try to kill themselves a certain amount of times (suicide option)
            shootOtherButton.setVisible(false);
            peekButton.setVisible(false);
            
            playerTurnLabel.setFont(customFont);
            playerTurnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            revolverStatusLabel.setFont(customFont);
            revolverStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            //revolverBullets.setFont(new Font("Arial", Font.PLAIN, 24));
            //revolverBullets.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            
            styleFunctionButton(shootButton);
            styleFunctionButton(spinButton);
            styleFunctionButton(forfeitButton);
            styleFunctionButton(shootOtherButton);
            styleFunctionButton(peekButton);
            
            panel.add(Box.createVerticalStrut(100));
            panel.add(playerTurnLabel);
            panel.add(Box.createVerticalStrut(40));
            panel.add(revolverStatusLabel);
            panel.add(Box.createVerticalStrut(40));
            //panel.add(revolverBullets);
            //panel.add(Box.createVerticalStrut(40));
            
            panel.add(shootButton);
            panel.add(Box.createVerticalStrut(40));
            panel.add(spinButton);
            panel.add(Box.createVerticalStrut(40));
            panel.add(forfeitButton);
            
            panel.add(Box.createVerticalStrut(40));
            panel.add(shootOtherButton);
            panel.add(Box.createVerticalStrut(40));
            panel.add(peekButton);
            
            add(panel);
        }
        catch(Exception e)
        {
        }
    }
    
    
    public JButton getShootButton()         { return shootButton; }
    public JButton getSpinButton()          { return spinButton; }
    public JButton getForfeitButton()       { return forfeitButton; }
    public JButton getShootOtherButton()    { return shootOtherButton; }
    public JButton getPeekButton()          { return peekButton; }
    
    public void setShootOtherVisible(boolean visible) 
    {
        shootOtherButton.setVisible(visible);
    }

    public void setPeekVisible(boolean visible) 
    {
        peekButton.setVisible(visible);
    }
    
    private void styleFunctionButton(JButton button) 
    {
        button.setFont(customFont);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    public void addGameListener(ActionListener listener) 
    {
        shootButton.addActionListener(listener);
        spinButton.addActionListener(listener);
        forfeitButton.addActionListener(listener);
        shootOtherButton.addActionListener(listener);
        peekButton.addActionListener(listener);
    }
    
    public void setPlayerTurn(String playerName) 
    {
        playerTurnLabel.setText("Player Turn: " + playerName);
    }

    public void setRevolverStatus(String status) 
    {
        revolverStatusLabel.setText("Revolver: " + status);
    }

    public void setRevolverBullets(String bullets) 
    {
        revolverBullets.setText(bullets);
    }

        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameGUI_MVC setup= new GameGUI_MVC();
            setup.setVisible(true);
        });
    }
    
    
}
