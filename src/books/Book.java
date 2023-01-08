package books;

import java.util.ArrayList;

import main.GamePanel;

public class Book 
{
	private int x,y,w,h,gX,gY; //grabX and grabY
	
	private static boolean hasGrabbed = false; // if there is any book that is grabbed
	private boolean isGrabbed = false;
	
	public Book(int x, int y)
	{
		double gScale = GamePanel.gScale;
		
		this.x = (int) (GamePanel.getZero() + x*gScale);
		this.y = (int) (y*gScale);
		w = (int) (576*gScale);
		h = (int) (576*gScale);
	}
	
	public int getWidth()
	{
		return w;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	public int[] getPos()
	{
		int[] a = {x,y};
		return a;
	}
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void updateGrab(int mx, int my)
	{
		if(isGrabbed)
		{
			x = mx - gX;
			y = my - gY;
		}
	}
	
	public void grabbed(int mx, int my)
	{
		if(hasGrabbed == false)
		{
			gX = mx-x;
			gY = my-y;
			
			isGrabbed = true;
			hasGrabbed = true;
		}
	}
	
	public void release()
	{
		isGrabbed = false;
		hasGrabbed = false;
	}
	
	public boolean getGrabbed()
	{
		return isGrabbed;
	}
}
