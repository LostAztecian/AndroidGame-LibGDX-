package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.jetbrains.annotations.NotNull;

public abstract class Consumable extends SpaceObject {

    public enum Type{
        COIN,
        ROCK;
    }

    private Type type;

    protected Consumable(@NotNull final Type type, @NotNull final TextureRegion texture) {
        super(SpaceObject.Type.CONSUMABLE, texture);
        this.type = type;
    }

    public Type getConsumableType(){
        return this.type;
    }

    public abstract void destroy();

}
