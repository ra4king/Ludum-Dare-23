package com.ra4king.spacegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import com.ra4king.gameutils.Game;
import com.ra4king.spacegame.screens.IntroScreen;

public class SpaceGame extends Game {
	private static final long serialVersionUID = 5597406795403538868L;
	
	public static void main(String[] args) {
		SpaceGame game = new SpaceGame();
		game.setupFrame("Space Game", false);
		game.start();
	}
	
	public SpaceGame() {
		super(800,600,MAX_FPS);
	}
	
	@Override
	protected void initGame() {
		try {
			getArt().splitAndAdd("spaceships.png", 90, 50);
			getArt().add("HUD1.png");
			getArt().add("HUD2.png");
			getArt().add("HUD3.png");
			getArt().add("layer1.png");
			getArt().add("layer4.png");
			getArt().add("explosion.png");
			for(int a = 1; a < 7; a++)
				getArt().add("planet".concat(String.valueOf(a)).concat(".png"));
			
			getSound().add("blast.wav");
			getSound().add("pickup.wav");
			getSound().add("select.wav");
			getSound().add("ClairDeLune.ogg");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		getSound().loop("ClairDeLune");
		
		setScreen("Intro", new IntroScreen());
	}
	
	private boolean checkResize;
	
	public void update(long deltaTime) {
		super.update(deltaTime);
		
		if(!checkResize) {
			if(Toolkit.getDefaultToolkit().getScreenSize().height >= 800)
				setSize(1024,768);
			
			checkResize = true;
			
			setFullScreen(true);
		}
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		g.setColor(Color.red);
	}
}
