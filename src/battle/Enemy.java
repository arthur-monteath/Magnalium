package battle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy {

	int posture = 0;
	
	private BufferedImage img;
	
	private static float scale = 1.0f;
	
	private int id,x,y,w,h;
	
	private int health,damage;
	
	private Random rand;
	
	public Enemy(int id)
	{	
		rand = new Random();
		
		this.id = id;
		
		setImage("./enemies/" + id + ".png");
		
		w = img.getWidth();
		h = img.getHeight();
		
		health = rand.nextInt(50, 251);
	}
	
	public void takeDamage(int amount)
	{
		health-=amount;
		
		if(health<=0)
		{
			// die()
		}
	}
	
	public int[] getPos()
	{
		int[] a = {x,y};
		return a;
	}
	
	public int GetWidth()
	{
		return w;
	}
	
	public int GetHeight()
	{
		return h;
	}
	
	public BufferedImage GetImg()
	{
		return img;
	}
	
	public int getId()
	{
		return id;
	}
	
	private void setImage(String file)
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
