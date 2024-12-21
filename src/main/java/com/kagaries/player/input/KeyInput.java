package com.kagaries.player.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.Objects;

import com.kagaries.entity.GameObject;
import com.kagaries.main.Game;
import com.kagaries.main.Game.STATE;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.player.entity.Player;
import com.kagaries.ui.hud.HUD;
import com.kagaries.ui.hud.HUD2;
import com.kagaries.ui.hud.HUD3;
import com.kagaries.ui.hud.HUD4;
import com.kagaries.util.json.JsonReader;

public class KeyInput extends KeyAdapter{
	
	private final Handler handler;
	private final boolean[] keyDown = new boolean[16];
	
	Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		
		this.game = game;
		
		//Player 1
		keyDown[0]=false;
		keyDown[1]=false;
		keyDown[2]=false;
		keyDown[3]=false;
		
		//Player 2
		keyDown[4]=false;
		keyDown[5]=false;
		keyDown[6]=false;
		keyDown[7]=false;
		
		//Player 3
		keyDown[8]=false;
		keyDown[9]=false;
		keyDown[10]=false;
		keyDown[11]=false;
		
		//Player 4
		keyDown[12]=false;
		keyDown[13]=false;
		keyDown[14]=false;
		keyDown[15]=false;
	}

	public void resetSpeed(GameObject tempObject, ID id) {
		if (id == ID.Player) {
			if (keyDown[0]) {
				tempObject.setVelY(-HUD.speed);
			}
			if (keyDown[1]) {
				tempObject.setVelY(HUD.speed);
			}
			if (keyDown[2]) {
				tempObject.setVelX(HUD.speed);
			}
			if (keyDown[3]) {
				tempObject.setVelX(-HUD.speed);
			}
		} else if (id == ID.Player2) {
			if (keyDown[4]) {
				tempObject.setVelY(-HUD2.speed);
			}
			if (keyDown[5]) {
				tempObject.setVelY(HUD2.speed);
			}
			if (keyDown[6]) {
				tempObject.setVelX(-HUD2.speed);
			}
			if (keyDown[7]) {
				tempObject.setVelX(HUD2.speed);
			}
		} else if (id == ID.Player3) {
			if (keyDown[8]) {
				tempObject.setVelY(-HUD3.speed);
			}
			if (keyDown[9]) {
				tempObject.setVelY(HUD3.speed);
			}
			if (keyDown[10]) {
				tempObject.setVelX(-HUD3.speed);
			}
			if (keyDown[11]) {
				tempObject.setVelX(HUD3.speed);
			}
		} else if (id == ID.Player4) {
			if (keyDown[12]) {
				tempObject.setVelY(-HUD4.speed);
			}
			if (keyDown[13]) {
				tempObject.setVelY(HUD4.speed);
			}
			if (keyDown[14]) {
				tempObject.setVelX(-HUD4.speed);
			}
			if (keyDown[15]) {
				tempObject.setVelX(HUD4.speed);
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_MINUS) {
			Game.muted = !Game.muted;
		}
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-HUD.speed); keyDown[0]=true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(HUD.speed); keyDown[1]=true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(HUD.speed); keyDown[2]=true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-HUD.speed); keyDown[3]=true; }

				if(key == KeyEvent.VK_E && JsonReader.readSettingsJson().path("allowDash").asBoolean()) {
					((Player) tempObject).dash();
					if (keyDown[0]) {
						tempObject.setVelY(-HUD.speed);
					}
					if (keyDown[1]) {
						tempObject.setVelY(HUD.speed);
					}
					if (keyDown[2]) {
						tempObject.setVelX(HUD.speed);
					}
					if (keyDown[3]) {
						tempObject.setVelX(-HUD.speed);
					}
				}
				/*
				if (key == KeyEvent.VK_V) {
					((Player) tempObject).hud.setHealth(5);
				}

				if (key == KeyEvent.VK_N) {
					((Player) tempObject).hud.setHealth(95);
				}

				 */

				if(key == KeyEvent.VK_ESCAPE && Objects.equals(Game.gameState.getType().getTypeString(), "game")) {
					Game.gameState = STATE.Menu;
					
					HUD.HEALTH = 0;
					HUD2.HEALTH = 0;
					HUD3.HEALTH = 0;
					HUD4.HEALTH = 0;
					Handler.setLevel(0.0f);
					Handler.setScore(0);
					Game.gameState = STATE.End;
					handler.clearEnemys();
				}

				if(key == KeyEvent.VK_P) {
                    Game.paused = !Game.paused;
				}
			}
				
			if(tempObject.getId() == ID.Player2) {
					if(key == KeyEvent.VK_UP) { tempObject.setVelY(-HUD2.speed); keyDown[4]=true; }
					if(key == KeyEvent.VK_DOWN) { tempObject.setVelY(HUD2.speed); keyDown[5]=true; }
					if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-HUD2.speed); keyDown[6]=true; }
					if(key == KeyEvent.VK_RIGHT) { tempObject.setVelX(HUD2.speed); keyDown[7]=true; }

				if(key == 17 && e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT && JsonReader.readSettingsJson().path("allowDash").asBoolean()) {
					((Player) tempObject).dash();
					if (keyDown[4]) {
						tempObject.setVelY(-HUD2.speed);
					}
					if (keyDown[5]) {
						tempObject.setVelY(HUD2.speed);
					}
					if (keyDown[6]) {
						tempObject.setVelX(-HUD2.speed);
					}
					if (keyDown[7]) {
						tempObject.setVelX(HUD2.speed);
					}
				}
			}
			
			if(tempObject.getId() == ID.Player3) {
					if(key == KeyEvent.VK_I) { tempObject.setVelY(-HUD3.speed); keyDown[8]=true; }
					if(key == KeyEvent.VK_K) { tempObject.setVelY(HUD3.speed); keyDown[9]=true; }
					if(key == KeyEvent.VK_J) { tempObject.setVelX(-HUD3.speed); keyDown[10]=true; }
					if(key == KeyEvent.VK_L) { tempObject.setVelX(HUD3.speed); keyDown[11]=true; }

				if(key ==  KeyEvent.VK_SEMICOLON && JsonReader.readSettingsJson().path("allowDash").asBoolean()) {
					((Player) tempObject).dash();
					if (keyDown[8]) {
						tempObject.setVelY(-HUD3.speed);
					}
					if (keyDown[9]) {
						tempObject.setVelY(HUD3.speed);
					}
					if (keyDown[10]) {
						tempObject.setVelX(-HUD3.speed);
					}
					if (keyDown[11]) {
						tempObject.setVelX(HUD3.speed);
					}
				}
			}
			
			if(tempObject.getId() == ID.Player4) {
					if(key == KeyEvent.VK_T) { tempObject.setVelY(-HUD4.speed); keyDown[12]=true; }
					if(key == KeyEvent.VK_G) { tempObject.setVelY(HUD4.speed); keyDown[13]=true; }
					if(key == KeyEvent.VK_F) { tempObject.setVelX(-HUD4.speed); keyDown[14]=true; }
					if(key == KeyEvent.VK_H) { tempObject.setVelX(HUD4.speed); keyDown[15]=true; }

				if(key ==  KeyEvent.VK_Y && JsonReader.readSettingsJson().path("allowDash").asBoolean()) {
					((Player) tempObject).dash();
					if (keyDown[12]) {
						tempObject.setVelY(-HUD4.speed);
					}
					if (keyDown[13]) {
						tempObject.setVelY(HUD4.speed);
					}
					if (keyDown[14]) {
						tempObject.setVelX(-HUD4.speed);
					}
					if (keyDown[15]) {
						tempObject.setVelX(HUD4.speed);
					}
				}
				}
			}
		}
		
	
	
			
		
	
	

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				if(key == KeyEvent.VK_W) keyDown[0]=false;	//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1]=false;	//tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) keyDown[2]=false;	//tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) keyDown[3]=false;	//tempObject.setVelX(0);
				
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
							
				
			}
			
			if(tempObject.getId() == ID.Player2) {
				if(key == KeyEvent.VK_UP) keyDown[4]=false;
				if(key == KeyEvent.VK_DOWN) keyDown[5]=false;
				if(key == KeyEvent.VK_LEFT) keyDown[6]=false;
				if(key == KeyEvent.VK_RIGHT) keyDown[7]=false;
				
				if(!keyDown[4] && !keyDown[5]) tempObject.setVelY(0);
				if(!keyDown[6] && !keyDown[7]) tempObject.setVelX(0);
			}
			
			if(tempObject.getId() == ID.Player3) {
				if(key == KeyEvent.VK_I) keyDown[8]=false;
				if(key == KeyEvent.VK_K) keyDown[9]=false;
				if(key == KeyEvent.VK_J) keyDown[10]=false;
				if(key == KeyEvent.VK_L) keyDown[11]=false;
				
				if(!keyDown[8] && !keyDown[9]) tempObject.setVelY(0);
				if(!keyDown[10] && !keyDown[11]) tempObject.setVelX(0);
			}
			
			if(tempObject.getId() == ID.Player4) {
				if(key == KeyEvent.VK_T) keyDown[12]=false;
				if(key == KeyEvent.VK_G) keyDown[13]=false;
				if(key == KeyEvent.VK_F) keyDown[14]=false;
				if(key == KeyEvent.VK_H) keyDown[15]=false;
				
				if(!keyDown[12] && !keyDown[13]) tempObject.setVelY(0);
				if(!keyDown[14] && !keyDown[15]) tempObject.setVelX(0);
			}
		}
		
	}


	    
}



