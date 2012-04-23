package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import com.ra4king.gameutils.Screen;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.gameutils.gui.Widget;
import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.resources.ResourceBank;

public class GUI extends Widget {
	private Planet planet;
	private boolean expanded;
	
	public GUI(Planet planet) {
		this.planet = planet;
	}
	
	@Override
	public void init(Screen screen) {
		super.init(screen);
		
		GameWorld g = (GameWorld)screen;
		setBounds(g.getWidth() - 318, g.getHeight() - 154, 258, 13);
	}
	
	@Override
	public GameWorld getParent() {
		return (GameWorld)super.getParent();
	}
	
	@Override
	public void update(long deltaTime) {}
	
	@Override
	public void draw(Graphics2D g) {
		g.setTransform(new AffineTransform());
		g.drawImage(getParent().getGame().getArt().get("HUD1"), getParent().getWidth() - 350, getParent().getHeight() - 200, null);
		
		if(expanded)
			g.drawImage(getParent().getGame().getArt().get("HUD3"), getParent().getWidth() - 318, getParent().getHeight() - 154, null);
		else
			g.drawImage(getParent().getGame().getArt().get("HUD2"), getParent().getWidth() - 318, getParent().getHeight() - 154, null);
		
		ResourceBank r = planet.getResources();
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
		g.drawString("Wood: " + r.getQuantity(Resource.WOOD),getParent().getWidth() - 330, getParent().getHeight() - 170);
	}
	
	@Override
	public void mousePressed(MouseEvent me) {
		System.out.println("hello!");
		
		if(contains(me.getPoint().x,me.getPoint().y)) {
			expanded = !expanded;
			setY(expanded ? getParent().getHeight() - 26 : getParent().getHeight() - 154);
		}
	}
}
