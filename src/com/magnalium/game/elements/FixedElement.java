package com.magnalium.game.elements;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.magnalium.utils.Hexagon;

import old.GamePanel;

public class FixedElement extends Element {
	
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
