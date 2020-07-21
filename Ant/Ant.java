// Team Practice 
// 8 September 2018

// Ant Tunneling

import java.io.*;
import java.util.*;

public class Ant
{
	public static final int oo = Integer.MAX_VALUE;
	
	int [][] matrix;

	public Ant(String filename)
	{
		try (Scanner in = new Scanner(new File(filename)))
		{
			int numCases = in.nextInt();
		
			for (int a = 1; a <= numCases; a++)
			{
				int V = in.nextInt();
				int E = in.nextInt();
				
				matrix = new int[V+1][V+1];
				for (int i = 0; i < V+1; i++)
					Arrays.fill(matrix[i], oo);
				
				for (int i = 0; i < E; i++)
				{
					int u = in.nextInt();
					int v = in.nextInt();
					int weight = in.nextInt();
					
					matrix[u][v] = weight;
					matrix[v][u] = weight;
				}
				
				System.out.print("Campus #" + a + ": ");
				
				prims();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void prims()
	{
		// set up some stuff
		int [] distance = new int[matrix.length];
		int [] parent = new int[matrix.length];
		boolean [] mst = new boolean[matrix.length];
		
		// initialize our data structures
		for (int i = 0; i < matrix.length; i++)
		{
			distance[i] = oo;
			parent[i] = 0;
			mst[i] = false;
		}
		
		// choose a starting vertex and update our data structures;
		int start = 1;
		distance[start] = 0;
		parent[start] = -1;
		
		for (int i = 1; i < matrix.length; i++)
		{
			int node = minDistance(distance, mst);
			
			// if node's value is -1 then we have a node that is disconnected
			if (node == -1)
			{
				System.out.println("I'm a programmer, not a miracle worker!");
				return;
			}
			
			mst[node] = true;
			
			
			// update all the distance and parents of nodes adjacent to node
			for (int j = 1; j < matrix.length; j++)
			{
				if (matrix[j][node] < distance[j] && !mst[j])
				{
					parent[j] = node;
					distance[j] = matrix[j][node];
				}
			}
		}
		
		// we want to return the total cost of connecting all the ant hills
		// i.e. we want the total weight of all edges in our mst
		int total = 0;
		for (int i = 1; i < distance.length; i++)
		{
			// node i is disconnected 
			if (distance[i] == oo)
			{
				System.out.println("I'm a programmer, not a miracle worker!");
				return;
			}
				
			total += distance[i];
		}
		
		System.out.println(total);
		
	}
	
	
	// get the index of the next node to process
	public int minDistance(int [] distance, boolean [] mst)
	{
		int min = oo, index = -1;
		
		for (int i = 1; i < distance.length; i++)
			if (distance[i] < min && !mst[i])
			{
				min = distance[i];
				index = i;
			}
		
		return index;
	}
	
	
	public void printMatrix()
	{
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix.length; j++)
			{
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args)
	{
		new Ant("ant.in");
	}
	
	class Edge
	{
		int u;
		int v;
		
		public Edge(int u, int v)
		{
			this.u = u;
			this.v = v;
		}
	}

}
