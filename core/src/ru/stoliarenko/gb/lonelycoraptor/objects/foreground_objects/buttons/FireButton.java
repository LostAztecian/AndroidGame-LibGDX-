package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class FireButton {

    private final ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen gs;
    private final Sprite img;
    private final Sprite circle;
    private final Vector2 basePosition;
    private final Vector2 temp = new Vector2();
    private final float r2;

    private boolean isPressed = false;
    private int finger = -1;

    private long timer = 0;

    public FireButton(@NotNull final ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen gs, @NotNull final Vector2 basePosition) {
        this.gs = gs;
        this.img = new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("slider"), 1f);
        this.circle = new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("sliderCircle"), 1f);
        circle.setScale(1.5f);
        r2 = img.getRightShift() * img.getRightShift();
        this.basePosition = basePosition;
    }

    public void draw(@NotNull final SpriteBatch batch) {
        img.drawAt(batch, basePosition, 90);
        circle.drawAt(batch, basePosition, 0);
    }

    public boolean isInside(float posX, float posY) {
        temp.set(posX, posY).sub(basePosition);
        return (temp.len2() <= r2);
    }

    public void press(int finger) {
        if (isPressed) return;
        this.isPressed = true;
        this.finger = finger;
        gs.getPlayer().setWeaponCharging(true);
    }

    public void release(int finger) {
        if (!isPressed || finger != this.finger) return;
        this.isPressed = false;
        this.finger = -1;
        gs.getPlayer().shoot();
    }

}
