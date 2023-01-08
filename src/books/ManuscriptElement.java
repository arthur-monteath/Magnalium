package books;

import java.awt.Toolkit;
import java.util.ArrayList;

import elements.Element;

public class ManuscriptElement extends Element {

	public static final double scale = 0.75;
	public static final int w=(int) Math.floor((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/16.875)*scale),h=(int) Math.floor((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/16.875)*scale);
	
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
