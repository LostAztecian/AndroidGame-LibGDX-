package ru.stoliarenko.gb.lonelycoraptor.objects;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import lombok.Setter;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Ship;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;

public class ShipWeapon {

    public enum Type {
        LASER_CANNON_GREEN(false, 1f, Projectile.Type.LASER_BULLET_GREEN),
        LASER_CANNON_RED(false, 1f, Projectile.Type.LASER_BULLET_RED),
        LASER_CANNON_BLUE(false, 1f, Projectile.Type.LASER_BULLET_BLUE),
        LASER_CANNON_PURPLE(false, 1f, Projectile.Type.LASER_BULLET_PURPLE),
        CORROSIVE_BILE_LAUNCHER(true, 1f, Projectile.Type.CORROSIVE_BILE);

        private boolean isChargeable;
        private float baseFirerate;
        private Projectile.Type bulletType;

        Type(boolean isChargeable, float baseFirerate, Projectile.Type bulletType) {
            this.isChargeable = isChargeable;
            this.baseFirerate = baseFirerate;
            this.bulletType = bulletType;
        }
    }

    private Type type;
    protected GameScreen gs;
    protected float charge = 0;


    protected long lastShotTime = 0;
    @Setter protected float fireRateScale;

    public ShipWeapon(@NotNull final GameScreen gs, @NotNull final Type type) {
        this.gs = gs;
        this.type = type;
        this.fireRateScale = 1f;
    }

    public void shoot(Ship owner, @NotNull final Vector2 currentPosition, @NotNull final Vector2 destinationPosition) {
        if (checkCooldown()) return;
        if (type.isChargeable) { destinationPosition.scl(50+250f*charge).add(currentPosition); }
        lastShotTime = System.currentTimeMillis();
        gs.getProjectileEmitter().spawn(type.bulletType, currentPosition, destinationPosition, owner);
        charge = 0;
    }

    protected boolean checkCooldown() {
        final long thisShotTime = System.currentTimeMillis();
        return (1000 / fireRateScale > thisShotTime - lastShotTime);
    }

    public void charge(float dCharge) {
        float before = charge;
        if (type.isChargeable) charge += dCharge * type.baseFirerate* fireRateScale;
        float after = charge;
        if (type.isChargeable) System.out.printf("Charging %f --> %f%n", before, after);
    }
}
