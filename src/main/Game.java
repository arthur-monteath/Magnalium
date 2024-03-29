package main;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

import utils.Hexagon;

public class Game implements Runnable {
	
	private GameWindow frame;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	
	public Game()
	{
		gamePanel = new GamePanel();
		frame = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
	}
	
	private void startGameLoop()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void update()
	{
		Hexagon hex = gamePanel.getGrid() != null ? gamePanel.getGrid().getClosestHex(gamePanel.getMousePos()[0], gamePanel.getMousePos()[1]) : null;
		
		if(gamePanel.getGrid() != null)
		{
			for(Hexagon[] h: gamePanel.getGrid().getHexGrid())
			{
				for(Hexagon hx: h)
				{
					if(hx!=null && !hx.isElementHex())
					{
						hx.colorToDefault();
					}
				}
			}
			
			if(hex != null && !hex.isElementHex())
			{
				hex.colorToWhite();
			}
		}
		
		gamePanel.updateGame();
	}

	@Override
	public void run() 
	{
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true)
		{
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1)
			{
				update();
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1)
			{
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();
				//System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
}
