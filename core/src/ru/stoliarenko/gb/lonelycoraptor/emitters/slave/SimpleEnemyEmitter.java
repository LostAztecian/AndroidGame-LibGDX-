package ru.stoliarenko.gb.lonelycoraptor.emitters.slave;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy;

public final class SimpleEnemyEmitter extends ObjectPool<SimpleEnemy> {

    private float spawnDelay = 0;

    @Override
    protected SimpleEnemy newObject() {
        return new SimpleEnemy();
    }

    public SimpleEnemyEmitter() {
        addObjectsToFreeList(10);
    }

    public void spawn() {
        getActiveElement().init();
    }

    @Override
    public void move(float dt) {
        super.move(dt);
        if (activeList.size() < 2) spawnWithDelay(dt);
    }

    private void spawnWithDelay(float dt) {
        if (spawnDelay < 0) {
            spawn();
            spawnDelay = MathUtils.random(2f, 5f);
        } else spawnDelay -= dt;

    }

}
