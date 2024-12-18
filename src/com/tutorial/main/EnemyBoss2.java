package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss2 extends GameObject{
	
	private Handler handler;
	
	private int timer = 80;
	private int timer2 = 50;
	private int timer3 = 100;
	private Color col;
	Random r = new Random();

	public EnemyBoss2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 1;
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 155, 155);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) {
			if(velX == 0) velX = 3;
			timer3--;
		}
		if(timer3 <= 0) {
			if(velX > 0)
			velX += 0.005f;
			else if(velX < 0)
			velX -= 0.005;
			int spawn = r.nextInt(6);
			if(spawn == 0) handler.addObject(new EnemyBoss2Bullet((int)x+48, (int)y+48, ID.FastEnemy, handler));
			
			velX = Game.clamp(velX, -7, 7);
		}
		
		//if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 155) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.yellow, 38, 38, 0.1f, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 155, 155);
	}

}
