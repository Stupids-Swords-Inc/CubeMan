package com.kagaries.audio;

public enum AudioRegistry {
    //Player
    PLAYER_HURT("player/hitHurt.wav", 0.75f, -5.0f),
    //Menu
    MENU_SELECT("menu/blipSelect.wav", 0.7f, -15.0f);

    private final String path;
    private final Float cutOff;
    private final Float gain;

    AudioRegistry(String path, Float cutOff, Float gain) {
        this.path = "assets/sounds/" + path;
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
