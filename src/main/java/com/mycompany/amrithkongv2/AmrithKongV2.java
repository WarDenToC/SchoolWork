package com.mycompany.amrithkongv2;

public class AmrithKongV2 
{
    public static void main(String[] args) 
    { //Got help from ChatGPT in making this entry point for my GUI since I've never used Java swing before
        javax.swing.SwingUtilities.invokeLater(() -> 
        {
            MenuGUI_MVC menu = new MenuGUI_MVC();
            new MenuController_MVC(menu);
            menu.setVisible(true);
        });
    }
}

