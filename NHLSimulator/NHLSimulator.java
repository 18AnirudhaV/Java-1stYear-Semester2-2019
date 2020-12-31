package NHLSimulator;

/*
Author: Anirudha Verma
Date: 14 Jan. 2019
Class Description: This is the main class which runs the simulation. It creates
                   the new teams and has the UI for the simulation.
 */

import java.io.*;
import java.util.*;

public class NHLSimulator {

    /* 
    Using ArrayList as it is dynamic, thus if the progam is needed to be expaned
    upon it can be done with ease.
    */
    public ArrayList<Team> EasternTeams = new ArrayList<Team>();
    private boolean firstOption = false;
    
    // creates the teams within the Eastern Conference
    public void playEasternConference()
    {
        EasternTeams.add(new Team("Boston", new int[] {5,9}, new int[] {4,9}, 
                new int[] {5,7}));
        EasternTeams.add(new Team("Buffalo", new int[] {6,9}, new int[] {4,7}, 
                new int[] {4,7}));
        EasternTeams.add(new Team("Carolina", new int[] {4,8}, new int[] {5,7}, 
                new int[] {4,9}));
        EasternTeams.add(new Team("Columbus", new int[] {4,9}, new int[] {5,8}, 
                new int[] {7,10}));
        EasternTeams.add(new Team("Detroit", new int[] {4,7}, new int[] {6,8}, 
                new int[] {4,6}));
        EasternTeams.add(new Team("Florida", new int[] {5,7}, new int[] {4,8}, 
                new int[] {5,9}));
        EasternTeams.add(new Team("Montreal", new int[] {4,7}, new int[] {4,7}, 
                new int[] {4,9}));
        EasternTeams.add(new Team("New Jersey", new int[] {4,7}, new int[] {4,7}, 
                new int[] {5,6}));
        EasternTeams.add(new Team("NY Islanders", new int[] {6,8}, new int[] {5,7}, 
                new int[] {6,8}));
        EasternTeams.add(new Team("NY Rangers", new int[] {5,7}, new int[] {4,6}, 
                new int[] {5,7}));
        EasternTeams.add(new Team("Ottawa", new int[] {4,6}, new int[] {4,5}, 
                new int[] {4,5}));
        EasternTeams.add(new Team("Philadelphia", new int[] {4,6}, new int[] {4,6}, 
                new int[] {4,7}));
        EasternTeams.add(new Team("Pittsburgh", new int[] {6,10}, new int[] {4,7}, 
                new int[] {5,7}));
        EasternTeams.add(new Team("Tampa Bay", new int[] {6,10}, new int[] {6,10}, 
                new int[] {7,9}));
        EasternTeams.add(new Team("Toronto Maple Leafs", new int[] {5,10}, new int[] {4,9}, 
                new int[] {5,10}));
        EasternTeams.add(new Team("Washington", new int[] {6,10}, new int[] {5,8}, 
                new int[] {6,8}));
    }
    
    // Menu/UI for the simulation
    public void UI()
    {
        String menu = "\n\nNHL Simulator (Version 0.1). Author: Anirudha Verma\n"
                + "1 - Simulate NHL Season (Eastern Conference)\n"
                + "2 - View Team Skill Level Profile\n"
                + "3 - Display End of Regular Season Table\n"
                + "Select Option [1, 2 or 3] (9 to Quit)";
        
        System.out.println(menu);
        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();
        checkOption(option);
        
    }
    
    // Addition to the UI for error checking of the option entered
    public void checkOption(String option)
    {
        
        if (option.equals("1"))
        {
            firstOption = true;
            simulateNHLSeason();
            UI();
        }
        
        else if (option.equals("2"))
        {
            if (firstOption == true)
            {
                viewTeamSkillLevelProfile();
                UI();   
            }
            
            else
            {
                System.out.println("Please run the simulation first.");
                UI();
            }
        }

        else if (option.equals("3"))
        {
            if (firstOption == true)
            {
                regularSeasonTable();
                UI(); 
            }
            
            else
            {
                System.out.println("Please run the simulation first.");
                UI();
            }
            
        }
        
        else if (option.equals("9"))
        {
            System.out.println("Thank you for using the simulation!\nHave a great day :D");
        }
        
        else
        {
            System.out.println("You have entered an invalid option. \nPlease enter a valid option");
            UI();
        }
    }
    
    // Runs the simulation of the NHL season
    public void simulateNHLSeason()    
    {
        // Resets the season - when they run the Eastern Conference again
        for (int i = 0; i < 16; i++)
        {
            EasternTeams.get(i).reset();
        }
        
        for (int teamOne = 0; teamOne < 15; teamOne++)
        {
            for (int teamTwo = 0; teamTwo < 15; teamTwo++)
            {
                // Makes sure that the teams don't play themselves
                if (teamOne != teamTwo)
                {
                    new Game(EasternTeams.get(teamOne), EasternTeams.get(teamTwo));
                }
            }
        }
        
        // Sorts the teams by points (as they do in real life)
        Collections.sort(EasternTeams,(teamOne, teamTwo) 
                -> teamOne.getPoints()- teamTwo.getPoints());
        Collections.reverse(EasternTeams);
        // (Code found online and edited)
        
        // Prints the first and last teams of the season
        System.out.println("NHL Regular Season - Eastern Conference - 2018/2019");
        
        System.out.println("First Team: " + EasternTeams.get(0).getTeams()
        + " Points: " + EasternTeams.get(0).getPoints());
        
        System.out.println("Last Team: " + 
                EasternTeams.get(EasternTeams.size()-1).getTeams()
        + " Points: " + EasternTeams.get(EasternTeams.size()-1).getPoints());
        
    }
    
    // Second entry of the UI
    // Lets you view the team you want
    public void viewTeamSkillLevelProfile()
    {
        String team = checkTeamName();
        
        if (!team.equals(""))
        {
            System.out.println(
                    String.format("%-20s", "\nNo.") +
                    String.format("%-20s", "Name") +
                    String.format("%-20s", "Position") +
                    String.format("%-20s", "Skill Level")        
            );
            
            System.out.println(
                    String.format("%-20s", "\r\n***") +
                    String.format("%-20s", "**********") +
                    String.format("%-20s", "******") +
                    String.format("%-20s", "******")
            );
            
            int teamIndex = Integer.valueOf(team);
            System.out.println(EasternTeams.get(teamIndex).printTeam());
        }
    }
    
    // Error check if the correct team name is entered or not
    public String checkTeamName()
    {
        while (true)
        {
            System.out.println("\n\nEnter Team Name: ");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            
            if (input.equals(""))
            {
                return input;
            }
            
            for (int i = 0; i <= 15; i++)
            {
                if (EasternTeams.get(i).getTeams().equals(input))
                {
                    return (Integer.toString(i));
                }
            }
            
        System.out.println(input + " is invalid! Please re-enter or press [Enter]\n\n");

        }
        
    }
    
    // 3rd option of the UI
    // Prints all the teams in a table
    // Sorted by points (like they do in real life)
    public void regularSeasonTable()
    {
        System.out.println(
                String.format("%-25s", "\r\n\r\nTeam Name") +
                String.format("%-20s", "GP") +
                String.format("%-20s", "W") +
                String.format("%-20s", "L") +
                String.format("%-20s", "OTL") +
                String.format("%-20s", "PTs") +
                String.format("%-20s", "GF") +
                String.format("%-20s", "GA") +
                String.format("%-20s", "Diff")
        );
        
        System.out.println(
                String.format("%-25s", "\r\n\r\n*******************") +
                String.format("%-20s", "*********") +
                String.format("%-20s", "*********") +
                String.format("%-20s", "*********") +
                String.format("%-20s", "*********") +
                String.format("%-20s", "*********") +
                String.format("%-20s", "*********") +
                String.format("%-20s", "*********") +
                String.format("%-20s", "*********")
        );
        
        for (int i = 0; i < 15; i++)
        {
            System.out.println(EasternTeams.get(i).printStatistics());
        }
    }
    
    // Runs the program
    public static void main(String[] args) 
    {
        NHLSimulator simulator = new NHLSimulator();
        simulator.playEasternConference();
        simulator.UI();
    }
    
}