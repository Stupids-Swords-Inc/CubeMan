package com.kagaries.entity.enemy;

import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.entity.Trail;

public class BasicEnemy extends Enemy {

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id, handler);
		
		velX = 8;
		velY = 6;
	}

    public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, this.color, 16, 16, 0.15f, handler));
	}
}
