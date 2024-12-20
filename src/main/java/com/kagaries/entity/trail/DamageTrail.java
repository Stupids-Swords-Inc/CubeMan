package com.kagaries.entity.trail;

import com.kagaries.entity.GameObject;
import com.kagaries.entity.ID;
import com.kagaries.main.Handler;
import com.kagaries.player.entity.Player;

import java.awt.*;

public class DamageTrail extends Trail {

    public final GameObject object;

    public DamageTrail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler, GameObject object) {
        super(x, y, id, color, width, height, life, handler);

        this.object = object;
    }

    public void tick() {
        super.tick();
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
