package store;

public class Currency {

	static int currency = 999999;
	
	public static int getCurrency()
	{
		return currency;
	}
	
	public static void addCurrency(int amount)
	{
		currency+=amount;
	}
}
