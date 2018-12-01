package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.SpaceObject;

public abstract class BackgroundObject extends SpaceObject {

    protected BackgroundObject(@NotNull final TextureRegion texture) {
        super(Type.BACKGROUND, texture);
    }

}
