package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class RandomEnemy extends GameObject{
	
	private Handler handler;
	
	Random r = new Random();

	public RandomEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 8;
		velY = 6;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		int randomY1 = -6 + r.nextInt(6) / -1;
		int randomY2 = 6 + r.nextInt(6) / 1;
		int randomX1 = -10 + r.nextInt(8) / -1;
		int randomX2 = 10 + r.nextInt(8) / 1;
		
		int randomBounce = r.nextInt(10);
		
		if(randomBounce == 5 || randomBounce == 6) velY = randomY1;
		if(randomBounce == 3 || randomBounce == 4) velY = randomY2;
		if(randomBounce == 7 || randomBounce == 8) velX = randomX1;
		if(randomBounce == 9 || randomBounce == 10) velX = randomX2;
		
		x = Game.clamp(x, -1, Game.WIDTH - 48);
		y = Game.clamp(y, -1, Game.HEIGHT - 71);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.15f, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
