package com.mycompany.amrithkongv2;
import java.util.*;
public class DecisionMaker 
{           
    //Refactored class that handles the buttons and return a string for controller, the controller will send it to the action handler
    public String getMenuChoice(Object source, GameGUI_MVC gameView)
    {
        String choice = "";
        
        if(source == gameView.getShootButton())
            choice = "S";
        else if(source == gameView.getShootOtherButton())
            choice = "K";
        else if(source == gameView.getForfeitButton())
            choice = "F";
        else if(source == gameView.getSpinButton())
            choice = "R";
        else if(source == gameView.getPeekButton())
            choice = "P";
        
        return choice;
    }
}