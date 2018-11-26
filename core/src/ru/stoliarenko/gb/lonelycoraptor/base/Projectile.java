package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Short-living space objects, which may interact with {@code = Ship}
 */
public abstract class Projectile extends SpaceObject{

    protected Projectile(){
        super(Type.PROJECTILE);
    }

}
