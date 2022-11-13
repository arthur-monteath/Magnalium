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
		researches.add(new ManaCrystal());
		
		researches.add(new ZephyrSword());
	}
}
