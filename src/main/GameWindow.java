package main;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameWindow {
	
	private JFrame frame;
	
	public GameWindow(GamePanel gamePanel)
	{
		frame = new JFrame();
		
		//frame.setSize(500,500);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.setVisible(true);
	}
	
	public void setPos(int x, int y)
	{
		frame.setLocation(x, y);
	}
}
