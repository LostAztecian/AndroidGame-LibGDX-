package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Basic projectile
 */
public class CorrosiveBile extends Projectile {

    @NotNull private final Vector2 destination;

    public CorrosiveBile(final @NotNull Vector2 currentPosition, final @NotNull Vector2 destinationPosition){
        super(new Sprite(Atlases.space.findRegion("corrosiveBile"), 1f));
        this.destination = destinationPosition.sub(WIDTH/2, HEIGHT/2);
        this.position.set(currentPosition);
        this.angle = temp.set(destinationPosition).sub(currentPosition).angle();
        this.visible = true;
    }

    @Override
    protected void checkDestination() {
        if (position.dst(destination) <= SPEED / 120)
            expire();
    }

    public void move(float dt) {
        temp.set(destination);
        position.mulAdd(temp.sub(position).nor(), SPEED * dt);
        checkDestination();
    }
}
