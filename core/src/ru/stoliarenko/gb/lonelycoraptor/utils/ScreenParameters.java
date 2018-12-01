package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

import org.jetbrains.annotations.NotNull;

public final class ScreenParameters {
    private float posX;
    private float posY;
    private float height;
    private float width;
    private float ratio;

    public final static ScreenParameters GLScreen = new ScreenParameters(-1f, -1f, 2f, 2f);
    public final static ScreenParameters myScreen = new ScreenParameters(0, 0, 500, 900);
    public static final ScreenParameters currentScreen = new ScreenParameters(0, 0, 0, 0);

    public ScreenParameters(float posX, float posY, float height, float width) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.ratio = width/height;
    }

    public static void calculateTranslationMatrix4(@NotNull final Matrix4 matrix, @NotNull final ScreenParameters from, @NotNull final ScreenParameters to){
        final float scaleX = to.getWidth() / from.getWidth();
        final float scaleY = to.getHeight() / from.getHeight();
        matrix.idt().translate(to.getPosX()+to.getWidth()/2, to.getPosY()+to.getHeight()/2, 0f).scale(scaleX, scaleY, 1f).translate(-from.getPosX()-from.getWidth()/2, -from.getPosY()-from.getHeight()/2, 0f);
    }
    public static void calculateTranslationMatrix3(@NotNull final Matrix3 matrix, @NotNull final ScreenParameters from, @NotNull final ScreenParameters to){
        final float scaleX = to.getWidth() / from.getWidth();
        final float scaleY = to.getHeight() / from.getHeight();
        matrix.idt().translate(to.getPosX()+to.getWidth()/2, to.getPosY()+to.getHeight()/2).scale(scaleX, scaleY).translate(-from.getPosX()-from.getWidth()/2, -from.getPosY()-from.getHeight()/2);
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRatio() { return ratio; }

    public void setWidthAndHeight(float width, float height){
        this.width = width;
        this.height = height;
        this.ratio = width/height;
    }

    public void setWidthAndRatio(float width, float ratio){
        this.width = width;
        this.ratio = ratio;
        this.height = width / this.ratio;
    }

    public void setHeightAndRatio(float height, float ratio){
        this.height = height;
        this.ratio = ratio;
        this.width = height * this.ratio;
    }
}
