package ru.stoliarenko.gb.lonelycoraptor.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;

public class CorrosiveBile extends Projectile {
    private static final Texture TEXTURE = new Texture("corrosiveBile_tiny.png");
    private final Sprite sprite = new Sprite(TEXTURE);
    private static final float SPEED = 15f;

    private Vector2 currentPosition;
    private final Vector2 destination;

    public CorrosiveBile(final Vector2 currentPosition, int destX, int destY){
        this.destination = new Vector2(destX, destY);
        this.currentPosition = currentPosition.cpy();
    }

    public void draw(SpriteBatch batch){
        System.out.println("draw call");
        sprite.setOriginBasedPosition(currentPosition.x, currentPosition.y);
        sprite.draw(batch);
        move();
        checkDestination();
    }

    private void checkDestination() {
        if (destination.cpy().sub(currentPosition).len() <= SPEED / 2)
            Corruptor.getCorruptor().removeProjectile(this);
    }

    private void move() {
        final Vector2 route = destination.cpy().sub(currentPosition);
        currentPosition.add(route.cpy().nor().scl(SPEED));
    }
}
