package com.kagaries.entity.trail;

import com.kagaries.entity.ID;
import com.kagaries.main.Game;
import com.kagaries.main.Handler;

import java.awt.*;

public class CircleTrail extends Trail {
    public CircleTrail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id, color, width, height, life, handler);
    }

    public void render(Graphics g) {
        if (Game.showTrail) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(makeTransparent(alpha));
            g.setColor(color);
            g.fillOval((int)x, (int)y, width, height);
            g2d.setComposite(makeTransparent(1));
        }
    }
}
