package books;

import java.util.ArrayList;

import elements.Combination;
import elements.StockElement;
import main.GamePanel;

public class ElementsManuscript extends Book {

	private ArrayList<ManuscriptElement> bookList = new ArrayList<ManuscriptElement>();
	int lastIndex = 6;
	
	public ElementsManuscript()
	{
		super(1560,71);
	}
	
	public void openBook()
	{		
		double gScale = GamePanel.gScale;
		
		ArrayList<StockElement> list = StockElement.getStock();
		
		for(int i = lastIndex; i<list.size(); i++)
		{
			int id = list.get(i).getID();

			if(i-6<16)
			{
				ManuscriptElement e = new ManuscriptElement(id, (int) (200*gScale+(300*gScale*(i%2))-getWidth()), (int) (48*gScale+(64*((i-6)/2))));
				bookList.add(e);
				int[] c = Combination.getRecipe(e.getID());
				if(c != null)
				{
					bookList.add(new ManuscriptElement(c[0], (int) (32*gScale+(300*gScale*(i%2))-getWidth()), (int) (48*gScale+(64*gScale*((i-6)/2)))));
					bookList.add(new ManuscriptElement(c[1], (int) (104*gScale+(300*gScale*(i%2))-getWidth()), (int) (48*gScale+(64*gScale*((i-6)/2)))));
				}
				lastIndex++;
			}
			else
			{
				ManuscriptElement e = new ManuscriptElement(id, (int) (200*gScale+(300*gScale*(i%2))), (int) (48*gScale+(64*((i-22)/2))));
				bookList.add(e);
				int[] c = Combination.getRecipe(e.getID());
				if(c != null)
				{
					bookList.add(new ManuscriptElement(c[0], (int) (32*gScale+(300*gScale*(i%2))), (int) (48*gScale+(64*gScale*((i-22)/2)))));
					bookList.add(new ManuscriptElement(c[1], (int) (104*gScale+(300*gScale*(i%2))), (int) (48*gScale+(64*gScale*((i-22)/2)))));
				}
				lastIndex++;
			}
		}
	}
}
