package battle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Utils;

public class BattleHandler 
{
	private static BattleHandler instance;
	
	private Enemy currentEnemy;
	
	private int maxHealth = 100, health, region = 0, oldRegion = 2, gZero, zero;
	private double gScale;
	
	private BufferedImage[] regions = new BufferedImage[3], playerStates = new BufferedImage[3];
	private BufferedImage playerIdle;
	private int playerState = -1;
	
	private boolean attackCooldown = true;
	
	private int enemiesKilled = 0;
	
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
		
		if(playerState == -1)
			g.drawImage(playerIdle, gZero+(int)(280*gScale), (int)(642*gScale), (int)(216*gScale), (int)(216*gScale), null);
		else
			g.drawImage(playerStates[playerState], gZero+(int)(280*gScale), (int)(642*gScale), (int)(216*gScale), (int)(216*gScale), null);
		
		g.setColor(Color.yellow);
		if(blockedTicks<=40)
			g.fillRect(gZero+(int)(280*gScale), (int)(642*gScale), 50, 50);
			
		
		if(currentEnemy != null)
		{
			g.setColor(Color.red);
			g.fillRect((int)(zero+1792*gScale), (int)(96*gScale), -(int)(500*gScale), (int)(64*gScale));
			g.setColor(Color.green);
			g.fillRect((int)(zero+1792*gScale), (int)(96*gScale), -(int)(500*gScale*currentEnemy.getHealth()/currentEnemy.getMaxHealth()), (int)(64*gScale));

			if(enemyEnterDelay>0)
				g.drawImage(currentEnemy.GetImg(), (int) (currentEnemy.getPos()[0]/Utils.smoothStep(currentEnemy.getPos()[0], currentEnemy.getPos()[0]+1000, currentEnemy.getPos()[0]+1000-enemyEnterDelay)), currentEnemy.getPos()[1]-100+currentEnemy.getDirection()*100, (int)(currentEnemy.GetImg().getWidth()*gScale), (int)(currentEnemy.GetImg().getHeight()*gScale), null);
			else
				g.drawImage(currentEnemy.GetImg(), (int) (currentEnemy.getPos()[0]-currentEnemy.getAttackLoadTime()*100), currentEnemy.getPos()[1]-100+currentEnemy.getDirection()*100, (int)(currentEnemy.GetImg().getWidth()*gScale), (int)(currentEnemy.GetImg().getHeight()*gScale), null);
			
			if(currentEnemy.getStunned())
				g.fillRect(currentEnemy.getPos()[0], currentEnemy.getPos()[1], 50, 50);
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
		enemiesKilled = 0;
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
		
		enemiesKilled++;
		enemyEnterDelay = 1000;
		
		if(enemiesKilled >= 5)
			GamePanel.getInstance().endBattle();
	}
	
	private boolean up;
	public void upArrow(boolean pressed)
	{
		if(enemyEnterDelay==-1)
		{
			if(!up && pressed)
			{
				blockedTicks = 0;
				playerState = 0;
				up = true;
				return;
			}
			
			up = pressed;
			
			if(playerState == 0)
			{
				if(right)
					playerState = 1;
				else if(down)
					playerState = 2;
			}
		}
	}
	
	private boolean right;
	public void rightArrow(boolean pressed)
	{
		if(enemyEnterDelay==-1)
		{
			if(!right && pressed)
			{
				blockedTicks = 0;
				playerState = 1;
				right = true;
				return;
			}
			
			right = pressed;
			
			if(playerState == 1)
			{
				if(up)
					playerState = 0;
				else if(down)
					playerState = 2;
			}
		}
	}
	
	private boolean down;
	public void downArrow(boolean pressed)
	{
		if(enemyEnterDelay==-1)
		{
			if(!down && pressed)
			{
				blockedTicks = 0;
				playerState = 2;
				down = true;
				return;
			}
			
			down = pressed;
			
			if(playerState == 2)
			{
				if(up)
					playerState = 0;
				else if(right)
					playerState = 1;
			}
		}
	}
	
	public void sufferAttack(int amount, int direction)
	{
		if(direction == playerState)
		{
			if(blockedTicks<=40)
				currentEnemy.stun();
				
			return;
		}
		
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
	int blockedTicks = 21;
	public void Update()
	{
		ticks++;
		
		if(enemyEnterDelay>0)
		{
			enemyEnterDelay--;
			playerState = -1;
			return;
		}
		else if(enemyEnterDelay==0)
		{
			currentEnemy.Start();
			enemyEnterDelay = -1;
		}
		
		if(up || right || down)
			blockedTicks++;
		else
			playerState = -1;
		
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
