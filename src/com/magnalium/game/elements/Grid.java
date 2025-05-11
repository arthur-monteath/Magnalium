package com.magnalium.game.elements;

import com.magnalium.engine.ui.GamePanel;
import com.magnalium.game.items.Item;
import com.magnalium.game.research.Research;
import com.magnalium.utils.Hexagon;

public class Grid {
	
	private int hexRadius, x, y, hexApothem;
	
	private Hexagon[][] hex;
	
	public Grid(int gridId, int x, int y, int radius, GamePanel gp)
	{
		hexRadius = radius;
		this.x = x;
		this.y = y;
		
		int[][] init = Research.getGrid(gridId);
		
		hex = new Hexagon[init[0].length][init.length];
		
		hexApothem = (int) (Math.cos(Math.toRadians(30.0))*hexRadius);
		
		for(int i = 0; i<init.length; i++)
		{
			for(int j = 0; j<init[0].length; j++)
			{
				if(init[i][j]==0)
				{
					if(j%2==0)
					{
						hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + ((hexApothem*2*i)+hexApothem)), hexRadius, j, i, false);
					}
					else
					{
						hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + (hexApothem*2*i)), hexRadius, j, i, false);
					}
				}
				else if(init[i][j]>0)
				{
					GridElement e = null;
					if(i == Research.getEnergized(gridId)[0] && j == Research.getEnergized(gridId)[1])
					{
						e = new GridElement(gp,init[i][j],j,i,true, true);
					}
					else
					{
						e = new GridElement(gp,init[i][j],j,i,true);
					}
					if(j%2==0)
					{
						hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + ((hexApothem*2*i)+hexApothem)), hexRadius, j, i, true);
						hex[j][i].setElement(e);
						e.setPos((int)(x + (hexRadius/2*j*3)-Element.w/2),(int)(y + (hexApothem*2*i)-Element.h/2)+hexApothem);
					}
					else
					{
						hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + (hexApothem*2*i)), hexRadius, j, i, true);
						hex[j][i].setElement(e);
						e.setPos((int)(x + (hexRadius/2*j*3)-Element.w/2),(int)(y + (hexApothem*2*i)-Element.h/2));
					}
				}
				else
				{
					hex[j][i] = null;
				}
			}
		}
	}
	
	public Grid(int row, int col, int x, int y, int radius)
	{
		hexRadius = radius;
		this.x = x;
		this.y = y;
		
		hex = new Hexagon[col][row];
		
		hexApothem = (int) (Math.cos(Math.toRadians(30.0))*hexRadius);
		
		for(int i = 0; i<row; i++)
		{
			for(int j = 0; j<col; j++)
			{
				if(j%2==0)
				{
					hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + ((hexApothem*2*i)+hexApothem)), hexRadius, j, i, false);
				}
				else
				{
					hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + (hexApothem*2*i)), hexRadius, j, i, false);
				}
			}
		}
	}
	
	public int[] getPos()
	{
		int[] a = {x, y};
		return a;
	}

	public Hexagon getHex(int row, int col)
	{
		return hex[row][col];
	}
	
	public Hexagon[][] getHexGrid()
	{
		return hex;
	}
	
	public Hexagon getClosestHex(int posX, int posY)
	{
		/*
		 * Subtract position by grid so grid 0 is the start
		 * Divide the value by the Diameter for the X and Apothem for the Y
		 * Round the value found to get the closest Hexagon! :D
		 */
		double startX = (double)x-(0.75*hexRadius);
		double startY = (double)y-hexApothem;
		
		double pX=posX-startX, pY=posY-startY;
		
		double maxX = hex.length*hexRadius*1.5;
		double maxY = hex[0].length*hexApothem*2+hexApothem;
		
		if(pX>0 && pY>0 && pX < maxX && pY < maxY)
		{
			pX/=1.5*hexRadius;
			pY = (int) pX % 2 == 0 ? ((pY-hexApothem)/(hexApothem*2)) : (pY/(hexApothem*2));
			
			if(pX<hex.length && pY<hex[0].length)
			{
				return hex[(int) pX][(int) pY];
			}
			
			/*if(pX-(int)pX>0)
			{
				pX = (int)pX + 1;
			}*/
		}
		
		/*if(pX>=0 && pX<=x+(hex[0].length*hexRadius*2) && pY>=0 && pY<=y+(hex.length*hexApothem))
		{
			int X=Math.round(pX/(hexRadius*3)),Y=Math.round(pY/(hexApothem));
			
			if(Y<hex.length && X<hex[0].length)
			return hex[Y][X];
		}*/
		
		return null;
	}
}
