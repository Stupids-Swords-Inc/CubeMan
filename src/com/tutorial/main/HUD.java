package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.main.Game.STATE;

public class HUD {
	
	public static int bounds = 0;
	public static float HEALTH = 100;
	
	private static float greenValue = 255f;
	static int score = 0;
	private static float level = 0;
	
	static int graze = 0;

	static int speed = 6;
	
	public static float reviveTimer = 0;
	public static boolean canRevive = false;
	
	public static void tick() {
		
		
		HEALTH = Game.clamp(HEALTH, 0, 100+(bounds/2));
		graze = (int) Game.clamp(graze, 0, 100);
		HUD.greenValue = Game.clamp(HUD.greenValue, 0, 255);
		
		HUD.greenValue = HEALTH*2;
		score++;
		
		if (HUD.HEALTH <= 0 &!(Game.gameState == STATE.PvPP2) &!(Game.gameState == STATE.PvPP4)) {
			if (reviveTimer >= 500) {
				canRevive = true;
			} else if (reviveTimer < 500) {
				reviveTimer++;
			} else if(HUD.HEALTH > 0) {
				reviveTimer = 0;
				canRevive = false;
				bounds = 0;
				speed = 6;
			}
		}
		
		if(graze >= 100) {
			bounds += 10;
			speed += 1;
			graze = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(15, 15, (int)HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		g.drawString("Player1", 15, 13);
		g.drawString("GRAZE: " + (int) graze, 155, 13);
		
		if(HEALTH <= 0 && Game.showExtraStats == false) {
			g.setColor(Color.WHITE);
			g.drawString("Player1 Died.", 15, 100);
		}
		
		if (Game.showExtraStats == true &!(Game.gameState == STATE.PvPP2) &!(Game.gameState == STATE.PvPP4)) {
			g.drawString("Health: " + (int) HEALTH, 75, 13);
			g.drawString("SPD: " + speed, 15, 94);
			g.drawString("reviveTimer: " + reviveTimer, 15, 109);
			g.drawString("canRevive: " + canRevive, 15, 124);
		}
	}
	
	public static void setScore(int score) {
		HUD.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public float getLevel() {
		return level;
	}
	
	public static void setLevel(float level) {
		HUD.level = level;
	}
	
}
