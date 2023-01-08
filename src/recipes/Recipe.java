package recipes;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

public abstract class Recipe {
	
	
	
	int[] itemIds, elementIds;
	
	protected int gZero, radius;
	
	protected int[] items, elements;
	
	protected int[][] itemSlots, elementSlots;
	
	protected double gScale;
	
	public Recipe(int[] itemRecipe, int[] elementRecipe)
	{
		itemIds = itemRecipe;
		elementIds = elementRecipe;
		
		items = new int[itemIds.length];
		elements = new int[elementIds.length];
		
		itemSlots = new int[itemIds.length][2];
		elementSlots = new int[elementIds.length][2];
		
		// get from gPanel
		
		gScale = GamePanel.gScale;
		gZero = GamePanel.getGzero();
	}
	
	public int[][] getItemSlots()
	{
		return itemSlots;
	}
	
	public int[][] getElementSlots()
	{
		return elementSlots;
	}
	
	public abstract void drawRecipe(Graphics g);
	
	public boolean recipeComplete()
	{
		
		for(int i = 0; i<itemIds.length; i++)
		{
			boolean hasItem = false;
			
			for(int j = 0; j<items.length; j++)
			{
				if(itemIds[i] == items[j])
				{
					hasItem = true;
				}
			}
			
			if(!hasItem)
			{
				return false;
			}
		}
		
		return true;
	}
}