package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import com.ra4king.gameutils.Screen;
import com.ra4king.gameutils.gameworld.GameWorld;
import com.ra4king.gameutils.gui.Button;
import com.ra4king.gameutils.gui.Widget;
import com.ra4king.spacegame.resources.Resource;
import com.ra4king.spacegame.resources.ResourceBank;

public class HomePlanetGUI extends Widget {
	private Planet planet;
	private boolean expanded;
	
	private Button store, equip, upgrade;
	
	public HomePlanetGUI(Planet planet) {
		this.planet = planet;
	}
	
	@Override
	public void init(Screen screen) {
		super.init(screen);
		
		GameWorld g = (GameWorld)screen;
		setBounds(g.getWidth() - 318, g.getHeight() - 154, 258, 13);
	}
	
	@Override
	public void show() {
		super.show();
		
		Button.Action action = new Button.Action() {
			@Override
			public void doAction(Button button) {
				Player p = ((Space)getParent()).getPlayer();
				ResourceBank playerRes = p.getShip().getResources();
				ResourceBank planetRes = planet.getResources();
				
				if(button == store)
					for(Resource r : Resource.values())
						planetRes.transferFrom(playerRes, r, playerRes.getQuantity(r));
				else if(button == equip)
					for(Resource r : Resource.values())
						playerRes.transferFrom(planetRes, r, planetRes.getQuantity(r));
				else if(button == upgrade) {
					planet.getHomePlanetData().upgradePlanet();
					
					if(!planet.getHomePlanetData().canUpgrade())
						upgrade.setEnabled(false);
				}
				
				getParent().getGame().getSound().play("pickup");
			}
		};
		
		store = new Button("Store Resources",12,getParent().getWidth() - 140, getParent().getHeight() - 130,25,25,true,action) {
			public void draw(Graphics2D g) {
				g.setTransform(new AffineTransform());
				super.draw(g);
			}
		};
		equip = new Button("Equip Resources",12,getParent().getWidth() - 140, getParent().getHeight() - 90,25,25,true,action) {
			public void draw(Graphics2D g) {
				g.setTransform(new AffineTransform());
				super.draw(g);
			}
		};
		upgrade = new Button("Upgrade Planet",12,getParent().getWidth() - 140, getParent().getHeight() - 50,25,25,true,action) {
			public void draw(Graphics2D g) {
				g.setTransform(new AffineTransform());
				super.draw(g);
			}
		};
		upgrade.setEnabled(false);
		
		updateButtons();
	}
	
	@Override
	public void hide() {
		super.hide();
		
		getParent().remove(store);
		getParent().remove(equip);
		getParent().remove(upgrade);
	}
	
	private void updateButtons() {
		if(expanded) {
			getParent().add(4,store);
			getParent().add(4,equip);
			getParent().add(4,upgrade);
		}
		else {
			getParent().remove(store);
			getParent().remove(equip);
			getParent().remove(upgrade);
		}
	}
	
	@Override
	public GameWorld getParent() {
		return (GameWorld)super.getParent();
	}
	
	@Override
	public void update(long deltaTime) {
		if(!upgrade.isEnabled() && planet.getHomePlanetData().canUpgrade())
			upgrade.setEnabled(true);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setTransform(new AffineTransform());
		g.drawImage(getParent().getGame().getArt().get("HUD1"), getParent().getWidth() - 350, getParent().getHeight() - 200, null);
		
		ResourceBank r = planet.getResources();
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		FontMetrics fm = g.getFontMetrics();
		
		int left = 300, sep = 70;
		for(String s : new String[] { "Level", "Wood", "Stone", "Slaves"}) {
			g.drawString(s,getParent().getWidth() - left - fm.stringWidth(s)/2, getParent().getHeight() - 180);
			left -= sep;
		}
		
		left = 300;
		for(int i : new Integer[] { planet.getHomePlanetData().getPlanetLevel(), r.getQuantity(Resource.WOOD), r.getQuantity(Resource.STONE), r.getQuantity(Resource.SLAVES) }) {
			String s = String.valueOf(i);
			g.drawString(s,getParent().getWidth() - left - fm.stringWidth(s)/2, getParent().getHeight() - 163);
			left -= sep;
		}
		
		if(!expanded)
			g.drawImage(getParent().getGame().getArt().get("HUD2"), getParent().getWidth() - 318, getParent().getHeight() - 154, null);
		else {
			g.drawImage(getParent().getGame().getArt().get("HUD3"), getParent().getWidth() - 318, getParent().getHeight() - 154, null);
			
			g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));
			g.setColor(Color.white);
			
			int top = 133;
			sep = 25;
			for(String s : new String[] { "Wood", "Stone", "Metal", "Oil", "Slaves" }) {
				g.drawString(s,getParent().getWidth() - 300, getParent().getHeight() - top);
				top -= sep;
			}
			
			top = 133;
			for(Resource s : Resource.values()) {
				g.drawString(String.valueOf(r.getQuantity(s)), getParent().getWidth() - 230, getParent().getHeight() - top);
				top -= sep;
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent me) {
		if(contains(me.getPoint().x,me.getPoint().y)) {
			expanded = !expanded;
			setY(expanded ? getParent().getHeight() - 26 : getParent().getHeight() - 154);
			updateButtons();
		}
	}
}
