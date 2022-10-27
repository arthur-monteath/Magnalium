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

	@Override
	public void run() 
	{
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		while(true)
		{
			now = System.nanoTime();
			if(now - lastFrame >= timePerFrame)
			{
				Hexagon hex = gamePanel.getGrid().getClosestHex(gamePanel.getMousePos()[0], gamePanel.getMousePos()[1]);
				if(hex != null)
				{
					hex.colorToWhite();
				}
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
}
