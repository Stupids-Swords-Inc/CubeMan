package com.kagaries.main;

import com.kagaries.entity.ID;
import com.kagaries.entity.MenuParticle;
import com.kagaries.entity.Spawn;
import com.kagaries.player.entity.Player;
import com.kagaries.player.input.KeyInput;
import com.kagaries.ui.Window;
import com.kagaries.ui.hud.HUD;
import com.kagaries.ui.hud.HUD2;
import com.kagaries.ui.hud.HUD3;
import com.kagaries.ui.hud.HUD4;
import com.kagaries.ui.menu.Menu;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 7580815534084638412L;
	
	public static final int WIDTH = 940, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	private final Handler handler;

	public static HUD hud;
	public static HUD2 hud2;
	public static HUD3 hud3;
	public static HUD4 hud4;
	public static Canvas window;

	private final Spawn spawner;
	public int diff = 0;
	public static int winner = 0;
	
	public static boolean showTrail = true;
	public static boolean showExtraStats = false;
	public static boolean muted = false;
	
	private final Menu menu;

	public static ResourceLoader resourceLoader = new ResourceLoader();

	public enum stateType {
		MENU,
		SELECT,
		GAMEP1("game"),
		GAMEP2("game"),
		GAMEP4("game"),
		PVPP2("game"),
		PVPP4("game");

		private final String type;

		stateType() {
			this.type = "menu";
		}

		stateType(String type) {
			this.type = type;
		}

		public String getTypeString() {
			return this.type;
		}
	}

	public enum STATE {
		Menu(stateType.MENU),
		PlayerNum(stateType.MENU),
		SelectP1(stateType.SELECT),
		SelectP2(stateType.SELECT),
		SelectP4(stateType.SELECT),
		Help(stateType.MENU),
		Options(stateType.MENU),
		GameP1(stateType.GAMEP1),
		GameP2(stateType.GAMEP2),
		GameP4(stateType.GAMEP4),
		PvPPlayerNum(stateType.MENU),
		PvPP2Select(stateType.SELECT),
		PvPP4Select(stateType.SELECT),
		PvPP2(stateType.PVPP2),
		PvPP4(stateType.PVPP4),
		End(stateType.MENU),
		EndPvP(stateType.MENU);

		private final stateType type;

		STATE(stateType type) {
			this.type = type;
		}

		public stateType getType() {
			return this.type;
		}
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		getLogger().info("Starting...");
        try {
            resourceLoader.preloadResources();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler);
		spawner = new Spawn(handler, this);
		hud2 = new HUD2();
		hud3 = new HUD3();
		hud4 = new HUD4();
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		window = new Window(WIDTH, HEIGHT, "CubeMan", this);

		getLogger().info("Window Init");
		
		if (Game.gameState == STATE.Menu){
			for(int i = 0; i < 1; i++) {
				handler.addObject(new Player(WIDTH/2, HEIGHT/2, ID.Player, handler));
				handler.addObject(new MenuParticle(WIDTH/3-164, HEIGHT/2-128, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-100, HEIGHT/2-165, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH -232, HEIGHT/4-128, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-100, HEIGHT/4-167, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-255, HEIGHT/3-245, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-168, HEIGHT -245, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH -125, HEIGHT/2-89, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-94, HEIGHT/3-10, ID.MenuParticle, handler));

			}
		}
	}

	public static Logger getLogger() {
		return LogUtils.getLogger();
	}

	public static ResourceLoader getResourceLoader() {
		return resourceLoader;
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try{
			thread.join();
			running = false;
			
		}catch(Exception e) {
			getLogger().error(e.getLocalizedMessage());
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			
			}
			if(running)
				render();
			frames++;
			
			frames = (int) clamp(frames, 0, 60);
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				frames = 0;
			}
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if(gameState.getType() == stateType.GAMEP4 || gameState.getType() == stateType.PVPP4) {
			hud.render(g);
			hud2.render(g);
			hud3.render(g);
			hud4.render(g);
			handler.render(g);
		}else if(gameState.getType() == stateType.MENU || gameState.getType() == stateType.SELECT) {
			handler.render(g);
			menu.render(g);
		}
		if(paused) {
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", 100, 100);
		} else if(gameState.getType() == stateType.GAMEP2 || gameState.getType() == stateType.PVPP2) {
			hud.render(g);
			hud2.render(g);
			handler.render(g);
		} else if(gameState.getType() == stateType.GAMEP1) {
			hud.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	private void tick() {
		if(!paused && gameState == STATE.GameP4) {
			handler.tick();
			hud.tick();
			spawner.tick();
			hud2.tick();
			hud3.tick();
			hud4.tick();
			if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH <= 0) {
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				gameState = STATE.End;
				handler.clearEnemys();
			}
		}else if(gameState.getType() == stateType.MENU || gameState.getType() == stateType.SELECT) {
			menu.tick();
			handler.tick();
		}else if(!paused && gameState == STATE.PvPP4) {
			handler.tick();
			hud.tick();
			spawner.tick();
			hud2.tick();
			hud3.tick();
			hud4.tick();
			if(HUD.HEALTH >= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 1;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH >= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 2;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH >= 0 && HUD4.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 3;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH >= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 4;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			}
		} else if(!paused && gameState == STATE.GameP2) {
			handler.tick();
			hud.tick();
			spawner.tick();
			hud2.tick();
			if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0) {
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				gameState = STATE.End;
				handler.clearEnemys();
			}
		} else if(!paused && gameState.getType() == stateType.GAMEP1) {
			handler.tick();
			hud.tick();
			spawner.tick();
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				gameState = STATE.End;
				handler.clearEnemys();
			}
		} else if(!paused && gameState == STATE.PvPP2) {
			handler.tick();
			hud.tick();
			spawner.tick();
			hud2.tick();
			if(HUD.HEALTH >= 0 && HUD2.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 1;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH >= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 2;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				Handler.setLevel(0.0f);
				Handler.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			}
			
		}
		

	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return max;
		else return Math.max(var, min);
	}

	public static void main(String[] agrs) {
		new Game();
	}
}
