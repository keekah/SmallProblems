import java.util.*;

public class Yinyang
{
	static final double PI = 3.14159;

	public Yinyang()
	{
		Scanner in = new Scanner(System.in);
		
		int testCases = in.nextInt();
		
		for (int a = 1; a <= testCases; a++)
		{
			int overallRadius = in.nextInt();
			int yangRadius = in.nextInt();

			int yinRadius = overallRadius - yangRadius;
			
			double overallArea = area(overallRadius);
			double yangArea = area(yangRadius);
			double yinArea = area(yinRadius);
			
			overallArea -= yangArea + yinArea;
			
			yangArea += overallArea / 2;
			yinArea += overallArea / 2;
			
			System.out.printf("Taijitu #%d: yin %.2f, yang %.2f\n\n", a, yinArea, yangArea);
		}
		
		in.close();
	}
	
	public double area(int r)
	{
		if (r < 0)
			throw new IllegalArgumentException();
		
		return PI * r * r;
	}
	
	public static void main(String[] args)
	{
		new Yinyang();
	}

}