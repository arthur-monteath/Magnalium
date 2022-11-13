package elements;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.GamePanel;
import utils.Hexagon;

public class FixedElement extends Element {
	
	public static final int w=64,h=64;
	
	public FixedElement(int id, int x, int y) {
		super(id);

		fixedElements.add(this);
		setPos(x,y);
	}
	
	private static ArrayList<FixedElement> fixedElements = new ArrayList<FixedElement>();

	public static ArrayList<FixedElement> getFList() 
	{
		return fixedElements;
	}
}
