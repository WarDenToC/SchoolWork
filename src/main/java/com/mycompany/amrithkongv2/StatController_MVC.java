package com.mycompany.amrithkongv2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StatController_MVC 
{
    
    private StatGUI_MVC view;
    private DataBaseConn db;

    public StatController_MVC() 
    {
        this.db = DataBaseConn.getInstance();
        this.view = new StatGUI_MVC();

        // On launch these methods will help us with viewing the stats
        loadMemorial();
        loadRecentGame();
        loadHistory();

        // Back button
        view.getBackButton().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose(); // Close stats window
            }
        });
    }
    
    public void showStatsGUI() {
        view.setVisible(true);
    }

    private void loadMemorial() {
        Player_DB_RW playerDAO = new Player_DB_RW(db, "", ""); // names not needed for reading
        List<String> players = playerDAO.getAllPlayers();
        StringBuilder sb = new StringBuilder();
        for (String p : players) {
            sb.append(p).append("\n");
        }
        view.getMemorialTextArea().setText(sb.toString());
    }
    
    
    private void loadRecentGame() 
    {
        RecentGame_DB_RW recentDAO = new RecentGame_DB_RW(db, null, null);   // last two stuff not needed for reading
        StringBuilder sb = recentDAO.readRecentGameDBToString();
        view.getRecentGameTextArea().setText(sb.toString());
    }
    
    private void loadHistory() 
    {
        History_DB_Reader historyDAO = new History_DB_Reader(db, null, null, null, null); // last four stuff not needed for reading
        StringBuilder sb = historyDAO.readFullHistoryDB();
        view.getHistoryTextArea().setText(sb.toString());
    }


}

 
    
    
    
