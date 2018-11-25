package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.stoliarenko.gb.lonelycoraptor.base.BaseScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.objects.Corruptor;

public final class MainScreen2D extends BaseScreen2D {
    public static int currentWidth = 0;
    public static int currentHeight = 0;

    private Texture wallPaper;
    private TextureRegion wall;

    @Override
    public void show() {
        super.show();
        wallPaper = new Texture("wp_space.jpg");
        wall = new TextureRegion(wallPaper, 0, 0, 2048, 1024);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(wall, 0, 0);
        Corruptor.getCorruptor().draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        wallPaper.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        switch (keycode) {
            case 19: {
                Corruptor.getCorruptor().accelerateUp();
                break;
            }
            case 20: {
                Corruptor.getCorruptor().accelerateDown();
                break;
            }
            case 21: {
                Corruptor.getCorruptor().accelerateLeft();
                break;
            }
            case 22: {
                Corruptor.getCorruptor().accelerateRight();
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        Corruptor.getCorruptor().launchBile(screenX, currentHeight - screenY);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        super.keyUp(keycode);
        switch (keycode) {
            case 19: {
                Corruptor.getCorruptor().accelerateDown();
                break;
            }
            case 20: {
                Corruptor.getCorruptor().accelerateUp();
                break;
            }
            case 21: {
                Corruptor.getCorruptor().accelerateRight();
                break;
            }
            case 22: {
                Corruptor.getCorruptor().accelerateLeft();
            }
        }
        return false;
    }
}
