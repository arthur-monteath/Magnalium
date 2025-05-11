package com.magnalium.game.elements;

public class Combination {
	
	private static final int[][] combinations =
		{
				{2,3}, // Water + Earth
				{1,4}, // Fire + Air
				{4,5}, // Air + Order
				{1,5}, // Fire + Order
				{3,4}, // Earth + Air
				{3,7}, // Earth + Life
				{1,6}, // Fire + Perditio
				{7,5}, // Life + Order
				{3,5},// Earth + Order
				{6,7}, // Perditio + Life
				{7,16},// Life + Death
				{3,9}, // Earth + Motus
				{7,9}, // Life + Motus
				{1,19},// Fire + Creature
				{1,2}, // Fire + Water
				{2,7}, // Water + Life
				{4,6}, // Air + Perditio
				{4,10},
				{24,12},
				{2,6},
		};
	
	//private static final int[] results = {7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,23}; ( just add +7 to the index of combinations )
	
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
				return i+7;
			}
		}
		
		return 0;
	}
	
	public static int[] getRecipe(int e)
	{
		if(e-7<combinations.length && e-7>=0)
		{
			return combinations[e-7];
		}
		
		return null;
	}
}
