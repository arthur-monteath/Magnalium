package elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public class Combination {
	
	private static final int[][] combinations =
		{
				{2,3}, // Water + Earth
				{1,4}, // Fire + Air
				{4,5}, // Air + Order
				{1,5}, // Fire + Order
				{3,5}, // Earth + Order
				{3,7}, // Earth + Life
				{1,6}, // Fire + Perditio
				{7,5}, // Life + Order
				{3,10},// Earth + Vitreum
				{6,7}, // Perditio + Life
				{7,16},// Life + Death
				{3,9}, // Earth + Motus
				{7,9}, // Life + Motus
				{1,19},// Fire + Creature
				{1,2}, // Fire + Water
				{2,7}, // Water + Life
				{4,6}  // Air + Perditio
		};
	
	private static final Map<Integer, Integer> map = Map.ofEntries(
		    entry((Integer)0, (Integer)7),
		    entry((Integer)1, (Integer)8),
		    entry((Integer)2, (Integer)9),
		    entry((Integer)3, (Integer)10),
		    entry((Integer)4, (Integer)11),
		    entry((Integer)5, (Integer)12),
		    entry((Integer)6, (Integer)13),
		    entry((Integer)7, (Integer)14),
		    entry((Integer)8, (Integer)15),
		    entry((Integer)9, (Integer)16),
		    entry((Integer)10, (Integer)17),
		    entry((Integer)11, (Integer)18),
		    entry((Integer)12, (Integer)19),
		    entry((Integer)13, (Integer)20),
		    entry((Integer)14, (Integer)21),
		    entry((Integer)15, (Integer)22),
		    entry((Integer)16, (Integer)23)
	);
/*
				6  Life
				7  Plant
				8  Light
				9  Motion
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
				return map.get(i);
			}
		}
		
		return 0;
	}
}
