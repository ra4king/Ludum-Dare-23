package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.spacegame.resources.ResourceBank;

public class Planet extends GameComponent {
	private Color tint;
	private ResourceBank resources;
	
	public Planet(double x, double y, double size, Color tint) {
		super(x,y,size,size);
		this.tint = tint;
		
		resources = new ResourceBank();
	}
	
	public ResourceBank getResources() {
		return resources;
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
