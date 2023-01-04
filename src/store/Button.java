package store;

public class Button
{
	private int x, y, price;
	
	private boolean unlocked = false, bought = false;
	
	public Button(int x, int y, int price)
	{
		this.x = x;
		this.y = y;
		this.price = price;
	}
	
	public int[] getPos()
	{
		int[] a = {x,y};
		return a;
	}
	
	public void buy()
	{
		if(unlocked && !bought && Currency.getCurrency() >= price)
		{
			Currency.addCurrency(-price);
			
			bought = true;
		}
	}

	public boolean getBought() 
	{
		return bought;
	}
	
	public boolean getUnlocked()
	{
		return unlocked;
	}
	
	public void unlock() 
	{
		unlocked = true;
	}
}
