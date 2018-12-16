package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

public final class Sprite implements Cloneable{

    private TextureRegion img;

    private final Vector2 centerPosition;
    private final float halfWidth;
    private final float halfHeight;
    private final float radius;
    private final float baseScale;
    @Getter private float scale;

    public Sprite(@NotNull final TextureRegion img, float baseScale) {
        this.img = img;
        this.baseScale = baseScale;
        this.scale = baseScale;
        this.halfWidth = img.getRegionWidth() / 2;
        this.halfHeight = img.getRegionHeight() / 2;
        this.radius = (float)Math.sqrt(halfHeight*halfHeight + halfWidth*halfWidth);
        this.centerPosition = new Vector2(img.getRegionWidth()/2, img.getRegionHeight()/2);
    }

    public Sprite(@NotNull final Sprite that) {
        this.img = that.img;
        this.baseScale = that.baseScale;
        this.scale = that.scale;
        this.halfWidth = that.halfWidth;
        this.halfHeight = that.halfHeight;
        this.radius = that.radius;
        this.centerPosition = that.centerPosition;
    }

    public void setImg(@NotNull final TextureRegion newImg) {
        if (img.getRegionWidth() != newImg.getRegionWidth()) return;
        if (img.getRegionHeight() != newImg.getRegionHeight()) return;
        this.img = newImg;
    }

    public void drawAt(@NotNull final SpriteBatch batch, @NotNull final Vector2 position, float angle) {
        batch.draw(img, position.x - centerPosition.x, position.y - centerPosition.y, centerPosition.x, centerPosition.y, img.getRegionWidth(), img.getRegionHeight(), scale, scale, angle);
    }
    public void drawAt(@NotNull final SpriteBatch batch, @NotNull final Vector2 position, float widthPercent, float heightPercent) {
        batch.draw(img, position.x - centerPosition.x, position.y - centerPosition.y, centerPosition.x, centerPosition.y, img.getRegionWidth()*widthPercent, img.getRegionHeight()*heightPercent, scale, scale, 0);
    }

    public float getLeftShift() {
        return -halfWidth * scale;
    }

    public float getRightShift() {
        return +halfWidth * scale;
    }

    public float getTopShift() {
        return +halfHeight * scale;
    }

    public float getBottomShift() {
        return -halfHeight * scale;
    }

    public float getRadius() {
        return radius * scale;
    }

    public void setScale(float scale) {
        this.scale = scale * baseScale;
    }

    public void changeScale(float ds) { this.scale += ds; }

    public void resetScale() {
        this.scale = baseScale;
    }

}
