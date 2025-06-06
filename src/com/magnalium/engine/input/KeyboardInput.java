package com.magnalium.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.magnalium.engine.ui.GamePanel;

public class KeyboardInput implements KeyListener {

	private GamePanel gamePanel;
	
	public KeyboardInput(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		gamePanel.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		gamePanel.keyReleased(e);
	}

}
