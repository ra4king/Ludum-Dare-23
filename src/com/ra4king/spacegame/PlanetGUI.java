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
import com.ra4king.spacegame.screens.GameOverScreen;
import com.ra4king.spacegame.screens.WinScreen;

public class PlanetGUI extends Widget {
	private Planet planet;
	private boolean expanded = true;
	
	private Button steal, attack;
	
	private int stealCount = 0;
	
	public PlanetGUI(Planet planet) {
		this.planet = planet;
	}
	
	@Override
	public void init(Screen screen) {
		super.init(screen);
		
		GameWorld g = (GameWorld)screen;
		setBounds(g.getWidth() - 318, g.getHeight() - 26, 258, 13);
	}
	
	@Override
	public void show() {
		super.show();
		
		Button.Action action = new Button.Action() {
			@Override
			public void doAction(Button button) {
				if(button == steal) {
					getParent().getGame().getSound().play("pickup");
					
					ResourceBank playerRes = ((Space)getParent()).getPlayer().getShip().getResources();
					ResourceBank planetRes = planet.getResources();
					
					boolean stoleAnything = false;
					
					for(Resource r : Resource.values()) {
						int toTransfer = Math.max(1,(int)(planetRes.getQuantity(r) * Math.random() * 0.3));
						
						if(toTransfer > 0) {
							playerRes.transferFrom(planetRes, r, toTransfer);
							stoleAnything = true;
						}
					}
					
					if(stoleAnything) {
						if(stealCount > 0)
							((Space)getParent()).getPlayer().getShip().applyDamage(10);
						
						stealCount++;
					}
					
					if(((Space)getParent()).getPlayer().getShip().getHealth() == 0)
						getParent().getGame().setScreen("Game Over", new GameOverScreen());
				}
				else if(button == attack) {
					Player p = ((Space)getParent()).getPlayer();
					
					p.getShip().applyDamage(planet.getStrength());
					
					if(p.getShip().getHealth() > 0) {
						ResourceBank playerRes = p.getShip().getResources();
						ResourceBank planetRes = planet.getResources();
						
						for(Resource r : Resource.values())
							playerRes.addQuantity(r,planetRes.getQuantity(r));
						
						playerRes.addQuantity(Resource.SLAVES, planet.getPopulation());
						
						getParent().remove(planet);
						getParent().add(1,new Explosion(planet.getX(),planet.getY(),planet.getWidth()));
						
						getParent().remove(PlanetGUI.this);
						
						p.getShip().destroyPlanet();
						
						getParent().getGame().getSound().play("blast");
						
						if(p.getShip().getPlanetsDestroyedNum() == 50)
							getParent().getGame().setScreen("Win Screen", new WinScreen());
					}
					else
						getParent().getGame().setScreen("Game Over", new GameOverScreen());
				}
			}
		};
		
		steal = new Button("Steal Resources",12,getParent().getWidth() - 140, getParent().getHeight() - 100,25,25,true,action) {
			public void draw(Graphics2D g) {
				g.setTransform(new AffineTransform());
				super.draw(g);
			}
		};
		attack = new Button("Attack",12,getParent().getWidth() - 140, getParent().getHeight() - 60,25,25,true,action) {
			public void draw(Graphics2D g) {
				g.setTransform(new AffineTransform());
				super.draw(g);
			}
		};
		
		updateButtons();
	}
	
	@Override
	public void hide() {
		super.hide();
		
		getParent().remove(steal);
		getParent().remove(attack);
	}
	
	private void updateButtons() {
		if(expanded) {
			getParent().add(4,steal);
			getParent().add(4,attack);
		}
		else {
			getParent().remove(steal);
			getParent().remove(attack);
		}
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
		
		ResourceBank r = planet.getResources();
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		FontMetrics fm = g.getFontMetrics();
		
		int left = 300, sep = 90;
		for(String s : new String[] { "Defense", "Population", "Wood", "Oil"}) {
			g.drawString(s,getParent().getWidth() - left - fm.stringWidth(s)/2, getParent().getHeight() - 180);
			left -= sep;
			sep -= 10;
		}
		
		left = 300;
		sep = 90;
		for(int i : new Integer[] { planet.getStrength(), planet.getPopulation(), r.getQuantity(Resource.WOOD), r.getQuantity(Resource.OIL) }) {
			String s = String.valueOf(i);
			g.drawString(s,getParent().getWidth() - left - fm.stringWidth(s)/2, getParent().getHeight() - 163);
			left -= sep;
			sep -= 10;
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
