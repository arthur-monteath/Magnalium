package com.magnalium.game.elements;

import com.magnalium.engine.ui.GamePanel;
import com.magnalium.utils.Hexagon;
import com.magnalium.utils.Utils;

public class GrabElement extends Element{
	
	private int x=0,y=0;
	
	private GamePanel gamePanel;
	private static GrabElement grabElement = null;
	
	public GrabElement(GamePanel gamePanel, int id) {
		super(id);
		this.gamePanel = gamePanel;
		grabElement = this;
		
		if(!getList().contains(id))
		{
			getList().add(id);
		}
		
		GamePanel.resetElementsOnStock();
	}
	
	public GrabElement(GamePanel gamePanel, int id, int x, int y) {
		super(id);
		this.x = x;
		this.y = y;
		this.gamePanel = gamePanel;
		grabElement = this;
		
		if(!getList().contains(id))
		{
			getList().add(id);
		}
		
		GamePanel.resetElementsOnStock();
	}
	
	public void updateToMouse(int mx, int my)
	{
		int[] pos = Utils.centerPosition(mx, my, w, h);
		x = pos[0];
		y = pos[1];
	}
	
	public int[] getPos()
	{
		int[] a = {x,y};
		return a;
	}
	
	public GamePanel getPanel()
	{
		return gamePanel;
	}
	
	public GridElement lock(Hexagon hex) {
		GridElement el = new GridElement(gamePanel, getID(), hex.getIndex()[0],hex.getIndex()[1],false);
		el.setPos(hex.getPos()[0]-w/2,hex.getPos()[1]-h/2);
		hex.setElement(el);
		GridElement.checkNeighborsOfGridElements();
		grabElement = null;
		return el;
	}
	
	public void release()
	{
		grabElement = null;
	}
	
	public static GrabElement getGrabbed()
	{
		return grabElement;
	}
}
