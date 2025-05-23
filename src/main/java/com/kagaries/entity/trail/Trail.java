package com.kagaries.entity.trail;

import com.kagaries.entity.GameObject;
import com.kagaries.entity.ID;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {
	
	float alpha = 1;
	Handler handler;
	Color color;
	int width, height;
	float life;

	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.color = color;
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.life = life;
	}

	public void tick() {
		if(alpha > life) {
			alpha -= life - 0.000001f;
		}else handler.removeObject(this);
	}

	public void render(Graphics g) {
		if (Game.showTrail == true) {
			Graphics2D g2d = (Graphics2D) g; 
			g2d.setComposite(makeTransparent(alpha));
			g.setColor(color);
			g.fillRect((int)x, (int)y, width, height);
			g2d.setComposite(makeTransparent(1));
		}
	}
	
	AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {		
		return null;
	}

}
