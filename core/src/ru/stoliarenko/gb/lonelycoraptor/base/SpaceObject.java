package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

/**
 * Everything drawable
 */
public abstract class SpaceObject {

    public enum Type {
        SHIP,
        PROJECTILE,
        EXPLOSION,
        CONSUMABLE,
        BACKGROUND;
    }

    protected static final Texture texturePack = null;

    private Type type;
    private boolean expired = false;
    protected boolean visible = false;

    @NotNull protected TextureRegion texture;
    protected int WIDTH;
    protected int HEIGHT;
    protected float radius;

    @NotNull protected Vector2 position = new Vector2();
    @NotNull protected Vector2 velocity = new Vector2();
    protected float angle = 0;
    protected float scale = 1.0f;
    protected float scaleRate = 1.0f;

    protected SpaceObject(@NotNull final Type type, @NotNull final TextureRegion texture){
        this.type = type;
        this.texture = texture;
        this.WIDTH = texture.getRegionWidth();
        this.HEIGHT = texture.getRegionHeight();
        this.radius = (float)Math.sqrt((WIDTH*WIDTH + HEIGHT*HEIGHT))/2;
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

    public void draw(final @NotNull SpriteBatch batch) {
        if (visible)
            batch.draw(texture, position.x, position.y, WIDTH/2, HEIGHT/2, WIDTH, HEIGHT, scale*scaleRate, scale, angle);
    }
    public abstract void move(float dt);

    public boolean checkCollision(SpaceObject that){
        return (radius * scale + that.radius * that.scale)*0.65f > Vector2.dst(that.position.x + that.WIDTH/2, that.position.y + that.HEIGHT/2,
                position.x + WIDTH/2, this.position.y + HEIGHT/2);
    }

    public void dispose(){
//        texturePack.dispose();
    }
}
