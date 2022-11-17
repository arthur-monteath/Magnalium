package main;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow {
	
	private JFrame frame;
	
	public GameWindow(GamePanel gamePanel)
	{
		frame = new JFrame();
		
		frame.setSize(1920,1080);
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.setVisible(true);
		
		//device.setFullScreenWindow(frame);
	}
	
	public void setPos(int x, int y)
	{
		frame.setLocation(x, y);
	}
}
