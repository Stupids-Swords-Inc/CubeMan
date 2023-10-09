package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends GameObject{
	
	Handler handler;
	private long lastCollisionTime = 0;
	private long collisionCooldown = 500; // Cooldown in milliseconds
	public static boolean isBlinking = false;
	private int blinkInterval = 25; // Blink interval in milliseconds
	private boolean shouldRenderPlayer = true;


	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 12, 12);
	}

	
	public void tick() {
		x += velX;
		y += velY; 
		
		x = Game.clamp(x, 0, Game.WIDTH - 48);
		y = Game.clamp(y, 0, Game.HEIGHT - 71);

		
		collision();
		
		if(HUD.HEALTH <= 0) {
			handler.removeObject(this);
		}
	}
	
	

	public void render(Graphics g) {
	    if (!isBlinking) {
	        g.setColor(Color.green);
	        g.fillRect((int)x, (int)y, 32, 32);
	    }
	}

	
	private void collision() {
		long currentTime = System.currentTimeMillis();
		
		
	    
	    for(int i = 0; i < handler.object.size(); i++) {
	
	
			
			GameObject tempObject = handler.object.get(i);
		    
		    
			
			if(tempObject.getId() == ID.BasicEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 35;
					lastCollisionTime = currentTime;
				}
			}
			
			else if(tempObject.getId() == ID.SlowEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 51;
					lastCollisionTime = currentTime;
			}	
		}
				
			else if(tempObject.getId() == ID.Friend) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD.HEALTH -= 50;
						lastCollisionTime = currentTime;
				}	
			}
			else if(tempObject.getId() == ID.FastEnemy) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD.HEALTH -= 29;
						lastCollisionTime = currentTime;
				} 
			}
			else if(tempObject.getId() == ID.SmartEnemy) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD.HEALTH -= 35;
						lastCollisionTime = currentTime;
				}
			}
				
			else if(tempObject.getId() == ID.EnemyBoss) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD.HEALTH -= 100;
						lastCollisionTime = currentTime;
				}
			}
				
			else if(tempObject.getId() == ID.HardEnemy) {
					if(getBounds().intersects(tempObject.getBounds())) {
						HUD.HEALTH -= 37;
						lastCollisionTime = currentTime;
				}
			}
			else if(tempObject.getId() == ID.LunaiticEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 51;
					lastCollisionTime = currentTime;
				}
			}
			else if (currentTime - lastCollisionTime < collisionCooldown) {
		        if (!isBlinking) {
		            isBlinking = true;
		            startBlinking();
		        }
		        return; 
		    }
			
			
	    }
	    
	    // Reset blinking state when cooldown is over
	    if (isBlinking) {
	        isBlinking = false;
	        stopBlinking();
	    }
	}

			private void startBlinking() {
			    Timer timer = new Timer();
			    timer.scheduleAtFixedRate(new TimerTask() {
			        @Override
			        public void run() {
			            shouldRenderPlayer = !shouldRenderPlayer; // Toggle rendering on and off
			        }
			    }, 0, blinkInterval);
			}

			private void stopBlinking() {
			    // Reset the shouldRenderPlayer flag and cancel the blinking timer
			    shouldRenderPlayer = true;
			}
}

