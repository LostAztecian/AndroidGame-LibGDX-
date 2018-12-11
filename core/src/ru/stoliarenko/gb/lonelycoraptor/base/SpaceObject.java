package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Everything drawable
 */
public abstract class SpaceObject {

    public enum Type {
        SHIP,
        PROJECTILE,
        EXPLOSION,
        CONSUMABLE,
        BACKGROUND
    }

    private Type type;
    @Getter protected boolean visible = false;
    @Getter protected boolean active = false;

    @NotNull protected Sprite img;
    protected int WIDTH;
    protected int HEIGHT;

    @NotNull protected Vector2 position = new Vector2();
    @NotNull protected Vector2 velocity = new Vector2();
    protected float angle = 0;
    protected float scale = 1.0f;

    protected Vector2 temp = new Vector2();

    protected SpaceObject(@NotNull final Type type, @NotNull final Sprite img){
        this.type = type;
        this.img = img;
    }

    public void setImg(@NotNull final Sprite img) {
        this.img = new Sprite(img);
    }

    public Type getType(){
        return this.type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void draw(final @NotNull SpriteBatch batch) {
        if (visible) {
            img.drawAt(batch, position, angle);
            drawUpgrades();
        }
    }

    private void drawUpgrades(){}

    public abstract void move(float dt);

    protected boolean checkCollision(SpaceObject that){
        return (img.getRadius() + that.img.getRadius()) * 0.65 > position.dst(that.position);
    }

    protected boolean checkOuterSpace() {
        if (position.x + img.getRightShift() < -1) return true;
        if (position.x + img.getLeftShift() > ScreenParameters.myScreen.getWidth() +1) return true;
        if (position.y + img.getTopShift() < -1) return true;
        return position.y + img.getBottomShift() > ScreenParameters.myScreen.getHeight() +1;
    }

}
