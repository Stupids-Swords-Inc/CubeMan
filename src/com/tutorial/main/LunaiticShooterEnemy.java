package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class LunaiticShooterEnemy extends GameObject{
	
	private Handler handler;
	
	Random r = new Random();

	public LunaiticShooterEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
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
			
			handler.addObject(new Trail(x, y, ID.Trail, Color.lightGray, 38, 38, 0.1f, handler));
		}
		
		
	

	
	public void render(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect((int)x, (int)y, 25, 25);
	}

}
