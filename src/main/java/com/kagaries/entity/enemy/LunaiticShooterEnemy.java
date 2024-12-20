package com.kagaries.entity.enemy;

import com.kagaries.entity.ID;
import com.kagaries.entity.trail.Trail;
import com.kagaries.entity.bullet.LunaiticShooterBullet;
import com.kagaries.main.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class LunaiticShooterEnemy extends Enemy {

	
	Random r = new Random();

	public LunaiticShooterEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		velX = 4;
		velY = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
			int spawn = r.nextInt(8);
			if(spawn == 0) handler.addObject(new LunaiticShooterBullet((int)x+48, (int)y+48, ID.LunaiticEnemy, handler));
			
			if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
			if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
			
			handler.addObject(new Trail(x, y, ID.Trail, this.color, 38, 38, 0.1f, handler));
		}
		
		
	

	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int)x, (int)y, 25, 25);
	}

}
