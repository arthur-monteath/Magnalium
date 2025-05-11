package com.magnalium.game.battle;

import old.GamePanel;

public class CreatureEnemy extends Enemy {

	public CreatureEnemy(int region) {
		super(region, "/enemies/creature/" + region + ".png", 200, 40, 400);
		
		setPos(GamePanel.getGzero()+(int)(440*GamePanel.gScale),(int)(492*GamePanel.gScale));
	}

	
}
