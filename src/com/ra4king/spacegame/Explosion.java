package com.ra4king.spacegame;

import java.awt.Graphics2D;

import com.ra4king.gameutils.gameworld.GameComponent;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.gameutils.util.Animation;

public class Explosion extends GameComponent {
	private Animation explosion;
	
	public Explosion(double x, double y, double size) {
		super(x,y,size,size);
		
		explosion = new Animation(false);
	}
	
	@Override
	public void init(GameWorld world) {
		super.init(world);
		
		explosion.addFrames(world.getGame().getArt().get("explosion"), 64, 64, (long)40e6);
	}
	
	@Override
	public void update(long deltaTime) {
		explosion.update(deltaTime);
		
		if(explosion.isDone())
			getParent().remove(this);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(explosion.getFrame(), getIntX(), getIntY(), getIntWidth(), getIntHeight(), null);
	}
}
