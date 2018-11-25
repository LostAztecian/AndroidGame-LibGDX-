package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Locale;

import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;

public abstract class BaseScreen2D implements Screen, InputProcessor {

    protected SpriteBatch batch;

    @Override
    public void show() {
        System.out.println("Show");
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        System.out.println(String.format(Locale.getDefault(),"Resize to %dx%d", width, height));
        MainScreen2D.currentWidth = width;
        MainScreen2D.currentHeight = height;
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
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
        System.out.println(String.format(Locale.getDefault(), "Touched at: %dx%d (pointer: %d, button %d)", screenX, screenY, pointer, button));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println(String.format(Locale.getDefault(), "Untouched at: %dx%d (pointer: %d, button %d)", screenX, screenY, pointer, button));
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
