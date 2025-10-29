package com.mycompany.amrithkongv2;

import javax.swing.*;
import java.awt.*;

public class StatGUI_MVC extends JFrame
{
    private JTabbedPane tabs;
    private JPanel memorialPanel;
    private JPanel recentGamePanel;
    private JPanel historyPanel;
    
    private JTextArea memorial;
    private JTextArea recentGame;
    private JTextArea history;
    
    private JButton back;
    
    public StatGUI_MVC()
    {
        setTitle("Game Statistics");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        tabs = new JTabbedPane();
        
        //memorial Panel
        memorialPanel = new JPanel(new BorderLayout());
        memorial = new JTextArea();
        memorial.setEditable(false);
        memorialPanel.add(new JScrollPane(memorial), BorderLayout.CENTER);
        tabs.addTab("Players (Memorial)", memorialPanel);
        
        recentGamePanel = new JPanel(new BorderLayout());
        recentGame = new JTextArea();
        recentGame.setEditable(false);
        recentGamePanel.add(new JScrollPane(recentGame), BorderLayout.CENTER);
        tabs.addTab("Recent Game", recentGamePanel);

        // Historical Panel
        historyPanel = new JPanel(new BorderLayout());
        history = new JTextArea();
        history.setEditable(false);
        historyPanel.add(new JScrollPane(history), BorderLayout.CENTER);
        tabs.addTab("History", historyPanel);

        // Back button
        back = new JButton("Back to Menu");

        // Layout
        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);
    }
    
    public JButton getBackButton() { return back; }
    public JTextArea getMemorialTextArea() { return memorial; }
    public JTextArea getRecentGameTextArea() { return recentGame; }
    public JTextArea getHistoryTextArea() { return history; }
    
}
