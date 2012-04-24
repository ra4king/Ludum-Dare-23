package com.ra4king.spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.ra4king.gameutils.BasicScreen;

public class WinScreen extends BasicScreen {
	@Override
	public void update(long deltaTime) {
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100));
		g.setColor(Color.red);
		g.drawString("YOU WIN!", getWidth()/2-210, getHeight()/2);
		
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
		g.drawString("You conquered the Universe!", getWidth()/2 - 250, getHeight()/2 + 50);
		
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		g.drawString("But....you are all alone....you and your tiny world......", getWidth()/2 - 350, getHeight()/2 + 100);
	}
}
