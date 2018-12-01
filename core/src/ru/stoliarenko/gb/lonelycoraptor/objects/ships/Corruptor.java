package ru.stoliarenko.gb.lonelycoraptor.objects.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.stoliarenko.gb.lonelycoraptor.base.Consumable;
import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.base.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.base.SpaceObject;
import ru.stoliarenko.gb.lonelycoraptor.objects.weapons.CorrosiveBileLauncher;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;


/**
 * Main ship
 */
public final class Corruptor extends Ship {

    @Nullable private static Corruptor instance;

    private float ACCELERATION = 300;
    private float ROTATION = 200;
    private float SPEED_DECAY = 1f;
    private float horizontalAcceleration = 0;
    private float verticalAcceleration = 0;

    @NotNull private ShipWeapon weapon = new CorrosiveBileLauncher();

    private Corruptor(int posX, int posY) {
        super(new TextureRegion(new Texture("corruptor_small.png")));
        position.set(posX, posY);
        visible = true;
    }

    public static Corruptor getCorruptor(){
        if (instance == null) {
            final int posX = (int)(ScreenParameters.myScreen.getWidth() / 2);
            final int posY = (int)(ScreenParameters.myScreen.getHeight() / 2);
            instance = new Corruptor(posX, posY);
        }
        return instance;
    }

    @Override
    public void move(float dt){
        checkCollision();
        position.mulAdd(velocity, dt);
        if (velocity.len() >= ACCELERATION/60) rotateTo(velocity.angle(), dt);
        accelerate(dt);
        slowDown(dt);
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

    private void accelerate(float dt){
        velocity.add(horizontalAcceleration*dt, verticalAcceleration*dt);
    }

    private void slowDown(float dt){
        velocity.scl(1f - SPEED_DECAY*dt);
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
        if ( (position.x < 0 && velocity.x < 0) || (position.x > ScreenParameters.myScreen.getWidth() - WIDTH && velocity.x > 0) )
            velocity.scl(-0.35f, 0.6f);

        if ( (position.y < 0 && velocity.y < 0) || (position.y > ScreenParameters.myScreen.getHeight() - HEIGHT && velocity.y > 0) )
            velocity.scl(0.6f, -0.35f);
    }

    public void shoot(int posX, int posY) {
        weapon.shoot(position.cpy().add(WIDTH/2 -4, HEIGHT/2 -1), new Vector2(posX, posY));
    }

    public void collide(SpaceObject object) {
        switch (object.getType()) {
            case CONSUMABLE: {
                consume((Consumable) object);
                break;
            }
        }
    }

    private void consume(Consumable consumable) {
        switch (consumable.getConsumableType()) {
            case COIN: {
                consumable.destroy();
            }
        }
    }

}
