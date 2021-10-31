package com.indra.math;

public class GameDetailFirebase {
    public GameDetailFirebase() {
    }

    public long getStreak() {
        return streak;
    }

    public void setStreak(long streak) {
        this.streak = streak;
    }

    public long getBase_coin() {
        return base_coin;
    }

    public void setBase_coin(long base_coin) {
        this.base_coin = base_coin;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public GameDetailFirebase(long streak, long base_coin, long level) {
        this.streak = streak;
        this.base_coin = base_coin;
        this.level = level;
    }

    long streak;
    long base_coin;
    long level;

    public GameDetailFirebase(long streak, long base_coin, long level, long xp) {
        this.streak = streak;
        this.base_coin = base_coin;
        this.level = level;
        this.xp = xp;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    long xp;
}
