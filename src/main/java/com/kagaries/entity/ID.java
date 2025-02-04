package com.kagaries.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.kagaries.entity.enemy.*;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.ui.hud.HudInterface;
import com.kagaries.util.json.JsonReader;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public enum ID {
	//Players
	Player(Color.GREEN, Game.hud),
	Player2(Color.BLUE, Game.hud2),
	Player3(Color.WHITE, Game.hud3),
	Player4(Color.RED, Game.hud4),
	//Graze
	GrazeBox(),
	//Enemy
	BasicEnemy(com.kagaries.entity.enemy.BasicEnemy.class, Color.RED, 35, 1),
	FastEnemy(com.kagaries.entity.enemy.FastEnemy.class, Color.CYAN, 25, 2),
	BasicCircleEnemy(com.kagaries.entity.enemy.TestCircleEnemy.class, Color.LIGHT_GRAY, 25, 2),
	RandomEnemy(com.kagaries.entity.enemy.RandomEnemy.class, Color.RED, 25, 1),
	SlowEnemy(com.kagaries.entity.enemy.SlowEnemy.class, Color.PINK, 51, 0),
	HardEnemy(com.kagaries.entity.enemy.HardEnemy.class, Color.ORANGE,37, 1),
	LunaiticEnemy(com.kagaries.entity.enemy.LunaiticEnemy.class, Color.YELLOW, 51, 2),
	LunaiticFastEnemy(com.kagaries.entity.enemy.LunaiticFastEnemy.class, Color.YELLOW, 41, 3),
	LunaiticShooterEnemy(com.kagaries.entity.enemy.LunaiticShooterEnemy.class, Color.LIGHT_GRAY, 75, 0),
	Friend(com.kagaries.entity.Friend.class, Color.GREEN,50, 0),
	ShooterEnemy(com.kagaries.entity.enemy.ShooterEnemy.class, Color.GRAY,50, 0),
	SmartEnemy(com.kagaries.entity.enemy.BasicEnemy.class, Color.RED, 35, 2),
	//Boss
	EnemyBoss(com.kagaries.entity.enemy.EnemyBoss.class, Color.YELLOW, 100, 5),
	EnemyBoss2(com.kagaries.entity.enemy.EnemyBoss2.class, Color.ORANGE, 100, 5),
	LunaiticBoss(com.kagaries.entity.enemy.LunaiticBoss.class, Color.GREEN, 100, 7),
	LunaiticBoss2(com.kagaries.entity.enemy.LunaiticBoss2.class, Color.MAGENTA, 100, 8),
	//Misc
	MenuParticle(),
	Trail(),
	CustomEnemy(),
	DamageTrail(Color.WHITE, 8, 0);

	private final Class aClass;
	private final Color color;
	private final HudInterface hud;
	private final float damage;
	private final int graze;
	private final Color trailColor;

	ID() {
		this(Color.WHITE);
	}

	ID(Color color) {
		this(color, 0, 0);
	}

	ID(Color color, float damage, int graze) {
		this(color, damage, graze, Color.WHITE);
	}

	ID(Color color, float damage, int graze, Color trailColor) {
		this(Enemy.class, color, damage, graze, trailColor);
	}

	ID(Class enemyClass, Color color, int damage, int graze) {
		this(enemyClass, color, damage, graze, Color.WHITE);
	}

	ID(Class enemyClass, Color color, float damage, int graze, Color trailColor) {
		this.aClass = enemyClass;
		this.damage = damage;
		this.trailColor = trailColor;
		this.graze = graze;
		this.hud = null;
		this.color = color;
	}

	ID(Color color, HudInterface hud) {
		this.aClass = Enemy.class;
		this.color = color;
		this.trailColor = Color.WHITE;
		this.hud = hud;
		this.damage = 0;
		this.graze = 0;
	}

	public Color getColor() {
		return this.color;
	}

	public Color getTrailColor() {
		return this.trailColor;
	}

	public HudInterface getHud() {
		return this.hud;
	}

	public float getDamage() {
		return this.damage;
	}

	public int getGraze() {
		return this.graze;
	}

	public Class getaClass() {
		return this.aClass;
	}

	public static ID getFromName(String name) {
		try {
			return ID.valueOf(name);
		} catch (IllegalArgumentException illegalArgumentException) {
			Game.getLogger().info("Failed to find ID for {}, Skipping", name);
			return null;
		}
	}

	public static Object createInstanceFromName(String name, Object... args) {
		try {
			// Get the ID from the name
			ID id = ID.getFromName(name);

			// Check if the ID has an associated class
			if (id.getaClass() == null) {
				throw new IllegalArgumentException("ID '" + name + "' does not have an associated class.");
			}

			// Use reflection to find a matching constructor
			Class<?> clazz = id.getaClass();
			Constructor<?>[] constructors = clazz.getConstructors();

			for (Constructor<?> constructor : constructors) {
				Class<?>[] parameterTypes = constructor.getParameterTypes();

				// Check if the constructor parameters match the provided arguments
				if (parameterTypes.length == args.length) {
					return constructor.newInstance(args);
				}
			}

			throw new IllegalArgumentException("No suitable constructor found for ID '" + name + "' with provided arguments.");
		} catch (IllegalArgumentException e) {
			System.err.println("Invalid ID name: " + name + ". " + e.getMessage());
			return null;
		} catch (Exception e) {
			System.err.println("Error creating instance for ID '" + name + "': " + e.getMessage());
			return null;
		}
	}

	public static Object createInstanceFromJson(String name, int x, int y, Handler handler) {
		JsonNode json = JsonReader.readJson(name, "data/entity");

		int xSize = json.path("size").path("x").asInt();
		int ySize = json.path("size").path("y").asInt();

		int hbXSize = json.path("size").path("hitbox").path("x").asInt();
		int hbYSize = json.path("size").path("hitbox").path("y").asInt();

		int colorR = json.path("color").path("r").asInt();
		int colorG = json.path("color").path("g").asInt();
		int colorB = json.path("color").path("b").asInt();

		int damage = json.path("damage").asInt();
		int graze = json.path("graze").asInt();

		String shape = json.path("shape").asText();

		int velX = json.path("velocity").path("x").asInt();
		int velY = json.path("velocity").path("y").asInt();

		int rT = json.path("trail").path("color").path("r").asInt();
		int gT = json.path("trail").path("color").path("g").asInt();
		int bT = json.path("trail").path("color").path("b").asInt();

		double tLife = json.path("trail").path("life").asDouble();

		int tSizeX = json.path("trail").path("size").path("x").asInt();
		int tSizeY = json.path("trail").path("size").path("y").asInt();

		return new CustomEnemy(x, y, handler, (float) json.path("spawnTime").asDouble(), xSize, ySize, hbXSize, hbYSize, colorR, colorG, colorB, damage, graze, shape, velX, velY, rT, gT, bT, (float) tLife, tSizeX, tSizeY);
	}
}
