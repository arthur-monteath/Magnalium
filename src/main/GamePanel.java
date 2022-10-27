package main;

import input.*;
import utils.Hexagon;
import utils.Utils;

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

import elements.Element;
import elements.Grid;

public class GamePanel extends JPanel {

	private MouseInput mouseInput;
	public int x=Toolkit.getDefaultToolkit().getScreenSize().width,y=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private int[][] init;
	
	private int w,h;
	
	public GamePanel()
	{
		mouseInput = new MouseInput(this);
		addKeyListener(new KeyboardInput(this));
		
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		this.setBackground(new Color(54, 41, 35));
		
		for(int i = 1; i<Element.names.length; i++)
		{
			Element e = new Element(this, i);
			if(i%2==0)
			{
				e.setPos(128,(i/2)*64);
			}
			else
			{
				e.setPos(64,((i+1)/2)*64);
			}
		}
		
		int[][] init = {
				
				{	0,1,1,1,1,0	},
				{	 1,1,1,1,1,0},
				{	1,1,1,1,1,1	},
				{	 1,1,1,1,1,0},
				{	0,1,1,1,1,0	}
		};
		
		h = 10;
		w = 14;
		
		//hexGrid = new Grid(init, 50, 50, 32);
		hexGrid = new Grid(h,w, (int)(0.4*x), (int)(0.1*y), 40);
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
	
	public Grid getGrid()
	{
		return hexGrid;
	}
	
	private Grid hexGrid;
	
	public void pressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		for(Element element: Element.getList())
		{
			if(mx>=element.getPos()[0] && my>=element.getPos()[1] && mx<=element.getPos()[0]+Element.w && my<=element.getPos()[1]+Element.h)
			{
				element.setGrabbed(!element.getGrabbed());
			}
		}
	}
	
	public void released(MouseEvent e)
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // calls the JPanel's paint component method this is used to clean the surface
		
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
		
		for(int i = 0; i<w; i++)
		{
			for(int j = 0; j<h; j++)
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
		
		g.setColor(Color.white);
		g.fillOval(64, 704, 66, 66);
		g.fillOval(256, 704, 65, 66);
		g.fillRect(145, 704, 96, 64);
		
		for(Element element: Element.getList())
		{
			if(element.getGrabbed())
			{
				element.updatePos();
			}
			else
			{
				Hexagon t = hexGrid.getClosestHex(element.getPos()[0]+Element.w/2,element.getPos()[1]+Element.h/2);
				if(t != null)
				{
					element.setPos(Utils.centerPosition(t.getPos()[0], t.getPos()[1], Element.w, Element.h)[0],Utils.centerPosition(t.getPos()[0], t.getPos()[1], Element.w, Element.h)[1]);
				}
				else
				{
					//element.setPos(0,0);
				}
			}
			g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], null);
		}
	}
}
