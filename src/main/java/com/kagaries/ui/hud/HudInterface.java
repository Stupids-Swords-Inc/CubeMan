package com.kagaries.ui.hud;

import java.awt.*;

public interface HudInterface {
    void tick();

    void render(Graphics g);

    void setHealth(float num);

    float getHealth();

    void setGraze(int num);

    int getGraze();
}
