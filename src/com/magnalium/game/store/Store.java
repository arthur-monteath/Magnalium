package com.magnalium.game.store;

import com.magnalium.engine.ui.GamePanel;
import com.magnalium.game.research.Research;

public class Store 
{
	private int tab = 1; // Axe Pick Sword
	
	private Button[] buttonsT1 = new Button[6];
	private Button[] buttonsT2 = new Button[6];
	private Button[] buttonsT3 = new Button[6];
	
	public Store()
	{
		double gScale = GamePanel.gScale;
		int zero = GamePanel.getZero();
		
		buttonsT1[0] = new Button(zero + (int)(784*gScale), (int)(524*gScale), 50);		
		buttonsT1[1] = new Button(zero + (int)(1040*gScale), (int)(444*gScale), 250);
		buttonsT1[2] = new Button(zero + (int)(1296*gScale), (int)(444*gScale), 1000);
		buttonsT1[3] = new Button(zero + (int)(1552*gScale), (int)(524*gScale), 2500);
		buttonsT1[4] = new Button(zero + (int)(1040*gScale), (int)(604*gScale), 250);
		buttonsT1[5] = new Button(zero + (int)(1296*gScale), (int)(604*gScale), 1000);
		
		buttonsT2[0] = new Button(zero + (int)(784*gScale), (int)(524*gScale), 50);
		buttonsT2[1] = new Button(zero + (int)(1040*gScale), (int)(444*gScale), 250);
		buttonsT2[2] = new Button(zero + (int)(1296*gScale), (int)(444*gScale), 1000);
		buttonsT2[3] = new Button(zero + (int)(1552*gScale), (int)(524*gScale), 2500);
		buttonsT2[4] = new Button(zero + (int)(1040*gScale), (int)(604*gScale), 250);
		buttonsT2[5] = new Button(zero + (int)(1296*gScale), (int)(604*gScale), 1000);
		
		buttonsT3[0] = new Button(zero + (int)(784*gScale), (int)(524*gScale), 50);
		buttonsT3[1] = new Button(zero + (int)(1040*gScale), (int)(444*gScale), 250);
		buttonsT3[2] = new Button(zero + (int)(1296*gScale), (int)(444*gScale), 1000);
		buttonsT3[3] = new Button(zero + (int)(1552*gScale), (int)(524*gScale), 2500);
		buttonsT3[4] = new Button(zero + (int)(1040*gScale), (int)(604*gScale), 250);
		buttonsT3[5] = new Button(zero + (int)(1296*gScale), (int)(604*gScale), 1000);
		
		buttonsT1[0].unlock();
		buttonsT2[0].unlock();
		buttonsT3[0].unlock();
	}
	
	public void switchTab(int tab)
	{
		this.tab = tab;
	}
	
	public int getTab()
	{
		return tab;
	}
	
	public Button[] getButtons()
	{
		if(tab == 1)
			return buttonsT1;
		else if(tab == 2)
			return buttonsT2;
		else
			return buttonsT3;
	}
	
	public void updateButtons()
	{
		switch(tab)
		{
		case 1: // Axes
			
			if(buttonsT1[0].getBought())
			{
				buttonsT1[1].unlock();
				buttonsT1[4].unlock();
				
				if(buttonsT1[1].getBought())
				{
					buttonsT1[2].unlock();
					
					if(buttonsT1[2].getBought() && buttonsT1[5].getBought())
					{
						buttonsT1[3].unlock();
					}
				}
				if(buttonsT1[4].getBought())
				{
					buttonsT1[5].unlock();
				}
			}
		case 2: // Pickaxes
			
			if(buttonsT2[0].getBought())
			{
				buttonsT2[1].unlock();
				buttonsT2[4].unlock();
				
				if(buttonsT2[1].getBought())
				{
					buttonsT2[2].unlock();
					
					if(buttonsT2[2].getBought() && buttonsT2[5].getBought())
					{
						buttonsT2[3].unlock();
					}
				}
				if(buttonsT2[4].getBought())
				{
					buttonsT2[5].unlock();
				}
			}
		case 3: // Swords
			
			if(buttonsT3[0].getBought())
			{
				int[] unlock = {0,1,2};
				Research.unlock(unlock);
				
				buttonsT3[1].unlock();
				buttonsT3[4].unlock();
				
				if(buttonsT3[1].getBought())
				{
					buttonsT3[2].unlock();
					
					if(buttonsT3[2].getBought() && buttonsT3[5].getBought())
					{
						buttonsT3[3].unlock();
					}
				}
				if(buttonsT3[4].getBought())
				{
					buttonsT3[5].unlock();
				}
			}
		}
	}
}
