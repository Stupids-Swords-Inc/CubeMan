package com.kagaries.entity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
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

	private JsonNode spawnList;
	private JsonNode levelAmount;
	
	public static int scoreKeep = 0;
	
	public Spawn(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void setJson(String jsonName, String path) throws IOException {
		this.json = JsonReader.readJson(jsonName, path);
		this.spawnList = json.path("spawns");
		this.levelAmount = json.path("levels");

	}

	public void setJson(String jsonName) throws IOException {
		this.json = JsonReader.readJson(jsonName, "data/spawns");
		this.spawnList = json.path("spawns");
		this.levelAmount = json.path("levels");
	}
	
	public void tick() {
		scoreKeep++;

		if (Objects.equals(Game.gameState.getType().getTypeString(), "game")) {
			if (scoreKeep >= 150) {
				scoreKeep = 0;
				Handler.setLevel(handler.getLevel() + 0.5f);

					if (this.json.path("levels") != null) {
						if (handler.getLevel() == this.levelAmount.asDouble()) {
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
										File file = new File(String.format(Game.getResourceLoader().getResourcePath() + "/data/entity/%s.json", id));
										if (file.exists()) {
											if (positions != null && positions.has(String.valueOf(i + 1))) {
												JsonNode positionNode = positions.path(String.valueOf(i + 1));
												int x = positionNode.path("x").asInt();
												int y = positionNode.path("y").asInt();
												handler.addObjectFromStringJSON(id, x, y);
											} else {
												handler.addObjectFromStringJSON(id, r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50));
											}
										} else {
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
								}
							} else {
								Game.getLogger().error("ENEMY SPAWNS SHOULD BE AN ARRAY");
							}
						}
					}
			}
		}
	}
}
