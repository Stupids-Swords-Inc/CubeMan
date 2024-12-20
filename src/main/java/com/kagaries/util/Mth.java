package com.kagaries.util;

public class Mth {
    public static int clamp(int p_14046_, int p_14047_, int p_14048_) {
        return Math.min(Math.max(p_14046_, p_14047_), p_14048_);
    }

    public static long clamp(long p_300696_, long p_298059_, long p_299237_) {
        return Math.min(Math.max(p_300696_, p_298059_), p_299237_);
    }

    public static float clamp(float p_14037_, float p_14038_, float p_14039_) {
        return p_14037_ < p_14038_ ? p_14038_ : Math.min(p_14037_, p_14039_);
    }

    public static double clamp(double p_14009_, double p_14010_, double p_14011_) {
        return p_14009_ < p_14010_ ? p_14010_ : Math.min(p_14009_, p_14011_);
    }

    public static int positiveModulo(int p_14101_, int p_14102_) {
        return Math.floorMod(p_14101_, p_14102_);
    }

    public static float positiveModulo(float p_14092_, float p_14093_) {
        return (p_14092_ % p_14093_ + p_14093_) % p_14093_;
    }

    public static double positiveModulo(double p_14110_, double p_14111_) {
        return (p_14110_ % p_14111_ + p_14111_) % p_14111_;
    }
}
