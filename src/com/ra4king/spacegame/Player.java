package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.Entity;
import com.ra4king.gameutils.Input;
import com.ra4king.gameutils.gameworld.GameComponent;

public class Player extends GameComponent {
	public Player() {
		super(50,50,48,48);
	}
	
	@Override
	public void update(long deltaTime) {
		Input i = getParent().getGame().getInput();
		
		double dist = 300 * deltaTime / 1e9;
		
		double oldX = getX(), oldY = getY();
		
		if(i.isKeyDown(KeyEvent.VK_LEFT))
			setX(getX() - dist);
		if(i.isKeyDown(KeyEvent.VK_RIGHT))
			setX(getX() + dist);
		
		for(Entity e : getParent().getEntities())
			if(e instanceof Planet && distanceSquared(e,this) < ((e.getWidth() + getWidth())/2) * ((e.getWidth() + getWidth())/2)) {
				setX(oldX);
				break;
			}
		
		if(i.isKeyDown(KeyEvent.VK_UP))
			setY(getY() - dist);
		if(i.isKeyDown(KeyEvent.VK_DOWN))
			setY(getY() + dist);
		
		for(Entity e : getParent().getEntities())
			if(e instanceof Planet && distanceSquared(e,this) < ((e.getWidth() + getWidth())/2) * ((e.getWidth() + getWidth())/2)) {
				setY(oldY);
				break;
			}
		
		getParent().setXOffset(-getCenterX() + getParent().getWidth()/2);
		getParent().setYOffset(-getCenterY() + getParent().getHeight()/2);
	}
	
	private double distanceSquared(Entity e1, Entity e2) {
		return (e1.getCenterX() - e2.getCenterX()) * (e1.getCenterX() - e2.getCenterX()) +
			   (e1.getCenterY() - e2.getCenterY()) * (e1.getCenterY() - e2.getCenterY());
	}
	
	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.red);
		//g.fillOval(getIntX(),getIntY(),getIntWidth(),getIntHeight());
		g.drawImage(getParent().getGame().getArt().get("robot"),getIntX(),getIntY(),getIntWidth(),getIntHeight(),Color.red,null);
	}
}
