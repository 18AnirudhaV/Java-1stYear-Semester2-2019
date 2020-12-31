package NHLSimulator;
import java.util.*;
import java.io.*;

/*
Author: Anirudha Verma
Date: 14 Jan. 2019
Class Description: This is the team class which creates objects of each 
                   team in the game.
 */

public class Team 
{    
    /* 
    Using ArrayList as it is dynamic, thus if the progam is needed to be expaned
    upon it can be done with ease.
    */
    public ArrayList<Player> teamPlayers = new ArrayList<Player>();
    private ArrayList<Integer> playerNumber = new ArrayList<Integer>();
    public Random random = new Random();
    
    private String teamName;
    private int wins;
    private int goalsAgainst;
    private int goalsFor;
    private int loses;
    private int OTLoses;
    private int points;
    
    Team(String name, int[] forwardSkillLevel, int[] defenseSkillLevel, 
            int[] goalieSkillLevel)
    {
        // Resets the season in case they want to run another simulation
        reset();
        
        this.teamName = name;
        
        // Toronto Maple Leafs data is given - no need to create data for it
        if(name == "Toronto Maple Leafs")
        {
            torontoMapleLeafsTeam();
        }
        
        // Create's the team based on data entered
        else
        {
            createTeam(forwardSkillLevel, defenseSkillLevel, goalieSkillLevel);
        }
    }
    
    // Method to set everything to zero
    public void reset()
    {
        this.wins = 0;
        this.goalsAgainst = 0;
        this.goalsFor = 0;
        this.loses = 0;
        this.OTLoses = 0;
        this.points = 0;
    }
    
    // Enter's in the Toronto Maple Leafs Team
    public void torontoMapleLeafsTeam()
    {
        teamPlayers.add(new Player(28, "C. Brown", "forward", 5));
        teamPlayers.add(new Player(63, "T. Ennis", "forward", 4));
        teamPlayers.add(new Player(33, "F. Gauthier", "forward", 5));
        teamPlayers.add(new Player(11, "Z. Hyman ", "forward", 7));
        teamPlayers.add(new Player(18, "A. Johnsson", "forward", 7));
        teamPlayers.add(new Player(43, "N. Kadri ", "forward", 7));
        teamPlayers.add(new Player(24, "K. Kapanen", "forward", 8));
        teamPlayers.add(new Player(26, "P. Lindholm", "forward", 8));
        teamPlayers.add(new Player(12, "P. Marleau", "forward", 8));
        teamPlayers.add(new Player(16, "M. Marner", "forward", 9));
        teamPlayers.add(new Player(34, "A. Matthews", "forward", 9));
        teamPlayers.add(new Player(29, "W. Nylander", "forward", 9));
        teamPlayers.add(new Player(91, "J. Tavares", "forward", 10));
        teamPlayers.add(new Player(23, "T. Dermott", "defenseman", 8));
        teamPlayers.add(new Player(51, "J. Gardiner", "defenseman", 4));
        teamPlayers.add(new Player(02, "R. Hainsey", "defenseman", 5));
        teamPlayers.add(new Player(03, "J. Holl", "defenseman", 6));
        teamPlayers.add(new Player(52, "M. Marincin", "defenseman", 4));
        teamPlayers.add(new Player(92, "I. Ozhiganov", "defenseman", 6));
        teamPlayers.add(new Player(44, "M. Rielly", "defenseman", 9));
        teamPlayers.add(new Player(22, "N. Zaitsev ", "defenseman", 8));
        teamPlayers.add(new Player(31, "F. Andersen", "goalie", 10));
        teamPlayers.add(new Player(30, "M. Hutchinson", "goalie", 7));
        teamPlayers.add(new Player(50, "K. Kaskisuo ", "goalie", 5));
        teamPlayers.add(new Player(40, "G. Sparks", "goalie", 6));
    }
    
    // Creates a team with 13 Forwads, 8 Defense, 4 Goalies
    public void createTeam(int[] forwardSkillLevel, int[] defenseSkillLevel, 
            int[] goalieSkillLevel)
    {
        for (int i = 0; i <= 12; i++)
        {
            createPlayer("Forward", forwardSkillLevel);
        }
        
        for (int i = 0; i <= 7; i++)
        {
            createPlayer("Defense", defenseSkillLevel);
        }
        
        for (int i = 0; i <= 3; i++)
        {
            createPlayer("Goalie", goalieSkillLevel);
        }
    }
    
    // Add the players of each team created
    private void createPlayer(String position, int[] skillLevel)
    {
        int num = createUniquePlayerNumber();
        int calculatedSkillLevel = random.nextInt(skillLevel[1] - skillLevel[0]) 
                + skillLevel[0];
        Player player = new Player(num, position.charAt(0) + Integer.toString(num),
                        position, calculatedSkillLevel);
        
        teamPlayers.add(player);
    }
    
    // Prints a team when it is called with the players and skill level, etc.
    public String printTeam()
    {
        String tempString = "";
        for (int i = 0; i < teamPlayers.size(); i++)
        {
           tempString += ("\n" + teamPlayers.get(i).printPlayer()); 
        }
        
        return tempString;
    }
    
    // Creates a unique player number for the teams that are not specified
    private int createUniquePlayerNumber()
    {
        boolean check = false;
        while (check == false)
        {
            int rand = random.nextInt(100);
            if (!playerNumber.contains(rand))
            {
                playerNumber.add(rand);
                check = true;
            }
        }
        
        return playerNumber.get(playerNumber.size()-1);
    }
    
    // Updates team wins
    public void updateWins()
    {
        this.wins += 1;
    }
    
    // Updates team loses
    public void updateLoses()
    {
        this.loses += 1;
    }
    
    // Updates team overtime loses
    public void updateOTLoses()
    {
        this.OTLoses +=  1;
    }
    
    // Updates team goals for
    public void updateGoalsFor(int goals)
    {
        this.goalsFor += goals;
    }
    
    // Updates team goals against
    public void updateGoalsAgainst(int goals)
    {
        this.goalsAgainst += goals;
    }
    
    // Updates team points
    public void updatePoints(int points)
    {
        this.points += points;
    }
    
    // Prints the team stats in a formatted way
    public String printStatistics()
    {
        String stats;
        stats = (
            String.format("%-25s", "\r\n" + this.teamName) +
            String.format("%-20s", "30") + 
            String.format("%-20s", this.wins) +
            String.format("%-20s", this.loses) +
            String.format("%-20s", this.OTLoses) +
            String.format("%-20s", this.points) +
            String.format("%-20s", this.goalsFor) +
            String.format("%-20s", this.goalsAgainst) +
            String.format("%-20s", getDifference()));
        
        return stats;
    }
    
    public int getWins()
    {
        return this.wins;
    }
    
    public int getPoints()
    {
        return this.points;
    }
    
    public String getTeams()
    {
        return this.teamName;
    }
    
    public String getDifference()
    {
        int diff = this.goalsFor - this.goalsAgainst;
        if (diff > 0)
        {
            return "+" + Integer.toString(diff);
        }
        
        else
        {
            return Integer.toString(diff);
        }
    }
}
