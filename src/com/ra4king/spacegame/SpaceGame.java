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
		setScreen("Space",new Space());
	}
	
	public void paint(Graphics2D g) {
		super.paint(g);
		g.setColor(Color.red);
	}
}
