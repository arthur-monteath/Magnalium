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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import elements.Combination;
import elements.Element;
import elements.Grid;

public class GamePanel extends JPanel {

	private MouseInput mouseInput;
	public int x=Toolkit.getDefaultToolkit().getScreenSize().width,y=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private int[][] init;
	
	private int w,h;
	
	private BufferedImage[] sprites = new BufferedImage[12];
	
	public GamePanel()
	{
		mouseInput = new MouseInput(this);
		addKeyListener(new KeyboardInput(this));
		
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		this.setBackground(new Color(54, 41, 35));
		
		InputStream is = getClass().getResourceAsStream("/Button.png");
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
		
		sprites[0] = img.getSubimage(0, 0, 128, 64);
		sprites[1] = img.getSubimage(128, 0, 128, 64);
		
		for(int i = 1; i<Element.names.length; i++)
		{
			Element e = new Element(this, i);
			//Element e1 = new Element(this, i);
			//Element e2 = new Element(this, i);
			if(i%5==0)
			{
				e.setPos(320,(i/5)*64);
				//e1.setPos(320,(i/5)*64);
				//e2.setPos(320,(i/5)*64);
			}
			else
			{
				e.setPos(i%5*64,((i+4)/5)*64);
				//e1.setPos(i%5*64,((i+4)/5)*64);
				//e2.setPos(i%5*64,((i+4)/5)*64);
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
	Element grabbed = null;
	
	public void updateGame() {
		
		grabbed = null;
		for(Element element: Element.getList())
		{
			if(element.getGrabbed())
			{
				element.updatePos();
				if(comb[0]!=null && comb[0].getGrabbed())
					comb[0] = null;
				else if(comb[1]!=null && comb[1].getGrabbed())
					comb[1] = null;
				grabbed = element;
			}
			else
			{
				Hexagon t = hexGrid.getClosestHex(element.getPos()[0]+Element.w/2,element.getPos()[1]+Element.h/2);
				
				if(t != null && t.isFree() && !element.getLock() && !element.getGrabbed())
				{
					element.setPos(Utils.centerPosition(t.getPos()[0], t.getPos()[1], Element.w, Element.h)[0],Utils.centerPosition(t.getPos()[0], t.getPos()[1], Element.w, Element.h)[1]);
					t.setElement(element);
				}
				else if(t != null && !element.getLock() && !element.getGrabbed())
				{
					t.getElement().setGrabbed(true);
					t.getElement().setLock(false);
					element.setPos(Utils.centerPosition(t.getPos()[0], t.getPos()[1], Element.w, Element.h)[0],Utils.centerPosition(t.getPos()[0], t.getPos()[1], Element.w, Element.h)[1]);
					t.setElement(element);
				}
				else if(!element.getLock() && element.getPos()[0] > x*0.4-32)
				{
					element.setPos(0,0);
				}
				else if(!element.getLock() && element.getPos()[1]+Element.h/2 >= 704 && element.getPos()[1]+Element.h/2 <=768)
				{
					int x = element.getPos()[0]+Element.w/2;
					if(x >=64 && x<=128)
					{
						comb[0] = element;
						element.setPos(64, 704);
					}
					else if(x >=320 && x<=384)
					{
						comb[1] = element;
						element.setPos(320, 704);
					}
				}
			}
		}
	}
	
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
	
	public void clicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(my >= 704)
		{
			if(mx >= 160 && mx <= 288 && comb[0] != null && comb[1] != null)
			{
				int result = Combination.getResult(comb[0].getID(), comb[1].getID());
				if(result > 0)
				{
					Element.getList().remove(comb[0]);
					Element.getList().remove(comb[1]);
					comb[0] = null;
					comb[1] = null;
					Element el = new Element(this, result);
					resetElementsOnStock();
				}
			}
		}
	}
	
	private void resetElementsOnStock()
	{
		ArrayList<Element> list = new ArrayList<Element>();
		for(Element e: Element.getList())
		{
			if(!e.getLock())
				list.add(e);
		}
		for(int i = 0; i<list.size(); i++)
		{
			i++;
			if(i%5==0)
			{
				list.get(i-1).setPos(320,(i/5)*64);
			}
			else
			{
				list.get(i-1).setPos(i%5*64,((i+4)/5)*64);
			}
			i--;
		}
	}
	
	private Element[] comb = new Element[2];
	
	public void released(MouseEvent e)
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // calls the JPanel's paint component method this is used to clean the surface
		
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
					
					g.setColor(Color.white);
					g.drawPolygon(
							hexGrid.getHex(i,j).getPoints()[0],
							hexGrid.getHex(i,j).getPoints()[1], 
							hexGrid.getHex(i,j).getPoints()[0].length);
				}
			}
		}
		
		g.setColor(new Color(39,31,25,255));
		g.fillOval(64, 704, 64, 64);
		g.fillOval(320, 704, 64, 64);
		
		if(comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0)
		{
			g.drawImage(sprites[1], 160, 704, null);
		}
		else
		g.drawImage(sprites[0], 160, 704, null);
		
		for(Element element: Element.getList())
		{
			if(!element.getGrabbed())
			g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], null);
		}
		if(grabbed != null)
		{
			g.drawImage(grabbed.getImg(), grabbed.getPos()[0], grabbed.getPos()[1], null);
		}
		
		g.setColor(Color.blue);
		for(Integer[] c: Element.getConnections())
		{
			g.drawLine(c[0], c[1], c[2], c[3]);
		}
	}
}
