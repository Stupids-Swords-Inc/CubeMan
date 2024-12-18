package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 7580815534084638412L;
	
	public static final int WIDTH = 940, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	static boolean paused = false;
	private Handler handler;
	private HUD hud;
	@SuppressWarnings("unused")
	private HUD2 hud2;
	private HUD3 hud3;
	private HUD4 hud4;
	private Spawn spawner;
	public int diff = 0;
	private Shop shop;
	public static int winner = 0;
	
	public static boolean showTrail = true;
	public static boolean fullscreen = false;
	public static boolean showExtraStats = false;
	
	private Menu menu;
	public enum STATE {
		Menu,
		PlayerNum,
		SelectP1,
		SelectP2,
		SelectP4,
		Help,
		Options,
		Shop,
		GameP1,
		GameP2,
		GameP4,
		PvPPlayerNum,
		PvPP2Select,
		PvPP4Select,
		PvPP2,
		PvPP4,
		End,
		EndPvP
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
	
		
		new Window(WIDTH, HEIGHT, "CubeMan", this);
		
		
		hud2 = new HUD2();
		hud3 = new HUD3();
		hud4 = new HUD4();
		spawner = new Spawn(handler, hud, this);
		menu = new Menu(this, handler, hud);

		
		if (Game.gameState == STATE.Menu){
			for(int i = 0; i < 1; i++) {
				handler.addObject(new MenuParticle(WIDTH/3-164, HEIGHT/2-128, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-100, HEIGHT/2-165, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/1-232, HEIGHT/4-128, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-100, HEIGHT/4-167, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-255, HEIGHT/3-245, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-168, HEIGHT/1-245, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/1-125, HEIGHT/2-89, ID.MenuParticle, handler));
				handler.addObject(new MenuParticle(WIDTH/2-94, HEIGHT/3-10, ID.MenuParticle, handler));

			}
		}
		
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
			e.printStackTrace();
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
				System.out.println("FPS: " + frames);
				
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
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);	
		
		
		
		if(gameState == STATE.GameP4 || gameState == STATE.PvPP4) {
			hud.render(g);
			HUD2.render(g);
			hud3.render(g);
			hud4.render(g);
			handler.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.SelectP1 || gameState == STATE.SelectP2 || gameState == STATE.SelectP4 || gameState == STATE.EndPvP || gameState == STATE.PlayerNum || gameState == STATE.PvPPlayerNum || gameState == STATE.PvPP2Select || gameState == STATE.PvPP4Select || gameState == STATE.Help || gameState == STATE.Options) {
			menu.render(g);
			handler.render(g);
		}else if(gameState == STATE.Shop)
			shop.render(g);
		if(paused) {
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", 100, 100);
		} else if(gameState == STATE.GameP2 || gameState == STATE.PvPP2) {
			hud.render(g);
			HUD2.render(g);
			handler.render(g);
		} else if(gameState == STATE.GameP1) {
			hud.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
	}

	private void tick() {
		
		
		
		if(!paused && gameState == STATE.GameP4) {
			handler.tick();
			HUD.tick();
			spawner.tick();
			HUD2.tick();
			HUD3.tick();
			HUD4.tick();
			if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH <= 0) {
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				gameState = STATE.End;
				handler.clearEnemys();
			}
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.SelectP1 || gameState == STATE.SelectP2 || gameState == STATE.SelectP4 || gameState == STATE.EndPvP || gameState == STATE.PlayerNum || gameState == STATE.PvPPlayerNum || gameState == STATE.PvPP2Select || gameState == STATE.PvPP4Select || gameState == STATE.Help || gameState == STATE.Options) {
			menu.tick();
			handler.tick();
		}else if(!paused && gameState == STATE.PvPP4) {
			handler.tick();
			HUD.tick();
			spawner.tick();
			HUD2.tick();
			HUD3.tick();
			HUD4.tick();
			if(HUD.HEALTH >= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 1;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH >= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 2;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH >= 0 && HUD4.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 3;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0 && HUD3.HEALTH <= 0 && HUD4.HEALTH >= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 4;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD3.HEALTH = 0;
				HUD4.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			}
		} else if(!paused && gameState == STATE.GameP2) {
			handler.tick();
			HUD.tick();
			spawner.tick();
			HUD2.tick();
			if(HUD.HEALTH <= 0 && HUD2.HEALTH <= 0) {
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				gameState = STATE.End;
				handler.clearEnemys();
			}
		} else if(!paused && gameState == STATE.GameP1) {
			handler.tick();
			HUD.tick();
			spawner.tick();
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				gameState = STATE.End;
				handler.clearEnemys();
			}
		} else if(!paused && gameState == STATE.PvPP2) {
			handler.tick();
			HUD.tick();
			spawner.tick();
			HUD2.tick();
			if(HUD.HEALTH >= 0 && HUD2.HEALTH <= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 1;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				handler.clearEnemys();
			} else if(HUD.HEALTH <= 0 && HUD2.HEALTH >= 0) {
				gameState = STATE.EndPvP;
				Game.winner = 2;
				HUD.HEALTH = 0;
				HUD2.HEALTH = 0;
				HUD.setLevel(0.0f);
				HUD.setScore(0);
				gameState = STATE.EndPvP;
				handler.clearEnemys();
			}
			
		}
		

	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String agrs[]) {
		new Game();
	}
	
	
}
