package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class LunaiticShooterBullet extends GameObject{
	
	private Handler handler;
	Random r = new Random();

	public LunaiticShooterBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(7 - -7) + -7);
		velY = 7;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 16, 16, 0.1f, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
