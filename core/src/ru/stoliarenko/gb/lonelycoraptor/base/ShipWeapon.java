package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

public abstract class ShipWeapon {

    protected long lastShotTime = 0;
    protected float fireRate;

    protected ShipWeapon() {
        this.fireRate = 1f;
    }

    protected ShipWeapon(float fireRate) {
        this.fireRate = fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public abstract void shoot(@NotNull final Vector2 currentPosition, @NotNull final Vector2 destinationPosition);

    protected boolean checkCooldown() {
        final long thisShotTime = System.currentTimeMillis();
        return (1000 / fireRate > thisShotTime - lastShotTime);
    }
}
