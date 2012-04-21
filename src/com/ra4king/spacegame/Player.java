package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.Input;
import com.ra4king.gameutils.gameworld.GameComponent;

public class Player extends GameComponent {
	public Player() {
		super(50,50,50,50);
	}
	
	@Override
	public void update(long deltaTime) {
		Input i = getParent().getGame().getInput();
		
		double dist = 300 * deltaTime / 1e9;
		
		if(i.isKeyDown(KeyEvent.VK_LEFT))
			setX(getX() - dist);
		if(i.isKeyDown(KeyEvent.VK_RIGHT))
			setX(getX() + dist);
		
		if(i.isKeyDown(KeyEvent.VK_UP))
			setY(getY() - dist);
		if(i.isKeyDown(KeyEvent.VK_DOWN))
			setY(getY() + dist);
		
		getParent().setXOffset(-getCenterX() + getParent().getWidth()/2);
		getParent().setYOffset(-getCenterY() + getParent().getHeight()/2);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.fill(getBounds());
	}
}
