package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean[] keyDown = new boolean[16];
	static int KeyPresses = 1;
	static int KeyPresses2 = 1;
	
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
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-HUD.speed); keyDown[0]=true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(HUD.speed); keyDown[1]=true; }
				if(key == KeyEvent.VK_D) { tempObject.setVelX(HUD.speed); keyDown[2]=true; }
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-HUD.speed); keyDown[3]=true; }
				
				
				if(Game.gameState == STATE.GameP1 || Game.gameState == STATE.GameP2) {
					if(KeyPresses >= 1) {
						if(key == KeyEvent.VK_Q) {
							HUD.HEALTH += 100;
							KeyPresses--;
						}
					}
				}
				if(key == KeyEvent.VK_ESCAPE) {
					Game.gameState = STATE.Menu;
					
					HUD.HEALTH = 0;
					HUD2.HEALTH = 0;
					HUD3.HEALTH = 0;
					HUD4.HEALTH = 0;
					HUD.setLevel(0.0f);
					HUD.setScore(0);
					Game.gameState = STATE.End;
					handler.clearEnemys();
				}
				if(key == KeyEvent.VK_P) {
					if (Game.paused == false) {
						Game.paused = true;
					} else if (Game.paused == true) {
						Game.paused = false;
					}
				}
			}
				
			if(tempObject.getId() == ID.Player2) {
					if(key == KeyEvent.VK_UP) { tempObject.setVelY(-HUD2.speed); keyDown[4]=true; }
					if(key == KeyEvent.VK_DOWN) { tempObject.setVelY(HUD2.speed); keyDown[5]=true; }
					if(key == KeyEvent.VK_LEFT) { tempObject.setVelX(-HUD2.speed); keyDown[6]=true; }
					if(key == KeyEvent.VK_RIGHT) { tempObject.setVelX(HUD2.speed); keyDown[7]=true; }

				
				if(Game.gameState == STATE.GameP2) {
					if(KeyPresses2 >= 1) {
						if(key == KeyEvent.VK_CONTROL) {
							HUD2.HEALTH += 100;
							KeyPresses2--;
						}
				}
				}
				}
			
			if(tempObject.getId() == ID.Player3) {
					if(key == KeyEvent.VK_I) { tempObject.setVelY(-HUD3.speed); keyDown[8]=true; }
					if(key == KeyEvent.VK_K) { tempObject.setVelY(HUD3.speed); keyDown[9]=true; }
					if(key == KeyEvent.VK_J) { tempObject.setVelX(-HUD3.speed); keyDown[10]=true; }
					if(key == KeyEvent.VK_L) { tempObject.setVelX(HUD3.speed); keyDown[11]=true; }
				}
			
			if(tempObject.getId() == ID.Player4) {
					if(key == KeyEvent.VK_T) { tempObject.setVelY(-HUD4.speed); keyDown[12]=true; }
					if(key == KeyEvent.VK_G) { tempObject.setVelY(HUD4.speed); keyDown[13]=true; }
					if(key == KeyEvent.VK_F) { tempObject.setVelX(-HUD4.speed); keyDown[14]=true; }
					if(key == KeyEvent.VK_H) { tempObject.setVelX(HUD4.speed); keyDown[15]=true; }
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



