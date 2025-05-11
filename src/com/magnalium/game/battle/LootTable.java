package com.magnalium.game.battle;

import java.util.Random;

import com.magnalium.game.elements.LootElement;

public class LootTable 
{
	public static int[][][] lootTable =
		{    // Sword     Creature   Wizard
			{ { 3,2,5 }, { 3,2,5 }, { 3,2,5 } }, // 0 - Grass
			
			{ { 1,3,6 }, { 1,3,6 }, { 1,3,6 } }, // 1 - Lava
			
			{ { 4,5,6 }, { 4,5,6 }, { 4,5,6 } }, // 2 - Dark
		};
	
	public static void spawnLoot(Enemy currentEnemy, int region)
	{
		Random rand = new Random();
		
		for(int i = 0; i<rand.nextInt(2,6); i++)
		{
			new LootElement(lootTable[region][currentEnemy.getId()][rand.nextInt(0,lootTable[region][currentEnemy.getId()].length)], currentEnemy.getPos()[0]+currentEnemy.GetWidth()/2, currentEnemy.getPos()[1]+currentEnemy.GetHeight()/2);
		}
	}
}
