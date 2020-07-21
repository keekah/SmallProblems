// After writing my solution, I was interested to see how it could be approached differently. I consulted
// the posted solution by a programming team coach and compared. I liked their idea of storing the locations
// of each letter in the puzzle because it offers an improvement on runtime. We can traverse the entire puzzle
// only once instead of w times, where w is the number of words to be found. Thus, the runtime becomes O(r*c)
// r is the number of rows and c is the number of columns, instead of O(r*c*w). Both these runtimes are linear,
// but hey, an improvement is an improvement. Depending on time and space constraints, this technique could prove
// useful in future situations.

import java.io.*;
import java.util.*;

public class Search
{
	char [][] puzzle;
	Map<Character, ArrayList<Location>> locationsOfChar;
	
	public Search(String filename)
	{
		try (Scanner in = new Scanner(new File(filename)))
		{
			int numPuzzles = in.nextInt();
			
			for (int a = 1; a <= numPuzzles; a++)
			{
				int rows = in.nextInt();
				int cols = in.nextInt();
				in.nextLine();		// consume '\n'
				
				// read in the puzzle
				puzzle = new char[rows][];
				for (int i = 0; i < rows; i++)
					puzzle[i] = in.nextLine().trim().toCharArray();
				
				// populate our map so we know where to look for each word
				storeCharacterLocations();

				// print heading
				System.out.println("Word search puzzle #" + a + ":");
				
				// read in and search for words
				int numWordsToSearch = in.nextInt();
				in.nextLine();
				
				for (int i = 0; i < numWordsToSearch; i++)
					searchFor(in.nextLine().trim());
				
				System.out.println();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void storeCharacterLocations()
	{
		locationsOfChar = new HashMap<Character, ArrayList<Location>>();
		
		for (int i = 0; i < puzzle.length; i++)
		{
			for (int j = 0; j < puzzle[i].length; j++)
			{
				char c = puzzle[i][j];
				
				if (!locationsOfChar.containsKey(c))
					locationsOfChar.put(c, new ArrayList<Location>());
				
				locationsOfChar.get(c).add(new Location(i, j));
			}
		}
	}
	
	public void searchFor(String word)
	{
		char firstChar = word.charAt(0);
		
		for (Location l : locationsOfChar.get(firstChar))
		{
			if (checkToTheRight(l.row, l.col, word))
			{
				System.out.println(new Word(word, l.row+1, l.col+1, 'R'));
				return;
			}
			
			if (checkToTheLeft(l.row, l.col, word))
			{
				System.out.println(new Word(word, l.row+1, l.col+1, 'L'));
				return;
			}
			
			if (checkDown(l.row, l.col, word))
			{
				System.out.println(new Word(word, l.row+1, l.col+1, 'D'));
				return;
			}
			
			if (checkUp(l.row, l.col, word))
			{
				System.out.println(new Word(word, l.row+1, l.col+1, 'U'));
				return;
			}
		}
	}

	public boolean checkToTheRight(int row, int col, String word)
	{
		col++;		// start at the next spot to the right
		int index = 1;	// advance to second character of word
		
		while (col < puzzle[0].length && index < word.length())
		{
			if (col == puzzle[0].length-1)
				col = 0;
			
			if (index == word.length()-1 && puzzle[row][col] == word.charAt(index))
				return true;
			
			if (puzzle[row][col] != word.charAt(index))
				break;
			
			col++; 
			index++;
		}
		
		return false;
	}
	
	public boolean checkToTheLeft(int row, int col, String word)
	{
		col--;		// start at the next spot to the left
		int index = 1;	// advance to second character of word
		
		while (col >= -1 && index < word.length())
		{
			if (col == -1)
				col = puzzle[0].length-1;
			
			if (index == word.length()-1 && puzzle[row][col] == word.charAt(index))
				return true;
			
			if (puzzle[row][col] != word.charAt(index))
				break;
			
			col--; 
			index++;
		}
		
		return false;
	}
	
	public boolean checkUp(int row, int col, String word)
	{
		row--;		// start at the next spot down
		int index = 1;	// advance to second character of word
		
		while (row >= -1 && index < word.length())
		{
			if (row == -1)
				row = puzzle.length-1;
			
			if (index == word.length()-1 && puzzle[row][col] == word.charAt(index))
				return true;
			
			if (puzzle[row][col] != word.charAt(index))
				break;
			
			row--; 
			index++;
		}
		
		return false;
	}
	
	public boolean checkDown(int row, int col, String word)
	{
		row++;		// start at the next spot above
		int index = 1;	// advance to second character of word
		
		while (row <= puzzle.length && index < word.length())
		{
			if (row == puzzle.length)
				row = 0;
			
			if (index == word.length()-1 && puzzle[row][col] == word.charAt(index))
				return true;
			
			if (puzzle[row][col] != word.charAt(index))
				break;
			
			row++; 
			index++;
		}
		
		return false;
	}
	
	public void printPuzzle()
	{
		for (int i = 0; i < puzzle.length; i++)
		{
			for (int j = 0; j < puzzle[i].length; j++)
				System.out.print(puzzle[i][j]);
			
			System.out.println();
		}	
	}
	
	public static void main(String[] args)
	{
		new Search("search.in");
	}

}


class Word
{
	String word;
	int row;		// 1-based, starting character of word
	int col;		// 1-based, starting character of word
	char direction;
	
	Word(String word, int row, int col, char direction)
	{
		this.word = word;
		this.row = row;
		this.col = col;
		this.direction = direction;
	}
	
	public String toString()
	{
		return direction + " " + row + " " + col + " " + word;
	}
}

class Location
{
	int row;
	int col;
	
	Location(int r, int c)
	{
		row = r;
		col = c;
	}
}
