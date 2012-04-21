package com.ra4king.spacegame;

import java.awt.Color;

import com.ra4king.gameutils.Entity;
import com.ra4king.gameutils.Game;
import com.ra4king.gameutils.gameworld.GameWorld;

public class Space extends GameWorld {
	private Player player;
	
	public Space() {
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
									new Color((float) Math.random(), (float) Math.random(),(float) Math.random()));
				
				for (Entity comp : getEntities()) {
					if (comp instanceof Planet
							&& comp.getBounds().intersects(planet.getBounds())) {
						planet = null;
						break;
					}
				}
			} while (planet == null);
			
			add(1, planet);
		}
		
		setBackground(Color.black);
	}
	
	@Override
	public void update(long deltaTime) {
		super.update(deltaTime);
		
	}
}
