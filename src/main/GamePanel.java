package main;

import input.*;
import utils.Hexagon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import elements.Grid;

public class GamePanel extends JPanel {

	private MouseInput mouseInput;
	public int x=Toolkit.getDefaultToolkit().getScreenSize().width,y=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private BufferedImage img;
	private int[][] init;
	
	public GamePanel()
	{
		mouseInput = new MouseInput(this);
		addKeyListener(new KeyboardInput(this));
		
		importImg();
		
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		this.setBackground(new Color(54, 41, 35));
		
		int pos = 32;
		
		int[][] init = {
				
				{	0,1,1,1,1,0	},
				{	 1,1,1,1,1,0},
				{	1,1,1,1,1,1	},
				{	 1,1,1,1,1,0},
				{	0,1,1,1,1,0	}
		};
		
		//hexGrid = new Grid(init, 50, 50, 32);
		hexGrid = new Grid(5,5, 50, 50, 32);
	}
	
	public void updateMousePosition(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}
	
	private int mx=0, my=0;
	
	public int[] getMousePos()
	{
		int[] a = {mx,my};
		return a;
	}
	
	public void importImg()
	{
		InputStream is = getClass().getResourceAsStream("/ElementPlaceholder.png");
		
		try {
			img = ImageIO.read(is);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public Grid getGrid()
	{
		return hexGrid;
	}
	
	private Grid hexGrid;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // calls the JPanel's paint component method this is used to clean the surface
		
		int[][] init = {
				
				{	0,1,1,1,1,0	},
				{	 1,1,1,1,1,0},
				{	1,1,1,1,1,1	},
				{	 1,1,1,1,1,0},
				{	0,1,1,1,1,0	}
		};
		
		/*for(int i = 0; i<init.length; i++)
		{
			for(int j = 0; j<init[0].length; j++)
			{
				if(hexGrid.getHex(i,j) != null)
				{
					g.setColor(hexGrid.getHex(i,j).getColor());
					g.fillPolygon(
							hexGrid.getHex(i,j).getPoints()[0],
							hexGrid.getHex(i,j).getPoints()[1], 
							hexGrid.getHex(i,j).getPoints()[0].length);
					hexGrid.getHex(i, j).colorToDefault();
					
					g.setColor(Color.white);
					g.drawPolygon(
							hexGrid.getHex(i,j).getPoints()[0],
							hexGrid.getHex(i,j).getPoints()[1], 
							hexGrid.getHex(i,j).getPoints()[0].length);
				}
			}
		}*/
		
		for(int i = 0; i<5; i++)
		{
			for(int j = 0; j<5; j++)
			{
				if(hexGrid.getHex(i,j) != null)
				{
					g.setColor(hexGrid.getHex(i,j).getColor());
					g.fillPolygon(
							hexGrid.getHex(i,j).getPoints()[0],
							hexGrid.getHex(i,j).getPoints()[1], 
							hexGrid.getHex(i,j).getPoints()[0].length);
					hexGrid.getHex(i, j).colorToDefault();
					
					g.setColor(Color.white);
					g.drawPolygon(
							hexGrid.getHex(i,j).getPoints()[0],
							hexGrid.getHex(i,j).getPoints()[1], 
							hexGrid.getHex(i,j).getPoints()[0].length);
				}
			}
		}
		
		//g.drawImage(img, 0, 0, null);
	}
}
