import java.io.*;
import java.util.*;

import javax.swing.tree.TreeNode;

public class Hiking
{

	int [][] matrix;
	int [] parent;
	
	public Hiking()
	{
		
		try (Scanner in = new Scanner(new File("hiking.in")))
		{
			int numTrails = in.nextInt();
			
			for (int t = 1; t <= numTrails; t++)
			{
				// initialize adjacency matrix
				int N = in.nextInt();
				matrix = new int[N][N];
				
				parent = new int[N];
				
				for (int i = 0; i < N-1; i++)
				{
					int u = in.next().charAt(0) - 'A';
					int v = in.next().charAt(0) - 'A';
					int cost = in.nextInt();
					
					matrix[u][v] = cost;
					matrix[v][u] = cost;
					
					if (i == 0)
						parent[u] = -1;
					
					else
						parent[v] = u;
				}
				
				System.out.printf("Trail #%d:\n", t);
				ArrayList<Integer> traversal = bfs();
				traversal.add(0);	// return to the root
				getTotalSum(traversal);
				System.out.println();	
				
			}
		}
		
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void getTotalSum(ArrayList<Integer> traversal)
	{
		int totalCost = 0;
		
		for (int i = 0; i < traversal.size()-1; i++)
		{
			int u = traversal.get(i);
			int v = traversal.get(i+1);
			
			// we need to find the lowest common ancestor of u and v to determine
			// how far we had to walk to get from u to v 

			int lca = getLCA(u, v);
			
			totalCost += getCost(u, lca);
			totalCost += getCost(v, lca);
		}
		
		System.out.println("BFS hike will cost " + totalCost);
	}
	
	// get the cost of traveling from u to its ancestor
	public int getCost(int u, int ancestor)
	{
		int cost = 0;
		
		// get u's ancestors again
		ArrayList<Integer> ancestorsU = new ArrayList<Integer>();
		int i = u;
		ancestorsU.add(u);
		while (parent[i] != -1)
		{
			ancestorsU.add(parent[i]);
			i = parent[i];
		}
		
		for (i = 0; i < ancestorsU.size()-1; i++)
		{
			if (ancestorsU.get(i) == ancestor)
				break;
			
			int node = ancestorsU.get(i);
			int parent = ancestorsU.get(i+1);
			
			cost += matrix[node][parent];
		}
		
		return cost;
	}
	
	// Lowest common ancestor is the node from which u can be reached, and v can be reached. If u 
	// is the parent of v, the lowest common ancestor is u. In other words, each node is 
	// its own most recent ancestor. 
	public int getLCA(int u, int v)
	{
		ArrayList<Integer> ancestorsU = new ArrayList<Integer>();
		ArrayList<Integer> ancestorsV = new ArrayList<Integer>();
		
		int i = u;
		ancestorsU.add(u);
		while (parent[i] != -1)
		{
			ancestorsU.add(parent[i]);
			i = parent[i];
		}
		
		i = v;
		ancestorsV.add(v);
		while (parent[i] != -1)
		{
			ancestorsV.add(parent[i]);
			i = parent[i];
		}
		
		// assign lca as the root node
		int lca = 0;
		
		for (int U = ancestorsU.size()-1, V = ancestorsV.size()-1; U >= 0 && V >= 0; --U, --V)
		{
			if (ancestorsU.get(U) == ancestorsV.get(V))
				lca = ancestorsU.get(U);
			else
				break;
		}
			
		return lca;
	}
	
	
	public ArrayList<Integer> bfs()
	{
		// start at 'A'
		int start = 0;
		
		boolean [] visited = new boolean[matrix.length];
		Queue<Integer> q = new ArrayDeque<Integer>();
		ArrayList<Integer> traversal = new ArrayList<Integer>();
		
		visited[start] = true;
		q.add(start);
		
		while (!q.isEmpty())
		{
			int node = q.remove();
			traversal.add(node);
			
			for (int i = 0; i < matrix.length; i++)
			{
				if (matrix[i][node] != 0 && !visited[i])
				{
					visited[i] = true;
					q.add(i);
				}
			}
		}
		
		return traversal;
	}
	
	
	public static void main(String[] args)
	{
		new Hiking();
	}

}
