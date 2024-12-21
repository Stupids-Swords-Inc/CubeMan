package com.kagaries.player.entity;

import com.kagaries.audio.AudioRegistry;
import com.kagaries.audio.SimpleAudioPlayer;
import com.kagaries.entity.GameObject;
import com.kagaries.entity.enemy.Enemy;
import com.kagaries.entity.trail.DamageTrail;
import com.kagaries.entity.trail.Trail;
import com.kagaries.main.Game;
import com.kagaries.player.entity.graze.GrazeBox;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.ui.hud.HUD;
import com.kagaries.ui.hud.HudInterface;

import java.awt.*;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import static com.kagaries.util.json.JsonReader.readSettingsJson;

public class Player extends GameObject {
	
	Handler handler;
	private long lastCollisionTime = 0;
    public boolean isBlinking = false;
    boolean shouldRenderPlayer = true;

	public boolean dashing = false;
	boolean canDash = true;
	private int dashCooldown = 0;
	private int dashTime = 0;

	final Color color;
	public final HudInterface hud;
	private final GrazeBox grazeBox;
	private Color currentPanicColor;
	private boolean runningPanic = false;

	Color[] color1 = {Color.RED};

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

		if (dashCooldown > 0) {
			--dashCooldown;
		} else {
			canDash = true;
		}

		if (dashTime > 0) {
			--dashTime;
		} else if(dashing) {
			hud.setSpeed(6);
			Game.keyInput.resetSpeed(this, this.id);
			dashing = false;
		}

		if (dashing && !(Game.gameState == Game.STATE.PvPP2) && !(Game.gameState == Game.STATE.PvPP4) && !readSettingsJson().path("doPvPDamageAlways").asBoolean()) {
			handler.addObject(new Trail(x, y, ID.Trail, this.color, 32, 32, 0.125f, handler));
		} else if (dashing) {
			handler.addObject(new DamageTrail(x, y, ID.DamageTrail, this.color, 32, 32, 0.01f, handler, this));
		}

		if(hud.getHealth() <= 0) {
			handler.removeObject(this);
			handler.removeObject(this.grazeBox);
		}

		if (!isBlinking && !shouldRenderPlayer) {
			shouldRenderPlayer = true;
		}

		if (runningPanic && this.hud.getHealth() > 15) {
			runningPanic = false;
		}
	}
	
	

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		float alpha = 0.5f;
		float healthAlpha = 1.0f;
		float innerAlpha = this.canDash ? 1f : 0.25f;

		if (!readSettingsJson().path("allowDash").asBoolean()) {
			innerAlpha = 0.25f;
		}

	    if (shouldRenderPlayer) {
			g2d.setColor(this.color);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, innerAlpha));
			g2d.fillRect((int)x + 10, (int)y + 10, 12, 12);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	        g2d.fillRect((int)x, (int)y, 32, 32);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, healthAlpha));
			if (hud.getHealth() > 50) {
				g2d.setColor(Color.GREEN);
			} else if (hud.getHealth() < 50 && hud.getHealth() > 25) {
				g2d.setColor(Color.ORANGE);
			} else if (hud.getHealth() < 25 && hud.getHealth() > 10) {
				g2d.setColor(Color.RED);
			} else if (hud.getHealth() < 15) {
				if (!runningPanic) {
					color1 = new Color[]{startPanicHealth()};
				}
				g2d.setColor(color1[0] == null ? Color.BLACK : color1[0]);
			}
			g2d.fillRect((int)x, (int)y, 5, 5);
	    }
	}

	public void dash() {
		if (this.canDash && ((this.velX > 0 || this.velY > 0) || (this.velX < 0 || this.velY < 0))) {
			SimpleAudioPlayer.playSound(AudioRegistry.DASH);
			hud.setSpeed(readSettingsJson().path("dashSpeed").asInt());
			dashCooldown = readSettingsJson().path("dashCooldown").asInt();
			canDash = false;
			dashTime = readSettingsJson().path("dashTime").asInt();
			dashing = true;
		}
	}
	
	private void collision() {
		long currentTime = System.currentTimeMillis();
	    
	    for(int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

            // Cooldown in milliseconds
            long collisionCooldown = readSettingsJson().path("collisionCooldown").asLong();
            if(tempObject.getId().getDamage() != 0 && tempObject instanceof Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
                    if (((Enemy) tempObject).enabled) {
						if (dashing && readSettingsJson().path("dashDoesInvul").asBoolean()) {
							return;
						}
                        SimpleAudioPlayer.playDamageSound(AudioRegistry.PLAYER_HURT, tempObject.getId().getDamage());
                        hud.setHealth((float) (hud.getHealth() - (tempObject.getId().getDamage() * readSettingsJson().path("damageMulti").asDouble())));
                        lastCollisionTime = currentTime;
                    }
                }
			} else if (tempObject.getId().getDamage() != 0 && tempObject instanceof DamageTrail) {
                if (getBounds().intersects(tempObject.getBounds())) {
					if (!(((DamageTrail) tempObject).object.equals(this))) {
						if (dashing && readSettingsJson().path("dashDoesInvul").asBoolean()) {
							return;
						}

						if (!readSettingsJson().path("allowPvPDamage").asBoolean()) {
							return;
						}

						SimpleAudioPlayer.playDamageSound(AudioRegistry.PLAYER_HURT, tempObject.getId().getDamage());
						hud.setHealth(hud.getHealth() - tempObject.getId().getDamage());
						lastCollisionTime = currentTime + readSettingsJson().path("PvPCollisionCooldownAdd").asLong();
						handler.removeObject(tempObject);
						return;
					}
				}
            }else if (currentTime - lastCollisionTime < collisionCooldown) {
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
                // Blink interval in milliseconds
                int blinkInterval = 25;
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

			private Color startPanicHealth() {
				this.runningPanic = true;
				Timer timer = new Timer();



				int blinkInterval = 50;
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						if (color1[0] == Color.BLACK) {
							color1[0] = Color.RED;
						} else {
							color1[0] = Color.BLACK;
						}

						if (!runningPanic) {
							timer.cancel();
						}
					}
				}, 0, blinkInterval);

				return color1[0];
			}

			private void stopBlinking() {
			    // Reset the shouldRenderPlayer flag and cancel the blinking timer
			    shouldRenderPlayer = true;
			}
}

