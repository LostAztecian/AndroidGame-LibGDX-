package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.jetbrains.annotations.NotNull;

/**
 * Space objects that may have weapons
 */
public abstract class Ship extends SpaceObject {

    protected Ship(@NotNull final TextureRegion texture){
        super(Type.SHIP, texture);
    }

}
