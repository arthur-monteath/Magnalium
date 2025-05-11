package com.magnalium.game.elements;

import java.util.ArrayList;

import old.GamePanel;

public class StockElement extends Element {

	private static ArrayList<StockElement> stock = new ArrayList<StockElement>();
	
	public StockElement(int id) {
		super(id);
		
		stock.add(this);
		
		if(!getList().contains(id))
		{
			getList().add(id);
		}
		
		GamePanel.resetElementsOnStock();
	}
	
	private int amount = 1;

	public void increase(int n)
	{
		amount+=n;
		if(amount<=0)
		{
			setImg("/ElementsSpritesheetZero.png");
			amount = 0;
		}
		else
		{
			setImg("/ElementsSpritesheet.png");
		}
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public static ArrayList<StockElement> getStock() 
	{
		return stock;
	}
}
