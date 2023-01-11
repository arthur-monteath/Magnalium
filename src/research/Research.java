package research;

import java.util.ArrayList;

public class Research {
	
	private static ArrayList<Integer> available = new ArrayList<Integer>();
	
	private static String[] researchNames =
	{
			"Wood Handle",
			
			"Wood Blade",
			
			"Wood Guard",
			
			"Zephyr's Core",
			
			"a"
	};
	
	private static final int[][] energizedSlots = 
	{
			{0,0},
			
			{0,1},
			
			{2,1},
			
			{4,3},
	};
	
	private static final int[][][] researches =
	{
		// Ímpar desce e Par sobe.
		
		{
			{1,-1},
			{-1,0},
			{0,0},
			{0,-1},
			{-1,1}
		},
		
		{	  	  
			{	-1,3,-1	},
			{	0,0,0	},
			{	0,-1,0	},
			{	0,0,0	},
			{	0,-1,0	},
			{	-1,0,-1	},
			{	-1,1,-1	},
		},
		
		{
			{0,-1,-1,-1,0},
			{0,-1, 0,-1,0},
		    {-1,0,-1,0,-1}
			
		},
			
		{
			{	 -1,9,-1,-1,-1,9,-1	},
			{	 10,0,0,-1,0,0,10	},
			{	-1,-1,-1,0,-1,-1,-1	},
			{	 -1,-1,0,0,0,-1,-1	},
			{	 -1,-1,0,4,0,-1,-1	},
			{	 -1,-1,-1,0,-1,-1,-1},
			{	 -1,-1,0,0,0,-1,-1	},
			{	 -1,0,-1,-1,-1,0,-1	},
			{	 -1,0,-1,-1,-1,0,-1	},
		},
	};
	
	public static int[][] getGrid(int id)
	{
		return researches[id];
	}
	
	public static int[] getEnergized(int id)
	{
		return energizedSlots[id];
	}
	
	public static String getName(int id)
	{
		return researchNames[id];
	}
	
	public static void unlock(int[] a)
	{
		for(int i: a)
		{
			if(!available.contains(i))
				available.add(i);
		}
	}
	
	public static ArrayList<Integer> getUnlocked()
	{
		return available;
	}
	
	public static int[] getSizeOfGrid(int id, int radius)
	{
		int[] size = {radius*2*researches[id][0].length,(int)(Math.cos(Math.toRadians(30.0))*radius*2*researches[id].length)};
		
		return size;
	}
}
