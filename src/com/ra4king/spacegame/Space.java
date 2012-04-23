package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.Entity;
import com.ra4king.gameutils.Game;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.screens.PauseScreen;

public class Space extends GameWorld {
	private Player player;
	
	public Space() {
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void init(Game game) {
		super.init(game);
		
		player = (Player) add(2, new Player());
		add(0, new Background(player));
		
		for (int a = 0; a < 100; a++) {
			Planet planet;
			do {
				planet = new Planet(Math.random() * 5000 - (5000 - getWidth())/ 2,
									Math.random() * 5000 - (5000 - getHeight()) / 2,
									Math.random() * 200 + 50,
									new Color((int)(256 * Math.random()), (int)(256 * Math.random()),(int)(256 * Math.random()),(int)(128 * Math.random())));
				
				for (Entity comp : getEntities()) {
					if ((comp instanceof Planet || comp instanceof Player) && comp.intersects(planet.getX() - planet.getWidth()/2,planet.getY() - planet.getHeight()/2,planet.getWidth() * 2, planet.getHeight() * 2)) {
						planet = null;
						break;
					}
				}
			} while (planet == null);
			
			planet.getResources().addQuantity(Resource.WOOD, (int)(200 * Math.random()) + 1);
			planet.getResources().addQuantity(Resource.STONE, (int)(50 * Math.random()) + 1);
			planet.getResources().addQuantity(Resource.METAL, (int)(30 * Math.random()) + 1);
			planet.getResources().addQuantity(Resource.OIL, (int)(100 * Math.random()) + 1);
			planet.getResources().addQuantity(Resource.SLAVES, (int)(200 * Math.random()) + 1);
			
			add(1, planet);
		}
		
		setBackground(Color.black);
	}
	
	@Override
	public void update(long deltaTime) {
		super.update(deltaTime);
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_P || key.getKeyCode() == KeyEvent.VK_ESCAPE)
			getGame().setScreen("Pause",new PauseScreen(this));
	}
}
