package com.ra4king.spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.Game;
import com.ra4king.gameutils.MenuPage;
import com.ra4king.gameutils.Screen;
import com.ra4king.gameutils.gui.Button;

public class PauseScreen extends MenuPage {
	private Screen background;
	
	public PauseScreen(Screen background) {
		this.background = background;
	}
	
	@Override
	public void init(Game game) {
		super.init(game);
		
		setBackground(new Color(.5f,.5f,.5f,.5f));
		
		add(new Button("Exit",50,getWidth()/2,getHeight()/2 + 100, 25, 25, true, new Button.Action() {
			@Override
			public void doAction(Button button) {
				System.exit(0);
			}
		}));
	}
	
	@Override
	public void draw(Graphics2D g) {
		background.draw(g);
		
		super.draw(g);
		
		g.setColor(Color.black);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
		String s = "PAUSED";
		g.drawString(s, getWidth()/2 - g.getFontMetrics().stringWidth(s)/2, getHeight()/2);
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_P || key.getKeyCode() == KeyEvent.VK_ESCAPE)
			getGame().setScreen(background);
	}
}
