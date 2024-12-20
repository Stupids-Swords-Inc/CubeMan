package com.kagaries.ui.menu;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.kagaries.audio.AudioRegistry;
import com.kagaries.audio.SimpleAudioPlayer;
import com.kagaries.entity.enemy.BasicEnemy;
import com.kagaries.entity.enemy.HardEnemy;
import com.kagaries.entity.enemy.LunaiticEnemy;
import com.kagaries.main.Game;
import com.kagaries.main.Game.STATE;
import com.kagaries.main.Handler;
import com.kagaries.entity.ID;
import com.kagaries.entity.Spawn;
import com.kagaries.player.entity.Player;
import com.kagaries.ui.hud.HUD;
import com.kagaries.ui.hud.HUD2;
import com.kagaries.ui.hud.HUD3;
import com.kagaries.ui.hud.HUD4;

import javax.swing.*;

public class Menu extends MouseAdapter {
	
	private final Game game;
	private final Handler handler;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(mouseOver(mx, my, 210, 150)) {
			if(Game.gameState == STATE.Menu) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PlayerNum;
			} else if(Game.gameState == STATE.PlayerNum) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.SelectP1;
			} else if(Game.gameState == STATE.PvPPlayerNum) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP2Select;
			}

		}

		if(mouseOver(mx, my, 210, 450)) {
			if (Game.gameState == STATE.Menu) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.Help;
			} else if (Game.gameState == STATE.Help) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.Menu;
			} else if (Game.gameState == STATE.SelectP1) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PlayerNum;
			} else if (Game.gameState == STATE.SelectP2) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PlayerNum;
			} else if (Game.gameState == STATE.SelectP4) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PlayerNum;
			} else if (Game.gameState == STATE.PvPPlayerNum) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.Menu;
			} else if (Game.gameState == STATE.PlayerNum) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.Menu;
			} else if (Game.gameState == STATE.PvPP2Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPPlayerNum;
			} else if (Game.gameState == STATE.PvPP4Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPPlayerNum;
			} else if (Game.gameState == STATE.Options) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.Menu;
			}
		}

		if(mouseOver(mx, my, 410, 250)) {
			if(Game.gameState == STATE.Menu) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPPlayerNum;
			} else if(Game.gameState == STATE.PlayerNum) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.SelectP2;
			} else if(Game.gameState == STATE.PvPPlayerNum) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP4Select;
			}
		}


		if(mouseOver(mx, my, 210, 350)) {
			if(Game.gameState == STATE.Menu) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				System.exit(1);
			}else if(Game.gameState == STATE.End || Game.gameState == STATE.EndPvP) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				HUD.HEALTH = 100;
				HUD2.HEALTH = 100;
				HUD3.HEALTH = 100;
				HUD4.HEALTH = 100;
				HUD.reviveTimer = 0;
				HUD2.reviveTimer = 0;
				HUD3.reviveTimer = 0;
				HUD4.reviveTimer = 0;
				HUD.canRevive = false;
				HUD2.canRevive = false;
				HUD3.canRevive = false;
				HUD4.canRevive = false;
				HUD.graze = 0;
				handler.clearEnemys();
				Game.gameState = STATE.Menu;


				Spawn.scoreKeep = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				Shop.B1 = 2000;
				Shop.B2 = 1500;
				Shop.B3 = 1000;
				HUD.bounds = 0;

				//hud2.setScore(0);

			} else if(Game.gameState == STATE.PlayerNum) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.SelectP4;
			}

		}

		if(mouseOver(mx, my, 410, 450)) {
			if(Game.gameState == STATE.Menu) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.Options;
			}
		}


		if(mouseOver(mx, my, 410, 150)) {
			if(Game.gameState == STATE.SelectP1) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP1;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.SelectP2) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.SelectP4) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.PvPP2Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.PvPP4Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.Options) {
				if(Game.showTrail) {
					SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
					Game.showTrail = false;
				} else {
					SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
					Game.showTrail = true;
				}
			}

	}
		if(mouseOver(mx, my, 210, 250)) {
			if(Game.gameState == STATE.SelectP1) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP1;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.HardEnemy, handler));


				game.diff = 1;
			} else if(Game.gameState == STATE.SelectP2) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.SelectP4) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.PvPP2Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.PvPP4Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.Options) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
                Game.showExtraStats = !Game.showExtraStats;
			}

	}

		if(mouseOver(mx, my, 10, 350)) {
			if(Game.gameState == STATE.SelectP1) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP1;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new LunaiticEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.LunaiticEnemy, handler));


				game.diff = 2;
			} else if(Game.gameState == STATE.SelectP2) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			} else if(Game.gameState == STATE.SelectP4) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.GameP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			} else if(Game.gameState == STATE.PvPP2Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			} else if(Game.gameState == STATE.PvPP4Select) {
				SimpleAudioPlayer.playSound(AudioRegistry.MENU_SELECT);
				Game.gameState = STATE.PvPP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			}

	}
	}

    private boolean mouseOver(int mx, int my, int x, int y) {
		if(mx > x && mx < x + 200) {
            return my > y && my < y + 64;
		}else return false;
	}
	
	public void tick() {
		
	}

	private void createButton(Graphics g, String string, int x, int y) {
		Font fnt = new Font("arial", Font.BOLD, 30);

		FontMetrics fm = g.getFontMetrics(fnt);

		g.setFont(fnt);
		g.setColor(Color.black);
		g.fillRect(x,y,200,64);
		g.setColor(Color.white);
		g.drawRect(x,y,200,64);
		g.drawString(string, x + (200 - fm.stringWidth(string)) / 2, y + (64 - fm.getHeight()) / 2 + fm.getAscent());
	}

	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("CubeMan", 240, 70);

            createButton(g, "Play",210, 150);
            createButton(g, "PvP",410, 250);
			createButton(g, "Quit", 210, 350);
			createButton(g, "Help", 210, 450);
			createButton(g, "Options", 410, 450);

		}else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt2);

			createButton(g, "Try Again?", 210, 350);
		}else if(Game.gameState.getType() == Game.stateType.SELECT) {
			Font fnt = new Font("arial", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 240, 70);
			
			g.setFont(fnt2);

			createButton(g, "Normal", 410, 150);
			createButton(g, "Hard", 210, 250);
			createButton(g, "Lunatic", 10, 350);
			createButton(g, "Back", 210, 450);

		}else if(Game.gameState == STATE.EndPvP) {
			Font fnt = new Font("arial", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Winner Player " + Game.winner, 180, 240);

			createButton(g, "Try Again?", 210, 350);
		}else if(Game.gameState == STATE.PlayerNum) {
			Font fnt = new Font("arial", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT PLAYER AMOUNT", 240, 70);
			
			g.setFont(fnt2);


			createButton(g, "Solo", 210, 150);
			createButton(g, "Duo", 410, 250);
			createButton(g, "Quad", 210, 350);
			createButton(g, "Back", 210, 450);

		} else if(Game.gameState == STATE.PvPPlayerNum) {
			Font fnt = new Font("arial", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT PLAYER AMOUNT", 240, 70);

			g.setFont(fnt2);

			createButton(g, "2 Players", 210, 150);
			createButton(g, "4 Players", 410, 250);
			createButton(g, "Back", 210, 450);

		} else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", Font.BOLD, 50);
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("CubeMan", 240, 70);
			
			g.setFont(fnt2);
			g.drawString("This game is about moving your cube to dodge other cubes", 60, 170);
			g.drawString("(with or without friends)", 60, 200);
			
			
			createButton(g, "Back", 210, 450);
		} else if(Game.gameState == STATE.Options) {
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			if (Game.showTrail) {
				createButton(g, "Disable Trail", 410, 150);
			} else {
				createButton(g, "Enable Trail", 410, 150);
			}
			
					
			if (!Game.showExtraStats) {
				createButton(g, "Show Stats", 210, 250);
			} else {
				createButton(g, "Hide Stats", 210, 250);
			}

			createButton(g, "Back", 210, 450);
		}
	}
}

