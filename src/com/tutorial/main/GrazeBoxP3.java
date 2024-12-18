package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;


public class GrazeBoxP3 extends GameObject{
	
	Handler handler;
	private long lastCollisionTime = 0;
	private long collisionCooldown = 50; // Cooldown in milliseconds (1 second in this example)


	public GrazeBoxP3(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 54, 54);
	}

	
	public void tick() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player3) {
				x = tempObject.getX();
				y = tempObject.getY(); 
			}
			
			
		}

		
		collision();
		
		if(HUD3.HEALTH <= 0.1) {
			handler.removeObject(this);
		}
	}
	
	

	public void render(Graphics g) {
		return;
	}

	
	private void collision() {
		long currentTime = System.currentTimeMillis();
		
		
	    
	    for(int i = 0; i < handler.object.size(); i++) {
	
	
			
			GameObject tempObject = handler.object.get(i);
		    
		    
			
			if(tempObject.getId() == ID.BasicEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD3.HEALTH += 1;
					HUD3.graze += 1;
					lastCollisionTime = currentTime;
				}
			}
			
			else if(tempObject.getId() == ID.SlowEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD3.HEALTH += 2;
					HUD3.graze += 1;
					lastCollisionTime = currentTime;
			}	
		}
				
			else if(tempObject.getId() == ID.Friend) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD3.HEALTH += 2;
						HUD3.graze += 1;
						lastCollisionTime = currentTime;
				}	
			}
			else if(tempObject.getId() == ID.FastEnemy) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD3.HEALTH += 1;
						HUD3.graze += 1;
						lastCollisionTime = currentTime;
				} 
			}
			else if(tempObject.getId() == ID.SmartEnemy) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD3.HEALTH += 1;
						HUD3.graze += 1;
						lastCollisionTime = currentTime;
				}
			}
				
			else if(tempObject.getId() == ID.EnemyBoss) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD3.HEALTH += 2;
						HUD3.graze += 1;
						lastCollisionTime = currentTime;
				}
			}
				
			else if(tempObject.getId() == ID.HardEnemy) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD3.HEALTH += 2;
						HUD3.graze += 1;
						lastCollisionTime = currentTime;
				}
			}
			else if(tempObject.getId() == ID.LunaiticEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD3.HEALTH += 3;
					HUD3.graze += 1;
					lastCollisionTime = currentTime;
				}
			}
			else if (currentTime - lastCollisionTime < collisionCooldown || Player3.isBlinking == true) {
		        return; 
		    }
			
			
	    }
	    
	}

}

