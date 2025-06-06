package com.magnalium.game.items;

public interface Item {
	
	public static final int[][] grid =
	{	  	  //Par sobe Ímpar desce.
			{	 -1,9,-1,-1,-1,9,-1	},
			{	 10,0,0,-1,0,0,10	},
			{	-1,-1,-1,0,-1,-1,-1	},
			{	 -1,-1,0,0,0,-1,-1	},
			{	 -1,-1,0,4,0,-1,-1	},
			{	 -1,-1,-1,0,-1,-1,-1},
			{	 -1,-1,0,0,0,-1,-1	},
			{	 -1,0,-1,-1,-1,0,-1	},
			{	 -1,0,-1,-1,-1,0,-1	},
	};
	
	public static final int[] energized = {4,3};
	
	public int[][] getGrid();
	public int[] getEnergized();
}
