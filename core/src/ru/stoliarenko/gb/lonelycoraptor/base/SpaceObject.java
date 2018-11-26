package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.jetbrains.annotations.NotNull;

/**
 * Everything drawable
 */
public abstract class SpaceObject {

    public enum Type {
        SHIP,
        PROJECTILE,
        EXPLOSION;
    }

    private Type type;
    private boolean expired = false;

    protected SpaceObject(Type type){
        this.type = type;
    }

    protected void expire(){
        expired = true;
    }

    public boolean isExpired() {
        return expired;
    }

    public Type getType(){
        return this.type;
    }

    public abstract void draw(final @NotNull SpriteBatch batch);
    public abstract void move(float dt);
}
