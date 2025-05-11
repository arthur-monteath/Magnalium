package com.magnalium.engine.physics;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

import old.GamePanel;

public class BoxCollider {

	private int width,height,x,y,rotation;
	private Area oArea, area;
	private Point center;
	
	private static ArrayList<BoxCollider> list = new ArrayList<BoxCollider>();
	
	public static ArrayList<BoxCollider> GetList()
	{
		return list;
	}
	
	public int GetRotation()
	{
		return rotation;
	}
	
	public Area GetArea()
	{
		return area;
	}
	
	private AffineTransform at;
	
	public void SetRotation(int angle)
	{
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(angle), center.x, center.y);
		area = oArea.createTransformedArea(at);
		
		center.x = (int)area.getBounds().getCenterX();
		center.y = (int)area.getBounds().getCenterY();
		
		this.at = at;
		
		x=(int) area.getBounds2D().getX();
		y=(int) area.getBounds2D().getY();
		
		rotation = angle;
	}
	
	public void SetRotation(int angle, int x, int y)
	{
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(angle), x, y);
		area = oArea.createTransformedArea(at);
		
		center.x = (int)area.getBounds().getCenterX();
		center.y = (int)area.getBounds().getCenterY();
		
		this.at = at;
		
		x=(int) area.getBounds2D().getX();
		y=(int) area.getBounds2D().getY();
		
		rotation = angle;
	}
	
	public void Move(int x, int y)
	{
		this.x+=x;
		this.y+=y;
	}
	
	public int GetX()
	{
		return x;
	}
	
	public int GetY()
	{
		return y;
	}
	
	public Point GetCenter()
	{
		return center;
	}
	
	public BoxCollider(int w, int h, int x, int y)
	{
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		
		Rectangle rect = new Rectangle(x,y,w,h);
		area = new Area(rect);
		oArea = new Area(rect);
		
		center = new Point(x+w/2, y+h/2);
		
		list.add(this);
	}
	
	public int GetWidth()
	{
		return width;
	}
	
	public int GetHeight()
	{
		return height;
	}
	
	public AffineTransform GetAt()
	{
		return at;
	}
	
	public static BoxCollider CheckCollision(BoxCollider c)
	{
		ArrayList<BoxCollider> l = new ArrayList<BoxCollider>(list);
		l.remove(c);
		
		for(int i = 0; i<l.size(); i++)
		{
			Area a = c.GetArea();
			a.intersect(l.get(i).GetArea());
			
			if(!a.isEmpty())
			{
				l.add(c);
				return l.get(i);
			}
		}
		
		return null;
	}
	
	public final int g = 1;
	
	public void ApplyGravity()
	{
		int[] pos = {x,y};
		
		if(y+(int)area.getBounds2D().getHeight()<=GamePanel.y)
		{
			y+=g;

			if(CheckCollision(this)!=null)
			{
				x = pos[0];
				y = pos[1];
				
				UpdatePos();
			}
			else
			{
				UpdatePos();
			}
		}
		else
		{
			System.out.println(GamePanel.y + " - " + area.getBounds2D().getHeight() + " = " + (GamePanel.y-area.getBounds2D().getHeight()) + " | Y: " + y + " | Y+h: " + (y+(int)area.getBounds2D().getHeight()));
			y=(int) (GamePanel.y-area.getBounds2D().getHeight());
		}
	}
	
	private void UpdatePos()
	{
		Rectangle rect = new Rectangle(x,y,width,height);
		oArea = new Area(rect);
		area = new Area(rect);
		
		center.x = (int)area.getBounds().getCenterX();
		center.y = (int)area.getBounds().getCenterY();
		
		SetRotation(rotation);
	}
}
