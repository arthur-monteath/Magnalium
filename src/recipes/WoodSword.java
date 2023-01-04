package recipes;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

public class WoodSword extends Recipe {

	static final int[] itemRecipe = {3,4,5};
	static final int[] elementRecipe = {1};
	
	public WoodSword() 
	{
		super(itemRecipe, elementRecipe);
		
		for(int i = 0; i<itemRecipe.length; i++)
		{
			itemSlots[i][0] = gZero+(int)(gScale*1312/2) + (int)(320*gScale*Math.cos(Math.toRadians( 270 + i*360/itemRecipe.length)));
			itemSlots[i][1] = (int)(32*gScale+1016*gScale/2) + (int)(320*gScale*Math.sin(Math.toRadians( 270 + i*360/itemRecipe.length)));
		}
		
		System.out.println(gScale + " " + gZero);
	}

	@Override
	public void drawRecipe(Graphics g) {
		
		g.setColor(Color.white);
		g.fillOval(gZero+(int)(gScale*1312/2)-(int)(64*gScale), (int)(GamePanel.y/2)-(int)(64*gScale), (int)(128*gScale), (int)(128*gScale));
		g.drawOval(gZero+(int)(gScale*1312/2)-(int)(320*gScale), (int)(GamePanel.y/2)-(int)(320*gScale), (int)(640*gScale), (int)(640*gScale));
		
		for(int i = 0; i<itemRecipe.length; i++)
		{
			g.drawLine(gZero+(int)(gScale*1312/2), (int)(GamePanel.y/2), gZero+(int)(gScale*1312/2) + (int)(320*gScale*Math.cos(Math.toRadians( 270 + i*360/itemRecipe.length))), (int)(32*gScale+1016*gScale/2) + (int)(320*gScale*Math.sin(Math.toRadians( 270 + i*360/itemRecipe.length))));
			g.fillOval(gZero+(int)(gScale*1312/2) + (int)(320*gScale*Math.cos(Math.toRadians( 270 + i*360/itemRecipe.length))-64*gScale), (int)(32*gScale+1016*gScale/2) + (int)(320*gScale*Math.sin(Math.toRadians( 270 + i*360/itemRecipe.length))-64*gScale),(int)(128*gScale),(int)(128*gScale));
		}
	}
}
