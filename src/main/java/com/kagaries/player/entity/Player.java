package com.kagaries.player.entity;

import com.kagaries.audio.AudioRegistry;
import com.kagaries.audio.SimpleAudioPlayer;
import com.kagaries.entity.GameObject;
import com.kagaries.main.Game;
import com.kagaries.player.entity.graze.GrazeBox;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.ui.hud.HudInterface;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends GameObject {
	
	Handler handler;
	private long lastCollisionTime = 0;
	private final long collisionCooldown = 500; // Cooldown in milliseconds
	public static boolean isBlinking = false;
	private final int blinkInterval = 25; // Blink interval in milliseconds
	private boolean shouldRenderPlayer = true;

	private final Color color;
	private final HudInterface hud;
	private final GrazeBox grazeBox;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.grazeBox = new GrazeBox(x, y, ID.GrazeBox, id, handler);
		handler.addObject(this.grazeBox);
		if (id.getColor() != null) {
			this.color = id.getColor();
		} else {
			this.color = Color.GREEN;
		}

		hud = id.getHud();
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x + 10, (int)y + 10, 12, 12);
	}

	
	public void tick() {
		x += velX;
		y += velY; 
		
		x = Game.clamp(x, 0, Game.WIDTH - 48);
		y = Game.clamp(y, 0, Game.HEIGHT - 71);

		
		collision();
		
		if(hud.getHealth() <= 0) {
			handler.removeObject(this);
			handler.removeObject(this.grazeBox);
		}

		if (!isBlinking && !shouldRenderPlayer) {
			shouldRenderPlayer = true;
		}
	}
	
	

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		float alpha = 0.75f;

	    if (shouldRenderPlayer) {
			g2d.fillRect((int)x + 10, (int)y + 10, 12, 12);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	        g2d.setColor(this.color);
	        g2d.fillRect((int)x, (int)y, 32, 32);
	    }
	}

	
	private void collision() {
		long currentTime = System.currentTimeMillis();
	    
	    for(int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId().getDamage() != 0) {
				if(getBounds().intersects(tempObject.getBounds())) {
					SimpleAudioPlayer.playSound(AudioRegistry.PLAYER_HURT);
                    hud.setHealth(hud.getHealth() - tempObject.getId().getDamage());
					lastCollisionTime = currentTime;
				}
			} else if (currentTime - lastCollisionTime < collisionCooldown) {
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
						if (!isBlinking) {
							timer.cancel();
						}
			        }
			    }, 0, blinkInterval);
			}

			private void stopBlinking() {
			    // Reset the shouldRenderPlayer flag and cancel the blinking timer
			    shouldRenderPlayer = true;
			}
}

