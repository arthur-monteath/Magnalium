package com.magnalium.game.items;

import java.awt.image.BufferedImage;

import com.magnalium.engine.ui.GamePanel;
import com.magnalium.utils.Utils;

public class GrabbedItem 
{
	private static GrabbedItem instance = null;
	
	private static InvItem item = null;
	
	private static int id;
	
	private BufferedImage img;
	
	private int x,y,w,h;
	
	public int[] getPos()
	{
		int[] a = {x,y};
		return a;
	}
	
	public void updateToMouse(int mx, int my)
	{
		int[] pos = Utils.centerPosition(mx, my, w, h);
		x = pos[0];
		y = pos[1];
	}
	
	public int getWidth()
	{
		return w;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public GrabbedItem(InvItem item)
	{
		instance = this;
		
		GrabbedItem.item = item;
		
		w = (int) (item.GetSize()[0]*64*GamePanel.gScale);
		h = (int) (item.GetSize()[1]*64*GamePanel.gScale);
		
		id = item.getId();
		
		img = item.GetImg();
	}
	
	public BufferedImage getImg()
	{
		return img;
	}
	
	public InvItem getItem()
	{
		return item;
	}
	
	public static GrabbedItem getGrabbed()
	{
		return instance;
	}
	
	public static void release()
	{
		Inventory.addItem(id);
		
		instance = null;
	}

	public static int getId() {
		return id;
	}
}
