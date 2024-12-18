package com.tutorial.main;

import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r = new Random();
	
	static int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(Game.gameState == STATE.GameP1 || Game.gameState == STATE.GameP2 || Game.gameState == STATE.GameP4) {
			
		
		if(scoreKeep >= 150) {
			scoreKeep = 0;
			HUD.setLevel(hud.getLevel() + 0.5f);

			
			
			
			if(game.diff == 0) 
			{
				if(hud.getLevel() == 1.0) {
					handler.addObject(new SlowEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SlowEnemy, handler));
					handler.addObject(new SlowEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SlowEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new RandomEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				
				if(hud.getLevel() == 3.0) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				
				if(hud.getLevel() == 5.5) {
					handler.allowRevive();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				}
				
				if(hud.getLevel() == 7.5) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
				}
				
				if(hud.getLevel() == 8.5) {
					handler.clearEnemys();
					handler.allowRevive();
					handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));
					
				}
				
				if(hud.getLevel() == 12.0) {
					handler.clearEnemys();
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2-34, 5, ID.BasicEnemy, handler));
				}
				
				if(hud.getLevel() == 14.5) {
					handler.clearEnemys();
					handler.allowRevive();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
				}
				
				if(hud.getLevel() == 16.5) {
					handler.clearEnemys();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				}
				if(hud.getLevel() == 18.5) {
					handler.clearEnemys();
					handler.allowRevive();
					handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
				}
				if(hud.getLevel() == 25) {
					handler.clearEnemys();
					handler.allowRevive();
				}
				if(hud.getLevel() == 25.5) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
				}
			}else if(game.diff == 1) {
				if(hud.getLevel() == 1.5) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				}
				
				if(hud.getLevel() == 4.0) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new ShooterEnemy(r.nextInt(Game.WIDTH - 50), 5, ID.BasicEnemy, handler));
				}
				
				if(hud.getLevel() == 5.5) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				}
				
				if(hud.getLevel() == 7.5) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
				}
				
				if(hud.getLevel() == 8.5) {
					handler.clearEnemys();
					handler.allowRevive();
					handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));
					
				}
				
				if(hud.getLevel() == 12.0) {
					handler.clearEnemys();
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2-34, 5, ID.BasicEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				}
				
				if(hud.getLevel() == 14.5) {
					handler.clearEnemys();
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				}
				
				if(hud.getLevel() == 16.5) {
					handler.clearEnemys();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				}
				if(hud.getLevel() == 18.5) {
					handler.clearEnemys();
					handler.allowRevive();
					handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
				}
				if(hud.getLevel() == 25) {
					handler.clearEnemys();
					Game.gameState = STATE.End;
				}
			}else if(game.diff == 2) {
				if(hud.getLevel() == 1.5) {
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
				}
				
				if(hud.getLevel() == 4.0) {
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					
				}
				
				if(hud.getLevel() == 5.5) {
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				}
				
				if(hud.getLevel() == 7.5) {
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticEnemy, handler));
				}
				
				if(hud.getLevel() == 8.5) {
					handler.clearEnemys();
					handler.addObject(new LunaiticBoss(Game.WIDTH / 2, -96, ID.LunaiticEnemy, handler));
					
				}
				
				if(hud.getLevel() == 12.0) {
					handler.clearEnemys();
					handler.allowRevive();
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
				}
				
				if(hud.getLevel() == 14.5) {
					handler.clearEnemys();
					handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
				}
				
				if(hud.getLevel() == 16.5) {
					handler.clearEnemys();
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
				}
				if(hud.getLevel() == 18.5) {
					handler.clearEnemys();
					handler.addObject(new LunaiticBoss2(Game.WIDTH / 2, -96, ID.LunaiticEnemy, handler));
				}
				if(hud.getLevel() == 25) {
					handler.clearEnemys();
					Game.gameState = STATE.End;
				}
			
		
	}
		
	
	}
	
			}
		else if(Game.gameState == STATE.PvPP2) {
			if(scoreKeep >= 150) {
				scoreKeep = 0;
				HUD.setLevel(hud.getLevel() + 0.5f);
				if(game.diff == 0) 
				{
					if(hud.getLevel() == 1.5) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 4.0) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 5.5) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					
					if(hud.getLevel() == 7.5) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));
						
					}
					
					if(hud.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2-34, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					if(hud.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
					}
					if(hud.getLevel() == 25) {
						handler.clearEnemys();
					}
					if(hud.getLevel() == 25.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
				}else if(game.diff == 1) {
					if(hud.getLevel() == 1.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					
					if(hud.getLevel() == 4.0) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					
					if(hud.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					
					if(hud.getLevel() == 7.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));
						
					}
					
					if(hud.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2-34, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					
					if(hud.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if(hud.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
					}
					if(hud.getLevel() == 25) {
						handler.clearEnemys();
					}
				}else if(game.diff == 2) {
					if(hud.getLevel() == 1.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 4.0) {
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						
					}
					
					if(hud.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 7.5) {
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss(Game.WIDTH / 2, -96, ID.LunaiticEnemy, handler));
						
					}
					
					if(hud.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if(hud.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss2(Game.WIDTH / 2, -96, ID.LunaiticEnemy, handler));
					}
					if(hud.getLevel() == 25) {
						handler.clearEnemys();
					}
				}
			}
			
			}
		else if(Game.gameState == STATE.PvPP4) {
			if(scoreKeep >= 150) {
				scoreKeep = 0;
				HUD.setLevel(hud.getLevel() + 0.5f);
				if(game.diff == 0) 
				{
					if(hud.getLevel() == 1.5) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 4.0) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 5.5) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					
					if(hud.getLevel() == 7.5) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));
						
					}
					
					if(hud.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2-34, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					if(hud.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
					}
					if(hud.getLevel() == 25) {
						handler.clearEnemys();
					}
					if(hud.getLevel() == 25.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
				}else if(game.diff == 1) {
					if(hud.getLevel() == 1.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					
					if(hud.getLevel() == 4.0) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					
					if(hud.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}
					
					if(hud.getLevel() == 7.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));
						
					}
					
					if(hud.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2-34, 5, ID.BasicEnemy, handler));
					}
					
					if(hud.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					
					if(hud.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if(hud.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
					}
					if(hud.getLevel() == 25) {
						handler.clearEnemys();
					}
				}else if(game.diff == 2) {
					if(hud.getLevel() == 1.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 4.0) {
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						
					}
					
					if(hud.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 7.5) {
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss(Game.WIDTH / 2, -96, ID.LunaiticEnemy, handler));
						
					}
					
					if(hud.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2-34, 5, ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}
					
					if(hud.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.BasicEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if(hud.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss2(Game.WIDTH / 2, -96, ID.LunaiticEnemy, handler));
					}
					if(hud.getLevel() == 25) {
						handler.clearEnemys();
					}
				}
			}
		}
		}
	}
