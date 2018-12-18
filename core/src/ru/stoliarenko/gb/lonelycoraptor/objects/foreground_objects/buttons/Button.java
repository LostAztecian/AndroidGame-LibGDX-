package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public abstract class Button {

    protected final ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game;
    private final Sprite img;
    private final Vector2 position;

    private BitmapFont font;
    private BitmapFont pressedFont;
    protected String text;

    private boolean isVisible = false;
    private boolean isPressed = false;
    private int finger = -1;

    public Button(@NotNull final ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull final Sprite img, @NotNull final Vector2 position) {
        this.img = img;
        this.position = position;
        this.game = game;
        font = Assets.getInstance().getAssetManager().get("mainFont32.ttf");
        pressedFont = Assets.getInstance().getAssetManager().get("mainFont28.ttf");
    }

    public void setPosition(@NotNull final Vector2 newPosition) {
        this.position.set(newPosition);
    }

    public void draw(@NotNull final SpriteBatch batch) {
        img.drawAt(batch, position, 0);
        if (isPressed) {
            pressedFont.draw(batch, text, position.x - (pressedFont.getScaleX() * pressedFont.getCapHeight() * text.length()) / 2, position.y + pressedFont.getCapHeight() / 2);
        } else font.draw(batch, text, position.x - (font.getScaleX() * font.getCapHeight() * text.length()) / 2, position.y + font.getCapHeight() / 2);
    }

    public void setFonts(@NotNull final BitmapFont font, @NotNull final BitmapFont pressedFont) {
        this.font = font;
        this.pressedFont = pressedFont;
    }

    public void setSizeScale(float scale) {
        img.setScale(scale);
    }

    public boolean isInside(float posX, float posY) {
        if (posX < position.x + img.getLeftShift() || posX > position.x + img.getRightShift()) return false;
        if (posY < position.y + img.getBottomShift() || posY > position.y + img.getTopShift()) return false;
        return true;
    }

    public void press(int finger) {
        if (isPressed) return;
        isPressed = true;
        this.finger = finger;
        img.setScale(0.9f);
    }

    public boolean release(float posX, float posY, int finger) {
        if (!isPressed || this.finger != finger) return false;
        isPressed = false;
        img.resetScale();
        if (isInside(posX, posY)) onClick();
        return true;
    }

    protected abstract void onClick();

}
