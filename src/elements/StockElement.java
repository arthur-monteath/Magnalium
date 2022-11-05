package elements;

import java.util.ArrayList;

import main.GamePanel;

public class StockElement extends Element {

	private static ArrayList<StockElement> stock = new ArrayList<StockElement>();
	
	public StockElement(int id) {
		super(id);
		stock.add(this);
		GamePanel.resetElementsOnStock();
	}
	
	private int amount = 1;

	public void increase(int n)
	{
		amount+=n;
		if(amount<0)
		{
			amount = 0;
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
