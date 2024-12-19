package com.kagaries.entity.enemy;

import com.kagaries.entity.GameObject;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy extends GameObject {

	Handler handler;
	final Color color;

	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		this.color = id.getColor();
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	
	public void tick() {}

	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
