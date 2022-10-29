package elements;

public class Combination {
	
	private static final int[][] combinations =
		{
				{2,3}, // Water + Earth
				{3,6},  // Earth + Life
				{1,4}, // Fire + Air
				{4,5}, // Air + Order
		};
/*
				6  Life
				7  Plant
				8  Light
				9  Motus
				10
				11,
				12,
				13,
				14,
				15,
				16,
				17,
				18,
				19,
*/
	public static int[][] getCombinations()
	{
		return combinations;
	}
	
	public static int getResult(int e1, int e2)
	{
		for(int i = 0; i<combinations.length; i++)
		{
			if((combinations[i][0] == e1 && combinations[i][1] == e2) || (combinations[i][0] == e2 && combinations[i][1] == e1))
			{
				return i+6;
			}
		}
		
		return 0;
	}
}
