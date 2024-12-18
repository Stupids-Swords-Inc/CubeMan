package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	@SuppressWarnings("unused")
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
		
//		if(mouseOver(mx, my, 210, 150, 200, 64)) {
//			if(Game.gameState == STATE.Menu) {
//				Game.gameState = STATE.Game;
//				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
//				handler.addObject(new Player2(Game.WIDTH/2-140, Game.HEIGHT/2-140, ID.Player2, handler));
//				handler.clearEnemys();
//				//handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
//				//handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
//			}}
		
		if(mouseOver(mx, my, 210, 150, 200, 64)) {
			if(Game.gameState == STATE.Menu) {
				
				Game.gameState = STATE.PlayerNum;
			} else if(Game.gameState == STATE.PlayerNum) {
				Game.gameState = STATE.SelectP1;
			} else if(Game.gameState == STATE.PvPPlayerNum) {
				Game.gameState = STATE.PvPP2Select;
			}
			
		}
		
		if(mouseOver(mx, my, 210, 450, 200, 64)) {
			if (Game.gameState == STATE.Menu) {
				Game.gameState = STATE.Help;
			} else if (Game.gameState == STATE.Help) {
				Game.gameState = STATE.Menu;
			} else if (Game.gameState == STATE.SelectP1) {
				Game.gameState = STATE.PlayerNum;
			} else if (Game.gameState == STATE.SelectP2) {
				Game.gameState = STATE.PlayerNum;
			} else if (Game.gameState == STATE.SelectP4) {
				Game.gameState = STATE.PlayerNum;
			} else if (Game.gameState == STATE.PvPPlayerNum) {
				Game.gameState = STATE.Menu;
			} else if (Game.gameState == STATE.PlayerNum) {
				Game.gameState = STATE.Menu;
			} else if (Game.gameState == STATE.PvPP2Select) {
				Game.gameState = STATE.PvPPlayerNum;
			} else if (Game.gameState == STATE.PvPP4Select) {
				Game.gameState = STATE.PvPPlayerNum;
			} else if (Game.gameState == STATE.Options) {
				Game.gameState = STATE.Menu;
			}
		}
		
		if(mouseOver(mx, my, 410, 250, 200, 64)) {
			if(Game.gameState == STATE.Menu) {
				Game.gameState = STATE.PvPPlayerNum;
			} else if(Game.gameState == STATE.PlayerNum) {
				Game.gameState = STATE.SelectP2;
			} else if(Game.gameState == STATE.PvPPlayerNum) {
				Game.gameState = STATE.PvPP4Select;
			}
		}
			
		
		if(mouseOver(mx, my, 210, 350, 200, 64)) {
			if(Game.gameState == STATE.Menu) {
				System.exit(1);
			}else if(Game.gameState == STATE.End || Game.gameState == STATE.EndPvP) {
				
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
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				Shop.B1 = 2000;
				Shop.B2 = 1500;
				Shop.B3 = 1000;
				HUD.bounds = 0;
				Handler.spd = 6;
				
				//hud2.setScore(0);
				
			} else if(Game.gameState == STATE.PlayerNum) {
				Game.gameState = STATE.SelectP4;
			}
			
		}
		
		if(mouseOver(mx, my, 410, 450, 200, 64)) {
			if(Game.gameState == STATE.Menu) {
				Game.gameState = STATE.Options;
			}
		}
		
		
		if(mouseOver(mx, my, 410, 150, 200, 64)) {
			if(Game.gameState == STATE.SelectP1) {
				
				Game.gameState = STATE.GameP1;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.SelectP2) {
				Game.gameState = STATE.GameP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.SelectP4) {
				Game.gameState = STATE.GameP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.PvPP2Select) {
				Game.gameState = STATE.PvPP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.PvPP4Select) {
				Game.gameState = STATE.PvPP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 0;
			} else if(Game.gameState == STATE.Options) {
				if(Game.showTrail == true) {
					Game.showTrail = false;
				} else if(Game.showTrail == false) {
					Game.showTrail = true;
				}
			}
		
	}
		if(mouseOver(mx, my, 210, 250, 200, 64)) {
			if(Game.gameState == STATE.SelectP1) {
				
				Game.gameState = STATE.GameP1;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new HardEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.HardEnemy, handler));
				
				
				game.diff = 1;
			} else if(Game.gameState == STATE.SelectP2) {
				Game.gameState = STATE.GameP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.SelectP4) {
				Game.gameState = STATE.GameP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.PvPP2Select) {
				Game.gameState = STATE.PvPP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.PvPP4Select) {
				Game.gameState = STATE.PvPP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 1;
			} else if(Game.gameState == STATE.Options) {
				if(Game.showExtraStats == false) {
					Game.showExtraStats = true;
					
				} else if(Game.showExtraStats == true) {
					Game.showExtraStats = false;
				}
			}
		
	}
		
		if(mouseOver(mx, my, 10, 350, 200, 64)) {
			if(Game.gameState == STATE.SelectP1) {
				
				Game.gameState = STATE.GameP1;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new LunaiticEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.LunaiticEnemy, handler));
				
				
				game.diff = 2;
			} else if(Game.gameState == STATE.SelectP2) {
				Game.gameState = STATE.GameP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			} else if(Game.gameState == STATE.SelectP4) {
				Game.gameState = STATE.GameP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			} else if(Game.gameState == STATE.PvPP2Select) {
				Game.gameState = STATE.PvPP2;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			} else if(Game.gameState == STATE.PvPP4Select) {
				Game.gameState = STATE.PvPP4;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(Game.WIDTH/3-164, Game.HEIGHT/2-128, ID.BasicEnemy, handler));
				handler.clearEnemys();
				game.diff = 2;
			}
		
	}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("CubeMan", 240, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
		
			g.drawRect(410, 250, 200, 64);
			g.drawString("PvP", 470, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
			
			g.drawRect(210, 450, 200, 64);
			g.drawString("Help", 270, 490);
			
			g.drawRect(410, 450, 200, 64);
			g.drawString("Options", 470, 490);
		}else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt2);
						
			
		
			
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again?", 245, 390);
		}else if(Game.gameState == STATE.SelectP1) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 240, 70);
			
			g.setFont(fnt2);
						
			g.drawRect(410, 150, 200, 64);
			
			g.drawString("Normal", 470, 190);
					
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);
			
			g.drawRect(10, 350, 200, 64);
			
			g.drawString("Lunatic", 70, 390);
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		}else if(Game.gameState == STATE.EndPvP) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Winner Player " + Game.winner, 180, 240);		
			
		
			
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again?", 245, 390);
		}else if(Game.gameState == STATE.SelectP2) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 240, 70);
			
			g.setFont(fnt2);
						
			g.drawRect(410, 150, 200, 64);
			
			g.drawString("Normal", 470, 190);
					
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);
			
			g.drawRect(10, 350, 200, 64);
			
			g.drawString("Lunatic", 70, 390);
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		}else if(Game.gameState == STATE.SelectP4) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 240, 70);
			
			g.setFont(fnt2);
						
			g.drawRect(410, 150, 200, 64);
			
			g.drawString("Normal", 470, 190);
					
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);
			
			g.drawRect(10, 350, 200, 64);
			
			g.drawString("Lunatic", 70, 390);
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		}else if(Game.gameState == STATE.PlayerNum) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT PLAYER AMOUNT", 240, 70);
			
			g.setFont(fnt2);
					
			g.drawRect(210, 150, 200, 64);
			g.drawString("1 Player", 270, 190);
		
			g.drawRect(410, 250, 200, 64);
			g.drawString("2 Players", 470, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("4 Players", 270, 390);
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		} else if(Game.gameState == STATE.PvPPlayerNum) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT PLAYER AMOUNT", 240, 70);
			
			g.setFont(fnt2);
		
			g.drawRect(210, 150, 200, 64);
			g.drawString("2 Players", 270, 190);
			
			g.drawRect(410, 250, 200, 64);
			g.drawString("4 Players", 470, 290);
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		}else if(Game.gameState == STATE.PvPP2Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 240, 70);
			
			g.setFont(fnt2);
						
			g.drawRect(410, 150, 200, 64);
			
			g.drawString("Normal", 470, 190);
					
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);
			
			g.drawRect(10, 350, 200, 64);
			
			g.drawString("Lunatic", 70, 390);
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		}else if(Game.gameState == STATE.PvPP4Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SELECT DIFFICULTY", 240, 70);
			
			g.setFont(fnt2);
						
			g.drawRect(410, 150, 200, 64);
			
			g.drawString("Normal", 470, 190);
					
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 270, 290);
			
			g.drawRect(10, 350, 200, 64);
			
			g.drawString("Lunatic", 70, 390);
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		} else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("CubeMan", 240, 70);
			
			g.setFont(fnt2);
			g.drawString("This game is about moving your cube to dodge other cubes", 60, 170);
			g.drawString("(with or without friends)", 60, 200);
			
			
			
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		} else if(Game.gameState == STATE.Options) {
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			if (Game.showTrail == true) {
				g.drawRect(410, 150, 200, 64);
				g.drawString("Disable Trail", 420, 190);
			} else if (Game.showTrail == false) {
				g.drawRect(410, 150, 200, 64);
				g.drawString("Enable Trail", 420, 190);
			}
			
					
			if (Game.showExtraStats == false) {
				g.drawRect(210, 250, 200, 64);
				g.drawString("Show Stats", 230, 290);
			} else if (Game.showExtraStats == true) {
				g.drawRect(210, 250, 200, 64);
				g.drawString("Hide Stats", 230, 290);
			}
			
			g.drawRect(210, 450, 200, 64);
			g.drawString("Back", 270, 490);
		}
	}
}

