package main;

import input.*;
import items.*;
import utils.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import elements.FixedElement;
import elements.GrabElement;
import elements.Grid;
import elements.GridElement;
import elements.StockElement;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private MouseInput mouseInput;
	public static int x=Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int y=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private final double gScale = (double)Toolkit.getDefaultToolkit().getScreenSize().height/1080;
	
	private BufferedImage[] sprites = new BufferedImage[12];
	private BufferedImage WoodBg, UpperUI, forest;
	
	private boolean paused = false, bookOpen = false;
	
	private Random r = new Random();
	private int zero = 0;
	
	public GamePanel()
	{
		System.out.println(gScale);
		mouseInput = new MouseInput(this);
		addKeyListener(new KeyboardInput(this));
		
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		this.setBackground(new Color(0,0,0,255));
		
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
		
		is = getClass().getResourceAsStream("/Icons.png");
		img = null;
		
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
		
		sprites[2] = img.getSubimage(128, 64, 64, 64);
		
		is = getClass().getResourceAsStream("/Book.png");
		img = null;
		
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
		
		sprites[3] = img;
		
		is = getClass().getResourceAsStream("/battleScenePlaceHolder.png");
		img = null;
		
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
		
		sprites[11] = img;
		
		is = getClass().getResourceAsStream("/LowerUId.png");
		img = null;
		
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
		
		WoodBg = img;
		System.out.println(WoodBg.getWidth()*gScale);
		
		is = getClass().getResourceAsStream("/ForestFilled.png");
		img = null;
		
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
		
		forest = img;
		
		is = getClass().getResourceAsStream("/UpperUI.png");
		img = null;
		
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
		
		UpperUI = img;
		
		zero = (int)((x/2)-(UpperUI.getWidth()*gScale/2));
		
		System.out.println(zero);
		
		x = UpperUI.getWidth();
		y = UpperUI.getHeight();
		
		for(int i = 1; i<Element.primary.length; i++)
		{
			for(int j = 0; j<99; j++)
			{
				addToStock(i);
			}
			
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
		
		//hexGrid = new Grid(init, 50, 50, 32);
		//hexGrid = new Grid(ManaCrystal.grid, (int)(0.4*x), (int)(0.1*y), 40, this);.
		
		new Research();
		
		int gridId = 0;
		hexGrid = new Grid(gridId, (int)(0.4*x), (int)(0.1*y), (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/27)*Element.scale), this);
		GridElement.checkNeighborsOfGridElements();
	}
	
	public void createNewGrid(int gridId)
	{
		GridElement.getGList().clear();
		hexGrid = new Grid(gridId, (int)(0.4*x), (int)(0.1*y), 40, this);
		GridElement.checkNeighborsOfGridElements();
	}
	
	public void updateMousePosition(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
	}
	
	private int mx=0, my=0;
	GrabElement grabbed = null;
	boolean SquareSide = true;
	
	public void updateGame() {
		
		if(Window==0 && !paused)
		{
			grabbed = GrabElement.getGrabbed();
			
			if(grabbed!=null)
			{
				grabbed.updateToMouse(mx,my);
			}
		}
		else if(Window==1)
		{
			cGrabbed = IComponent.getGrabbed();
			
			if(cGrabbed!=null)
			{
				cGrabbed.updateToMouse(mx,my);
			}
		}
		else if(Window==2)
		{
			if(SquareSide)
			{
				WSquare+=WSpd;
				if(WSquare >= MaxSpace-WWidth)
				{
					SquareSide = false;
					WSquare = MaxSpace-WWidth;
				}
			}
			else
			{
				WSquare-=WSpd;
				if(WSquare <= 0)
				{
					SquareSide = true;
					WSquare = 0;
				}
			}
		}
		else if(Window==3)
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
	
	private boolean canComb;
	
	public void pressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();

		if(mx<=32)
		{
			if(my<=y/4)
			{
				Window = 0;
			}
			else if(my<=(2*y)/4)
			{
				Window = 1;
			}
			else if(my<=(3*y)/4)
			{
				Window = 2;
			}
			else
			{
				Window = 3;
			}
		}
		
		if(Window==0 && !paused)
		{
			if(grabbed==null)
			{
				Hexagon h = hexGrid.getClosestHex(mx, my);
				if(h!=null&&!h.isElementHex()&&h.getElement()!=null)
				{
					GridElement.getGList().remove(h.getElement());
					new GrabElement(this, h.getElement().getID(), mx-Element.w/2, my-Element.h/2);
					h.setElement(null);
					GridElement.checkNeighborsOfGridElements();
				}
				for(StockElement sElement: StockElement.getStock())
				{
					if(mx>=sElement.getPos()[0] && my>=sElement.getPos()[1] && mx<=sElement.getPos()[0]+Element.w && my<=sElement.getPos()[1]+Element.h && sElement.getAmount()>0)
					{
						grabbed = new GrabElement(this, sElement.getID(), mx-Element.w/2, my-Element.h/2);
						sElement.increase(-1);
					}
				}
				if(my>=928*gScale && my<=1008*gScale)
				{
					if(comb[0] != null && mx>=112*gScale && mx<=192*gScale)
					{
						grabbed = new GrabElement(this, comb[0].getID(), mx-Element.w/2, my-Element.h/2);
						FixedElement.getFList().remove(comb[0]);
						comb[0] = null;
						canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
					}
					else if(comb[1] != null && mx>=352*gScale && mx<=432*gScale)
					{
						grabbed = new GrabElement(this, comb[1].getID(), mx-Element.w/2, my-Element.h/2);
						FixedElement.getFList().remove(comb[1]);
						comb[1] = null;
						canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
					}
				}
				else if(mx>x-64 && my<64)
				{
					if(grabbed!=null)
					{
						addToStock(grabbed.getID());
						grabbed.release();
						grabbed = null;
					}
					openBook();
				}
			}
			else
			{
				Hexagon h = hexGrid.getClosestHex(mx, my);
				if(h!=null&&h.getElement()==null)
				{
					grabbed.lock(h);
					grabbed = null;
				}
				else if(h!=null && !h.isElementHex())
				{
					int t = h.getElement().getID();
					GridElement.getGList().remove(h.getElement());
					grabbed.lock(h);
					grabbed = new GrabElement(this, t, mx-Element.w/2, my-Element.h/2);
					GridElement.checkNeighborsOfGridElements();
				}
				else if(my>=928*gScale && my<=1008*gScale)
				{
					if(comb[0] == null && mx>=112*gScale && mx<=192*gScale)
					{
						comb[0] = new FixedElement(grabbed.getID(), (int)Math.ceil(120*gScale), (int)Math.ceil(936*gScale));
						grabbed.release();
						grabbed = null;
						canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
					} 
					else if(comb[1] == null && mx>=352*gScale && mx<=432*gScale)
					{
						comb[1] = new FixedElement(grabbed.getID(), (int)Math.ceil(360*gScale), (int)Math.ceil(936*gScale));
						grabbed.release();
						grabbed = null;
						canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
					}
					else if(comb[0] != null && mx>=112*gScale && mx<=192*gScale)
					{
						int t = comb[0].getID();
						FixedElement.getFList().remove(comb[0]);
						comb[0] = new FixedElement(grabbed.getID(), (int)Math.ceil(120*gScale), (int)Math.ceil(936*gScale));
						grabbed = new GrabElement(this, t, mx-Element.w/2, my-Element.h/2);
						canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
					}
					else if(comb[1] != null && mx>=352*gScale && mx<=432*gScale)
					{
						int t = comb[1].getID();
						FixedElement.getFList().remove(comb[1]);
						comb[1] = new FixedElement(grabbed.getID(), (int)Math.ceil(360*gScale), (int)Math.ceil(936*gScale));
						grabbed = new GrabElement(this, t, mx-Element.w/2, my-Element.h/2);
						canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
					}
					else
					{
						addToStock(grabbed.getID());
						grabbed.release();
						grabbed = null;
					}
				}
				else if(mx>x-64 && my<64)
				{
					if(grabbed!=null)
					{
						addToStock(grabbed.getID());
						grabbed.release();
						grabbed = null;
					}
					openBook();
				}
				else
				{
					addToStock(grabbed.getID());
					grabbed.release();
					grabbed = null;
				}
			}
		}
		else if(Window == 0 ) // PAUSED
		{
			if(mx>x-64 && my<64)
			{
				if(grabbed!=null)
				{
					addToStock(grabbed.getID());
					grabbed.release();
					grabbed = null;
				}
				for(FixedElement f: bookList)
				{
					FixedElement.getFList().remove(f);
				}
				bookList.clear();
				bookOpen = false;
				paused = false;
			}
		}
		else if(Window==1)
		{
			if(cGrabbed == null)
			{
				for(IComponent c: IComponent.getList())
				{
					int[] p = c.getPos();
					if(mx>=p[0] && mx<=p[0]+c.getWidth() && my>=p[1] && my<= p[1]+c.getHeight())
					{
						cGrabbed = c;
						c.setGrabbed();
						break;
					}
				}
			}
			else
			{
				cGrabbed.release();
				cGrabbed = null;
			}
			if(mx>=100&&mx<=200&&my>=100&&my<=200)
			{
				int id = r.nextInt(1, 4);
				new SComponent(id, r.nextInt(32,1400),r.nextInt(120,700));
			}
		}
		else if(Window==2)
		{
			MineTree();
		}
	}
	
	int Debug = 0;
	private IComponent cGrabbed = null;
	
	private Boolean MineTree()
	{	
		int i = 0;
		for(int GSquare: GSquare)
		{
			if((WSquare>GSquare || WSquare+WWidth>GSquare) && WSquare<GSquare+GWidth[i])
			{
				GWidth[i] = (int) (GOWidth*r.nextDouble(0.8,1.3));
				this.GSquare[i] = r.nextInt(0,MaxSpace-GWidth[i]);
				Debug++;
				return true;
			}
			
			i++;
		
			//GSquare = r.nextInt(0,MaxSpace-GWidth[i]);
		}
		
		return false;
	}
	
	private ArrayList<FixedElement> bookList = new ArrayList<FixedElement>();
	private void openBook()
	{
		bookOpen = true; // Cacau was herebbbbbb
		paused = true;
		
		ArrayList<StockElement> list = StockElement.getStock();
		
		bookList.clear();
		int x=GamePanel.x-1152, y = (GamePanel.y/2)-432;
		
		for(int i = 0; i<list.size(); i++)
		{
			int id = list.get(i).getID();
			if(id>6)
			{
				FixedElement e = new FixedElement(id, x+656, (i-6)*80+y+144);
				bookList.add(e);
				int[] c = Combination.getRecipe(e.getID());
				if(c != null)
				{
					bookList.add(new FixedElement(c[0], x+256, (i-6)*80+y+144));
					bookList.add(new FixedElement(c[1], x+400, (i-6)*80+y+144));
				}
			}
			/*else
			{
				FixedElement e = new FixedElement(id, x+256+(80*i), y+64);
				bookList.add(e);
			}*/
		}
		
	}
	
	public void clicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(Window==0 && !paused)
		{

		}
		else if(Window==1)
		{
			
		}
		else if(Window==2)
		{
			
		}
	}
		
	public StockElement addToStock(int id)
	{
		for(StockElement st: StockElement.getStock())
		{
			if(id==st.getID())
			{
				st.increase(1);
				resetElementsOnStock();
				return st;
			}
		}
		return new StockElement(id);
	}
	
	public static void resetElementsOnStock()
	{
		ArrayList<StockElement> list = StockElement.getStock();
		int hMargin = Element.w*5/4, vMargin = Element.h*3/4;
		
		for(int i = 0; i<list.size(); i++)
		{
			if(i%6==0)
			{
				list.get(i).setPos(hMargin,(i/6)*Element.h + vMargin);
			}
			else
			{
				list.get(i).setPos(i%6*Element.w + hMargin,(i/6)*Element.h + vMargin);
			}
		}
	}
	
	private Element[] comb = new Element[2];
	
	public void released(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(Window==0 && !paused)
		{
			if(my >= 704 && my<=768 && mx >= 160 && mx <= 288)
			{
				if(comb[0] != null && comb[1] != null)
				{
					int result = Combination.getResult(comb[0].getID(), comb[1].getID());
					if(result > 0)
					{
						if(grabbed!=null)
						{
							addToStock(grabbed.getID());
							grabbed.release();
						}

						for(StockElement st: StockElement.getStock())
						{
							if(comb[0] != null && comb[0].getID()==st.getID())
							{
								if(st.getAmount()>0)
								{
									st.increase(-1);
									resetElementsOnStock();
								}
								else
								{
									FixedElement.getFList().remove(comb[0]);
									comb[0] = null;
								}
							}
							else if(comb[1] != null && comb[1].getID()==st.getID())
							{
								if(st.getAmount()>0)
								{
									st.increase(-1);
									resetElementsOnStock();
								}
								else
								{
									FixedElement.getFList().remove(comb[1]);
									comb[1] = null;
								}
							}
						}
						StockElement sE = addToStock(result);
						if(sE!=null)
						{
							sE.increase(-1);
						}
						grabbed = new GrabElement(this, result, mx-Element.w/2, my-Element.h/2);
						resetElementsOnStock();
						canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
					}
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
	
	private int Window = 0;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // calls the JPanel's paint component method this is used to clean the surface
		
		//g.setColor(new Color(88,67,50,255));
		//g.fillRect(32, 0, x-32, y);
		
		if(Window==0)
		{
			g.drawImage(WoodBg, zero + (int)(544*gScale), (int)(32*gScale), (int)(WoodBg.getWidth()*gScale), (int)(WoodBg.getHeight()*gScale), null);
			
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
			
			/*
			g.setColor(new Color(39,31,25,255));
			g.fillOval(64, 704, Element.w, Element.h);
			g.fillOval(320, 704, Element.w, Element.h);
			
			if(canComb)
				g.drawImage(sprites[1], 160, 704, null);
			else
				g.drawImage(sprites[0], 160, 704, null);
			*/

			for(StockElement element: StockElement.getStock())
			{
				g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
			}
			
			g.setColor(Color.white);
			g.setFont(new Font("/SimpleLife.ttf", Font.BOLD, (int)(20*gScale)));
			for(StockElement element: StockElement.getStock())
			{
				g.drawString(String.valueOf(element.getAmount()), element.getPos()[0]+(Element.w*7/8), element.getPos()[1]+(Element.h));
			}
			
			for(GridElement element: GridElement.getGList())
				if(Element.getList().contains(element.getID()))
				{
					g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
				}
				else
				{
					g.drawImage(element.getUnknown(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
				}

			int min=-2, max=3;
			
			for(Integer[] c: GridElement.getConnections())
			{
				
				if(c[4] == 1)
				{
					g.setColor(Color.blue);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					
					g.setColor(Color.cyan);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					
					g.setColor(Color.blue);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
				}
				else
				{
					g.setColor(Color.gray);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					
					g.setColor(Color.lightGray);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					
					g.setColor(Color.gray);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
				}
			}
			
			//Book Button
			
			if(bookOpen)
			{
				g.setColor(new Color(243,239,214,255));
				g.fillRect((int)(x*0.4), (int)(y*0.05), (int)(x*0.6), (int)(y*0.85));
			}
			g.drawImage(sprites[2], x-64, 0, null);
			
			for(FixedElement element: FixedElement.getFList())
				g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
			
			if(grabbed != null)
				g.drawImage(grabbed.getImg(), grabbed.getPos()[0], grabbed.getPos()[1], Element.w, Element.h, null);
			
		}
		else if(Window==1)
		{
			g.setColor(new Color(88,67,50,255));
			g.fillRect(0, y/4, 32, y/4);
			g.setColor(new Color(234,214,182,255));
			g.drawLine(0, y/4, 31, y/4);
			g.drawLine(0, 3*y/4, 31, 3*y/4);
			g.drawLine(0, 2*y/4, 31, 2*y/4);
			
			g.setColor(Color.red);
			g.fillRect(100, 100, 100, 100);
			
			for(IComponent c: IComponent.getList())
			{
				g.drawImage(c.getImg(), c.getPos()[0], c.getPos()[1], null);
			}
		}
		else if(Window==2)
		{
			g.setColor(Color.blue);
			g.drawLine(200, y/2, 300, y/2-64);
			g.drawLine(200, y/2, 300, y/2+64);
			
			g.setColor(Color.white);
			
			g.fillOval(200, y/2, 64, 64);
			
			g.fillOval(300, y/2-64, 64, 64);
			g.fillOval(300, y/2+64, 64, 64);
		}
		else if(Window==3)
		{
			g.drawImage(forest, zero + (int)(544*gScale), (int)(32*gScale), (int)(forest.getWidth()*gScale), (int)(forest.getHeight()*gScale), null);
			
			g.setColor(new Color(88,67,50,255));
			g.setColor(new Color(234,214,182,255));
			
			g.setColor(Color.red);
			g.fillRect(zero + x/2, BHeight, MaxSpace, y/10);
			g.setColor(Color.green);
			
			int i = 0;
			for(int a: GSquare)
			{
				g.fillRect(zero + x/2+a, BHeight, GWidth[i], y/10);
				i++;
			}
			
			g.setColor(Color.white);
			g.fillRect(zero + x/2+WSquare, BHeight, WWidth, y/10);
			
			g.setFont(new Font("/SimpleLife.ttf", Font.BOLD, 64));
			g.drawString(Integer.toString(Debug), zero + x/2, BHeight-10);
		}
		else if(Window==4)
		{
			
			/*g.drawImage(sprites[11], 0, 0, null);
			g.setColor(new Color(88,67,50,255));
			g.fillRect(0, 3*y/4, 32, y/4);
			g.setColor(new Color(234,214,182,255));
			g.drawLine(0, y/4, 31, y/4);
			g.drawLine(0, 3*y/4, 31, 3*y/4);
			g.drawLine(0, 2*y/4, 31, 2*y/4);*/
		}
		
		/*
		g.setColor(new Color(234,214,182,255));
		g.drawLine(0, y/5, 31, y/5);
		g.drawLine(0, 4*y/5, 31, 4*y/5);
		g.drawLine(0, 3*y/5, 31, 3*y/5);
		g.drawLine(0, 2*y/5, 31, 2*y/5);
		*/
		
		//g.setColor(Color.blue);
		//g.drawLine(0, 864, x, 864);
		//g.drawLine(0, 300, x, 300);
		//g.drawLine(0, bc.GetY(), x, bc.GetY());
		
		/*for(BoxCollider bc: BoxCollider.GetList())
		{
			g.setColor(Color.white);
			((Graphics2D) g).fill(bc.GetArea());
			g.setColor(Color.red);
			((Graphics2D) g).draw(bc.GetArea().getBounds2D());
		}*/
		
		/*int res = 5;
		for(int x = zero + (int) (544*gScale); x < zero + (int)(1856*gScale); x+=res)
		{
			for(int y = (int) (32*gScale); y < (int)(1048*gScale); y+=res)
			{
				int col = (int)(Math.round(Math.random()*100)%50);
				
				if(Math.random() > 0.5)
				{
					col = 255-col;
				}
				
				g.setColor(new Color(col, col, col));
				g.fillRect(x, y, res, res);
			}
		}*/
		
		g.drawImage(UpperUI, zero, 0, (int)(UpperUI.getWidth()*gScale), (int)(UpperUI.getHeight()*gScale), null);
	}
	
	int MaxSpace = (int)(x*0.3), BHeight = (int)(y*0.8), WWidth = MaxSpace/80, GOWidth = MaxSpace/10, WSpd = 2, WSquare = 0;
	int[] GWidth = {GOWidth, GOWidth, GOWidth};
	int[] GSquare = {0,  50, 150};
}