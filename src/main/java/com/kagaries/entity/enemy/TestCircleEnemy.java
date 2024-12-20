package com.kagaries.entity.enemy;

import com.kagaries.entity.GameObject;
import com.kagaries.entity.ID;
import com.kagaries.entity.trail.CircleTrail;
import com.kagaries.entity.trail.Trail;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;
import com.kagaries.player.entity.Player;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TestCircleEnemy extends Enemy {
    public boolean angered = false;

    public TestCircleEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);

        this.velX = 3;
        this.velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public Rectangle getAngerBounds() {
        return new Rectangle((int)x - 8, (int)y + 6, 48, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 50) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        collision();

        handler.addObject(new CircleTrail(x, y, ID.Trail, this.color, 32, 32, 0.15f, handler));
    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject instanceof Player) {
                if (getAngerBounds().intersects(tempObject.getBounds())) {
                    if (!this.angered) {
                        this.angered = true;
                        this.velX = 8;
                        this.velY = 6;
                    }
                }
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //g2d.fillRect((int)x - 8, (int)y + 6, 48, 16);

        if (!enabled) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2d.setColor(this.color);
        g2d.fillOval((int)x, (int)y, 32, 32);
    }

    @Override
    public void enableTick() {
        if (timeToSpawn <= 0 && !this.enabled) {
            this.enabled = true;
        } else {
            --timeToSpawn;
        }
    }
}
