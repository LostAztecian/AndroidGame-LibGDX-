package ru.stoliarenko.gb.lonelycoraptor.emitters.slave;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles.LaserBullet;

public final class LaserBulletEmitter extends ObjectPool<LaserBullet> {

    @Override
    protected LaserBullet newObject() {
        return new LaserBullet();
    }

    public LaserBulletEmitter() {
        addObjectsToFreeList(30);
    }

    public void spawn(final @NotNull Vector2 currentPosition, final @NotNull Vector2 destinationPosition) {
        getActiveElement().init(currentPosition, destinationPosition);
    }

}
