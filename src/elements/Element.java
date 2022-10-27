package elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Utils;

public class Element {
	
	private static ArrayList<Element> list = new ArrayList<Element>();
	public static final String[] names = {"", "fire", "water", "earth", "air", "ordo"};
	private BufferedImage img;
	
	private int x=0,y=0,mx=0,my=0, ID=0;
	public static final int w=64,h=64;
	private int[] index;
	
	private GamePanel gamePanel;
	
	public static ArrayList<Element> getList()
	{
		return list;
	}
	
	public Element(GamePanel gamePanel, int id)
	{
		this.gamePanel = gamePanel;
		ID = id;
		list.add(this);
		setImg();
	}
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int[] getPos()
	{
		int[] a = {x,y};
		return a;
	}
	
	public String getName()
	{
		return names[ID];
	}
	
	public static String getNameByID(int ID)
	{
		return names[ID];
	}
	
	public int getIdByName(String name)
	{
		for(int i = 0; i<names.length; i++)
		{
			if(name.equals(names[i]))
			{
				return i;
			}
		}
		return 0;
	}
	
	public void grid(int[] index)
	{
		this.index = index;
		checkForNeighbors(gamePanel.getGrid());
	}
	
	private void checkForNeighbors(Grid grid)
	{
		
	}
	
	public void updatePos()
	{
		mx = gamePanel.getMousePos()[0];
		my = gamePanel.getMousePos()[1];
		
		int[] pos = Utils.centerPosition(mx, my, w, h);
		x = pos[0];
		y = pos[1];
	}

	private boolean grabbed = false;
	
	public boolean getGrabbed()
	{
		return grabbed;
	}
	
	private static boolean oGrabbed = false;
	
	public void setGrabbed(boolean b) {
		if(oGrabbed)
		{
			if(!b)
			{
				grabbed = b;
				oGrabbed = false;
			}
		}
		else
		{
			grabbed = b;
			oGrabbed = b;
		}
		
		
	}

	private void setImg()
	{
		InputStream is = getClass().getResourceAsStream("/ElementsSpritesheet.png");
		
		try {
			img = ImageIO.read(is);
			
			int x = ID%4;
			int y = ID/4;
			img = img.getSubimage(x*64, y*64, 64, 64);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public BufferedImage getImg()
	{
		return img;
	}
}
