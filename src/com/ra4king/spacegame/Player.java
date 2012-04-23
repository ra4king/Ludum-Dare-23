package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.ra4king.gameutils.Input;
import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.gameutils.util.FastMath;
import com.ra4king.spacegame.resources.ResourceBank;

public class Player extends GameComponent {
	private ResourceBank resources;
	private final double ACCELERATION = 700, MAX_SPEED = 700, RESISTANCE = 100;
	private double direction, speed;
	private int currentFrame;
	
	public Player() {
		super(50,50,90,50);
		
		resources = new ResourceBank();
	}
	
	public ResourceBank getResources() {
		return resources;
	}
	
	@Override
	public void update(long deltaTime) {
		Input i = getParent().getGame().getInput();
		
		double delta = deltaTime / 1e9;
		
		double turnSpeed = Math.PI * delta;
		double accel = ACCELERATION * delta;
		double resist = RESISTANCE * delta; 
		
		if(i.isKeyDown(KeyEvent.VK_A))
			direction -= turnSpeed;
		if(i.isKeyDown(KeyEvent.VK_D))
			direction += turnSpeed;
		
		if(i.isKeyDown(KeyEvent.VK_W))
			speed = Math.min(speed + accel, MAX_SPEED);
		if(i.isKeyDown(KeyEvent.VK_S))
			speed = Math.max(speed - accel, -MAX_SPEED);
		
		if(speed > 0)
			speed = Math.max(speed - resist, 0);
		if(speed < 0)
			speed = Math.min(speed + resist, 0);
		
		if(Math.abs(speed) < 200)
			currentFrame = 0;
		else if(Math.abs(speed) < 2 * MAX_SPEED / 3)
			currentFrame = 1;
		else if(Math.abs(speed) < MAX_SPEED-5)
			currentFrame = 3;
		else
			currentFrame = 2;
		
		setX(getX() + speed * FastMath.cos(direction) * delta);
		setY(getY() + speed * FastMath.sin(direction) * delta);
		
//		for(Entity e : getParent().getEntities())
//			if(e instanceof Planet && e.contains(getCenterX(), getCenterY())) {
//				if(getParent().getGame().getInput().isKeyDown(KeyEvent.VK_E))
//					getParent().getGame().setScreen("Action",new ActionScreen((Space)getParent(),(Planet)e));
//				
//				break;
//			}
		
		getParent().setXOffset(-getCenterX() + getParent().getWidth()/2);
		getParent().setYOffset(-getCenterY() + getParent().getHeight()/2);
		
//		MouseEvent me;
//		if((me = i.isMouseDown()) != null && System.nanoTime() - lastTime >= 1e9/10) {
//			lastTime = System.nanoTime();
//			getParent().add(3,new Bullet(getCenterX() - 5, getCenterY() - 5, Math.atan2(me.getY() - (getScreenY() + getHeight()/2), me.getX() - (getScreenX() + getWidth()/2))));
//		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.rotate(direction, getCenterX(), getCenterY());
		g.setColor(Color.red);
		g.drawImage(getParent().getGame().getArt().get("spaceships" + currentFrame),getIntX(),getIntY(),getIntWidth(),getIntHeight(),null);
	}
}
