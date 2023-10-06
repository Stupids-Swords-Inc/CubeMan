package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.main.Game.STATE;

public class HUD3 {
	
	
	public static float HEALTH = 100;
	public static int bounds2 = 0;
	
	private static float greenValue = 255f;
	private int score2 = 0;
	
	public static float reviveTimer = 0;
	public static boolean canRevive = false;
	
	public static void tick() {
		
		
		HEALTH = (int) Game.clamp(HEALTH, 0, 100+(bounds2/2));
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH*2; 
		
		if (HUD3.HEALTH <= 0 &!(Game.gameState == STATE.PvPP4)) {
			if (reviveTimer >= 500) {
				canRevive = true;
			} else if (reviveTimer < 500) {
				reviveTimer++;
			} else if(HUD3.HEALTH > 0) {
				reviveTimer = 0;
				canRevive = false;
			}
		}
		
		if(HUD.graze >= 100) {
			bounds2 += 10;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(715, 615, 200 + bounds2, 32);
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(715, 615, (int)HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(715, 615, 200 + bounds2, 32);
		
		g.drawString("Player3", 870, 613);
		
		if(HEALTH <= 0 && Game.showExtraStats == false) {
			g.setColor(Color.WHITE);
			g.drawString("Player3 Died.", 745, 575);
		}
		
		if (Game.showExtraStats == true &!(Game.gameState == STATE.PvPP4)) {
			g.drawString("Health: " + (int) HEALTH, 775, 613);
			g.drawString("SPD: " + Handler.spd, 715, 594);
			g.drawString("reviveTimer: " + reviveTimer, 715, 559);
			g.drawString("canRevive: " + canRevive, 715, 574);
		}
	}
	
	public void setScore(int score2) {
		this.score2 = score2;
	}
	
	public int getScore() {
		return score2;
	}
	

	
}
