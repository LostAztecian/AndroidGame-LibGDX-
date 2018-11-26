package ru.stoliarenko.gb.lonelycoraptor.objects.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;

/**
 * Basic projectile
 */
public class CorrosiveBile extends Projectile {
    private static final Texture TEXTURE = new Texture("corrosiveBile_tiny.png");
    private final Sprite sprite = new Sprite(TEXTURE);
    private static final float SPEED = 750f;

    @NotNull private Vector2 currentPosition;
    @NotNull private final Vector2 destination;

    public CorrosiveBile(final @NotNull Vector2 currentPosition, final @NotNull Vector2 destinationPosition){
        this.destination = destinationPosition.cpy();
        this.currentPosition = currentPosition.cpy();
    }

    public void draw(final @NotNull SpriteBatch batch){
        sprite.setOriginBasedPosition(currentPosition.x, currentPosition.y);
        sprite.draw(batch);
        checkDestination();
    }

    private void checkDestination() {
        if (destination.cpy().sub(currentPosition).len() <= SPEED / 120)
            expire();
    }

    public void move(float dt) {
        final Vector2 route = destination.cpy().sub(currentPosition);
        currentPosition.add(route.cpy().nor().scl(SPEED * dt));
    }
}
