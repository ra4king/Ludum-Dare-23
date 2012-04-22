package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.Entity;
import com.ra4king.gameutils.Input;
import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.resources.ResourceBank;

public class Player extends GameComponent {
	private ResourceBank resources;
	private final double acceleration = 1000, maxSpeed = 500, resistance = 100;
	private double vx, vy;
	
	public Player() {
		super(50,50,48,48);
		
		resources = new ResourceBank();
	}
	
	@Override
	public void update(long deltaTime) {
		Input i = getParent().getGame().getInput();
		
		double accel = acceleration * deltaTime / 1e9;
		double resist = resistance * deltaTime / 1e9; 
		
		double oldX = getX(), oldY = getY();
		
		if(i.isKeyDown(KeyEvent.VK_LEFT))
			vx = Math.max(vx - accel, -maxSpeed);
		if(i.isKeyDown(KeyEvent.VK_RIGHT))
			vx = Math.min(vx + accel, maxSpeed);
		
		if(vx > 0)
			vx = Math.max(vx - resist, 0);
		if(vx < 0)
			vx = Math.min(vx + resist, 0);
		
		setX(getX() + vx * deltaTime / 1e9);
		
		Planet planet = null;
		
		for(Entity e : getParent().getEntities())
			if(e instanceof Planet && distanceSquared(e,this) < ((e.getWidth() + getWidth())/2) * ((e.getWidth() + getWidth())/2)) {
				planet = (Planet)e;
				setX(oldX);
				vx = 0;
				break;
			}
		
		if(planet != null)
			pillagePlanet(planet);
		
		if(i.isKeyDown(KeyEvent.VK_UP))
			vy = Math.max(vy - accel, -maxSpeed);
		if(i.isKeyDown(KeyEvent.VK_DOWN))
			vy = Math.min(vy + accel, maxSpeed);
		
		if(vy > 0)
			vy = Math.max(vy - resist, 0);
		if(vy < 0)
			vy = Math.min(vy + resist, 0);
		
		setY(getY() + vy * deltaTime / 1e9);
		
		Planet planet2 = null;
		
		for(Entity e : getParent().getEntities())
			if(e instanceof Planet && distanceSquared(e,this) < ((e.getWidth() + getWidth())/2) * ((e.getWidth() + getWidth())/2)) {
				planet2 = (Planet)e;
				setY(oldY);
				vy = 0;
				break;
			}
		
		if(planet != planet2 && planet2 != null)
			pillagePlanet(planet2);
		
		getParent().setXOffset(-getCenterX() + getParent().getWidth()/2);
		getParent().setYOffset(-getCenterY() + getParent().getHeight()/2);
		
		System.out.println(vx + " " + vy);
	}
	
	private void pillagePlanet(Planet planet) {
		resources.transfer(planet.getResources(), Resource.WOOD, 50);
		System.out.println("p1: " + resources.getQuantity(Resource.WOOD));
	}
	
	private double distanceSquared(Entity e1, Entity e2) {
		return (e1.getCenterX() - e2.getCenterX()) * (e1.getCenterX() - e2.getCenterX()) +
			   (e1.getCenterY() - e2.getCenterY()) * (e1.getCenterY() - e2.getCenterY());
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.fillOval(getIntX(), getIntY(), getIntWidth(), getIntHeight());
		g.drawImage(getParent().getGame().getArt().get("robot"),getIntX(),getIntY(),getIntWidth(),getIntHeight(),null);
	}
}
