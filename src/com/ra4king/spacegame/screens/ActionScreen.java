package com.ra4king.spacegame.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ra4king.gameutils.Game;
import com.ra4king.gameutils.MenuPage;
import com.ra4king.gameutils.Screen;
import com.ra4king.gameutils.gui.Button;

public class ActionScreen extends MenuPage {
	private Screen background;
	
	private Button steal, attack;
	
	public ActionScreen(Screen background) {
		this.background = background;
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
				}
				else if(button == attack) {
					System.out.println("Clicked on attack!");
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
