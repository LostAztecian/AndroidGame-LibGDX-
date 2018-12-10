package ru.stoliarenko.gb.lonelycoraptor.objects.weapons;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.utils.Emitters;

public class LaserCannon extends ShipWeapon {

    {
        setFireRate(1.5f);
    }

    @Override
    public void shoot(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition) {
        if (checkCooldown()) return;
        this.lastShotTime = System.currentTimeMillis();
        Emitters.laserBullets.spawn(currentPosition, destinationPosition);
    }
}
