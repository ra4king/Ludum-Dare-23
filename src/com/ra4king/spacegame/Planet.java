package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ra4king.gameutils.gameworld.GameComponent;

public class Planet extends GameComponent {
	private Color tint;
	
	public Planet(double x, double y, double size, Color tint) {
		super(x,y,size,size);
		this.tint = tint;
	}
	
	@Override
	public void update(long deltaTime) {
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(tint);
		g.fillOval(getIntX(),getIntY(),getIntWidth(),getIntHeight());
	}
}
