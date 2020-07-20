import java.util.*;

public class Stopping
{
	
   public Stopping()
   {
      Scanner in = new Scanner(System.in);
      int trips = in.nextInt();

      while (trips-- > 0)
      {
         int totalMiles = in.nextInt();
         int gasMiles = in.nextInt();
         int foodMiles = in.nextInt();

         System.out.println(totalMiles + " " + gasMiles + " " + foodMiles);

         int numberOfStops = countNumberOfStops(totalMiles, gasMiles, foodMiles);

         System.out.println(numberOfStops);
      }
   }

   public int countNumberOfStops(int total, int gas, int food)
   {
      HashSet<Integer> stops = new HashSet<Integer>();

      int position = gas;
      while (position < total)
      {
         stops.add(position);   
         position += gas;
      }

      position = food;
      while (position < total)
      {
         stops.add(position);
         position += food;
      }

      return stops.size();

   }

   public static void main(String [] args)
   {
      new Stopping();
   }
}
