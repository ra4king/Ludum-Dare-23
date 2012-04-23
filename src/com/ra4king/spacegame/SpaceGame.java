package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ra4king.gameutils.Game;

public class SpaceGame extends Game {
	private static final long serialVersionUID = 5597406795403538868L;
	
	public static void main(String[] args) {
		SpaceGame game = new SpaceGame();
		game.setupFrame("Space Game", false);
		game.start();
	}
	
	public SpaceGame() {
		super(1024,768,MAX_FPS);
	}
	
	@Override
	protected void initGame() {
		try {
			getArt().splitAndAdd("spaceships.png", 90, 50);
			getArt().add("HUD1.png");
			getArt().add("HUD2.png");
			getArt().add("HUD3.png");
			getArt().add("robot.png");
			getArt().add("layer1.png");
			//getArt().add("layer2.png");
			//getArt().add("layer3.png");
			getArt().add("layer4.png");
			getArt().add("explosion.png");
			for(int a = 1; a < 7; a++)
				getArt().add("planet".concat(String.valueOf(a)).concat(".png"));
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		setScreen("Space",new Space());
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		g.setColor(Color.red);
	}
}
