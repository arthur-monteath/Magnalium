package battle;

import main.GamePanel;

public class WizardEnemy extends Enemy {

	public WizardEnemy(int region) {
		super(region, "/enemies/wizard/" + region + ".png", 50, 50, 600);
		
		setPos(GamePanel.getGzero()+816*GamePanel.gScale,426*GamePanel.gScale);
	}

}
