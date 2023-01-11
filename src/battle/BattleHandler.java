package battle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BattleHandler 
{
	private static BattleHandler instance;
	
	private Enemy currentEnemy;
	
	private int maxHealth = 100, health, region = 0, oldRegion = 2, gZero, zero;
	private double gScale;
	
	private BufferedImage[] regions = new BufferedImage[3], playerStates = new BufferedImage[3];
	private BufferedImage playerIdle;
	
	private boolean attackCooldown = true;
	
	Random rand;
	
	public BattleHandler()
	{
		rand = new Random();
		
		instance = this;
		
		gScale = GamePanel.gScale;
		gZero = GamePanel.getGzero();
		zero = GamePanel.getZero();
		
		InputStream is = getClass().getResourceAsStream("/grassRegion.png");
		BufferedImage img = null;
		
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
		
		regions[0] = img;
		
		is = getClass().getResourceAsStream("/lavaRegion.png");
		img = null;
		
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
		
		regions[1] = img;
		
		is = getClass().getResourceAsStream("/darkRegion.png");
		img = null;
		
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
		
		regions[2] = img;
		
		is = getClass().getResourceAsStream("/player/playeridle.png");
		img = null;
		
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
		
		playerIdle = img;
		
		is = getClass().getResourceAsStream("/player/playerupper.png");
		img = null;
		
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
		
		playerStates[0] = img;
		
		is = getClass().getResourceAsStream("/player/playerfrontal.png");
		img = null;
		
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
		
		playerStates[1] = img;
		
		is = getClass().getResourceAsStream("/player/playerdown.png");
		img = null;
		
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
		
		playerStates[2] = img;
	}
	
	public int getRegion()
	{
		return region;
	}
	
	public void drawBattle(Graphics g)
	{
		g.drawImage(regions[region], GamePanel.getGzero(), (int)(32*gScale), (int)(1312*gScale), (int)(1016*gScale), null);
		
		g.setColor(Color.red);
		g.fillRect((int)(gZero+64*gScale), (int)(96*gScale), (int)(500*gScale), (int)(64*gScale));
		g.setColor(Color.green);
		g.fillRect((int)(gZero+64*gScale), (int)(96*gScale), (int)(500*gScale*health/100), (int)(64*gScale));
		
		g.drawImage(playerIdle, gZero+(int)(280*gScale), (int)(642*gScale), (int)(216*gScale), (int)(216*gScale), null);
		
		if(currentEnemy != null)
		{
			g.setColor(Color.red);
			g.fillRect((int)(zero+1792*gScale), (int)(96*gScale), -(int)(500*gScale), (int)(64*gScale));
			g.setColor(Color.green);
			g.fillRect((int)(zero+1792*gScale), (int)(96*gScale), -(int)(500*gScale*currentEnemy.getHealth()/currentEnemy.getMaxHealth()), (int)(64*gScale));

			g.drawImage(currentEnemy.GetImg(), currentEnemy.getPos()[0]+enemyEnterDelay, currentEnemy.getPos()[1], (int)(currentEnemy.GetImg().getWidth()*gScale), (int)(currentEnemy.GetImg().getHeight()*gScale), null);
		}
	}
	
	public static BattleHandler getInstance()
	{
		return instance;
	}
	
	public void startBattle()
	{
		health = maxHealth;
		
		while(region == oldRegion)
		{
			region = rand.nextInt(0,3);
		}
		
		oldRegion = region;

		switch(rand.nextInt(0,2))
		{
		case 0:
			currentEnemy = new SwordEnemy(region);
			break;
		case 1:
			currentEnemy = new CreatureEnemy(region);
			break;
		case 2:
			currentEnemy = new WizardEnemy(region);
			break;
		}
		
		enemyEnterDelay = 1000;
	}
	
	int oldEnemy;
	
	public void respawnEnemy()
	{
		while(oldEnemy == currentEnemy.getId())
		{
			oldEnemy = rand.nextInt(0,3);
		}
		
		switch(oldEnemy)
		{
		case 0:
			currentEnemy = new SwordEnemy(region);
			break;
		case 1:
			currentEnemy = new CreatureEnemy(region);
			break;
		case 2:
			currentEnemy = new WizardEnemy(region);
			break;
		}
		
		enemyEnterDelay = 1000;
	}
	
	public void takeDamage(int amount)
	{
		health -= amount;
		
		if(health<=0)
		{
			health = 0;
			
			GamePanel.getInstance().endBattle();
		}
	}
	
	int enemyEnterDelay = 1000;
	
	int ticks = 0;
	int storedTicks = 0;
	public void Update()
	{
		ticks++;
		
		if(enemyEnterDelay>0)
		{
			enemyEnterDelay--;
			return;
		}
		else if(enemyEnterDelay==0)
		{
			currentEnemy.Start();
			enemyEnterDelay = -1;
		}
		
		if(attackCooldown)
			storedTicks++;
		
		if(storedTicks >= 100)
		{
			attackCooldown = false;
			storedTicks = 0;
		}
		
		if(currentEnemy != null)
			currentEnemy.update();
	}

	public void pressed() 
	{
		if(currentEnemy != null && !attackCooldown)
		{
			attackCooldown = true;
			currentEnemy.takeDamage(Equipment.getSwordDamage());
		}
	}
}
