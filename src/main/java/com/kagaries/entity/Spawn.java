package com.kagaries.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.fasterxml.jackson.databind.JsonNode;
import com.kagaries.entity.enemy.*;
import com.kagaries.main.Game;
import com.kagaries.main.Game.STATE;
import com.kagaries.main.Handler;
import com.kagaries.util.json.JsonReader;

public class Spawn {
	
	private final Handler handler;
	private final Game game;
	private final Random r = new Random();
	private JsonNode json;
	
	public static int scoreKeep = 0;
	
	public Spawn(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void setJson(String jsonName, String path) throws IOException {
		this.json = JsonReader.readJson(jsonName, path);
	}

	public void setJson(String jsonName) throws IOException {
		this.json = JsonReader.readJson(jsonName, "data/spawns");
	}
	
	public void tick() {
		scoreKeep++;

		if (Game.gameState == STATE.GameP1 || Game.gameState == STATE.GameP2 || Game.gameState == STATE.GameP4) {
			if (scoreKeep >= 150) {
				JsonNode spawnList = json.path("spawns");
				scoreKeep = 0;
				Handler.setLevel(handler.getLevel() + 0.5f);

				if (game.diff == 0) {
					if (this.json.path("levels") != null) {
						if (String.valueOf(handler.getLevel()).equals(this.json.path("levels").asText())) {
							handler.clearEnemys();
							Game.gameState = STATE.End;
						}
					}

					if (spawnList.path(String.valueOf(Handler.level)) != null) {
						JsonNode array = spawnList.get(String.valueOf(Handler.level));
						if (array != null) {
							if (array.isArray()) {
								for (JsonNode element : array) {
									if (!element.has("id")) {
										Game.getLogger().error("ID IS REQUIRED IN AN ENEMY ELEMENT");
										Game.getLogger().error("EXAMPLE: \"id\": \"BasicCircleEnemy\"");
										Game.getLogger().error("FOR FURTHER DETAILS [LINK TO REFERENCE HERE]");
										break;
									}

									String id = element.path("id").asText();
									int amount = element.has("amount") ? element.path("amount").asInt() : 1;
									boolean clear = element.has("clear") && element.path("clear").asBoolean();
									boolean revive = element.has("revive") && element.path("revive").asBoolean();
									JsonNode positions = element.has("positions") ? element.path("positions") : null;

									if (clear) {
										handler.clearEnemys();
									}

									if (revive) {
										handler.allowRevive();
									}

									for (int i = 0; i < amount; i++) {
										if (positions != null && positions.has(String.valueOf(i + 1))) {
											JsonNode positionNode = positions.path(String.valueOf(i + 1));
											int x = positionNode.path("x").asInt();
											int y = positionNode.path("y").asInt();
											handler.addObjectFromStringID(id, x, y);
										} else {
											handler.addObjectFromStringID(id, r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50));
										}
									}
								}
							} else {
								Game.getLogger().error("ENEMY SPAWNS SHOULD BE AN ARRAY");
							}
						}
					}
				} else if (game.diff == 1) {
					if (handler.getLevel() == 1.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}

					if (handler.getLevel() == 4.0) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new ShooterEnemy(r.nextInt(Game.WIDTH - 50), 5, ID.ShooterEnemy, handler));
					}

					if (handler.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}

					if (handler.getLevel() == 7.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
					}

					if (handler.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.allowRevive();
						handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));

					}

					if (handler.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.ShooterEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}

					if (handler.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}

					if (handler.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if (handler.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.allowRevive();
						handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
					}
					if (handler.getLevel() == 25) {
						handler.clearEnemys();
						Game.gameState = STATE.End;
					}
				} else if (game.diff == 2) {
					if (handler.getLevel() == 1.5) {
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}

					if (handler.getLevel() == 4.0) {
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));

					}

					if (handler.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}

					if (handler.getLevel() == 7.5) {
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticShooterEnemy, handler));
					}

					if (handler.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss(Game.WIDTH / 2, -96, ID.LunaiticBoss, handler));

					}

					if (handler.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.allowRevive();
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}

					if (handler.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticShooterEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}

					if (handler.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if (handler.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss2(Game.WIDTH / 2, -96, ID.LunaiticBoss2, handler));
					}
					if (handler.getLevel() == 25) {
						handler.clearEnemys();
						Game.gameState = STATE.End;
					}


				}
			}

		} else if (Game.gameState == STATE.PvPP2 || Game.gameState == STATE.PvPP4) {
			if (scoreKeep >= 150) {
				scoreKeep = 0;
				Handler.setLevel(handler.getLevel() + 0.5f);
				if (game.diff == 0) {
					if (handler.getLevel() == 1.5) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}

					if (handler.getLevel() == 4.0) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}

					if (handler.getLevel() == 5.5) {
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}

					if (handler.getLevel() == 7.5) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
					}

					if (handler.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));

					}

					if (handler.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.ShooterEnemy, handler));
					}

					if (handler.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
					}

					if (handler.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					}
					if (handler.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
					}
					if (handler.getLevel() == 25) {
						handler.clearEnemys();
					}
					if (handler.getLevel() == 25.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
					}
				} else if (game.diff == 1) {
					if (handler.getLevel() == 1.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}

					if (handler.getLevel() == 4.0) {
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}

					if (handler.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
					}

					if (handler.getLevel() == 7.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
					}

					if (handler.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss(Game.WIDTH / 2, -96, ID.EnemyBoss, handler));

					}

					if (handler.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.ShooterEnemy, handler));
					}

					if (handler.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}

					if (handler.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if (handler.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new EnemyBoss2(Game.WIDTH / 2, -96, ID.EnemyBoss2, handler));
					}
					if (handler.getLevel() == 25) {
						handler.clearEnemys();
					}
				} else if (game.diff == 2) {
					if (handler.getLevel() == 1.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}

					if (handler.getLevel() == 4.0) {
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));

					}

					if (handler.getLevel() == 5.5) {
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
					}

					if (handler.getLevel() == 7.5) {
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticShooterEnemy, handler));
					}

					if (handler.getLevel() == 8.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss(Game.WIDTH / 2, -96, ID.LunaiticBoss, handler));

					}

					if (handler.getLevel() == 12.0) {
						handler.clearEnemys();
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}

					if (handler.getLevel() == 14.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticShooterEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(Game.WIDTH / 2 - 34, 5, ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticShooterEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticShooterEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
					}

					if (handler.getLevel() == 16.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new LunaiticEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticEnemy, handler));
						handler.addObject(new ShooterEnemy(Game.WIDTH / 2, 5, ID.ShooterEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new LunaiticFastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LunaiticFastEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
						handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
					}
					if (handler.getLevel() == 18.5) {
						handler.clearEnemys();
						handler.addObject(new LunaiticBoss2(Game.WIDTH / 2, -96, ID.LunaiticBoss2, handler));
					}
					if (handler.getLevel() == 25) {
						handler.clearEnemys();
					}
				}
			}
		}
	}
}
