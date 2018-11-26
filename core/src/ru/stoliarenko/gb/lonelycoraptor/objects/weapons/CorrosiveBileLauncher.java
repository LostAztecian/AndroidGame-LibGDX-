package ru.stoliarenko.gb.lonelycoraptor.objects.weapons;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.objects.projectiles.CorrosiveBile;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;

public class CorrosiveBileLauncher extends ShipWeapon {

    @Override
    public void shoot(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition) {
        MainScreen2D.addSpaceObject(new CorrosiveBile(currentPosition, destinationPosition));
    }

}
