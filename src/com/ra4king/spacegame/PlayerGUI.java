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

public class PlayerGUI extends Widget {
	private Player player;
	private boolean expanded;
	
	private Button upgrade;
	
	public PlayerGUI(Player player) {
		this.player = player;
	}
	
	//HUD1 = 321 x 46
	//HUD2 = 258 x 13 
	//HUD3 = 258 x 141
	
	@Override
	public void init(Screen screen) {
		super.init(screen);
		
		setBounds(51, 66, 258, 13);
	}
	
	@Override
	public void show() {
		super.show();
		
		Button.Action action = new Button.Action() {
			@Override
			public void doAction(Button button) {
				
			}
		};
		
		upgrade = new Button("Upgrade Ship",12,235,120,25,25,true,action) {
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
		
		getParent().remove(upgrade);
	}
	
	private void updateButtons() {
		if(expanded) {
			getParent().add(4,upgrade);
		}
		else {
			getParent().remove(upgrade);
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
		g.drawImage(getParent().getGame().getArt().get("HUD1"), 20, 20, null);
		
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		FontMetrics fm = g.getFontMetrics();
		
		int left = 55, sep = 70;
		for(String s : new String[] { "Health", "Ship Level", "Metal", "Oil", "Slaves" }) {
			g.drawString(s,left - fm.stringWidth(s)/2, 40);
			left += sep;
			sep -= 5;
		}
		
		left = 55;
		sep = 70;
		for(int i : new Integer[] { player.getShip().getHealth(), player.getShip().getShipLevel(), player.getShip().getResources().getQuantity(Resource.METAL), player.getShip().getResources().getQuantity(Resource.OIL), player.getShip().getResources().getQuantity(Resource.SLAVES) }) {
			String s = String.valueOf(i);
			g.drawString(s,left - fm.stringWidth(s)/2, 57);
			left += sep;
			sep -= 5;
		}
		
		if(!expanded)
			g.drawImage(getParent().getGame().getArt().get("HUD2"), 51, 66, null);
		else {
			g.drawImage(getParent().getGame().getArt().get("HUD3"), 51, 66, null);
			
			g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));
			g.setColor(Color.white);
			
			int top = 85;
			sep = 25;
			for(String s : new String[] { "Wood", "Stone", "Metal", "Oil", "Slaves" }) {
				g.drawString(s,60,top);
				top += sep;
			}
			
			top = 85;
			for(Resource s : Resource.values()) {
				g.drawString(String.valueOf(player.getShip().getResources().getQuantity(s)).concat(" / ").concat(String.valueOf(player.getShip().getMaximumValues().getQuantity(s))), 120, top);
				top += sep;
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent me) {
		if(contains(me.getPoint().x,me.getPoint().y)) {
			expanded = !expanded;
			setY(expanded ? 194 : 66);
			updateButtons();
		}
	}
}
