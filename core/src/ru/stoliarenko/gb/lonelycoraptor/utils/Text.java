package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import lombok.Setter;

public final class Text {

    @Setter private BitmapFont font;
    @Setter private Vector2 position;
    private String text;

    private float drawX;
    private float drawY;

    public Text(BitmapFont font, Vector2 position, String text) {
        this.font = font;
        this.position = position;
        this.text = text;

        drawX = position.x - (font.getXHeight() * text.length()) / 2 - font.getXHeight()/2;
        drawY = position.y + font.getCapHeight() / 2;
    }

    public void draw(@NotNull final SpriteBatch batch) {
        font.draw(batch, text, drawX, drawY);
    }

    public void setText(String text) {
        this.text = text;
        drawX = position.x - (font.getXHeight() * text.length()) / 2 - font.getXHeight()/2;
        drawY = position.y + font.getCapHeight() / 2;
    }

}
