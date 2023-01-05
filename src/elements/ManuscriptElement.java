package elements;

import java.util.ArrayList;

public class ManuscriptElement extends Element {

	public ManuscriptElement(int id, int x, int y) {
		super(id);
		
		bookElements.add(this);
		setPos(x,y);
	}
	
	private static ArrayList<ManuscriptElement> bookElements = new ArrayList<ManuscriptElement>();

	public static ArrayList<ManuscriptElement> getBList() 
	{
		return bookElements;
	}
}
