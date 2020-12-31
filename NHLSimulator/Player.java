package NHLSimulator;
import java.util.*;
        
/*
Author: Anirudha Verma
Date: 14 Jan. 2019
Class Description: This is the player class which creates objects of each 
                   player in the game.
 */

public class Player 
{
    public int playerNumber;
    public String playerName;
    public String playerPosition;
    public int playerSkillLevel;
    
    Player(int playerNumber, String playerName, String playerPosition, 
            int playerSkillLevel)
    {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.playerSkillLevel = playerSkillLevel;
    }
    
    public int getPlayerNumber()
    {
        return this.playerNumber;
    }
    
    public int getSkillLevel()
    {
        return this.playerSkillLevel;
    }
    
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    public String getPlayerPosition()
    {
        return this.playerPosition;
    }
    
    // Formating the way of printing a player
    public String printPlayer()
    {
        return  String.format("%-20s", this.playerNumber) + 
                String.format("%-20s", this.playerName) +
                String.format("%-18s", this.playerPosition) +
                String.format("%-20s", this.playerSkillLevel);
    }
    
}
