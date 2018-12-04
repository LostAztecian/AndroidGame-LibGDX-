package ru.stoliarenko.gb.lonelycoraptor.base;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public abstract class Consumable extends SpaceObject {

    public enum Type{
        COIN,
        ROCK;
    }

    private Type type;

    protected Consumable(@NotNull final Type type, @NotNull final Sprite img) {
        super(SpaceObject.Type.CONSUMABLE, img);
        this.type = type;
    }

    public Type getConsumableType(){
        return this.type;
    }

    public abstract void destroy();

}
