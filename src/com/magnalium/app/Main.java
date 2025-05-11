package com.magnalium.app;

import com.magnalium.engine.core.Engine;
import com.magnalium.engine.core.GameLoopListener;
import com.magnalium.engine.rendering.GameWindow;
import com.magnalium.engine.ui.GamePanel;

public class Main {
    public static void main(String[] args) {
    	
        GamePanel gamePanel = new GamePanel();
        GameWindow window = new GameWindow(gamePanel);
        
        gamePanel.requestFocusInWindow();

        int targetFPS = 120;
        int targetUPS = 200;
        
        Engine engine = new Engine(targetFPS, targetUPS, new GameLoopListener() {
            @Override
            public void update(double dt) {
                gamePanel.updateGame();
            }

            @Override
            public void render() {
                gamePanel.repaint();
            }
        });
        
        engine.start();
    }
}