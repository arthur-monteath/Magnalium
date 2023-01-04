package items;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

public class InvItem {
	
	private BufferedImage img;
	
	private static float scale = 1.0f;
	public static final int w=(int) Math.floor((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/16.875)*scale),h=(int) Math.floor((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/16.875)*scale);
	
	public static ArrayList<InvItem> list = new ArrayList<InvItem>();
	
	private int[] size = new int[2];
	private int id,row,col,x,y;
	
	public int[] getPos()
	{
		int[] a = {x,y};
		return a;
	}
	
	public int[] GetSlot()
	{
		int[] a = {row, col};
		return a;
	}
	
	public int GetWidth()
	{
		return w*size[0];
	}
	
	public int GetHeight()
	{
		return h*size[1];
	}
	
	public BufferedImage GetImg()
	{
		return img;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int[] GetSize()
	{
		return size;
	}
	
	static public ArrayList<InvItem> GetList()
	{
		return list;
	}
	
	public InvItem(int id, int row, int col)
	{
		this.id = id;
		
		this.row = row;
		this.col = col;
		
		SetImage("/itemsRes/" + id + ".png");
		
		size[0] = Inventory.getSize(id)[0];
		size[1] = Inventory.getSize(id)[1];
		
		x = GamePanel.getMargin()[0]+(int)(col*64*GamePanel.gScale);
		y = GamePanel.getMargin()[1]+(int)(row*64*GamePanel.gScale);
		
		list.add(this);
	}
	
	private void SetImage(String file)
	{
		InputStream is = getClass().getResourceAsStream(file);
		
		try {
			img = ImageIO.read(is);
			
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
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
