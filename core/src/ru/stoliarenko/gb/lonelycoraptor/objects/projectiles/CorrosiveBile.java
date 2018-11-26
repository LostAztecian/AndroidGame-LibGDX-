package ru.stoliarenko.gb.lonelycoraptor.objects.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;

/**
 * Basic projectile
 */
public class CorrosiveBile extends Projectile {
    private static final Texture TEXTURE = new Texture("corrosiveBile_tiny_green.png");
    private static final int WIDTH = 10;
    private static final int HEIGHT = 8;
    private final TextureRegion sprite = new Sprite(TEXTURE);
    private static final float SPEED = 750f;

    @NotNull private Vector2 currentPosition;
    @NotNull private final Vector2 destination;
    @NotNull private final Vector2 route = new Vector2(0, 0);
    private final float angle;

    public CorrosiveBile(final @NotNull Vector2 currentPosition, final @NotNull Vector2 destinationPosition){
        this.destination = destinationPosition.sub(WIDTH/2, HEIGHT/2);
        this.currentPosition = currentPosition;
        this.angle = destinationPosition.cpy().sub(currentPosition.cpy()).angle();
    }

    public void draw(final @NotNull SpriteBatch batch){
        batch.draw(sprite, currentPosition.x, currentPosition.y, WIDTH/2, HEIGHT/2, WIDTH, HEIGHT, 1.0f, 1.5f, angle - 90);
        checkDestination();
    }

    private void checkDestination() {
        if (currentPosition.dst(destination) <= SPEED / 120)
            expire();
    }

    public void move(float dt) {
        route.set(destination);
        currentPosition.mulAdd(route.sub(currentPosition).nor(), SPEED * dt);
    }
}
