package elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Hexagon;
import utils.Utils;

public class Element {
	
	private static ArrayList<Element> list = new ArrayList<Element>();
	public static final String[] primary = {"", "fire", "water", "earth", "air", "ordo", "perditio"};
	
	public static final String[] names = {"", 
			"fire", "water", "earth", "air", "ordo", "perditio", "victus",
			 "lux", "motus", "potentia", "vitreus", "herba", "gelum", "sano", "metallum",
			 "death", "spiritus", "iter", "bestia", "cognitio", "tempestas", "limus", "vacuos"
	};
	
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
	
	public int getID()
	{
		return ID;
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
	
	private Element[] checkForNeighbors()
	{
		Element[] nbl = new Element[6];
		
		Hexagon[][] grid = gamePanel.getGrid().getHexGrid();
		int i = index[0];
		int j = index[1];
		
		if(i%2!=0)
		{
			nbl[0] = i+1 < grid.length ? grid[i+1][j].getElement() : null;
			nbl[1] = i-1 >= 0 ? grid[i-1][j].getElement() : null;
			nbl[2] = i+1 < grid.length && j-1 >= 0 ? grid[i+1][j-1].getElement() : null;
			nbl[3] = i-1 >= 0 && j-1 >= 0 ? grid[i-1][j-1].getElement() : null;
			nbl[4] = j+1 < grid[0].length ? grid[i][j+1].getElement() : null;
			nbl[5] = j-1 >= 0 ? grid[i][j-1].getElement() : null;
		}
		else
		{
			nbl[0] = i+1 < grid.length ? grid[i+1][j].getElement() : null;
			nbl[1] = i-1 >= 0 ? grid[i-1][j].getElement() : null;
			nbl[2] = i+1 < grid.length && j+1 < grid[0].length ? grid[i+1][j+1].getElement() : null;
			nbl[3] = i-1 >= 0 && j+1 < grid[0].length ? grid[i-1][j+1].getElement() : null;
			nbl[4] = j+1 < grid[0].length ? grid[i][j+1].getElement() : null;
			nbl[5] = j-1 >= 0 ? grid[i][j-1].getElement() : null;
		}
		
		ArrayList<Integer[]> a = new ArrayList<Integer[]>();
		for(Integer[] arr: connections)
		{
			if(grid[arr[4]][arr[5]].getElement()!=null && grid[arr[6]][arr[7]].getElement()!=null)
			{
				a.add(arr);
			}
		}
		connections = a;
		for(Element e: nbl)
		{
			if(e!=null && e.getID()>5 && (Combination.getCombinations()[e.getID()-6][0] == ID || Combination.getCombinations()[e.getID()-6][1] == ID))
			{
				Integer[] c = {x+w/2,y+h/2,e.getPos()[0]+w/2,e.getPos()[1]+h/2,index[0],index[1],e.getIndex()[0], e.getIndex()[1]};
				connections.add(c);
			}
		}
		return nbl;
	}
	
	public int[] getIndex() {
		return index;
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
		checkNeighborsOfLocked();
	}

	private void setImg()
	{
		InputStream is = getClass().getResourceAsStream("/ElementsSpritesheet.png");
		
		try {
			img = ImageIO.read(is);
			
			int x = ID%8;
			int y = ID/8;
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

	private boolean locked;
	private Hexagon hex;
	
	public boolean getLock()
	{
		return locked;
	}
	
	private static ArrayList<Integer[]> connections = new ArrayList<Integer[]>();
	
	public static ArrayList<Integer[]> getConnections()
	{
		return connections;
	}
	
	public static void checkNeighborsOfLocked()
	{
		for(Element e: list)
			if(e.getLock())
			{
				e.checkForNeighbors();
			}
			
	}
			
	public void setLock(boolean b) {
		locked = b;
		if(b)
		{
			hex = gamePanel.getGrid().getClosestHex(x+Element.w/2,y+Element.h/2);
			index = hex.getIndex();
			checkNeighborsOfLocked();
		}
		else
		hex = null;
	}
}
