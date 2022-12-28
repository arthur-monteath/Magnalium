package items;

public class Inventory {

	private static int[][] inventory = new int[32][6];
			
	private static String[] items = 	    {"" ,"wood", "rock", "w_handle", "w_guard", "w_blade", "w_toolGuard", "w_axe"};
	private static int[][] itemSizes =	  {{0,0}, {2,2}, {1,1} ,   {1,2}   ,   {2,1}  ,   {1,2}  ,     {1,1}    ,  {2,2} };
	private static int[] itemAmounts =     {0   ,   0  ,   0   ,     0     ,     0    ,     0    ,       0      ,    0   };
	
	public static int[][] getInv()
	{
		return inventory;
	}
	
	public static int[] getSize(int id)
	{
		return itemSizes[id];
	}
	
	public static int getAmount(int id)
	{
		return itemAmounts[id];
	}
	
	public static void addItem(String name)
	{
		int id = nameToId(name);
		
		for(int i = 0; i<inventory.length; i++)
		{
			for(int j = 0; j<inventory[0].length; j++)
			{
				if(inventory[i][j] == 0)
				{
					if(checkSlot(i, j, itemSizes[id]))
					{
						new InvItem(id, i, j);
						
						for(int r = 0; r<itemSizes[id][1]; r++)
						{
							for(int c = 0; c<itemSizes[id][0]; c++)
							{
								inventory[r+i][c+j] = id;
							}
						}
						
						return;
					}
				}
			}
		}
	}
	
	private static boolean checkSlot(int i, int j, int[] size)
	{
		for(int r = 0; r<size[1]; r++)
			for(int c = 0; c<size[0]; c++)
			{
				if(j+c>=inventory[0].length || i+r>=inventory.length || inventory[i+r][j+c] != 0)
					return false;
			}
		
		return true;
	}
	
	public static int nameToId(String name)
	{
		for(int i = 0; i<items.length; i++)
		{
			if(items[i].equals(name))
			{
				return i;
			}
		}
		return 0;
	}
}
