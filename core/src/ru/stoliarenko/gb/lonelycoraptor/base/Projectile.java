package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Short-living space objects, which may interact with {@code = Ship}
 */
public abstract class Projectile extends SpaceObject{

    protected boolean isAllied = false; //TODO add friendly fire check
    protected float SPEED = 750f;
    protected Vector2 direction = new Vector2();

    protected Projectile(@NotNull final Sprite img){
        super(Type.PROJECTILE, img);
    }

    protected abstract void checkDestination();

}
