package books;

import main.GamePanel;
import research.Research;

public class RecipesGrimoire extends Book {
	
	int page = 0;
	
	private String[] itemNames = 
	{
			"Wood Handle"
	};
	
	public RecipesGrimoire()
	{
		super(1560,432);
	}
	
	public int getPage()
	{
		return page;
	}
	
	public void pressButton(int b)
	{
		if(page+b < Research.getUnlocked().size())
			GamePanel.getInstance().createNewGrid(Research.getUnlocked().get(page+b));
	}
	
	public void turnPage(int pages)
	{
		page+=pages;
		
		if(page < 0)
		{
			page = 0;
		}
	}
	
	public String getName(int b)
	{
		return itemNames[page+b];
	}
	
	public void openBook()
	{
		
	}
}