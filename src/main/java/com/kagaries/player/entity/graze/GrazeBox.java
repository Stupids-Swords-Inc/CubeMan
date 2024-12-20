package com.kagaries.player.entity.graze;

import com.kagaries.entity.GameObject;
import com.kagaries.ui.hud.HUD;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.player.entity.Player;
import com.kagaries.ui.hud.HudInterface;

import java.awt.Graphics;
import java.awt.Rectangle;


public class GrazeBox extends GameObject {
	
	Handler handler;
	private long lastCollisionTime = 0;

    private final ID playerID;
	private final HudInterface hud;

	public GrazeBox(int x, int y, ID id, ID playerID, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.playerID = playerID;
		this.hud = playerID.getHud();
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 54, 54);
	}

	
	public void tick() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == this.playerID) {
				x = tempObject.getX();
				y = tempObject.getY(); 
			}
			
			
		}

		
		collision();
		
		if(HUD.HEALTH <= 0.1) {
			handler.removeObject(this);
		}
	}
	
	

	public void render(Graphics g) {}

	
	private void collision() {
		long currentTime = System.currentTimeMillis();
		
		
	    
	    for(int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

            // Cooldown in milliseconds (1 second in this example)
            long collisionCooldown = 50;
            if(tempObject.getId().getGraze() != 0) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hud.setHealth(hud.getHealth() + tempObject.getId().getGraze());
					hud.setGraze(hud.getGraze() + tempObject.getId().getGraze());
					lastCollisionTime = currentTime;
				}
			} else if (currentTime - lastCollisionTime < collisionCooldown || Player.isBlinking) {
		        return; 
		    }
	    }
	    
	}

}

