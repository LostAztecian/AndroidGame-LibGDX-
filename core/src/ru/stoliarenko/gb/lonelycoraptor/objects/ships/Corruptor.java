package ru.stoliarenko.gb.lonelycoraptor.objects.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.base.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.objects.weapons.CorrosiveBileLauncher;


/**
 * Main ship
 */
public final class Corruptor extends Ship {

    @Nullable private static Corruptor instance;
    private static final Texture pic = new Texture("corruptor_small.png");
    private final TextureRegion sprite = new Sprite(pic);
    private static final int WIDTH = pic.getWidth();
    private static final int HEIGHT = pic.getHeight();

    private static final float ACCELERATION = 25;
    private static final float ROTATION = 200;
    private static final float SPEED_DECAY = 0.97f;
    private float horizontalAcceleration = 0;
    private float verticalAcceleration = 0;
    private float angle = 90;
    private float scale = 1.0f;

    private Vector2 velocity = new Vector2(0, 0);
    @NotNull private Vector2 position;

    @NotNull private ShipWeapon weapon = new CorrosiveBileLauncher();

    private Corruptor(int posX, int posY) {
        position = new Vector2(posX, posY);
    }

    public static Corruptor getCorruptor(){
        if (instance == null) {
            final int posX = (Gdx.graphics.getWidth() / 2);
            final int posY = (Gdx.graphics.getHeight() / 2);
            instance = new Corruptor(posX, posY);
        }
        return instance;
    }

    @Override
    public void draw(final @NotNull SpriteBatch batch){
        batch.draw(sprite, position.x, position.y, WIDTH/2, HEIGHT/2, WIDTH, HEIGHT, scale, scale, angle - 90);
    }

    @Override
    public void move(float dt){
        checkCollision();
        position.mulAdd(velocity, dt);
        if (velocity.len() >= ACCELERATION) rotateTo(velocity.angle(), dt);
        accelerate();
        slowDown();
    }

    private void rotateTo(float angle, float dt){
        final float dif = Math.abs(angle - this.angle);
        if (dif < 3) return;
        if (dif > 180) {
            this.angle += ROTATION * (this.angle >= angle ? dt : -dt);
        } else
            this.angle -= ROTATION * (this.angle > angle ? dt : -dt);
        if (this.angle >= 360) this.angle -= 360;
        if (this.angle < 0) this.angle += 360;
    }

    private void accelerate(){
        velocity.add(horizontalAcceleration, verticalAcceleration);
    }

    private void slowDown(){
        velocity.scl(SPEED_DECAY);
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
        if ( (position.x < 0 && velocity.x < 0) || (position.x > Gdx.graphics.getWidth() - WIDTH && velocity.x > 0) )
            velocity.scl(-0.35f, 0.6f);

        if ( (position.y < 0 && velocity.y < 0) || (position.y > Gdx.graphics.getHeight() - HEIGHT && velocity.y > 0) )
            velocity.scl(0.6f, -0.35f);
    }

    public void shoot(int posX, int posY) {
        weapon.shoot(position.cpy().add(WIDTH/2 -4, HEIGHT/2 +2), new Vector2(posX, posY));
    }

}
