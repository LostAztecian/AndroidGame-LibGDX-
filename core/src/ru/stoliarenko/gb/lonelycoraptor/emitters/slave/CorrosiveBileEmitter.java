package ru.stoliarenko.gb.lonelycoraptor.emitters.slave;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles.CorrosiveBile;

public final class CorrosiveBileEmitter extends ObjectPool<CorrosiveBile> {

    @Override
    protected CorrosiveBile newObject() {
        return new CorrosiveBile();
    }

    public CorrosiveBileEmitter() {
        addObjectsToFreeList(10);
    }

    public void spawn(final @NotNull Vector2 currentPosition, final @NotNull Vector2 direction, float charge) {
        getActiveElement().init(currentPosition, direction, charge);
    }

}
