
package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class LunaiticFastEnemy extends GameObject{
	
	private Handler handler;
	
	Random r = new Random();

	public LunaiticFastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 15;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 25, 25);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		int randomY1 = -10 + r.nextInt(20) / -1;
		int randomY2 = 10 + r.nextInt(20) / 1;
		int randomX1 = r.nextInt(5) / -1;
		int randomX2 = r.nextInt(5) / 1;
		
		if(y >= Game.HEIGHT - 71) velY = randomY1;
		if(y <= 0) velY = randomY2;
		if(x >= Game.WIDTH - 48) velX = randomX1;
		if(x <= 0) velX = randomX2;
		
		x = Game.clamp(x, -1, Game.WIDTH - 48);
		y = Game.clamp(y, -1, Game.HEIGHT - 71);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 25, 25, 0.1f, handler));
	}

	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 25, 25);
	}

}
