package com.kagaries.player.entity;

import com.kagaries.entity.ID;
import com.kagaries.main.Handler;

import java.awt.*;

public class CirclePlayer extends Player {
    public CirclePlayer(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        float alpha = 0.5f;

        if (this.shouldRenderPlayer) {
            g2d.fillOval((int)x + 10, (int)y + 10, 12, 12);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(this.color);
            g2d.fillOval((int)x, (int)y, 32, 32);
        }
    }
}
