package com.mycompany.amrithkongv2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class SetupGUI_MVC extends JFrame 
{
    private JTextField player1Field;
    private JTextField player2Field;
    private JComboBox<String> gunDropdown;
    private JButton startButton;
    private JButton backButton;
    private JButton GP100;
    private JButton BountyHunter;
    private JButton ColtPython;
    private Font customFont;
    String selectedGun = "";


    public SetupGUI_MVC() 
    {
        try 
        {
            //custom font
            File font = new File("Molot.otf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, font);
            customFont = customFont.deriveFont(48f);
            this.customFont = customFont;
            
            setTitle("Russian Roulette - Setup");
            setSize(800, 1000);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.BLACK);

            
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(false);

            // Title
            JLabel titleLabel = new JLabel("Player Setup ");
            titleLabel.setFont(customFont);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            //Label for player 1 and 2
            JLabel playerLabel1 = new JLabel("Player one name");
            playerLabel1.setFont(customFont);
            playerLabel1.setForeground(Color.WHITE);
            playerLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel playerLabel2 = new JLabel("Player two name");
            playerLabel2.setFont(customFont);
            playerLabel2.setForeground(Color.WHITE);
            playerLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Input fields
            player1Field = new JTextField(20);
            player2Field = new JTextField(20);

            styleTextField(player1Field);
            styleTextField(player2Field);

            // Buttons
            startButton = new JButton("Start Game");
            backButton = new JButton("Back to Menu");
            
            //So the problem with the text icon is that they don't work well with Molot font so it has to be Arial font instead
            GP100 = new JButton("GP100");
            JLabel GP100Label = new JLabel("Bullet amount: ⌯⁍");
            GP100Label.setFont(new Font("Arial", Font.PLAIN, 24));
            GP100Label.setForeground(Color.WHITE);
            GP100Label.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            BountyHunter = new JButton("Bounty Hunter");
            JLabel BountyHunterLabel = new JLabel("Bullet amount: ⌯⁍ ⌯⁍");
            BountyHunterLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            BountyHunterLabel.setForeground(Color.WHITE);
            BountyHunterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            ColtPython = new JButton("Colt Python");
            JLabel ColtPythonLabel = new JLabel("Bullet amount: ⌯⁍");
            ColtPythonLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            ColtPythonLabel.setForeground(Color.WHITE);
            ColtPythonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            //start of chat code, needed this since I don't wanna use a drop down button. I wish to use 3 buttons that can highlights and pass them to the controller. 
            //this helps with highliting the guns buttons
            ActionListener gunSelectListener = e -> 
            {
                JButton clicked = (JButton) e.getSource();
                selectedGun = clicked.getText();
                highlightSelectedGun(clicked);
            };

            GP100.addActionListener(gunSelectListener);
            BountyHunter.addActionListener(gunSelectListener);
            ColtPython.addActionListener(gunSelectListener);
            //enndd of chat's code
            
            //adding stuff vertically and sequentially, looks ugly but I don't know much about GUI
            styleGunButton(GP100);
            styleGunButton(BountyHunter);
            styleGunButton(ColtPython);
            styleFunctionButton(startButton);
            styleFunctionButton(backButton);
            
            panel.add(Box.createVerticalStrut(100));
            panel.add(titleLabel);
            //Gun section
            
            panel.add(Box.createVerticalStrut(50));
            panel.add(GP100);
            panel.add(GP100Label);
            panel.add(Box.createHorizontalStrut(40));
            panel.add(BountyHunter);
            panel.add(BountyHunterLabel);
            panel.add(Box.createHorizontalStrut(40));
            panel.add(ColtPython);
            panel.add(ColtPythonLabel);
            panel.add(Box.createVerticalStrut(100));
            
            //player section
            panel.add(playerLabel1);
            panel.add(player1Field);
            panel.add(Box.createVerticalStrut(20));
            panel.add(playerLabel2);
            panel.add(player2Field);
            panel.add(Box.createVerticalStrut(60));
            panel.add(startButton);
            panel.add(Box.createVerticalStrut(20));
            panel.add(backButton);

            getContentPane().setLayout(new BorderLayout());
            add(panel, BorderLayout.CENTER);
        }
        catch(Exception e)
        {
            
        }
    }
    
    private void highlightSelectedGun(JButton selected) // method helped by ChatGPT, no clue how highlights work
    {
        JButton[] guns = {GP100, BountyHunter, ColtPython};
        for (JButton b : guns) 
        {
            b.setBackground(null);
            b.setForeground(Color.BLACK);
        }
        // Highlight selected one
        selected.setBackground(Color.RED);
        selected.setForeground(Color.WHITE);
    }

    //Methods for making life easier by using it as a way to style the button with my custom font
    private void styleTextField(JTextField field) 
    {
        field.setMaximumSize(new Dimension(400, 40));
        field.setFont(customFont);
        field.setHorizontalAlignment(JTextField.CENTER);
    }

    private void styleFunctionButton(JButton button) {
        button.setFont(customFont);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    private void styleGunButton(JButton button) 
    {
        customFont = customFont.deriveFont(24f);
        button.setFont(customFont);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    //

    public void addSetupListener(ActionListener listener) 
    {
        startButton.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public String getPlayer1Name()        { return player1Field.getText().trim(); }
    public String getPlayer2Name()        { return player2Field.getText().trim(); }
    public JButton getGP100GUI()          { return GP100; }
    public JButton getColtPythonGUI()     { return ColtPython; }
    public JButton getBountyHunterGUI()   { return BountyHunter; }
    public String getSelectedGun()        { return selectedGun; }
    public JButton getStartButton()       { return startButton; }
    public JButton getBackButton()        { return backButton; }

    
    /* for testing 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SetupGUI_MVC setup = new SetupGUI_MVC();
            setup.setVisible(true);
        });
    }*/
}
