import java.io.*;
import java.util.*;

public class Palind
{

	HashMap<Character, Character> same;
	
	public Palind(String filename)
	{
		try (Scanner in = new Scanner(new File(filename)))
		{
			int testCases = in.nextInt();
			
			for (int a = 1; a <= testCases; a++)
			{
				// read in pairs of phonemes
				int numPairs = in.nextInt();
				same = new HashMap<Character, Character>();
				
				for (int i = 0; i < numPairs; i++)
				{
					// store (c1, c2) and (c2, c1) so we can loop up both ways
					char c1 = in.next().charAt(0);
					char c2 = in.next().charAt(0);
					
					same.put(c1, c2);
					same.put(c2, c1);
				}
					
				
				System.out.println("Test case #" + a + ":");
				
				// read in q strings to test
            int q = in.nextInt();
            in.nextLine();
				for (int i = 0; i < q; i++)
				{
					String str = in.nextLine().trim();
					System.out.println(str + " " + (isPhonemePalindrome(str) ? "YES" : "NO"));
				}
					
				
				System.out.println();
			}
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean isPhonemePalindrome(String str)
	{
		for (int i = 0, j = str.length()-1; i < j; i++, j--)
		{
			char c1 = str.charAt(i);
			char c2 = str.charAt(j);
			
			if (c1 != c2)
				if (same.get(c1) == null || same.get(c1) != c2)
					return false;
		}
		
		return true;
	}
	
	public static void main(String[] args)
	{
		new Palind("palind.in");
	}
}
