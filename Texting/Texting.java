import java.util.*;

public class Texting
{

	public Texting()
	{
		Scanner in = new Scanner(System.in);
		
		// store the abbreviations
		int numAbbreviations = in.nextInt();
		in.nextLine();			// consume end of line character
		
		
		//		abbreviation, expansion
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < numAbbreviations; i++)
		{
			String abbreviation = in.next().trim();
			String expansion = in.nextLine().trim();
			
			map.put(abbreviation, expansion);
		}
		
		
		// expand all abbreviations in the following lines
		int numLines = in.nextInt();
		in.nextLine();			// consume end of line
		
		for (int i = 0; i < numLines; i++)
		{
			String [] words = in.nextLine().trim().split(" ");
			
			StringBuilder sb = new StringBuilder();
			
			for (String word : words)
			{
				if (map.containsKey(word))
					sb.append(map.get(word));
				else
					sb.append(word);
				
				sb.append(' ');
			}

			// print out the expanded version
			System.out.println(sb.toString().trim());
		}
		
		in.close();
		
	}
	
	public static void main(String [] args)
	{
		new Texting();
	}

}