package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{
	
	Handler handler;
	
	static int B1 = 1000;
	static int B2 = 1500;
	static int B3 = 2000;
	HUD hud;
	HUD2 hud2;

	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-100, 50);
		
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost: " + B1, 110, 140);
		g.drawRect(100, 100, 100, 80);
		
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Cost: " + B2, 260, 140);
		g.drawRect(250, 100, 100, 80);
		
		g.drawString("Refill Health", 410, 120);
		g.drawString("Cost: " + B3, 410, 140);
		g.drawRect(400, 100, 100, 80);
		
		g.drawString("SCORE:" + hud.getScore(), Game.WIDTH/2-50, 300);
		g.drawString("Press Space to go back", Game.WIDTH/2-50, 330);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//box 1
		if(mx >= 100 && mx <= 200) {
			if(my >= 100 && my <= 180) {
				if(hud.getScore() >= B1) {
					HUD.setScore(hud.getScore() - B1);
					
					B1 += 1000;
					HUD.bounds += 20;
					HUD.HEALTH = (100 + (HUD.bounds/2));
					HUD2.bounds2 += 20;
					HUD2.HEALTH = (100 + (HUD2.bounds2/2));
					HUD3.bounds2 += 20;
					HUD3.HEALTH = (100 + (HUD3.bounds2/2));
					HUD4.bounds2 += 20;
					HUD4.HEALTH = (100 + (HUD4.bounds2/2));
				}
				
				
			}
		}
		//box 1
		if(mx >= 250 && mx <= 350) {
			if(my >= 100 && my <= 180) {
				if(hud.getScore() >= B2) {
					HUD.setScore(hud.getScore() - B2);
					B2 += 1500;	
					Handler.spd++;
				}
						
			}
		}
		//box 1
		if(mx >= 400 && mx <= 500) {
			if(my >= 100 && my <= 180) {
				if(hud.getScore() >= B3) {
					HUD.setScore(hud.getScore() - B3);
					B3 += 2000;	
					HUD.HEALTH += 1000;
					HUD2.HEALTH += 1000;
				}	
						
			}
		}
		
	}

	
	
}
