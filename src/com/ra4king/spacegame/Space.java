package com.ra4king.spacegame;

import java.awt.Color;

import com.ra4king.gameutils.Game;
import com.ra4king.gameutils.gameworld.GameWorld;

public class Space extends GameWorld {
	private Player player;
	
	public Space() {
	}
	
	public void init(Game game) {
		super.init(game);
		
		player = (Player)add(2, new Player());
		add(0, new Background(player));
		
		setBackground(Color.black);
	}
	
	@Override
	public void update(long deltaTime) {
		super.update(deltaTime);
		
		
	}
}
