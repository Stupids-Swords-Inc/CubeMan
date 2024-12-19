package com.kagaries.entity;

import com.kagaries.main.Game;
import com.kagaries.ui.hud.HUD;
import com.kagaries.ui.hud.HudInterface;

import java.awt.*;

public enum ID {
	
	Player(Color.GREEN, Game.hud),
	Player2(Color.BLUE, Game.hud2),
	Player3(Color.WHITE, Game.hud3),
	Player4(Color.RED, Game.hud4),
	GrazeBox(),
	BasicEnemy(35, 1),
	SlowEnemy(51, 0),
	HardEnemy(37, 1),
	LunaiticEnemy(51, 1),
	FastEnemy(29, 2),
	Friend(50, 0),
	ShooterEnemy(50, 0),
	SmartEnemy(35, 2),
	EnemyBoss(100, 5),
	MenuParticle(),
	EnemyBoss2(100, 5),
	Trail();

	private final Color color;
	private final HudInterface hud;
	private final float damage;
	private final int graze;

	ID() {
		this.color = Color.WHITE;
		this.hud = null;
		this.damage = 0;
		this.graze = 0;
	}

	ID(Color color) {
		this.color = color;
		this.hud = null;
		this.damage = 0;
		this.graze = 0;
	}

	ID(Color color, HudInterface hud) {
		this.color = color;
		this.hud = hud;
		this.damage = 0;
		this.graze = 0;
	}

	ID(float damage, int graze) {
		this.damage = damage;
		this.graze = graze;
		this.hud = null;
		this.color = null;
	}

	public Color getColor() {
		return this.color;
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
