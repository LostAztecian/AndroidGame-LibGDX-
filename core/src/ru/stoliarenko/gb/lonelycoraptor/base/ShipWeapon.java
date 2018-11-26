package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

public abstract class ShipWeapon {

    public abstract void shoot(@NotNull final Vector2 currentPosition, @NotNull final Vector2 destinationPosition);

}
