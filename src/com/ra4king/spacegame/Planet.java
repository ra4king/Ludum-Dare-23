package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.spacegame.resources.ResourceBank;

public class Planet extends GameComponent {
	private Color tint;
	private ResourceBank resources;
	
	private int strength, population;
	
	private GUI gui;
	private boolean isGuiShown;
	
	public Planet(double x, double y, double size, Color tint) {
		super(x,y,size,size);
		this.tint = tint;
		
		resources = new ResourceBank();
		
		gui = new GUI(this);
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public ResourceBank getResources() {
		return resources;
	}
	
	@Override
	public void update(long deltaTime) {
		Player p = ((Space)getParent()).getPlayer();
		if(contains(p.getCenterX(), p.getCenterY())) {
			if(!isGuiShown) {
				getParent().add(3,gui);
				isGuiShown = true;
			}
		}
		else {
			getParent().remove(gui);
			isGuiShown = false;
		}
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(tint);
		g.fillOval(getIntX(), getIntY(), getIntWidth(), getIntHeight());
	}
}
