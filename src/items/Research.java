package items;

import java.util.ArrayList;

public class Research {

	private static ArrayList<Item> researches = new ArrayList<Item>();
	
	public static ArrayList<Item> getList()
	{
		return researches;
	}
	
	public Research()
	{
		researches.add(new WoodBlade());
		
		researches.add(new ManaCrystal());
		
		researches.add(new ZephyrSword());
	}
	
	public static int[] getSizeOfGrid(int id, int radius)
	{
		int[] size = {radius*2*researches.get(id).getGrid()[0].length,(int)(Math.cos(Math.toRadians(30.0))*radius*2*researches.get(id).getGrid().length)};
		
		return size;
	}
}
