package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class LunaiticBoss2 extends GameObject{
	
	private Handler handler;
	
	private int timer = 30;
	private int timer2 = 20;
	private int timer3 = 10;
	Random r = new Random();

	public LunaiticBoss2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 1;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 128, 128);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) {
			if(velX == 0) velX = 4;
			timer3--;
		}
		if(timer3 <= 0) {
			if(velX > 0)
			velX += 0.005f;
			else if(velX < 0)
			velX -= 0.005;
			int spawn = r.nextInt(4);
			if(spawn == 0) handler.addObject(new LunatitcBossBullet((int)x+48, (int)y+48, ID.LunaiticEnemy, handler));
			
			velX = Game.clamp(velX, -10, 10);
		}
		
		if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 128) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 38, 38, 0.1f, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 128, 128);
	}

}
