package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.objects.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Space objects that may have weapons
 */
public abstract class Ship extends SpaceObject {

    @NotNull protected ShipWeapon weapon;

    protected Sprite regular;
    protected Sprite damaged;
    protected float ROTATION = 200;

    @Getter protected int hp = 100;
    @Getter protected int maxHp = 100;
    protected int scoreBounty = 10;

    protected boolean isDamaged = false;
    protected float damagedTimer = 0;
    protected MainScreen2D gs;

    @Getter private float damageMultiplier = 1;
    @Getter private float bulletSpeedMultiplier = 1;
    @Getter private float collisionDamageMultiplier = 1;

    protected Ship(@NotNull final Sprite img, @NotNull final MainScreen2D gs){
        super(Type.SHIP, img);
        this.gs = gs;
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
        weapon.shoot(this, getPosition(), new Vector2(posX, posY));
    }

    protected void checkDamagedTimer(float dt) {
        if (isDamaged) {
            damagedTimer -= dt;
            if (damagedTimer <= 0) {
                setImg(regular);
                isDamaged = false;
            }
        }
    }

    public void takeDamage(float damage) {
        if (isDamaged) return;
        isDamaged = true;
        setImg(damaged);
        damagedTimer = 0.25f;

        hp -= damage; //TODO add armor
        if (hp > 0) return;
        destroy();
        gs.getPlayer().getScore(scoreBounty);
    }

    protected void chargeWeapon(float dCharge) {
        weapon.charge(dCharge);
    }

    protected abstract void destroy();

}
