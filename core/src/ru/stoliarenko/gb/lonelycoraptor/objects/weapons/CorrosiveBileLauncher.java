package ru.stoliarenko.gb.lonelycoraptor.objects.weapons;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.utils.Emitters;

public class CorrosiveBileLauncher extends ShipWeapon {

    {
        isChargeable = true;
        chargeRate = 1f / 3;
        fireRate = 2;
    }

    @Override
    public void shoot(@NotNull Vector2 currentPosition, @NotNull Vector2 direction) {
        if (checkCooldown()) return;
        this.lastShotTime = System.currentTimeMillis();
        Emitters.corrosiveBiles.spawn(currentPosition, direction, charge);
        charge = 0;
    }

}
