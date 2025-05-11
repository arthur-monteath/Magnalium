package com.magnalium.game.battle;

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
		if(stunnedTicks>0)
		{
			stunnedTicks--;
			
			return;
		}
		
		if(attackCooldown)
			storedTicks++;
		
		if(storedTicks >= cooldownDuration)
		{
			attackCooldown = false;
			storedTicks = 0;
		}
		
		Attack();
	}
	
	public float getAttackLoadTime()
	{
		if(storedTicks<=cooldownDuration-80)
			return 0;
		
		return (storedTicks-cooldownDuration+80)/80f;
	}
	
	public void Start()
	{
		attackCooldown = true;
		storedTicks = 0;
	}
	
	int direction = 1;
	private void Attack()
	{
		if(!attackCooldown)
		{
			attackCooldown = true;
			BattleHandler.getInstance().sufferAttack(damage, direction);
			direction = rand.nextInt(0,3);
		}
		
	}
	
	private int stunnedTicks = 0;
	public void stun()
	{
		stunnedTicks = 400;
		attackCooldown = true;
		storedTicks = 0;
	}
	
	public boolean getStunned()
	{
		return stunnedTicks>0;
	}
	
	public int getDirection()
	{
		return direction;
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
			
			LootTable.spawnLoot(this, id);
			
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
