package com.ra4king.spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.BasicScreen;
import com.ra4king.gameutils.Screen;

public class PauseScreen extends BasicScreen {
	private Screen background;
	
	public PauseScreen(Screen background) {
		this.background = background;
	}
	
	@Override
	public void update(long deltaTime) {}
	
	@Override
	public void draw(Graphics2D g) {
		background.draw(g);
		
		g.setColor(new Color(.5f,.5f,.5f,.5f));
		g.fillRect(0, 0, getWidth(), getHeight());
		
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
