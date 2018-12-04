package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public abstract class Button {

    protected final Sprite img;
    protected final Vector2 position;

    private boolean isVisible = false;
    private boolean isPressed = false;

    public Button(@NotNull final Sprite img, @NotNull final Vector2 position) {
        this.img = img;
        this.position = position;
    }

    public void setPosition(@NotNull final Vector2 newPosition) {
        this.position.set(newPosition);
    }

    public void draw(@NotNull final SpriteBatch batch) {
        img.drawAt(batch, position, 0);
    }

    public boolean isInside(float posX, float posY) {
        if (posX < position.x + img.getLeftShift() || posX > position.x + img.getRightShift()) return false;
        if (posY < position.y + img.getBottomShift() || posY > position.y + img.getTopShift()) return false;
        return true;
    }

    public void press() {
        isPressed = true;
        img.setScale(0.9f);
    }

    public boolean release(float posX, float posY) {
        if (isPressed) {
            isPressed = false;
            img.resetScale();
            if (isInside(posX, posY)) onClick();
            return true;
        }
        return false;
    }

    public abstract void onClick();

}
