package elements;

import java.util.ArrayList;
import main.GamePanel;
import utils.Hexagon;

public class GridElement extends Element {

	private boolean bi = false;

	private int[] index = {0,0};
	
	private static GamePanel gamePanel;
	
	public GridElement(GamePanel gamePanel, int id, int i, int j,boolean builtin) {
		super(id);
		
		GridElement.gamePanel = gamePanel;
		
		index[0] = i;
		index[1] = j;
		
		gridList.add(this);
		
		bi = builtin;
		bEnergized = false;
	}
	
	private boolean bEnergized = false;
	
	public GridElement(GamePanel gamePanel, int id, int i, int j,boolean builtin,boolean energized) {
		super(id);
		
		GridElement.gamePanel = gamePanel;
		
		index[0] = i;
		index[1] = j;
		
		gridList.add(this);
		
		bi = builtin;
		bEnergized = energized;
	}
	
	public boolean isBuiltIn()
	{
		return bi;
	}
	
	public int[] getIndex() {
		return index;
	}
	
	private static ArrayList<Integer[]> connections = new ArrayList<Integer[]>();
	
	public static ArrayList<Integer[]> getConnections()
	{
		return connections;
	}
	
	private void checkForNeighbors()
	{
		GridElement[] nbl = new GridElement[6];
		Hexagon[][] grid = gamePanel.getGrid().getHexGrid();
		int i = index[0];
		int j = index[1];
		
		if(i%2!=0)
		{
			nbl[0] = i+1 < grid.length				&& grid[i+1][j] != null ? grid[i+1][j].getElement() : null;
			nbl[1] = i-1 >= 0 						&& grid[i-1][j] != null ? grid[i-1][j].getElement() : null;
			nbl[2] = i+1 < grid.length && j-1 >= 0  && grid[i+1][j-1] != null ? grid[i+1][j-1].getElement() : null;
			nbl[3] = i-1 >= 0 && j-1 >= 0 			&& grid[i-1][j-1] != null ? grid[i-1][j-1].getElement() : null;
			nbl[4] = j+1 < grid[0].length 			&& grid[i][j+1] != null ? grid[i][j+1].getElement() : null;
			nbl[5] = j-1 >= 0 						&& grid[i][j-1] != null ? grid[i][j-1].getElement() : null;
		}
		else
		{
			nbl[0] = i+1 < grid.length 							&& grid[i+1][j] != null ? grid[i+1][j].getElement() : null;
			nbl[1] = i-1 >= 0 									&& grid[i-1][j] != null ? grid[i-1][j].getElement() : null;
			nbl[2] = i+1 < grid.length && j+1 < grid[0].length  && grid[i+1][j+1] != null ? grid[i+1][j+1].getElement() : null;
			nbl[3] = i-1 >= 0 && j+1 < grid[0].length 			&& grid[i-1][j+1] != null ? grid[i-1][j+1].getElement() : null;
			nbl[4] = j+1 < grid[0].length 						&& grid[i][j+1] != null ? grid[i][j+1].getElement() : null;
			nbl[5] = j-1 >= 0 									&& grid[i][j-1] != null ? grid[i][j-1].getElement() : null;
		}

		for(GridElement e: nbl)
		{
			if(e!=null)
			{
				int[][] c = Combination.getCombinations();
				for(int k = 0; k<Combination.getCombinations().length; k++)
				{
					if((c[k][0] == getID() || c[k][1] == getID()) && k+7 == e.getID())
					{
						if(energized)
						{
							e.energized = true;
						}
						if(e.getEnergized())
						{
							energized = true;
						}
						
						Integer[] a;
						if(energized || e.getEnergized())
						{
							Integer[] b = {getPos()[0]+w/2, getPos()[1]+h/2,e.getPos()[0]+w/2,e.getPos()[1]+h/2,1,index[0],index[1],e.getIndex()[0], e.getIndex()[1]};
							a = b;
						}
						else
						{
							Integer[] b = {getPos()[0]+w/2, getPos()[1]+h/2,e.getPos()[0]+w/2,e.getPos()[1]+h/2,0,index[0],index[1],e.getIndex()[0], e.getIndex()[1]};
							a = b;
						}
						
						if(!connections.contains(a))
						{
							connections.add(a);
						}
					}
				}
			}
		}
	}
	
	private boolean energized = bEnergized;
	
	public boolean getEnergized()
	{
		return energized;
	}
	
	private static ArrayList<GridElement> gridList = new ArrayList<GridElement>();
	
	public static void checkNeighborsOfGridElements()
	{
		boolean abe = true;
		
		for(GridElement e: gridList)
		{
			e.energized = e.bEnergized;
		}
		int cEnergy = 0;
		int oEnergy = -1;
		while(cEnergy!=oEnergy)
		{
			abe = true;
			oEnergy = cEnergy;
			cEnergy = 0;
			
			connections.clear();
			for(GridElement e: gridList)
			{
				e.checkForNeighbors();
				if(e.isBuiltIn() && !e.getEnergized())
				{
					abe = false;
				}
			}
			for(Integer[] c: connections)
			{
				if(c[4] == 1)
				{
					cEnergy++;
				}
			}
			if(cEnergy == oEnergy)
			{
				break;
			}
			else
			{
				System.out.println("O: " + oEnergy + "C: " + cEnergy);
			}
		}
		for(GridElement e: gridList)
		{
			if(!e.getEnergized())
			{
				e.setImg("/ElementsSpritesheetZero.png");
			}
			else
			{
				e.setImg("/ElementsSpritesheet.png");
			}
		}
		
		if(abe)
		{
			gamePanel.createNewGrid(1);
		}
	}

	public static ArrayList<GridElement> getGList() {
		return gridList;
	}
}
