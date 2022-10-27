package elements;

import utils.Hexagon;

public class Grid {
	
	private int hexRadius, x, y, hexApothem;
	
	private Hexagon[][] hex;
	
	private int[][] init;
	
	public Grid(int[][] init, int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		hexRadius = radius;
		hex = new Hexagon[init.length][init[0].length];
		
		hexApothem = (int) (Math.cos(Math.toRadians(30.0))*hexRadius);
		
		for(int i = 0; i<hex.length; i++)
		{
			for(int j = 0; j<hex[0].length; j++)
			{
				if(init[i][j] == 1)
				{
					if(i%2==0)
					{
						hex[i][j] = new Hexagon((int)(x + (3*hexRadius*j)), (int)(y + (hexApothem*i)), hexRadius);
					}
					else
					{
						hex[i][j] = new Hexagon((int)(x + ((3*hexRadius*j)+(3*hexRadius/2))), (int)(y + (hexApothem*i)), hexRadius);
					}
				}
				else
				{
					hex[i][j] = null;
				}
			}
		}
	}
	
	public Grid(int row, int col, int x, int y, int radius)
	{
		hexRadius = radius;
		this.x = x;
		this.y = y;
		
		hex = new Hexagon[row][col];
		
		hexApothem = (int) (Math.cos(Math.toRadians(30.0))*hexRadius);
		
		for(int i = 0; i<row; i++)
		{
			for(int j = 0; j<col; j++)
			{
				if(j%2==0)
				{
					hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + ((hexApothem*2*i)+hexApothem)), hexRadius);
				}
				else
				{
					hex[j][i] = new Hexagon((int)(x + (hexRadius/2*j*3)),(int)(y + (hexApothem*2*i)), hexRadius);
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
