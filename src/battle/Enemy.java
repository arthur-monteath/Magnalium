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
	
	private int health,maxHealth,damage;
	
	private Random rand;
	
	public Enemy(int region, String file, int maxHealth, int damage, int cooldownDuration)
	{	
		rand = new Random();
		
		this.id = region;

		setImage(file);
		
		w = img.getWidth();
		h = img.getHeight();
		
		health = maxHealth;
		this.maxHealth = maxHealth;
		
		this.damage = damage;
		
		this.cooldownDuration = cooldownDuration;
	}
	
	private int cooldownDuration;
	private boolean attackCooldown = true;
	private int storedTicks = -1200;
	public void update()
	{
		if(attackCooldown)
			storedTicks++;
		
		if(storedTicks >= cooldownDuration)
		{
			attackCooldown = false;
			storedTicks = 0;
		}
		
		Attack();
	}
	
	public void Start()
	{
		attackCooldown = false;
		storedTicks = 0;
	}
	
	private void Attack()
	{
		if(!attackCooldown)
		{
			attackCooldown = true;
			BattleHandler.getInstance().takeDamage(damage);
		}
		
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void takeDamage(int amount)
	{
		health-=amount;
		
		if(health<=0)
		{
			health = 0;
			
			BattleHandler.getInstance().respawnEnemy();
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
	
	public void setPos(double x, double y)
	{
		this.x = (int)x;
		this.y = (int)y;
	}
}
