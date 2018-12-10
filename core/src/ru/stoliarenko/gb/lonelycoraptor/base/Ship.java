package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.objects.weapons.CorrosiveBileLauncher;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Space objects that may have weapons
 */
public abstract class Ship extends SpaceObject {

    @NotNull protected ShipWeapon weapon = new CorrosiveBileLauncher();
    protected float ROTATION = 200;
    protected float hp; //TODO add hit points

    protected Ship(@NotNull final Sprite img){
        super(Type.SHIP, img);
    }

    protected void rotateTo(float angle, float dt){
        final float dif = Math.abs(angle - this.angle);
        if (dif < 3) return;
        if (dif > 180) {
            this.angle += ROTATION * (this.angle >= angle ? dt : -dt);
        } else
            this.angle -= ROTATION * (this.angle > angle ? dt : -dt);
        if (this.angle >= 360) this.angle -= 360;
        if (this.angle < 0) this.angle += 360;
    }

    @Override
    public void move(float dt) {
        position.add(velocity);
    }

    public void shoot(float posX, float posY) {
        weapon.shoot(getPosition(), new Vector2(posX, posY));
    }

    public void takeDamage(float damage) {
        hp -= damage; //TODO add armor
        if (hp < 0) destroy(); //TODO main ship cant expire
    }

    protected void chargeWeapon(float dCharge) {
        weapon.charge(dCharge);
    }

    protected abstract void destroy();

}
