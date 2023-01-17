package utils;

public class Utils {
	
	public static int[] centerPosition(int x, int y, int width, int height)
	{
		int[] a = {x-(width/2), y-(height/2)};
		return a;
	}
	
	public static float smoothStep(float start, float end, float x)
	{
		   if (x < start)
		      return 0;

		   if (x >= end)
		      return 1;

		   // Scale/bias into [0..1] range
		   x = (x - start) / (end - start);

		   return x * x * (3 - 2 * x);
	}
}
