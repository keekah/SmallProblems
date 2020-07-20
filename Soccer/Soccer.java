import java.io.*;
import java.util.*;

public class Soccer
{
	ArrayList<String> teamNames;
	HashMap<String, Team> teams;
	
	public Soccer(String filename)
	{
		try (Scanner in = new Scanner(new File(filename)))
		{
			int numDataSets = in.nextInt();
			
			for (int a = 1; a <= numDataSets; a++)
			{
	            teamNames = new ArrayList<String>();
	            teams = new HashMap<String, Team>();
            
				int numTeams = in.nextInt();
				int numGames = in.nextInt();
				
				
				// initialize the teams list
				for (int i = 0; i < numTeams; i++)
				{
					String name = in.next().trim();
					teamNames.add(name);
					teams.put(name, new Team(name));
				}
				
				// process the results of the games
				for (int i = 0; i < numGames; i++)
				{
					String name1 = in.next().trim();
					int score1 = in.nextInt();
					
					String name2 = in.next().trim();
					int score2 = in.nextInt();
					
					Team team1 = teams.get(name1);
					Team team2 = teams.get(name2);
				
					updateStats(team1, score1, team2, score2);
				}
				
	            System.out.println("Group " + a + ":");
	            printSortedTeamsList();
	            System.out.println();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void updateStats(Team team1, int score1, Team team2, int score2)
	{
		team1.addGoalsScored(score1);
		team1.addGoalsAllowed(score2);
		
		team2.addGoalsScored(score2);
		team2.addGoalsAllowed(score1);
		
		if (score1 > score2)
		{	
			// team1 wins
			team1.incrementWins();
			team2.incrementLosses();
		}
		
		else if (score1 < score2)
		{
			// team 2 wins
			team2.incrementWins();
			team1.incrementLosses();
		}
		
		else
		{
			team1.incrementDraws();
			team2.incrementDraws();
		}
   }
	
	public void printSortedTeamsList()
	{
		ArrayList<Team> teamsList = new ArrayList<Team>();
		for (String name : teamNames)
			teamsList.add(teams.get(name));

		Team [] sortedTeams = teamsList.toArray(new Team[teamsList.size()]);
		Arrays.sort(sortedTeams, new TeamSorter());
		
		for (Team t : sortedTeams)
			System.out.println(t);
	}
	
	public static void main(String[] args)
	{
		new Soccer("soccer.in");
	}
}


class TeamSorter implements Comparator<Team>
{
	
	public int compare(Team t1, Team t2)
	{
		if (t1.points != t2.points)
			return t2.points - t1.points;
		
		else
		{
			int goalDiff1 = t1.goalsScored - t1.goalsAllowed;
			int goalDiff2 = t2.goalsScored - t2.goalsAllowed;
			
			if (goalDiff1 != goalDiff2)
				return goalDiff2 - goalDiff1;
		}
		
		if (t1.goalsScored != t2.goalsScored)
			return t2.goalsScored - t1.goalsScored;
		
		return t1.name.compareTo(t2.name);
	}
}


class Team
{
	String name;
	int points;
	int wins;
	int losses;
	int draws;
	int goalsScored;
	int goalsAllowed;
	
	Team(String name)
	{
		this.name = name;
	}
	
	public void addGoalsScored(int goals)
	{
		goalsScored += goals;
	}
	
	public void addGoalsAllowed(int goals)
	{
		goalsAllowed += goals;
	}
	
	public void incrementWins()
	{
		wins++;
		points += 3;
	}
	
	public void incrementDraws()
	{
		draws++;
		points++;
	}
	
	public void incrementLosses()
	{
		losses++;
	}
	
	public String toString()
	{
		return name + " " + points + " " + wins + " " + losses + " " + draws + " " + goalsScored + " " + goalsAllowed;
	}
}
