import java.io.*;
import java.util.*;

public class Tnine
{
	Map<Character, String> keypad;					// contains letter=number associations
	Map<String, ArrayList<String>> dictionary;		// maps a number sequence to a list of words

	public Tnine()
	{
		initializeKeypad();
		
		try (Scanner in = new Scanner(new File("tnine.in")))
		{
			int numDictionaryWords = in.nextInt();
			initializeDictionary(in, numDictionaryWords);
			
			int numMessages = in.nextInt();
			in.nextLine();		// consume '\n'
			
			for (int i = 1; i <= numMessages; i++)
			{
				System.out.print("Message #" + i + ": ");
				translateMessage(in.nextLine());
				System.out.println();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void initializeKeypad()
	{
		keypad = new HashMap<Character, String>();
		
		keypad.put('a', "2");
		keypad.put('b', "2");
		keypad.put('c', "2");
		
		keypad.put('d', "3");
		keypad.put('e', "3");
		keypad.put('f', "3");
		
		keypad.put('g', "4");
		keypad.put('h', "4");
		keypad.put('i', "4");
		
		keypad.put('j', "5");
		keypad.put('k', "5");
		keypad.put('l', "5");
		
		keypad.put('m', "6");
		keypad.put('n', "6");
		keypad.put('o', "6");
		
		keypad.put('p', "7");
		keypad.put('q', "7");
		keypad.put('r', "7");
		keypad.put('s', "7");
		
		keypad.put('t', "8");
		keypad.put('u', "8");
		keypad.put('v', "8");
		
		keypad.put('w', "9");
		keypad.put('x', "9");
		keypad.put('y', "9");
		keypad.put('z', "9");
	}
	
	public void initializeDictionary(Scanner in, int numWords)
	{
		dictionary = new HashMap<String, ArrayList<String>>();
		
		for (int i = 0; i < numWords; i++)
		{
			String word = in.next();
			
			String seq = getNumberSequence(word);
			
			ArrayList<String> list = dictionary.get(seq);
			
			if (list == null)
				dictionary.put(seq, new ArrayList<String>());
			
			list = dictionary.get(seq);
			list.add(word);
		}
	}
	
	public String getNumberSequence(String word)
	{
		String s = "";
		
		for (int i = 0; i < word.length(); i++)
			s += keypad.get(word.charAt(i));
		
		return s;
   }
   
	// message is given as a number sequence
	public void translateMessage(String message)
	{
		boolean multiplePossible = false;
		int possibleMessages = 0;
		String output = "";
		
		String [] words = message.split(" ");
		
		for (int i = 0; i < words.length; i++)
		{
			ArrayList<String> list = dictionary.get(words[i]);
			
			// check number of words that correspond to this sequence
			if (list == null)
			{
				System.out.println("not a valid text");
				return;
			}
			
			if (list.size() > 1)
			{
				possibleMessages += list.size();
				multiplePossible = true;
			}
				
			
			else
				output += list.get(0) + " ";
		}
		
		if (multiplePossible)
			System.out.println("there are " + possibleMessages + " possible messages");
		else
			System.out.println(output);
	}

	public static void main(String[] args)
	{
		new Tnine();
	}
}
