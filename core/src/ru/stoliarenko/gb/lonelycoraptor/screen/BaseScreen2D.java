package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;

public abstract class BaseScreen2D implements Screen, InputProcessor {

    @Getter protected final ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game;
    protected SpriteBatch batch;
    protected Vector2 touch = new Vector2();
    protected Matrix4 myScreenToGL = new Matrix4();
    protected Matrix3 currentScreenToMyScreen = new Matrix3();

    protected boolean isPaused;

    protected Music music;

    public BaseScreen2D(@NotNull final ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull final SpriteBatch batch) {
        this.batch = batch;
        this.game = game;
    }

    @Override
    public void show() {
//        System.out.println("Show");
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        ScreenParameters.currentScreen.setWidthAndHeight(width, height);
        ScreenParameters.myScreen.setHeightAndRatio(ScreenParameters.myScreen.getHeight(),  ScreenParameters.currentScreen.getRatio());
        ScreenParameters.calculateTranslationMatrix4(myScreenToGL, ScreenParameters.myScreen, ScreenParameters.GLScreen);
        batch.setProjectionMatrix(myScreenToGL);
        ScreenParameters.calculateTranslationMatrix3(currentScreenToMyScreen, ScreenParameters.currentScreen, ScreenParameters.myScreen);
//        System.out.println(String.format(Locale.getDefault(),"Resize to %dx%d", width, height));
    }

    @Override
    public void pause() {
//        System.out.println("pause");
        if (music != null) music.stop();
        isPaused = true;
    }

    @Override
    public void resume() {
//        System.out.println("resume");
        if (music != null) music.play();
        isPaused = false;
    }

    @Override
    public void hide() {
//        System.out.println("hide");
        pause();
    }

    @Override
    public void dispose() {
//        System.out.println("dispose");
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println(String.format(Locale.getDefault(), "Button pressed: %d", keycode));
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println(String.format(Locale.getDefault(), "Button released: %d", keycode));
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
//        System.out.println(String.format(Locale.getDefault(), "Button typed: %s", String.valueOf(character)));
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        System.out.println(String.format(Locale.getDefault(), "Touched at: %dx%d (pointer: %d, button %d)", screenX, screenY, pointer, button));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//        System.out.println(String.format(Locale.getDefault(), "Untouched at: %dx%d (pointer: %d, button %d)", screenX, screenY, pointer, button));
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
//        System.out.println(String.format(Locale.getDefault(), "Dragged at: %dx%d (pointer: %d)", screenX, screenY, pointer));
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
//        System.out.println(String.format(Locale.getDefault(), "Mouse moved at: %dx%d", screenX, screenY));
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println(String.format(Locale.getDefault(), "Scrolled %s.", amount == 1 ? "down" : "up"));
        return false;
    }
}
