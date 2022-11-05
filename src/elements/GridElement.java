package elements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import utils.Hexagon;
import utils.Utils;

public class GridElement extends Element {

	private boolean bi = false;
	
	public static final String[] primary = {"", "fire", "water", "earth", "air", "ordo"};
	public static final String[] names = {"", "fire", "water", "earth", "air", "ordo", "victus", "lux", "motus", "herba"};
	private BufferedImage img;
	
	private int x=0,y=0,mx=0,my=0, ID=0;
	public static final int w=64,h=64;
	private int[] index;
	
	private GamePanel gamePanel;
	
	public GridElement(GamePanel gamePanel, int id, boolean builtin) {
		super(id);
		
		this.gamePanel = gamePanel;
		gridList.add(this);
		
		bi = builtin;
	}
	
	public boolean isBuiltIn()
	{
		return bi;
	}
	
	private static ArrayList<Integer[]> connections = new ArrayList<Integer[]>();
	
	public static ArrayList<Integer[]> getConnections()
	{
		return connections;
	}
	
	private Element[] checkForNeighbors()
	{
		Element[] nbl = new Element[6];
		
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
		
		ArrayList<Integer[]> a = new ArrayList<Integer[]>();
		for(Integer[] arr: connections)
		{
			if(grid[arr[4]][arr[5]].getElement()!=null && grid[arr[6]][arr[7]].getElement()!=null)
			{
				a.add(arr);
			}
		}
		connections = a;
		for(Element e: nbl)
		{
			if(e!=null && e.getID()>5 && (Combination.getCombinations()[e.getID()-6][0] == ID || Combination.getCombinations()[e.getID()-6][1] == ID))
			{
				Integer[] c = {x+w/2,y+h/2,e.getPos()[0]+w/2,e.getPos()[1]+h/2,index[0],index[1],e.getIndex()[0], e.getIndex()[1]};
				connections.add(c);
			}
		}
		return nbl;
	}
	
	private static ArrayList<GridElement> gridList = new ArrayList<GridElement>();
	
	public static void checkNeighborsOfGridElements()
	{
		for(GridElement e: gridList)
		{
			e.checkForNeighbors();
		}
	}

	public static ArrayList<GridElement> getGList() {
		return gridList;
	}
}
