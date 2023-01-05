package items;

public class WoodBlade implements Item {
	
	public static final int[][] grid =
		{	  	  //Par sobe Ímpar desce.
			{	-1,3,-1},
			{	0,0,0},
			{	0,-1,0},
			{	0,0,0},
			{	0,-1,0},
			{	-1,0,-1},
			{	-1,1,-1},
		};
	
	public static final int[] energized = {0,1};

	public int[][] getGrid()
	{
		return grid;
	}
	
	public int[] getEnergized()
	{
		return energized;
	}
}
