package com.kagaries.entity.enemy;

import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.entity.trail.Trail;

import java.awt.*;

public class SlowEnemy extends Enemy {

	public SlowEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler, 150f);
		
		velX = 4;
		velY = 4;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, this.color, 32, 32, 0.15f, handler));
	}

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (!enabled) {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}

		g2d.setColor(this.color);
		g2d.fillRect((int)x, (int)y, 32, 32);
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
