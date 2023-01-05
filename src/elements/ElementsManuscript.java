package elements;

import java.util.ArrayList;

import main.GamePanel;

public class ElementsManuscript extends Book {

	private ArrayList<ManuscriptElement> bookList = new ArrayList<ManuscriptElement>();
	
	public ElementsManuscript()
	{
		super(1560,71);
	}
	
	public void openBook()
	{		
		ArrayList<StockElement> list = StockElement.getStock();
		
		int x=GamePanel.x-1152, y = (GamePanel.y/2)-432;
		
		for(int i = bookList.size(); i<list.size(); i++)
		{
			int id = list.get(i).getID();
			if(id>6)
			{
				ManuscriptElement e = new ManuscriptElement(id, x+656, (i-6)*80+y+144);
				bookList.add(e);
				int[] c = Combination.getRecipe(e.getID());
				if(c != null)
				{
					bookList.add(new ManuscriptElement(c[0], x+256, (i-6)*80+y+144));
					bookList.add(new ManuscriptElement(c[1], x+400, (i-6)*80+y+144));
				}
			}
		}
	}
}
