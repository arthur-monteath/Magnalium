package com.magnalium.game.items;

import java.util.ArrayList;

public class ManaCrystal implements Item {
	
	public static final int[][] grid =
	{	  	  //Par sobe Ímpar desce.
		{	-1,1,3,5,-1},
		{	11,0,24,0,11},
		{	0,0,-1,0,0},
		{	11,0,24,0,11},
		{	-1,0,6,0,-1},
		{	-1,2,-1,4,-1},
	};
	
	public static final int[] energized = {0,3};

	public int[][] getGrid()
	{
		return grid;
	}
	
	public int[] getEnergized()
	{
		return energized;
	}
}
