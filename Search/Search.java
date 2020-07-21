import java.io.*;
import java.util.*;

public class Search
{
	char [][] puzzle;
	ArrayList<Word> solutions;
	
	public Search(String filename)
	{
		try (Scanner in = new Scanner(new File(filename)))
		{
			int numPuzzles = in.nextInt();
			
			for (int a = 1; a <= numPuzzles; a++)
			{
				solutions = new ArrayList<Word>();
				
				int rows = in.nextInt();
				int cols = in.nextInt();
				in.nextLine();		// consume '\n'
				
				// read in the puzzle
				puzzle = new char[rows][];
				for (int i = 0; i < rows; i++)
					puzzle[i] = in.nextLine().trim().toCharArray();

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
	
	public void searchFor(String word)
	{
		int index = 0;
		char firstChar = word.charAt(0);
		
		for (int i = 0; i < puzzle.length; i++)
		{
			for (int j = 0; j < puzzle[i].length; j++)
			{
				if (puzzle[i][j] == firstChar)
				{
					// check adjacent locations
					if (checkToTheRight(i, j, word, index))
					{
						System.out.println(new Word(word, i+1, j+1, 'R'));
						return;
					}
					
					if (checkToTheLeft(i, j, word, index))
					{
						System.out.println(new Word(word, i+1, j+1, 'L'));
						return;
					}
					
					if (checkDown(i, j, word, index))
					{
						System.out.println(new Word(word, i+1, j+1, 'D'));
						return;
					}
					
					if (checkUp(i, j, word, index))
					{
						System.out.println(new Word(word, i+1, j+1, 'U'));
						return;
					}
				}
			}
		}
	}
	
	public boolean checkToTheRight(int row, int col, String word, int index)
	{
		col++;		// start at the next spot to the right
		index++;	// advance to next character of word
		
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
	
	public boolean checkToTheLeft(int row, int col, String word, int index)
	{
		col--;		// start at the next spot to the left
		index++;	// advance to next character of word
		
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
	
	public boolean checkUp(int row, int col, String word, int index)
	{
		row--;		// start at the next spot down
		index++;	// advance to next character of word
		
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
	
	public boolean checkDown(int row, int col, String word, int index)
	{
		row++;		// start at the next spot above
		index++;	// advance to next character of word
		
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
