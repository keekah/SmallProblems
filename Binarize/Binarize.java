import java.util.*;

public class Binarize
{

	public Binarize()
	{
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		
		while (n-- > 0)
		{
			int num = in.nextInt();
			
			System.out.println("Input value: " + num);
			
			int val = 2;
			while (val < num)
				val *= 2;
			
			System.out.println(val + "\n");
		}
		
		in.close();
	}
	
	public static void main(String[] args)
	{
		new Binarize();
	}

}