package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ra4king.gameutils.gameworld.GameComponent;

public class Bullet extends GameComponent {
	private double vx, vy;
	
	public Bullet(double sx, double sy, double dir) {
		super(sx,sy,10,10);
		
		double speed = 700;
		vx = speed * Math.cos(dir);
		vy = speed * Math.sin(dir);
	}
	
	@Override
	public void update(long deltaTime) {
		double delta = deltaTime / 1e9;
		
		setX(getX() + vx * delta);
		setY(getY() + vy * delta);
		
		if(getScreenX() < -500 || getScreenX() > getParent().getWidth() + 500)
			getParent().remove(this);
		if(getScreenY() < -500 || getScreenY() > getParent().getHeight() + 500)
			getParent().remove(this);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillOval(getIntX(), getIntY(), getIntWidth(), getIntHeight());
	}
}
