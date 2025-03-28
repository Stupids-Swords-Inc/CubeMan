package com.kagaries.entity.bullet;

import com.kagaries.entity.GameObject;
import com.kagaries.entity.enemy.Enemy;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class LunatitcBossBullet extends Enemy {

	private Handler handler;
	Random r = new Random();

	public LunatitcBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		this.handler = handler;
		
		velX = (r.nextInt(9 - -9) + -9);
		velY = 9;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 25, 25, 0.1f, handler));
	}

	@Override
	public void enableTick() {
		this.enabled = true;
	}


	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 32, 32);
	}

}
