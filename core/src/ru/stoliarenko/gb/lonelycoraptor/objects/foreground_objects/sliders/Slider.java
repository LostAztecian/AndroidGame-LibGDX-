package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.sliders;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class Slider {

    private final Sprite img;
    private final Sprite circle;
    private final Vector2 basePosition;
    private final Vector2 currentPosition = new Vector2();
    private final Vector2 shift = new Vector2(0, 0);
    private final Vector2 temp = new Vector2();
    private final float r2;

    private boolean isPressed = false;
    private int finger = -1;

    public Slider(@NotNull final Vector2 basePosition) {
        this.img = new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("slider"), 1f);
        this.circle = new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("sliderCircle"), 1f);
        circle.setScale(1.5f);
        r2 = img.getRightShift() * img.getRightShift();
        this.basePosition = basePosition;
        this.currentPosition.set(basePosition);
    }

    public void draw(@NotNull final SpriteBatch batch) {
        circle.drawAt(batch, basePosition, 0);
        img.drawAt(batch, currentPosition, 0);

    }

    public Vector2 getShift() {
        return temp.set(shift).scl(1f/img.getRightShift());
    }

    public boolean isInside(float posX, float posY) {
        temp.set(posX, posY).sub(basePosition);
        return (temp.len2() <= r2);
    }

    public void press(int finger) {
        if (isPressed) return;
        this.isPressed = true;
        this.finger = finger;
    }

    public void release(int finger) {
        if (!isPressed || finger != this.finger) return;
        this.isPressed = false;
        this.finger = -1;
        currentPosition.set(basePosition);
        shift.setZero();
    }

    public void drag(float posX, float posY, int finger) {
        if (!isPressed || finger != this.finger) return;
        if (basePosition.dst(posX, posY) <= img.getRightShift()) {
            currentPosition.set(posX, posY);
            shift.set(currentPosition).sub(basePosition);
            return;
        }
        shift.set(posX, posY).sub(basePosition).nor().scl(img.getRightShift());
        currentPosition.set(basePosition).add(shift);
    }

}
