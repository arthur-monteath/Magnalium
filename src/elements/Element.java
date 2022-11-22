package elements;

import java.awt.Toolkit;
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
			"fire", "water", "earth", "air", "order", "chaos", 
			"life","light","motion","energy","crystal","plant",
			"ice","sano","metal","death","spirit","iter", 
			"creature","brain","storm","slime","void","sorcery", 
			"natura"
	};
	
	private BufferedImage img;
	
	private int x=0,y=0,ID=0;
	public static final double scale = 1.25f;
	public static final int w=(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/16.875)*scale),h=(int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/16.875)*scale);
	
	
	public static ArrayList<Integer> getList()
	{
		return list;
	}
	 
	public Element(int id)
	{
		ID = id;
		
		setImg("/ElementsSpritesheet.png");
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

	public void setImg(String file)
	{
		InputStream is = getClass().getResourceAsStream(file);
		
		try {
			img = ImageIO.read(is);
			
			int x = ID%8;
			int y = ID/8;
			unknown = img.getSubimage(0, 0, 64, 64);
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
	
	private BufferedImage unknown;
	
	public BufferedImage getUnknown()
	{
		return unknown;
	}
	
	public BufferedImage getImg()
	{
		return img;
	}
}
