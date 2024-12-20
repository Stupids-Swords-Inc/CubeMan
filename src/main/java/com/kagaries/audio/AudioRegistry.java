package com.kagaries.audio;

import com.kagaries.util.ByIdMap;

import java.util.HashMap;
import java.util.function.IntFunction;

public enum AudioRegistry {
    //Player
    PLAYER_HURT("assets/sounds/player/hitHurt.wav", 0.75f, -15.0f),
    //Menu
    MENU_SELECT("assets/sounds/menu/blipSelect.wav", 0.7f, -15.0f);

    private final String path;
    private final Float cutOff;
    private final Float gain;

    AudioRegistry(String path, Float cutOff, Float gain) {
        this.path = path;
        this.cutOff = cutOff;
        this.gain = gain;
    }

    public String getPath() {
        return this.path;
    }

    public Float getCutOff() {
        return this.cutOff;
    }

    public Float getGain() {
        return this.gain;
    }
}
