package com.mycompany.amrithkongv2;
public class AmrithKongV2 
{
    public static void main(String[] args) 
    {
        Logger log = new Logger();
        Game game = new Game(log); // was told by ChatGPT that this is the best way to log the game, from the top level. But can also be made in the game class and would still work.
        game.start();// This will start the game
    }
}
