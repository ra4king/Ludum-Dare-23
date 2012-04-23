package com.ra4king.spacegame.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ra4king.gameutils.Game;
import com.ra4king.gameutils.MenuPage;
import com.ra4king.gameutils.gui.Button;
import com.ra4king.spacegame.Explosion;
import com.ra4king.spacegame.Planet;
import com.ra4king.spacegame.Space;
import com.ra4king.spacegame.resources.Resource;

public class ActionScreen extends MenuPage {
	private Space background;
	private Planet planet;
	
	private Button steal, attack;
	
	public ActionScreen(Space background, Planet planet) {
		this.background = background;
		this.planet = planet;
		
		setBackground(new Color(.5f,.5f,.5f,.5f));
	}
	
	@Override
	public void init(Game game) {
		super.init(game);
		
		Button.Action action = new Button.Action() {
			@Override
			public void doAction(Button button) {
				if(button == steal) {
					System.out.println("Clicked on steal!");
					background.getPlayer().getResources().transfer(planet.getResources(), Resource.WOOD, 50);
					System.out.println(background.getPlayer().getResources().getQuantity(Resource.WOOD));
				}
				else if(button == attack) {
					System.out.println("Clicked on attack!");
					background.remove(planet);
					background.add(1,new Explosion(planet.getX(),planet.getY(),planet.getWidth()));
				}
				
				ActionScreen.this.getGame().setScreen(background);
			}
		};
		
		steal = new Button("Steal Resources",30,getWidth()/2,getHeight()/2-50,25,25,true,action);
		attack = new Button("Attack",30,getWidth()/2,getHeight()/2+50,25,25,true,action);
		
		add(steal);
		add(attack);
	}
	
	@Override
	public void draw(Graphics2D g) {
		background.draw(g);
		super.draw(g);
	}
}
