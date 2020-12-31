package NHLSimulator;
import java.util.*;

/*
Author: Anirudha Verma
Date: 14 Jan. 2019
Class Description: This is the game class which runs and follows the rules given
                   in order for the simulation to run correctly.
 */

public class Game 
{
    public Team teamA;
    public Team teamB;
    private int teamAGoals;
    private int teamBGoals;
    private int teamAPoints;
    private int teamBPoints;
    private Random random = new Random();
    
    Game(Team teamA, Team teamB)
    {
        this.teamA = teamA;
        this.teamB = teamB;
        simulateGame();
    }
    
    public void simulateGame()
    {
        this.teamAPoints = calculatePoints(teamA);
        this.teamBPoints = calculatePoints(teamB);
        this.teamAGoals = calculateGoalsScored(teamAPoints);
        this.teamBGoals = calculateGoalsScored(teamBPoints);
        updateGame();
    }
    
    // Calculates the points of each team
    public int calculatePoints(Team team)
    {
        int totalScore = 0;
        
        // Removes last 4 players as they are the goalies and not included
        for (int i = 0; i <= team.teamPlayers.size() - 5; i++)
        {
            totalScore += team.teamPlayers.get(i).getSkillLevel();
        }
        
        // Chooses a random goalie
        int randomGoalie = random.nextInt(4);
        totalScore += team.teamPlayers.get(randomGoalie).getSkillLevel();
        
        // Decides if they play well or not
        totalScore += (random.nextInt(3) - 1) * 25;
        totalScore += (random.nextInt(3) - 1) * 40;
        totalScore += (random.nextInt(3) - 1) * 60;
        
        return totalScore;
    }
    
    public int calculateGoalsScored(int score)
    {
        int goals = 0;
        
        // Remainder fraction of 50 points
        for (int i = 0; i < score/50; i++)
        {
            goals += 1 * random.nextInt(3);
        }
        
        // Leftover points goals calculated
        if (score % 50 > 0){
        goals += 1 * random.nextInt(2);    
        }

        return goals;
    }
    
    public void updateGame()
    {
        // Checks if there is overtime or not
        boolean overtime = overtime();
        
        // Updates necessary areas
        if(this.teamAGoals > this.teamBGoals)
        {
            teamA.updateWins();
            
            if(overtime)
            {
                teamA.updatePoints(1);
                teamB.updateOTLoses();
            }
            else
            {
                teamA.updatePoints(2);
                teamB.updateWins();
            }
        }
        
        else
        {
            teamB.updateWins();
            
            if(overtime)
            {
                teamB.updatePoints(1);
                teamA.updateOTLoses();
            }
            
            else
            {
              teamB.updatePoints(1);
              teamA.updateLoses();
            }
        }
    
    teamA.updateGoalsFor(this.teamAGoals);
    teamA.updateGoalsAgainst(this.teamBGoals);
    
    teamB.updateGoalsFor(this.teamBGoals);
    teamB.updateGoalsAgainst(this.teamAGoals);
    }
    
    // Method to check overtime
    public boolean overtime()
    {
        if (this.teamAGoals == this.teamBGoals)
        {
            if (this.teamAPoints == this.teamBPoints)
            {
                scoresTied(random.nextInt(2), 0);
                return true;
            }
            
            else
            {
                scoresTied(this.teamAPoints, this.teamBPoints);
            }
        }
        return false;
    }
    
    // Method to calculate what to do when the scores are tied
    public void scoresTied(int numA, int numB)
    {
        if (numA > numB)
        {
            this.teamAGoals += 1;
        }
        
        else
        {
            this.teamBGoals += 1;
        }
    }
    
}
 