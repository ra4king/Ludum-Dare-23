package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.gameutils.util.Bag;

public class Background extends GameComponent {
	private Bag<Star> stars;
	private Player player;
	
	public Background(Player player) {
		stars = new Bag<Star>();
		this.player = player;
	}
	
	public void init(GameWorld space) {
		super.init(space);
		
		for(int a = 0; a < 300; a++)
			stars.add(new Star(Math.random() * (space.getWidth()+200) - 100, Math.random() * (space.getHeight()+200) - 100, 1 + Math.random() * 2, 0.2 + Math.random() * 0.3));
	}
	
	@Override
	public void update(long deltaTime) {
		for(Star s : stars)
			s.update();
	}
	
	@Override
	public void draw(Graphics2D g) {
		AffineTransform old = g.getTransform();
		g.setTransform(new AffineTransform());
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
}
