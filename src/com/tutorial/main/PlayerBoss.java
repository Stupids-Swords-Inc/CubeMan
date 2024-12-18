package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PlayerBoss extends GameObject{
	
	private Handler handler;
	

	Random r = new Random();

	public PlayerBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 128, 128);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
			int spawn = r.nextInt(1);
			if(spawn == 0) handler.addObject(new EnemyBossBullet((int)x+48, (int)y+48, ID.BasicEnemy, handler));
			
			velX = Game.clamp(velX, -5, 5);
		}{
		
		if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 128) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 38, 38, 0.1f, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 128, 128);
	}

}
