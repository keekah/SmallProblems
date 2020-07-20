import java.io.*;
import java.util.*;

public class Symm
{
	char [][] matrix;
	int count = 1;
	
	public Symm(String filename)
	{
		try (Scanner in = new Scanner(new File(filename)))
		{
			int n = in.nextInt();
			
			while (n != 0)
			{
				// read in the matrix
				matrix = new char[n][n];
				
				for (int i = 0; i < n; i++)
					for (int j = 0; j < n; j++)
						matrix[i][j] = in.next().charAt(0);
				
				printMatrix();
				
				// check k diagonals in this matrix
				int k = in.nextInt();
				
				for (int i = 0; i < k; i++)
				{
					int diagonal = in.nextInt();
					printDiagonal(diagonal);
				}
				
				System.out.println();
				n = in.nextInt();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void printMatrix()
	{
		System.out.println("Input matrix #" + count++ + ":");
		
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix.length; j++)
				System.out.print(matrix[i][j] + " ");
			
			System.out.println();
		}
	}
	
   public void printDiagonal(int d)
	{
		System.out.println("Symmetric diagonals " + d + ":");
		
		// get matrix index
		d -= 1;
		
		// print upper diagonal
		int col = d;
		for (int i = 0; col < matrix.length; i++)
			System.out.print(matrix[i][col++] + " ");
		System.out.println();
		
		// quit early if printing middle diagonal
		if (d == 0)
			return;
		
		// print lower diagonal
		int row = d;
		for (int i = 0; row < matrix.length; i++)
			System.out.print(matrix[row++][i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		new Symm("symm.in");
	}

}
