package com.kagaries.entity.enemy;

import com.kagaries.entity.GameObject;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;

import java.awt.*;

public abstract class Enemy extends GameObject {

	protected Handler handler;
	final Color color;
	float timeToSpawn;

	public boolean enabled;

	public Enemy(int x, int y, ID id, Handler handler, float timeToSpawn) {
		super(x, y, id);

		this.handler = handler;
		this.color = id.getColor();
		this.timeToSpawn = timeToSpawn;
		this.enabled = false;
	}

	public Enemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
		this.color = id.getColor();
		this.timeToSpawn = 50f;
		this.enabled = false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	public void tick() {}

	public abstract void enableTick();

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (!enabled) {
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		}

		g2d.setColor(this.color);
		g2d.fillRect((int)x, (int)y, 16, 16);
	}

}
