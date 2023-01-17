package elements;

import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class LootElement extends Element {

	private double xForce, yForce;
	
	private static ArrayList<LootElement> lootList = new ArrayList<LootElement>();
	
	public static ArrayList<LootElement> getLootElementList()
	{
		return lootList;
	}
	
	public LootElement(int id, int x, int y) {
		super(id);
		
		setPos(x,y);
		
		Random rand = new Random();
		
		xForce = Math.round(rand.nextDouble(-5, 5)*10.0/10.0);
		yForce = Math.round(rand.nextDouble(-5, 5)*10.0/10.0);
		
		lootList.add(this);
	}
	
	public static void updateForces()
	{
		for(LootElement e: lootList)
		{
			e.updateForce();
		}
	}
	
	public void updateForce()
	{
		
		setPos((int)(getPos()[0]+xForce),(int)(getPos()[1]+yForce));
		
		if(xForce > 0.1)
			xForce -= 0.1;
		else if(xForce < 0.1)
			xForce += 0.1;
		else
			xForce = 0;
		
		if(yForce > 0.1)
			yForce -= 0.1;
		else if(yForce < 0.1)
			yForce += 0.1;
		else
			yForce = 0;
	}
}
