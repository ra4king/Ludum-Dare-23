package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.gameutils.util.Bag;

public class Background extends GameComponent {
	private ArrayList<Layer> layers;
	private ArrayList<Star> stars;
	private Player player;
	
	public Background(Player player) {
		layers = new ArrayList<Layer>();
		stars = new ArrayList<Star>();
		this.player = player;
	}
	
	public void init(GameWorld space) {
		super.init(space);
		
		for(int a = 0; a < 4; a++)
			layers.add(new Layer("layer" + a,0,0,0.2 + a/10.0));
		
		for(int a = 0; a < 300; a++)
			stars.add(new Star(Math.random() * (space.getWidth()+200) - 100, Math.random() * (space.getHeight()+200) - 100, 1 + Math.random() * 2, 0.2 + Math.random() * 0.3));
	}
	
	@Override
	public void update(long deltaTime) {
		for(Layer l : layers)
			l.update();
		
		for(Star s : stars)
			s.update();
	}
	
	@Override
	public void draw(Graphics2D g) {
		AffineTransform old = g.getTransform();
		g.setTransform(new AffineTransform());
		
		for(Layer l : layers)
			l.draw(g);
		
		for(Star s : stars)
			s.draw(g);
		
		g.setTransform(old);
	}
	
	private class Star {
		private double x, y, speed;
		private int size;
		
		public Star(double x, double y, double size, double speed) {
			this.x = x;
			this.y = y;
			this.size = (int)Math.round(size);
			this.speed = speed;
		}
		
		public void update() {
			if(x - player.getCenterX() * speed < -100)
				x += getParent().getWidth() + 200;
			if(x - player.getCenterX() * speed > getParent().getWidth() + 100)
				x -= getParent().getWidth() + 200;
			
			if(y - player.getCenterY() * speed < -100)
				y += getParent().getHeight() + 200;
			if(y - player.getCenterY() * speed > getParent().getHeight() + 100)
				y -= getParent().getHeight() + 200;
		}
		
		public void draw(Graphics2D g) {
			g.setColor(Color.white);
			g.fillOval((int)Math.round(x - player.getCenterX() * speed), (int)Math.round(y - player.getCenterY() * speed), size, size);
		}
	}
	
	private class Layer {
		private String name;
		private double x, y, speed;
		
		public Layer(String name, double x, double y, double speed) {
			this.name = name;
			this.x = x;
			this.y = y;
			this.speed = speed;
		}
		
		public void update() {
			if(x + 2400 - player.getCenterX() * speed < 0)
				x += 2400;
			if(x - player.getCenterX() * speed >= 0)
				x -= 2400;
			
			if(y + 1500 - player.getCenterY() * speed < 0)
				y += 1500;
			if(y - player.getCenterY() * speed >= 0)//getParent().getHeight())
				y -= 1500;
		}
		
		public void draw(Graphics2D g) {
			g.drawImage(getParent().getGame().getArt().get(name),(int)Math.round(x - player.getCenterX() * speed), (int)Math.round(y - player.getCenterY() * speed),null);
			g.drawImage(getParent().getGame().getArt().get(name),(int)Math.round(x + 2400 - player.getCenterX() * speed), (int)Math.round(y - player.getCenterY() * speed),null);
			g.drawImage(getParent().getGame().getArt().get(name),(int)Math.round(x - player.getCenterX() * speed), (int)Math.round(y + 1500 - player.getCenterY() * speed),null);
			g.drawImage(getParent().getGame().getArt().get(name),(int)Math.round(x + 2400 - player.getCenterX() * speed), (int)Math.round(y + 1500 - player.getCenterY() * speed),null);
		}
	}
}
