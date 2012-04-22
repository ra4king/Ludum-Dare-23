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
		super(1024,768);
	}
	
	@Override
	protected void initGame() {
		try {
			getArt().add("background.jpg");
			getArt().add("robot.png");
			getArt().add("layer1.png");
			getArt().add("layer2.png");
			getArt().add("layer3.png");
			getArt().add("layer4.png");
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
