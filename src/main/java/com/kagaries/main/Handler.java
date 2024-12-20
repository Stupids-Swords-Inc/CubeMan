package com.kagaries.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Objects;

import com.kagaries.entity.GameObject;
import com.kagaries.entity.ID;
import com.kagaries.entity.enemy.Enemy;
import com.kagaries.main.Game.STATE;
import com.kagaries.player.entity.Player;
import com.kagaries.ui.hud.HUD;
import com.kagaries.ui.hud.HUD2;
import com.kagaries.ui.hud.HUD3;
import com.kagaries.ui.hud.HUD4;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public static int score = 0;
	public static float level = 0;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);

			if (tempObject instanceof Enemy) {
				((Enemy) tempObject).enableTick();
				if (((Enemy) tempObject).enabled) {
					tempObject.tick();
				}
			} else {
				tempObject.tick();
			}
		}
		
		if (Objects.equals(Game.gameState.getType().getTypeString(), "game")) {
			score++;
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearEnemys() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			if(Game.gameState == STATE.GameP4 || Game.gameState == STATE.PvPP4) {
				if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2 || tempObject.getId() == ID.Player3 || tempObject.getId() == ID.Player4) {
					object.clear();
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player2, this));
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player3, this));
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player4, this));
					}
				} else if (Game.gameState == STATE.GameP2 || Game.gameState == STATE.PvPP2) {
					if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {
						object.clear();
						addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
						addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player2, this));
					}
				} else if (Game.gameState == STATE.GameP1) {
					if(tempObject.getId() == ID.Player) {
						object.clear();
						addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
					}
				}
			
			
			
				
			}}
	
	
		
		public void allowRevive() {
			for(int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);
				
				if(tempObject.getId() == ID.Player && HUD.canRevive == true && Game.gameState == STATE.GameP2 || Game.gameState == STATE.GameP4) {
					HUD.canRevive = false;
					HUD.HEALTH = 100;
				}
				if(tempObject.getId() == ID.Player2 && HUD2.canRevive == true && Game.gameState == STATE.GameP2 || Game.gameState == STATE.GameP4) {
					HUD2.canRevive = false;
					HUD2.HEALTH = 100;
				}
				if(tempObject.getId() == ID.Player3 && HUD3.canRevive == true && Game.gameState == STATE.GameP4) {
					HUD3.canRevive = false;
					HUD3.HEALTH = 100;
				}
				if(tempObject.getId() == ID.Player4 && HUD4.canRevive == true && Game.gameState == STATE.GameP4) {
					HUD4.canRevive = false;
					HUD4.HEALTH = 100;
				}
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public static void setScore(int scoreInput) {
		score = scoreInput;
	}

	public int getScore() {
		return score;
	}

	public float getLevel() {
		return level;
	}

	public static void setLevel(float levelInput) {
		level = levelInput;
	}
}
