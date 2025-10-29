package com.mycompany.amrithkongv2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class MenuGUI_MVC extends JFrame 
{
    private JButton startButton;
    private JButton statsButton;
    private JButton exitButton;

    public MenuGUI_MVC() 
    {
        try 
        {
            //custom font
            File font = new File("Molot.otf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, font);
            customFont = customFont.deriveFont(48f);

            setTitle("Russian Roulette - Main Menu");
            setSize(600, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.BLACK); 

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(false);
            panel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel title = new JLabel("RUSSIAN ROULLETE\n");
            JLabel icon = new JLabel("\u262D \u262D \u262D \u262D \u262D \u262D \u262D"); //communist sickle, you can find this from ascii emoji art 

            
            title.setFont(customFont);
            icon.setFont(new Font("Arial", Font.PLAIN, 24));
            setVisible(true);
            
            startButton = new JButton("Start Game");
            statsButton = new JButton("Stats");
            exitButton = new JButton("Exit");

            //Font buttonFont = new Font("Molot.otf", Font.BOLD, 24);
            startButton.setFont(customFont);
            statsButton.setFont(customFont);
            exitButton.setFont(customFont);

            //Set the buttosn and icons to the aliggn at the middle without having to hardcode it
            icon.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            statsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            //add the buttons and title as well as label to the panel
            panel.add(title);
            panel.add(icon);
            
            panel.add(Box.createVerticalStrut(150)); // all the struct stuff is helped by ChatGPT and some of the framwork, I had to redesign it however since it was still clunky
            panel.add(startButton);

            panel.add(Box.createVerticalStrut(40));
            panel.add(statsButton);

            panel.add(Box.createVerticalStrut(40));
            panel.add(exitButton);

            getContentPane().setLayout(new BorderLayout());
            add(panel, BorderLayout.CENTER);
        } 
        catch (Exception e) 
        {

        }

    }
    
    public void addMenuListener(ActionListener listener) 
    {
        startButton.addActionListener(listener);
        statsButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
    
    public JButton getStartButton() { return startButton; }
    public JButton getStatsButton() { return statsButton; }
    public JButton getExitButton() { return exitButton; }

}
