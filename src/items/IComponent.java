package items;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Utils;

public abstract class IComponent {
	
private BufferedImage img;
	
	private int x=0,y=0,w=0,h=0,ID=0;
	private int[] pos;
	//public static final double scale = 1f;
	
	private static ArrayList<IComponent> components = new ArrayList<IComponent>();
	
	public static ArrayList<IComponent> getList()
	{
		return components;
	}
	
	public int getWidth()
	{
		return w;
	}
	
	public int getHeight()
	{
		return h;
	}
	
	public IComponent(int ID, int x, int y)
	{
		setImg("/itemsRes/" + ID + ".png");
		this.x = x;
		this.y = y;
		int[] a = {x,y};
		pos = a;
		w=(int) (img.getWidth()*GamePanel.gScale);
		h=(int) (img.getHeight()*GamePanel.gScale);
		
		components.add(this);
	}
	
	private void setImg(String file)
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
	
	public BufferedImage getImg()
	{
		return img;
	}

	public int[] getPos() 
	{	
		return pos;
	}
	
	private boolean locked = false;
	
	public boolean isLocked()
	{
		return locked;
	}

	public void updateToMouse(int mx, int my)
	{
		pos = Utils.centerPosition(mx, my, w, h);
		x = pos[0];
		y = pos[1];
	}
	
	private static IComponent grabbed;
	
	public void setGrabbed()
	{
		grabbed = this;
	}
	
	public static IComponent getGrabbed()
	{
		return grabbed;
	}

	public void release() {
		
		grabbed = null;
	}
}
