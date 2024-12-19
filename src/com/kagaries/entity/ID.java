package com.kagaries.entity;

import com.kagaries.main.Game;
import com.kagaries.ui.hud.HUD;
import com.kagaries.ui.hud.HudInterface;

import java.awt.*;

public enum ID {
	//Players
	Player(Color.GREEN, Game.hud),
	Player2(Color.BLUE, Game.hud2),
	Player3(Color.WHITE, Game.hud3),
	Player4(Color.RED, Game.hud4),
	//Graze
	GrazeBox(),
	//Enemy
	BasicEnemy(Color.RED, 35, 1),
	RandomEnemy(Color.RED, 25, 1),
	SlowEnemy(Color.PINK, 51, 0),
	HardEnemy(Color.ORANGE,37, 1),
	LunaiticEnemy(Color.YELLOW, 51, 2),
	LunaiticFastEnemy(Color.YELLOW, 41, 3, Color.CYAN),
	LunaiticShooterEnemy(Color.LIGHT_GRAY, 75, 0),
	FastEnemy(Color.CYAN,29, 2),
	Friend(Color.GREEN,50, 0),
	ShooterEnemy(Color.GRAY,50, 0),
	SmartEnemy(Color.RED, 35, 2),
	//Boss
	EnemyBoss(Color.YELLOW, 100, 5),
	EnemyBoss2(Color.ORANGE, 100, 5),
	LunaiticBoss(Color.GREEN, 100, 7),
	LunaiticBoss2(Color.MAGENTA, 100, 8),
	//Misc
	MenuParticle(),
	Trail();

	private final Color color;
	private final HudInterface hud;
	private final float damage;
	private final int graze;
	private final Color trailColor;

	ID() {
		this.color = Color.WHITE;
		this.trailColor = Color.WHITE;
		this.hud = null;
		this.damage = 0;
		this.graze = 0;
	}

	ID(Color color) {
		this.color = color;
		this.trailColor = Color.WHITE;
		this.hud = null;
		this.damage = 0;
		this.graze = 0;
	}

	ID(Color color, HudInterface hud) {
		this.color = color;
		this.trailColor = Color.WHITE;
		this.hud = hud;
		this.damage = 0;
		this.graze = 0;
	}

	ID(Color color, float damage, int graze) {
		this.damage = damage;
		this.graze = graze;
		this.hud = null;
		this.color = color;
		this.trailColor = Color.WHITE;
	}

	ID(Color color, float damage, int graze, Color trailColor) {
		this.damage = damage;
		this.trailColor = trailColor;
		this.graze = graze;
		this.hud = null;
		this.color = color;
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
}
