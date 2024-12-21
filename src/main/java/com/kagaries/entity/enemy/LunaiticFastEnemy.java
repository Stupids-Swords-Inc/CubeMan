
package com.kagaries.entity.enemy;

import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.entity.trail.Trail;

import java.awt.*;
import java.util.Random;

public class LunaiticFastEnemy extends Enemy {
	
	Random r = new Random();

	public LunaiticFastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);

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
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 25, 25, 0.1f, handler));
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
