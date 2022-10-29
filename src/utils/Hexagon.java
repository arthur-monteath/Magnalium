package utils;

import java.awt.Color;

import elements.Element;

public class Hexagon {

	private int[] x, y;
	private int xPos, yPos;
	private int[] index = new int[2];
	private int radius;
	private Color color;
	
	public boolean isFree()
	{
		return element == null;
	}
	
	// @param r = radius of the hexagon
	public Hexagon(int xPos, int yPos, int r, int row, int col)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = r;
		
		index[0] = row;
		index[1] = col;
		
		color = new Color(222,180,108,255);
		
		if(index[0]==0)
			colorToWhite();
		
		x = new int[6];
		y = new int[6];
		
		x[0] = r;
		x[1] = (int) Math.round(Math.cos(Math.toRadians(60.0))*r);
		x[2] = (int) Math.round(Math.cos(Math.toRadians(120.0))*r);
		x[3] = -r;
		x[4] = (int) Math.round(Math.cos(Math.toRadians(240.0))*r);
		x[5] = (int) Math.round(Math.cos(Math.toRadians(300.0))*r);
		
		y[0] = 0;
		y[1] = (int) Math.round(Math.sin(Math.toRadians(60.0))*r);
		y[2] = (int) Math.round(Math.sin(Math.toRadians(120.0))*r);
		y[3] = 0;
		y[4] = (int) Math.round(Math.sin(Math.toRadians(240.0))*r);
		y[5] = (int) Math.round(Math.sin(Math.toRadians(300.0))*r);
		
		for(int i = 0; i<x.length; i++)
		{
			x[i] += xPos;
			y[i] += yPos;
		}
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void colorToDefault()
	{
		color = new Color(222,180,108,255);
	}
	
	public void colorToWhite()
	{
		color = Color.white;
	}
	
	public int[][] getPoints()
	{
		int[][] a = {x,y};
		return a;
	}
	
	public int getApothem()
	{
		return (int) (Math.cos(Math.toRadians(30.0))*radius);
	}
	
	public int[] getPos()
	{
		int[] a = {xPos, yPos};
		return a;
	}
	
	public int[] getIndex()
	{
		return index;
	}

	private Element element;
	
	public Element getElement()
	{
		return element;
	}
	
	public void checkState()
	{
		if(element!=null && element.getGrabbed())
		{
			element.setLock(false);
			element = null;
		}
	}
	
	public void setElement(Element element) 
	{
		this.element = element;
		element.setLock(true);
	}
}
