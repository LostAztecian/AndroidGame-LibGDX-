package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.emitters.slave.CoinEmitter;
import ru.stoliarenko.gb.lonelycoraptor.emitters.slave.CorrosiveBileEmitter;
import ru.stoliarenko.gb.lonelycoraptor.emitters.slave.LaserBulletEmitter;
import ru.stoliarenko.gb.lonelycoraptor.emitters.slave.SimpleEnemyEmitter;

public final class Emitters {
    private static List<ObjectPool> all = new ArrayList<>();

    public static LaserBulletEmitter laserBullets = new LaserBulletEmitter();
    public static CorrosiveBileEmitter corrosiveBiles = new CorrosiveBileEmitter();
    public static SimpleEnemyEmitter simpleEnemies = new SimpleEnemyEmitter();
    public static CoinEmitter coins = new CoinEmitter();

    static {
        all.add(laserBullets);
        all.add(corrosiveBiles);
        all.add(simpleEnemies);
        all.add(coins);
    }

    public static void drawAll(SpriteBatch batch) {
        for (int i = 0; i < all.size(); i++) {
            all.get(i).draw(batch);
        }
    }

    public static void moveAll(float dt) {
        for (int i = 0; i < all.size(); i++) {
            all.get(i).move(dt);
        }
    }

}
