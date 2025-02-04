package com.kagaries.entity.enemy;

import com.kagaries.entity.ID;
import com.kagaries.entity.trail.CircleTrail;
import com.kagaries.entity.trail.Trail;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;

import java.awt.*;
import java.util.Objects;

public class CustomEnemy extends Enemy {
    private int sizeX;
    private int sizeY;

    private int hbSizeX;
    private int hbSizeY;

    private Color color;
    private Color colorT;

    private int damage;
    private int graze;

    private String shape;

    private float tLife;
    private int tSizeX;
    private int tSizeY;

    public CustomEnemy(int x, int y, Handler handler, float timeToSpawn, int sizeX, int sizeY, int hbSizeX, int hbSizeY, int r, int g, int b, int damage, int graze, String shape, int velX, int velY, int rT, int gT, int bT, float tLife, int tSizeX, int tSizeY) {
        super(x, y, ID.CustomEnemy, handler, timeToSpawn);

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.hbSizeX = hbSizeX;
        this.hbSizeY = hbSizeY;

        this.color = new Color(r, g, b);

        this.damage = damage;
        this.graze = graze;

        this.shape = shape;

        this.velX = velX;
        this.velY = velY;

        this.colorT = new Color(rT, gT, bT);

        this.tLife = tLife;

        this.tSizeX = tSizeX;
        this.tSizeY = tSizeY;
    }

    public CustomEnemy(int x, int y, ID id, Handler handler, float timeToSpawn) {
        super(x, y, id, handler, timeToSpawn);
    }

    public int getDamage() {
        return this.damage;
    }

    public int getGraze() {
        return this.graze;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, hbSizeX, hbSizeY);
    }

    public void tick() {
        if (enabled) {
            x += velX;
            y += velY;

            if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
            if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

            if (Objects.equals(shape, "cube")) {
                handler.addObject(new Trail(x, y, ID.Trail, this.colorT, tSizeX, tSizeY, tLife, handler));
            } else if (Objects.equals(shape, "circle")) {
                handler.addObject(new CircleTrail(x, y, ID.Trail, this.colorT, tSizeX, tSizeY, tLife, handler));
            } else {
                handler.addObject(new Trail(x, y, ID.Trail, this.colorT, tSizeX, tSizeY, tLife, handler));
            }
        }
    }

    @Override
    public void enableTick() {
        if (timeToSpawn <= 0 && !this.enabled) {
            this.enabled = true;
        } else {
            --timeToSpawn;
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (!enabled) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2d.setColor(this.color);

        if (Objects.equals(shape, "cube")) {
            g2d.fillRect((int)x, (int)y, sizeX, sizeY);
        } else if (Objects.equals(shape, "circle")) {
            g2d.fillOval((int)x, (int)y, sizeX, sizeY);
        } else {
            g2d.fillRect((int)x, (int)y, sizeX, sizeY);
        }
    }
}
