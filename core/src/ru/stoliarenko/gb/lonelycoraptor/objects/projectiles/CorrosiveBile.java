package ru.stoliarenko.gb.lonelycoraptor.objects.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;

/**
 * Basic projectile
 */
public class CorrosiveBile extends Projectile {
    private static final float SPEED = 750f;

    @NotNull private final Vector2 destination;
    @NotNull private final Vector2 route = new Vector2(0, 0);

    public CorrosiveBile(final @NotNull Vector2 currentPosition, final @NotNull Vector2 destinationPosition){
        super(new TextureRegion(new Texture("corrosiveBile_tiny_green.png")));
        this.destination = destinationPosition.sub(WIDTH/2, HEIGHT/2);
        this.position.set(currentPosition);
        this.angle = destinationPosition.cpy().sub(currentPosition).angle();
        this.scaleRate = 1.5f;
        this.visible = true;
    }

    private void checkDestination() {
        if (position.dst(destination) <= SPEED / 120)
            expire();
    }

    public void move(float dt) {
        route.set(destination);
        position.mulAdd(route.sub(position).nor(), SPEED * dt);
        checkDestination();
    }
}
