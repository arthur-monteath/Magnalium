package main;

import input.*;
import items.*;
import recipes.Recipe;
import recipes.RecipesGrimoire;
import recipes.WoodSword;
import store.Button;
import store.Currency;
import store.Store;
import utils.*;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import elements.ElementsManuscript;
import elements.FixedElement;
import elements.GrabElement;
import elements.Grid;
import elements.GridElement;
import elements.ManuscriptElement;
import elements.StockElement;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private MouseInput mouseInput;
	public static int x=Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int y=Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public final static double gScale = (double)Toolkit.getDefaultToolkit().getScreenSize().height/1080;
	
	private BufferedImage[] sprites = new BufferedImage[12];
	private BufferedImage WoodBg, UpperUI, forest, cave, Wood, Stone, vellum, manuscriptImg, grimoireImg, manuscriptOpenImg, grimoireOpenImg;
	
	private boolean grimoireOpen = false, manuscriptOpen = false, InvMode = false;
	
	private Random r = new Random();
	private static int zero = 0, gZero = 0;
	
	private BufferedImage[] Icons = new BufferedImage[6];
	
	private Store store;
	private ElementsManuscript manuscript;
	private RecipesGrimoire grimoire;
	Recipe recipe;
	
	private void loadImages()
	{
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
		
		Icons[0] = img.getSubimage(0, 0, 64, 64);
		Icons[1] = img.getSubimage(64, 0, 64, 64);
		Icons[2] = img.getSubimage(128, 0, 64, 64);
		Icons[3] = img.getSubimage(0, 64, 64, 64);
		Icons[4] = img.getSubimage(64, 64, 64, 64);
		Icons[5] = img.getSubimage(128, 64, 64, 64);
		
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
		
		is = getClass().getResourceAsStream("/Cave.png");
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
		
		cave = img;
		
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
		
		is = getClass().getResourceAsStream("/Wood.png");
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
		
		Wood = img;
		
		is = getClass().getResourceAsStream("/itemsRes/2.png");
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
		
		Stone = img;
		
		is = getClass().getResourceAsStream("/vellum.png");
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
		
		vellum = img;
		
		is = getClass().getResourceAsStream("/manuscriptOpen.png");
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
		
		manuscriptOpenImg = img;
		
		is = getClass().getResourceAsStream("/grimoireOpen.png");
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
		
		grimoireOpenImg = img;
		
		is = getClass().getResourceAsStream("/ElementsManuscript.png");
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
		
		manuscriptImg = img;
		
		is = getClass().getResourceAsStream("/RecipesGrimoire.png");
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
		
		grimoireImg = img;
	}
	
	public GamePanel()
	{
		System.out.println(gScale);
		mouseInput = new MouseInput(this);
		addKeyListener(new KeyboardInput(this));
		
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		this.setBackground(new Color(0,0,0,255));
		
		loadImages();
		
		zero = (int)((x/2)-(UpperUI.getWidth()*gScale/2));
		gZero = zero + (int)(544*gScale);
		
		System.out.println(zero);
		
		x = UpperUI.getWidth();
		y = UpperUI.getHeight();
		
		x *= gScale;
		y *= gScale;
		
		for(int i = 1; i<Element.primary.length; i++)
		{
			addToStock(i,99);
			
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
		
		store = new Store();
		recipe = new WoodSword();
		
		manuscript = new ElementsManuscript();
		grimoire = new RecipesGrimoire();
		
		int gridId = 2;
		
		int hRadius = (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/27)*Element.scale);
		
		int[] size = Research.getSizeOfGrid(gridId, hRadius);
		
		hexGrid = new Grid(gridId, (int)(zero + (gScale*583) + (938*gScale/2) - (size[0]/2)) + (int)(hRadius*(1+Math.ceil(Research.getList().get(gridId).getGrid()[0].length/2.0))/2), (int)((gScale*71) + (938*gScale/2) - (size[1]/2)) + (int)(Math.cos(Math.toRadians(30.0))*hRadius), hRadius, this);
		GridElement.checkNeighborsOfGridElements();
	}
	
	public static int getGzero()
	{
		return zero + (int)(544*gScale);
	}
	
	public static int getZero()
	{
		return zero;
	}
	
	public void createNewGrid(int gridId)
	{
		int hRadius = (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/27)*Element.scale);
		
		int[] size = Research.getSizeOfGrid(gridId, hRadius);
		
		GridElement.getGList().clear();
		hexGrid = new Grid(gridId, (int)((gScale*583) + (938*gScale/2) - (size[0]/2)) + (int)(hRadius*(1+Math.ceil(Research.getList().get(gridId).getGrid()[0].length/2.0))/2), (int)((gScale*71) + (938*gScale/2) - (size[1]/2)) + (int)(Math.cos(Math.toRadians(30.0))*hRadius), hRadius, this);
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
	
	private void grabbedToMouse()
	{
		grabbed = GrabElement.getGrabbed();
		
		if(grabbed!=null)
		{
			grabbed.updateToMouse(mx,my);
		}
		
		if(GrabbedItem.getGrabbed() != null)
		{
			GrabbedItem.getGrabbed().updateToMouse(mx,my);
		}
	}
	
	public void updateGame() {
		
		if(Window==0)
		{
			grabbedToMouse();
			
			manuscript.updateGrab(mx, my);
			grimoire.updateGrab(mx, my);
		}
		else if(Window==1)
		{
			grabbedToMouse();
		}
		else if(Window==2)
		{
			grabbedToMouse();
		}
		else if(Window==3)
		{
			grabbedToMouse();
			
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
			
			UpdatesWithEffect--;
			woodAlpha = woodAlpha >= 0.0025 ? woodAlpha - 0.0025f : 0;
			stoneAlpha = stoneAlpha >= 0.0025 ? stoneAlpha - 0.0025f : 0;
		}
		else if(Window==4)
		{
			grabbedToMouse();
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
	
	private void grabbedElementsLogic()
	{
		if(grabbed==null)
		{
			Hexagon h = hexGrid.getClosestHex(mx, my);
			if(Window == 0 && h!=null&&!h.isElementHex()&&h.getElement()!=null)
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
				if(comb[0] != null && mx>=zero+112*gScale && mx<=zero+192*gScale)
				{
					grabbed = new GrabElement(this, comb[0].getID(), mx-Element.w/2, my-Element.h/2);
					FixedElement.getFList().remove(comb[0]);
					comb[0] = null;
					canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
				}
				else if(comb[1] != null && mx>=zero+352*gScale && mx<=zero+432*gScale)
			{
					grabbed = new GrabElement(this, comb[1].getID(), mx-Element.w/2, my-Element.h/2);
					FixedElement.getFList().remove(comb[1]);
					comb[1] = null;
					canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
				}
			}
		}
		else
		{
			Hexagon h = hexGrid.getClosestHex(mx, my);
			if(Window == 0 && h!=null&&h.getElement()==null)
			{
				grabbed.lock(h);
				grabbed = null;
			}
			else if(Window == 0 && h!=null && !h.isElementHex())
			{
				int t = h.getElement().getID();
				GridElement.getGList().remove(h.getElement());
				grabbed.lock(h);
				grabbed = new GrabElement(this, t, mx-Element.w/2, my-Element.h/2);
				GridElement.checkNeighborsOfGridElements();
			}
			else if(my>=928*gScale && my<=1008*gScale)
			{
				if(comb[0] == null && mx>=zero+112*gScale && mx<=zero+192*gScale)
				{
					comb[0] = new FixedElement(grabbed.getID(), zero+(int)Math.ceil(120*gScale), (int)Math.ceil(936*gScale));
					grabbed.release();
					grabbed = null;
					canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
				} 
				else if(comb[1] == null && mx>=zero+352*gScale && mx<=zero+432*gScale)
				{
					comb[1] = new FixedElement(grabbed.getID(), zero+(int)Math.ceil(360*gScale), (int)Math.ceil(936*gScale));
					grabbed.release();
					grabbed = null;
					canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
				}
				else if(comb[0] != null && mx>=zero+112*gScale && mx<=zero+192*gScale)
				{
					int t = comb[0].getID();
					FixedElement.getFList().remove(comb[0]);
					comb[0] = new FixedElement(grabbed.getID(), zero+(int)Math.ceil(120*gScale), (int)Math.ceil(936*gScale));
					grabbed = new GrabElement(this, t, mx-Element.w/2, my-Element.h/2);
					canComb = comb[0] != null && comb[1] != null && Combination.getResult(comb[0].getID(), comb[1].getID()) > 0;
				}
				else if(comb[1] != null && mx>=zero+352*gScale && mx<=zero+432*gScale)
				{
					int t = comb[1].getID();
					FixedElement.getFList().remove(comb[1]);
					comb[1] = new FixedElement(grabbed.getID(), zero+(int)Math.ceil(360*gScale), (int)Math.ceil(936*gScale));
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
			else
			{
				addToStock(grabbed.getID());
				grabbed.release();
				grabbed = null;
			}
		}
	}
	
	private void storeWindowPressLogic()
	{
		for(Button b: store.getButtons())
		{
			if(mx>=b.getPos()[0] && mx<=b.getPos()[0]+64*gScale && my>=b.getPos()[1] && my<= b.getPos()[1]+64*gScale)
			{
				b.buy();
				store.updateButtons();
				return;
			}
		}
	}
	
	private void grabbedItemsLogic()
	{
		if(GrabbedItem.getGrabbed() == null)
		{
			if(mx>=zero+(int)(64*gScale) && mx<=zero+(int)(480*gScale) && my>=(int)(112*gScale) && my<=(int)(880*gScale))
			{
				int[] clickedSlot = {(int)((my-(int)(112*gScale))/(64*gScale)), (int)((mx-zero-(int)(80*gScale))/(64*gScale))};
				
				System.out.println("Row: " + clickedSlot[0] + "  Col: " + clickedSlot[1]);
				
				int id = Inventory.getInv() [clickedSlot[0]] [clickedSlot[1]];
				
				if(Inventory.removeItem(id))
				{
					for(InvItem item: InvItem.GetList())
					{
						if(item.getId() == id)
						{
							new GrabbedItem(item);
							
							break;
						}
					}
				}
			}
			else
			{
				
			}
		}
		else
		{
			GrabbedItem.release();
		}
	}
	
	public void pressed(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();

		if(my>=380*gScale && my<=700*gScale && mx>=zero+1795*gScale)
		{
			if(my<=444*gScale)
			{
				manuscript.setPos((int) (zero+1560*gScale), (int) (71*gScale));
				grimoire.setPos((int) (zero+1560*gScale), (int) (432*gScale));
				
				manuscriptOpen = false;
				grimoireOpen = false;
				
				Window = 0;
			}
			else if(my<=508*gScale)
			{
				Window = 1;
			}
			else if(my<=572*gScale)
			{
				Window = 2;
			}
			else if(my<=636*gScale)
			{
				UpdatesWithEffect = 120;
				
				forestToggle = !forestToggle;
				
				Window = 3;
			}
			else
			{
				Window = 4;
			}
		}
		else if(my >= 48*gScale && my <= 96*gScale)
		{
			if(mx >= zero+144*gScale && mx<zero+272*gScale)
				InvMode = false;
			else if(mx <= zero+400*gScale)
				InvMode = true;
		}
		
		if(Window==0)
		{
			if(!InvMode)
				grabbedElementsLogic();
			else
				grabbedItemsLogic();

			if(grimoireOpen && mx>=grimoire.getPos()[0]-grimoire.getWidth()/2 && mx<=grimoire.getPos()[0]+(grimoire.getWidth()*3)/2 && my>=grimoire.getPos()[1] && my<=grimoire.getPos()[1]+grimoire.getHeight())
			{
				grimoire.grabbed(mx, my);
			}
			else if(manuscriptOpen && mx>=manuscript.getPos()[0]-manuscript.getWidth()/2 && mx<=manuscript.getPos()[0]+(manuscript.getWidth()*3)/2 && my>=manuscript.getPos()[1] && my<=manuscript.getPos()[1]+manuscript.getHeight())
			{
				manuscript.grabbed(mx, my);
			}
			else if(mx>=grimoire.getPos()[0] && mx<=grimoire.getPos()[0]+grimoire.getWidth() && my>=grimoire.getPos()[1] && my<=grimoire.getPos()[1]+grimoire.getHeight())
			{
				if(!grimoireOpen && mx>=grimoire.getPos()[0]+448*gScale && my>=grimoire.getPos()[1]+448*gScale)
				{
					openGrimoire();
				}
				else
					grimoire.grabbed(mx, my);
			}
			else if(mx>=manuscript.getPos()[0] && mx<=manuscript.getPos()[0]+manuscript.getWidth() && my>=manuscript.getPos()[1] && my<=manuscript.getPos()[1]+manuscript.getHeight())
			{
				if(!manuscriptOpen && mx>=manuscript.getPos()[0]+448*gScale && my>=manuscript.getPos()[1]+448*gScale)
				{
					openManuscript();
				}
				else
					manuscript.grabbed(mx, my);
			}
		}
		/*else if(Window == 0) // PAUSED
		{
			if(mx>zero+x-64*gScale && my<zero+64*gScale)
			{
				if(grabbed!=null)
				{
					addToStock(grabbed.getID());
					grabbed.release();
					grabbed = null;
				}
				
				manuscriptOpen = false;
				paused = false;
			}
		}*/
		else if(Window==1)
		{
			if(my >= 14*gScale && my <= 93*gScale && mx >= zero+1104*gScale && mx <= zero+1296*gScale)
			{
				if(mx <= zero+1168*gScale)
					store.switchTab(1);
				else if(mx <= zero+1232*gScale)
					store.switchTab(2);
				else
					store.switchTab(3);
				System.out.println(store.getTab());
			}
			
			storeWindowPressLogic();
			
			if(!InvMode)
				grabbedElementsLogic();
			else
				grabbedItemsLogic();
		}
		else if(Window==2)
		{
			if(!InvMode)
				grabbedElementsLogic();
			else
				grabbedItemsLogic();
		}
		else if(Window==3)
		{
			if(!InvMode)
				grabbedElementsLogic();
			else
				grabbedItemsLogic();
			
			if(forestToggle && MineBarCollides() && UpdatesWithEffect <= 0)
			{
				TreeProgress++;
				
				if(TreeProgress >= 10)
				{
					woodAlpha = 1;
					
					Inventory.addItem("wood");
					
					TreeProgress = 0;
				}
			}
			else if(!forestToggle && MineBarCollides() && UpdatesWithEffect <= 0)
			{
				StoneProgress++;
				
				if(TreeProgress >= 0)
				{
					stoneAlpha = 1;
					
					Inventory.addItem("rock");
					
					StoneProgress = 0;
				}
			}
			else
			{
				TreeProgress = 0;
				StoneProgress = 0;
			}
		}
		else if(Window==4)
		{
			if(!InvMode)
				grabbedElementsLogic();
			else
				grabbedItemsLogic();
		}
	}
	
	int Debug = 0;
	int TreeProgress = 0;
	int StoneProgress = 0;
	float woodAlpha = 0;
	float stoneAlpha = 0;
	
	boolean forestToggle = false;
	
	private Boolean MineBarCollides()
	{	
		for(int i = 0; i<GSquare.length; i++)
		{
			if((WSquare>GSquare[i] || WSquare+WWidth>GSquare[i]) && WSquare<GSquare[i]+GWidth[i])
			{
				GWidth[i] = (int) (GOWidth*r.nextDouble(0.8,1.3));
				GSquare[i] = r.nextInt(0,MaxSpace-GWidth[i]);
				
				while(checkCollision(i))
				{
					GSquare[i] = r.nextInt(0,MaxSpace-GWidth[i]);
				}
				
				Debug++;
				return true;
			}
		}
		
		return false;
	}
	
	private boolean checkCollision(int index)
	{
		for(int i = 0; i<GSquare.length; i++)
		{
			if(i!=index)
			{
				if(GSquare[index] + GWidth[index] >= GSquare[i] && GSquare[index] < GSquare[i] + GWidth[i])
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void openManuscript()
	{
		manuscriptOpen = true;
		
		manuscript.openBook();
	}
	
	private void openGrimoire()
	{
		grimoireOpen = true;
		
		grimoire.openBook();
	}
	
	public void clicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(Window==0)
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
	
	public StockElement addToStock(int id, int amount)
	{
		for(StockElement st: StockElement.getStock())
		{
			if(id==st.getID())
			{
				st.increase(amount);
				resetElementsOnStock();
				return st;
			}
		}
		
		StockElement st = new StockElement(id);
		st.increase(amount-1);
		
		return st;
	}
	
	private static int hMargin = (int)(Element.w*5/4), vMargin = (int)(Element.h*3/4+Element.h);
	
	public static int[] getMargin()
	{
		int[] a = {hMargin, vMargin};
		return a;
	}
	
	public static void resetElementsOnStock()
	{
		ArrayList<StockElement> list = StockElement.getStock();
		
		for(int i = 0; i<list.size(); i++)
		{
			if(i%6==0)
			{
				list.get(i).setPos(zero+hMargin,(int) ((i/6)*Element.h + vMargin));
			}
			else
			{
				list.get(i).setPos((int) (zero+i%6*Element.w + hMargin),(int)((i/6)*Element.h + vMargin));
			}
		}
	}
	
	private Element[] comb = new Element[2];
	
	public void released(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		if(Window==0)
		{
			if(my >= 704 && my<=768 && mx >= zero+160 && mx <= zero+288)
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
			else
			{
				manuscript.release();
				grimoire.release();
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
	
	AlphaComposite fullOpacity = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1);
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // calls the JPanel's paint component method this is used to clean the surface
		
		//g.setColor(new Color(88,67,50,255));
		//g.fillRect(32, 0, x-32, y);
		
		if(Window==0)
		{
			g.drawImage(WoodBg, zero + (int)(544*gScale), (int)(32*gScale), (int)(WoodBg.getWidth()*gScale), (int)(WoodBg.getHeight()*gScale), null);
			
			//g.setColor(new Color(218, 177, 113, 255));
			//g.fillRect((int)(zero + gScale*583), (int)(gScale*71), (int)(gScale*938), (int)(gScale*938));
			g.drawImage(vellum, (int)(zero + gScale*583), (int)(gScale*71), (int)(gScale*938), (int)(gScale*938), null);
			
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
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					
					g.setColor(Color.cyan);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					
				}
				else
				{
					g.setColor(Color.gray);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
					
					g.setColor(Color.lightGray);
					g.drawLine(c[0]+r.nextInt(min, max), c[1]+r.nextInt(min, max), c[2]+r.nextInt(min, max), c[3]+r.nextInt(min, max));
				}
			}

			g.drawImage(manuscriptImg, manuscript.getPos()[0], manuscript.getPos()[1], manuscript.getWidth(), manuscript.getHeight(), null);
			g.drawImage(grimoireImg, grimoire.getPos()[0], grimoire.getPos()[1], grimoire.getWidth(), grimoire.getHeight(), null);
			
			if(manuscriptOpen)
			{
				//g.setColor(new Color(243,239,214,255));
				//g.fillRect(manuscript.getPos()[0]-manuscript.getWidth()/2, manuscript.getPos()[1], manuscript.getWidth()*2, manuscript.getHeight());
				g.drawImage(manuscriptOpenImg ,manuscript.getPos()[0]-manuscript.getWidth()/2, manuscript.getPos()[1], manuscript.getWidth()*2, manuscript.getHeight(), null);
				
				for(ManuscriptElement element: ManuscriptElement.getBList())
				{
					g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
				}
			}
			
			if(grimoireOpen)
			{
				g.drawImage(grimoireOpenImg ,grimoire.getPos()[0]-grimoire.getWidth()/2, grimoire.getPos()[1], grimoire.getWidth()*2, grimoire.getHeight(), null);
			}
			
			DrawUpperBg(g);
			
			if(InvMode)
				DrawItems(g);
			else
				DrawStockElements(g);
			
			DrawFGElements(g);
			
			/*
			if(canComb)
				g.drawImage(sprites[1], 160, 704, null);
			else
				g.drawImage(sprites[0], 160, 704, null);
			*/
		}
		else if(Window==1)
		{
			g.drawImage(WoodBg, zero + (int)(544*gScale), (int)(32*gScale), (int)(WoodBg.getWidth()*gScale), (int)(WoodBg.getHeight()*gScale), null);
			
			g.setColor(Color.white);
			
			for(int i = 0; i<3; i++)
			{
				if(i!=store.getTab()-1)
					g.drawImage(Icons[0], zero+(int)((1104+(64*i))*gScale), (int)(14*gScale), (int)(Icons[i].getWidth()*gScale), (int) (Icons[i].getHeight()*gScale), null);
				else
					g.drawImage(Icons[0], zero+(int)((1104+(64*i))*gScale), (int)(29*gScale), (int)(Icons[i].getWidth()*gScale), (int) (Icons[i].getHeight()*gScale), null);
			}
			
			for(Button b: store.getButtons())
			{
				if(b.getBought())
					g.setColor(Color.white);
				else if(b.getUnlocked())
					g.setColor(Color.gray);
				else
					g.setColor(Color.darkGray);
				
				g.fillOval(b.getPos()[0], b.getPos()[1], (int)(64*gScale), (int)(64*gScale));
			}
			
			DrawUpperBg(g);
			
			if(InvMode)
				DrawItems(g);
			else
				DrawStockElements(g);

			DrawFGElements(g);
			
			for(IComponent c: IComponent.getList())
			{
				g.drawImage(c.getImg(), c.getPos()[0], c.getPos()[1], (int)(c.getWidth()), (int)(c.getHeight()), null);
			}
			
			IComponent c = IComponent.getGrabbed();
			
			if(c != null)
				g.drawImage(c.getImg(), c.getPos()[0], c.getPos()[1], (int)(c.getWidth()), (int)(c.getHeight()), null);
		}
		else if(Window==2)
		{
			g.drawImage(WoodBg, zero + (int)(544*gScale), (int)(32*gScale), (int)(WoodBg.getWidth()*gScale), (int)(WoodBg.getHeight()*gScale), null);
			
			recipe.drawRecipe(g);
				
			DrawUpperBg(g);
			
			if(InvMode)
				DrawItems(g);
			else
				DrawStockElements(g);
			
			DrawFGElements(g);
		}
		else if(Window==3)
		{
			if(forestToggle)
			{
				g.drawImage(forest, zero + (int)(544*gScale), (int)(32*gScale), (int)(forest.getWidth()*gScale), (int)(forest.getHeight()*gScale), null);
				
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
				g.drawString(Integer.toString(TreeProgress), zero + x/2, BHeight-10);
				
				if(UpdatesWithEffect>0)
				{
					for(int x = zero + (int) (544*gScale); x < zero + (int)(1856*gScale); x+=5)
					{
						for(int y = (int) (32*gScale); y < (int)(1048*gScale); y+=5)
						{
							int col = (int)(Math.round(Math.random()*100)%50);
							
							if(Math.random() > 0.5)
							{
								col = 255-col;
							}
							
							g.setColor(new Color(col, col, col));
							g.fillRect(x, y, 5, 5);
						}
					}
				}
				
				DrawUpperBg(g);
				
				if(InvMode)
					DrawItems(g);
				else
					DrawStockElements(g);
				
				DrawFGElements(g);
				
				 //draw half transparent
				AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,woodAlpha);
				((Graphics2D) g).setComposite(ac);
				g.drawImage(Wood, zero + x/2, (int)(BHeight-100-(10/woodAlpha)), (int)(Wood.getWidth()*2*gScale), (int)(Wood.getHeight()*2*gScale), null);
			}
			else
			{
				g.drawImage(cave, zero + (int)(544*gScale), (int)(32*gScale), (int)(forest.getWidth()*gScale), (int)(forest.getHeight()*gScale), null);
				
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
				g.drawString(Integer.toString(StoneProgress), zero + x/2, BHeight-10);
				
				if(UpdatesWithEffect>0)
				{
					for(int x = zero + (int) (544*gScale); x < zero + (int)(1856*gScale); x+=5)
					{
						for(int y = (int) (32*gScale); y < (int)(1048*gScale); y+=5)
						{
							int col = (int)(Math.round(Math.random()*100)%50);
							
							if(Math.random() > 0.5)
							{
								col = 255-col;
							}
							
							g.setColor(new Color(col, col, col));
							g.fillRect(x, y, 5, 5);
						}
					}
				}
				
				DrawUpperBg(g);
				
				if(InvMode)
					DrawItems(g);
				else
					DrawStockElements(g);
				
				DrawFGElements(g);
				
				 //draw half transparent
				AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,stoneAlpha);
				((Graphics2D) g).setComposite(ac);
				g.drawImage(Stone, zero + x/2, (int)(BHeight-100-(10/stoneAlpha)), (int)(Stone.getWidth()*2*gScale), (int)(Stone.getHeight()*2*gScale), null);
			}
		}
		else if(Window==4)
		{
			g.drawImage(WoodBg, zero + (int)(544*gScale), (int)(32*gScale), (int)(WoodBg.getWidth()*gScale), (int)(WoodBg.getHeight()*gScale), null);
			
			DrawUpperBg(g);
			
			if(InvMode)
				DrawItems(g);
			else
				DrawStockElements(g);
			
			DrawFGElements(g);
		}
		
		((Graphics2D) g).setComposite(fullOpacity);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, zero, y);
		g.fillRect(zero+x, 0, zero, y);
	}
	
	int UpdatesWithEffect = 120;
	
	int MaxSpace = (int)(x*0.3), BHeight = (int)(y*0.8), WWidth = MaxSpace/80, GOWidth = MaxSpace/10, WSpd = 2, WSquare = 0;
	int[] GWidth = {GOWidth, GOWidth, GOWidth};
	int[] GSquare = {0,  50, 150};
	
	private void DrawUpperBg(Graphics g)
	{
		for(int i = 0; i<5; i++)
		{
			if(i!=Window)
				g.drawImage(Icons[i], zero+(int)(1810*gScale), (int)((380+(64*i))*gScale), (int)(Icons[i].getWidth()*gScale), (int) (Icons[i].getHeight()*gScale), null);
			else
				g.drawImage(Icons[i], zero+(int)(1795*gScale), (int)((380+(64*i))*gScale), (int)(Icons[i].getWidth()*gScale), (int) (Icons[i].getHeight()*gScale), null);
		}
		
		g.drawImage(UpperUI, zero, 0, (int)(UpperUI.getWidth()*gScale), (int)(UpperUI.getHeight()*gScale), null);
	}
	
	private void DrawStockElements(Graphics g)
	{
		g.setColor(new Color(129,76,47));
		g.fillRect(zero+(int)(144*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
		g.setColor(new Color(104,62,40));
		g.fillRect(zero+(int)(272*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
		
		g.setColor(new Color(0,0,0));
		g.drawRect(zero+(int)(144*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
		g.drawRect(zero+(int)(272*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
		
		g.setColor(new Color(39,31,25,255));
		g.fillOval(zero+(int)(112*gScale), (int)(928*gScale), (int)(80*gScale), (int)(80*gScale));
		g.fillOval(zero+(int)(352*gScale), (int)(928*gScale), (int)(80*gScale), (int)(80*gScale));
		
		g.setColor(Color.white);
		g.setFont(new Font("/SimpleLife.ttf", Font.BOLD, (int)(20*gScale)));
		
		for(StockElement element: StockElement.getStock())
		{
			g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
			g.drawString(String.valueOf(element.getAmount()), element.getPos()[0]+(Element.w*(8-String.valueOf(element.getAmount()).length())/8), element.getPos()[1]+(Element.h));
		}
		
		for(FixedElement element: FixedElement.getFList())
			if(element==comb[0] || element==comb[1])
				g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
	}
	
	private void DrawFGElements(Graphics g)
	{
		for(FixedElement element: FixedElement.getFList())
			if(element!=comb[0] && element!=comb[1])
				g.drawImage(element.getImg(), element.getPos()[0], element.getPos()[1], Element.w, Element.h, null);
		
		if(grabbed != null)
			g.drawImage(grabbed.getImg(), grabbed.getPos()[0], grabbed.getPos()[1], Element.w, Element.h, null);
	}
	
	private void DrawItems(Graphics g)
	{
		g.setColor(new Color(104,62,40));
		g.fillRect(zero+(int)(144*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
		g.setColor(new Color(129,76,47));
		g.fillRect(zero+(int)(272*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
			
		g.setColor(new Color(0,0,0));
		g.drawRect(zero+(int)(144*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
		g.drawRect(zero+(int)(272*gScale), (int)(48*gScale), (int)(128*gScale), (int)(48*gScale));
		
		g.setColor(Color.white);
		g.setFont(new Font("/SimpleLife.ttf", Font.BOLD, (int)(20*gScale)));
		
		for(InvItem item: InvItem.GetList())
		{
			g.drawImage(item.GetImg(), hMargin+(int)(item.GetSlot()[1]*64*gScale), vMargin+(int)(item.GetSlot()[0]*64*gScale), item.GetWidth(), item.GetHeight(), null);
			g.drawString(String.valueOf(Inventory.getAmount(item.getId())), item.getPos()[0]+(InvItem.w*item.GetSize()[0]*(8-String.valueOf(Inventory.getAmount(item.getId())).length())/8), item.getPos()[1]+(InvItem.h*item.GetSize()[1]));
		}
		
		GrabbedItem grabbed = GrabbedItem.getGrabbed();
		if(grabbed != null)
		{
			g.drawImage(grabbed.getItem().GetImg(), grabbed.getPos()[0], grabbed.getPos()[1], grabbed.getWidth(), grabbed.getHeight(), null);
		}
	}
}