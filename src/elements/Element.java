package elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Element {
	
	//private static ArrayList<Element> list = new ArrayList<Element>();
	private static ArrayList<Integer> list = new ArrayList<Integer>();
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
	
	public static ArrayList<Integer> getList()
	{
		return list;
	}
	
	public Element(int id)
	{
		ID = id;
		boolean has = false;
		for(Integer e: list)
		{
			if(e == id)
			{
				has = true;
			}
		}
		if(!has)
		{
			list.add(id);
		}
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
	
	public int[] getIndex() {
		return index;
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
}
