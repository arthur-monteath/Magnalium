package com.magnalium.game.battle;

import old.GamePanel;

public class SwordEnemy extends Enemy {
	
	public SwordEnemy(int region) {
		super(region, "/enemies/sword/" + region + ".png", 100, 35, 200);
		
		setPos(GamePanel.getGzero()+548*GamePanel.gScale,642*GamePanel.gScale);
	}

}