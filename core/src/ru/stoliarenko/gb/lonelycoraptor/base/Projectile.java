package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.jetbrains.annotations.NotNull;

/**
 * Short-living space objects, which may interact with {@code = Ship}
 */
public abstract class Projectile extends SpaceObject{

    protected Projectile(@NotNull final TextureRegion texture){
        super(Type.PROJECTILE, texture);
    }

}
