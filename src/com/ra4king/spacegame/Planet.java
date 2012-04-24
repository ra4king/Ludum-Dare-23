package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.ra4king.gameutils.Art;
import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.spacegame.resources.ResourceBank;

public class Planet extends GameComponent {
	private Color tint;
	private ResourceBank resources;
	
	private int strength, population;
	
	private PlanetGUI gui;
	private boolean isGuiShown;
	
	private String image;
	
	public Planet(double x, double y, double size, Color tint) {
		super(x,y,size,size);
		this.tint = tint;
		
		resources = new ResourceBank();
		
		gui = new PlanetGUI(this);
	}
	
	@Override
	public void init(GameWorld world) {
		super.init(world);
		
		Art art = world.getGame().getArt();
		
		String name = "planet" + (int)(6 * Math.random() + 1);
		
		Image i = art.get(name);
		
		BufferedImage newImage = new BufferedImage(getIntWidth(),getIntHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(i,0,0,getIntWidth(),getIntHeight(),null);
		g.setColor(tint);
		g.fillOval(0, 0, getIntWidth(), getIntHeight());
		g.dispose();
		
		image = "planet" + art.size();
		
		art.add(image, newImage);
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
//		g.setColor(tint);
//		g.fillOval(getIntX(), getIntY(), getIntWidth(), getIntHeight());
		
		g.drawImage(getParent().getGame().getArt().get(image),getIntX(),getIntY(),null);
	}
}
