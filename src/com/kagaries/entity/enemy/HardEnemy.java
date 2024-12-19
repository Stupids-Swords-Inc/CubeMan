package com.kagaries.entity.enemy;

import com.kagaries.entity.GameObject;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.entity.Trail;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HardEnemy extends Enemy {

	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		velX = 11;
		velY = 9;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 24, 24);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, this.color, 24, 24, 0.15f, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int)x, (int)y, 24, 24);
	}

}
