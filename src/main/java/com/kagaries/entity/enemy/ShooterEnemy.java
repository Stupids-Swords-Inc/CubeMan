package com.kagaries.entity.enemy;

import com.kagaries.entity.ID;
import com.kagaries.entity.trail.Trail;
import com.kagaries.entity.bullet.ShooterEnemyBullet;
import com.kagaries.main.*;

import java.awt.*;
import java.util.Random;

public class ShooterEnemy extends Enemy {
	
	Random r = new Random();

	public ShooterEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		velX = 1;
		velY = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
			int spawn = r.nextInt(30);
			if(spawn == 0) handler.addObject(new ShooterEnemyBullet((int)x+48, (int)y+48, ID.BasicEnemy, handler));
			
			if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
			if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
			
			handler.addObject(new Trail(x, y, ID.Trail, this.color, 38, 38, 0.1f, handler));
		}
		
		
	

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (!enabled) {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}

		g2d.setColor(this.color);
		g2d.fillRect((int)x, (int)y, 25, 25);
	}

	@Override
	public void enableTick() {
		if (timeToSpawn <= 0 && !this.enabled) {
			this.enabled = true;
		} else {
			--timeToSpawn;
		}
	}
}
