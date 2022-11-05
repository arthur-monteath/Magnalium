package elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Hexagon;
import utils.Utils;

public class GrabElement extends Element{
	
	private BufferedImage img;
	
	private int x=0,y=0,mx=0,my=0;
	public static final int w=64,h=64;
	
	private GamePanel gamePanel;
	private static GrabElement grabElement = null;
	
	public GrabElement(GamePanel gamePanel, int id) {
		super(id);
		this.gamePanel = gamePanel;
		grabElement = this;
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
	
	private Hexagon hex;
	
	public GridElement lock() {
			hex = gamePanel.getGrid().getClosestHex(x+Element.w/2,y+Element.h/2);
			GridElement el = new GridElement(gamePanel, getID(), false);
			el.setPos(hex.getPos()[0]-w/2,hex.getPos()[1]-h/2);
			hex.setElement(el);
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
