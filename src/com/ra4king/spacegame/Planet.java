package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.ra4king.gameutils.Art;
import com.ra4king.gameutils.Element;
import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.gameutils.gui.Widget;
import com.ra4king.gameutils.util.FastMath;
import com.ra4king.spacegame.resources.ResourceBank;

public class Planet extends GameComponent {
	private Color tint;
	private ResourceBank resources;
	
	private int strength, population;
	
	private Widget gui;
	private boolean isGuiShown, isHome;
	
	private String image, original;
	
	public Planet(double x, double y, double size, Color tint, boolean isHome) {
		super(x,y,size,size);
		this.tint = tint;
		
		resources = new ResourceBank();
		
		this.isHome = isHome;
		
		gui = isHome ? new HomePlanetGUI(this) : new PlanetGUI(this);
	}
	
	@Override
	public void init(GameWorld world) {
		super.init(world);
		
		if(isHome)
			world.add(2,new Arrow());
		
		Art art = world.getGame().getArt();
		
		original = "planet" + (int)(6 * Math.random() + 1);
		
		Image i = art.get(original);
		
		BufferedImage newImage = new BufferedImage(getIntWidth(),getIntHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(i,0,0,getIntWidth(),getIntHeight(),null);
		g.setColor(tint);
		g.fillOval(0, 0, getIntWidth(), getIntHeight());
		g.dispose();
		
		image = "planet" + art.size();
		
		art.add(image, newImage);
	}
	
	@Override
	public void setSize(double width, double height) {
		super.setSize(width, height);
		
		Art art = getParent().getGame().getArt();
		
		Image i = art.get(original);
		
		BufferedImage newImage = new BufferedImage(getIntWidth(),getIntHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(i,0,0,getIntWidth(),getIntHeight(),null);
		g.setColor(tint);
		g.fillOval(0, 0, getIntWidth(), getIntHeight());
		g.dispose();
		
		getParent().getGame().getArt().replace(image, newImage);
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
		if(isHome) {
			g.setColor(Color.red);
			
			FontMetrics fm = g.getFontMetrics();
			String s = "HOME";
			g.drawString(s, getIntCenterX() - fm.stringWidth(s)/2, getIntY() - 5);
		}
		
		g.drawImage(getParent().getGame().getArt().get(image),getIntX(),getIntY(),null);
	}
	
	private class Arrow extends GameComponent {
		@Override
		public void update(long deltaTime) {}
		
		@Override
		public void draw(Graphics2D g) {
			if(Planet.this.getScreenX() < 0 || Planet.this.getScreenY() < 0 ||
			   Planet.this.getScreenX() + Planet.this.getWidth() >= Planet.this.getParent().getWidth() ||
			   Planet.this.getScreenY() + getHeight() >= Planet.this.getParent().getHeight()) {
				
				g.setTransform(new AffineTransform());
				g.setColor(Color.red);
				
				Player p = ((Space)getParent()).getPlayer();
				
				double angle = FastMath.atan2(Planet.this.getY() - p.getY(), Planet.this.getX() - p.getX());
				
				double x = (Planet.this.getParent().getWidth()/2 - 2) * FastMath.cos(angle) + getParent().getWidth()/2;
				double y = (Planet.this.getParent().getHeight()/2 - 2) * FastMath.sin(angle) + getParent().getHeight()/2;
				
				int[] xs = { (int)Math.round(x), (int)Math.round(x-10), (int)Math.round(x+10) };
				int[] ys = { (int)Math.round(y), (int)Math.round(y+20), (int)Math.round(y+20) };
				Polygon polygon = new Polygon(xs,ys,3);
				
				g.rotate(angle + Math.PI/2,x,y);
				g.fill(polygon);
			}
		}
	}
}
