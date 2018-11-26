package ru.stoliarenko.gb.lonelycoraptor.objects.ships;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.base.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.objects.weapons.CorrosiveBileLauncher;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;


/**
 * Main ship
 */
public final class Corruptor extends Ship {

    @Nullable private static Corruptor instance;
    private final Texture pic = new Texture("corruptor_small.png");
    private final Sprite sprite = new Sprite(pic);

    private static final float ACCELERATION = 25;
    private static final float SPEED_DECAY = 0.97f;
    private float horizontalAcceleration = 0;
    private float verticalAcceleration = 0;

    private Vector2 movement = new Vector2(0, 0);
    private static final Vector2 POSITION_DELTA = new Vector2(18, 22);
    @NotNull private Vector2 position;

    @NotNull private ShipWeapon weapon = new CorrosiveBileLauncher();

    private Corruptor(int posX, int posY) {
        position = new Vector2(posX, posY);
    }

    public static Corruptor getCorruptor(){
        if (instance == null) {
            final int posX = (MainScreen2D.currentWidth / 2);
            final int posY = (MainScreen2D.currentHeight / 2);
            instance = new Corruptor(posX, posY);
            instance.sprite.setOrigin(POSITION_DELTA.x, POSITION_DELTA.y);
        }
        return instance;
    }

    @Override
    public void draw(final @NotNull SpriteBatch batch){
        sprite.setOriginBasedPosition(position.x, position.y);
        sprite.draw(batch);
    }

    @Override
    public void move(float dt){
        position.add(movement.cpy().scl(dt));
        checkCollision();
        if (movement.len() >= ACCELERATION) sprite.setRotation(movement.angle() - 90);
        accelerate();
        slowDown();
    }

    private void accelerate(){
        movement.add(horizontalAcceleration, verticalAcceleration);
    }

    private void slowDown(){
        movement.scl(SPEED_DECAY);
    }

    public void accelerateUp(){
        verticalAcceleration += ACCELERATION;
    }
    public void accelerateDown(){
        verticalAcceleration -= ACCELERATION;
    }
    public void accelerateRight(){
        horizontalAcceleration += ACCELERATION;
    }
    public void accelerateLeft(){
        horizontalAcceleration -= ACCELERATION;
    }

    private void checkCollision(){
        if (position.x < POSITION_DELTA.x || position.x > MainScreen2D.currentWidth - POSITION_DELTA.x)
            movement.scl(-1.1f, 0.6f);
        if (position.y < POSITION_DELTA.y || position.y > MainScreen2D.currentHeight - POSITION_DELTA.y)
            movement.scl(0.6f, -1.1f);
    }

    public void shoot(int posX, int posY) {
        weapon.shoot(position, new Vector2(posX, posY));
    }

}
