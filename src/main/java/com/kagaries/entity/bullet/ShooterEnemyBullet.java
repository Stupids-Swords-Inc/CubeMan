package com.kagaries.entity.bullet;

import com.kagaries.entity.GameObject;
import com.kagaries.entity.enemy.Enemy;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.entity.trail.Trail;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class ShooterEnemyBullet extends Enemy {

	Random r = new Random();

	public ShooterEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		if(y >= Game.HEIGHT) this.handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 8, 8, 0.25f, handler));
	}

	@Override
	public void enableTick() {
		this.enabled = true;
	}


	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 8, 8);
	}

}
