package battle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import items.Inventory;

public class Equipment {
										  	   // Rusty S/A/P 	Wood S/A/P
	private static int[] damage = {0,0,0,0,0,0,0,0,		50,50,50,		10,10,10};
	
	private static BufferedImage[] swords = new BufferedImage[99];
	private static String[] allowedSwords = 	{"r_sword","w_sword"};
	
	private static BufferedImage[] axes = new BufferedImage[99];
	private static String[] allowedAxes = 		{"r_axe"  };//,"w_axe"};
	
	private static BufferedImage[] pickaxes = new BufferedImage[99];
	private static String[] allowedPickaxes = 	{"r_pickaxe"  };//,"w_pickaxe"};
	
	private static int equippedSword = Inventory.nameToId("r_sword");
	private static int equippedAxe = Inventory.nameToId("r_axe");
	private static int equippedPickaxe = Inventory.nameToId("r_pickaxe");
	
	public Equipment()
	{
		for(String t: allowedSwords)
		{
			int id = Inventory.nameToId(t);
			
			InputStream is = getClass().getResourceAsStream("/itemsRes/" + id + ".png");
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
			
			swords[id] = img;
		}
		
		for(String t: allowedAxes)
		{
			int id = Inventory.nameToId(t);
			
			InputStream is = getClass().getResourceAsStream("/itemsRes/" + id + ".png");
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
			
			axes[id] = img;
		}
		
		for(String t: allowedPickaxes)
		{
			int id = Inventory.nameToId(t);
			
			InputStream is = getClass().getResourceAsStream("/itemsRes/" + id + ".png");
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
			
			pickaxes[id] = img;
		}
	}
	
	public static BufferedImage getSwordImg()
	{
		return swords[equippedSword];
	}
	
	public static BufferedImage getAxeImg()
	{
		return axes[equippedAxe];
	}
	
	public static BufferedImage getPickaxeImg()
	{
		return pickaxes[equippedPickaxe];
	}
	
	public static boolean allowedSword(int id)
	{
		for(String t: allowedSwords)
		{
			if(id == Inventory.nameToId(t))
				return true;
		}
		
		return false;
	}
	
	public static boolean allowedAxe(int id)
	{
		for(String t: allowedAxes)
		{
			if(id == Inventory.nameToId(t))
				return true;
		}
		
		return false;
	}
	
	public static boolean allowedPickaxe(int id)
	{
		for(String t: allowedPickaxes)
		{
			if(id == Inventory.nameToId(t))
				return true;
		}
		
		return false;
	}
	
	public static int getSword()
	{
		return equippedSword;
	}
	
	public static int getAxe()
	{
		return equippedAxe;
	}
	
	public static int getPickaxe()
	{
		return equippedPickaxe;
	}
	
	public static int takeSword()
	{
		int temp = equippedSword;
		equippedSword = 0;
		return temp;
	}
	
	public static int takeAxe()
	{
		int temp = equippedAxe;
		equippedAxe = 0;
		return temp;
	}
	
	public static int takePickaxe()
	{
		int temp = equippedPickaxe;
		equippedPickaxe = 0;
		return temp;
	}
	
	public static int getSwordDamage()
	{
		return damage[equippedSword];
	}
	
	public static int getAxeDamage()
	{
		return damage[equippedAxe];
	}
	
	public static int getPickaxeDamage()
	{
		return damage[equippedPickaxe];
	}
}
