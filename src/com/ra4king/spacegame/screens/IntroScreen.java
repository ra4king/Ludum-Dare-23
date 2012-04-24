package com.ra4king.spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.BasicScreen;
import com.ra4king.spacegame.Space;

public class IntroScreen extends BasicScreen {
	@Override
	public void update(long deltaTime) {}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,60));
		g.setColor(Color.red);
		g.drawString("Conquer the Universe!", getWidth()/2-300, getHeight()/2);
		
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
		g.drawString("You start on your tiny planet....", getWidth()/2 - 250, getHeight()/2 + 80);
		
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		g.drawString("Hit any key to start ;)", getWidth()/2 - 100, getHeight()/2 + 150);
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		getGame().setScreen("Space", new Space());
	}
}
