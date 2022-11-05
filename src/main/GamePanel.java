package main;

import input.*;
import items.Item;
import utils.Hexagon;
import utils.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import elements.Combination;
import elements.Element;
import elements.GrabElement;
import elements.Grid;
import elements.GridElement;
import elements.StockElement;

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
		this.setBackground(new Color(40,37,33,255));
		
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
		
		for(int i = 1; i<Element.primary.length; i++)
		{
			//Element e = new StockElement(i);
			
			/*if(i%5==0)
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
			}*/
		}
		
		int[][] init = {
				
				{	0,1,1,1,1,0	},
				{	 1,1,1,1,1,0},
				{	1,1,1,1,1,1	},
				{	 1,1,1,1,1,0},
				{	0,1,1,1,1,0	}
		};
		
		h = 5;
		w = 5;
		
		//hexGrid = new Grid(init, 50, 50, 32);
		hexGrid = new Grid(Item.grid, (int)(0.4*x), (int)(0.1*y), 40, this);
	}
	
	public void updateMousePosition(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}
	
	private int mx=0, my=0;
	GrabElement grabbed = null;
	
	public void updateGame() {
		
		if(Window==0)
		{
			grabbed = GrabElement.getGrabbed();
			
			if(grabbed!=null)
			{
				grabbed.updateToMouse(mx,my);
			}
		}
		else if(Window==1)
		{
			
		}
		else if(Window==2)
		{
			
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

		if(Window==0)
		{
			if(grabbed==null)
			{
				Hexagon h = hexGrid.getClosestHex(mx, my);
				if(h!=null&&h.getElement()!=null)
				{
					GridElement.getGList().remove(h.getElement());
					new GrabElement(this, h.getElement().getID());
					h.setElement(null);
				}
				for(StockElement sElement: StockElement.getStock())
				{
					if(mx>=sElement.getPos()[0] && my>=sElement.getPos()[1] && mx<=sElement.getPos()[0]+Element.w && my<=sElement.getPos()[1]+Element.h && sElement.getAmount()>0)
					{
						grabbed = new GrabElement(this, sElement.getID());
						sElement.increase(-1);
					}
				}
			}
			else
			{
				Hexagon h = hexGrid.getClosestHex(mx, my);
				if(h!=null&&h.getElement()==null)
				{
					grabbed.lock();
					grabbed = null;
				}
				else if(h!=null)
				{
					int t = h.getElement().getID();
					grabbed.lock();
					grabbed = new GrabElement(this, t);
				}
				else
				{
					addToStock(grabbed.getID());
					grabbed.release();
					grabbed = null;
				}
			}
		}
		else if(Window==1)
		{
			
		}
		else if(Window==2)
		{
			
		}
	}
	
	public void clicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(mx<=32)
		{
			if(my<=y/3)
			{
				Window = 0;
			}
			else if(my<=(2*y)/3)
			{
				Window = 1;
			}
			else
			{
				Window = 2;
			}
		}
		else if(Window==0)
		{
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
						Element el = new GrabElement(this, result);
						resetElementsOnStock();
					}
				}
			}
		}
		else if(Window==1)
		{
			if(mx>=100&&mx<=200&&my>=100&&my<=200)
			{
				Random rand = new Random();
				int id = rand.nextInt(1, 7);
				addToStock(id);
			}
		}
		else if(Window==2)
		{
			
		}
	}
		
	public void addToStock(int id)
	{
		for(StockElement st: StockElement.getStock())
		{
			if(id==st.getID())
			{
				st.increase(1);
				resetElementsOnStock();
				return;
			}
		}
		new StockElement(id);
	}
	
	public static void resetElementsOnStock()
	{
		ArrayList<StockElement> list = StockElement.getStock();
		
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
	
	private int Window = 0;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // calls the JPanel's paint component method this is used to clean the surface
		
		g.setColor(new Color(88,67,50,255));
		g.fillRect(32, 0, x-32, y);
		
		if(Window==0)
		{
			g.fillRect(0, 0, 32, y/3);
			g.setColor(new Color(234,214,182,255));
			g.drawLine(0, y/3, 31, y/3);
			g.drawLine(0, 2*y/3, 31, 2*y/3);
			
			for(int i = 0; i<hexGrid.getHexGrid().length; i++)
			{
				for(int j = 0; j<hexGrid.getHexGrid()[0].length; j++)
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
			
			for(StockElement element: StockElement.getStock())
			{
				g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], null);
				g.setColor(Color.white);
				g.setFont(new Font("/SimpleLife.ttf", Font.BOLD, 16));
				g.drawString(String.valueOf(element.getAmount()), element.getPos()[0]+(Element.w*7/8), element.getPos()[1]+(Element.h*11/12));
			}
			for(GridElement element: GridElement.getGList())
			{
				g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], null);
			}
			if(grabbed != null)
			{
				g.drawImage(grabbed.getImg(), grabbed.getPos()[0], grabbed.getPos()[1], null);
			}
			
			g.setColor(Color.blue);
			for(Integer[] c: GridElement.getConnections())
			{
				g.drawLine(c[0], c[1], c[2], c[3]);
			}
		}
		else if(Window==1)
		{
			g.fillRect(0, y/3, 32, y/3);
			g.setColor(new Color(234,214,182,255));
			g.drawLine(0, y/3, 31, y/3);
			g.drawLine(0, 2*y/3, 31, 2*y/3);
			
			g.setColor(Color.red);
			g.fillRect(100, 100, 100, 100);
		}
		else if(Window==2)
		{
			g.fillRect(0, 2*y/3, 32, y/3);
			g.setColor(new Color(234,214,182,255));
			g.drawLine(0, y/3, 31, y/3);
			g.drawLine(0, 2*y/3, 31, 2*y/3);
		}
	}
}