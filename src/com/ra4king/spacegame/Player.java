package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.ra4king.gameutils.Entity;
import com.ra4king.gameutils.Input;
import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.resources.ResourceBank;

public class Player extends GameComponent {
	private ResourceBank resources;
	private final double acceleration = 1000, maxSpeed = 400, resistance = 100;
	private double vx, vy;
	private long lastTime;
	
	public Player() {
		super(50,50,48,48);
		
		resources = new ResourceBank();
	}
	
	@Override
	public void update(long deltaTime) {
		Input i = getParent().getGame().getInput();
		
		double accel = acceleration * deltaTime / 1e9;
		double resist = resistance * deltaTime / 1e9; 
		
		if(i.isKeyDown(KeyEvent.VK_A))
			vx = Math.max(vx - accel, -maxSpeed);
		if(i.isKeyDown(KeyEvent.VK_D))
			vx = Math.min(vx + accel, maxSpeed);
		
		if(vx > 0)
			vx = Math.max(vx - resist, 0);
		if(vx < 0)
			vx = Math.min(vx + resist, 0);
		
		setX(getX() + vx * deltaTime / 1e9);
		
		if(i.isKeyDown(KeyEvent.VK_W))
			vy = Math.max(vy - accel, -maxSpeed);
		if(i.isKeyDown(KeyEvent.VK_S))
			vy = Math.min(vy + accel, maxSpeed);
		
		if(vy > 0)
			vy = Math.max(vy - resist, 0);
		if(vy < 0)
			vy = Math.min(vy + resist, 0);
		
		setY(getY() + vy * deltaTime / 1e9);
		
		for(Entity e : getParent().getEntities())
			if(e instanceof Planet && e.contains(getCenterX(), getCenterY())) {
				pillagePlanet((Planet)e);
				break;
			}
		
		getParent().setXOffset(-getCenterX() + getParent().getWidth()/2);
		getParent().setYOffset(-getCenterY() + getParent().getHeight()/2);
		
		MouseEvent me;
		if((me = i.isMouseDown()) != null && System.nanoTime() - lastTime >= 1e9/10) {
			lastTime = System.nanoTime();
			getParent().add(3,new Bullet(getCenterX() - 5, getCenterY() - 5, Math.atan2(me.getY() - (getScreenY() + getHeight()/2), me.getX() - (getScreenX() + getWidth()/2))));
		}
	}
	
	private void pillagePlanet(Planet planet) {
		resources.transfer(planet.getResources(), Resource.WOOD, 50);
		System.out.println("p1: " + resources.getQuantity(Resource.WOOD));
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.fillOval(getIntX(), getIntY(), getIntWidth(), getIntHeight());
		g.drawImage(getParent().getGame().getArt().get("robot"),getIntX(),getIntY(),getIntWidth(),getIntHeight(),null);
	}
}
