package ru.stoliarenko.gb.lonelycoraptor.emitters.slave;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.consumables.Coin;

public final class CoinEmitter extends ObjectPool<Coin> {

    private float spawnTime;

    @Override
    protected Coin newObject() {
        return new Coin();
    }

    public CoinEmitter() {
        addObjectsToFreeList(20);
    }

    @Override
    public void move(float dt) {
        spawnTime -= dt;
        if (spawnTime < 0) spawn();
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).move(dt);
        }
        checkPool();
    }

    private void spawn() {
        spawnTime = MathUtils.random(3f, 7f);
        getActiveElement().init();
    }

}
