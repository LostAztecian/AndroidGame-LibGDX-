package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.stoliarenko.gb.lonelycoraptor.base.BaseScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.base.SpaceObject;
import ru.stoliarenko.gb.lonelycoraptor.objects.Background;
import ru.stoliarenko.gb.lonelycoraptor.objects.consumables.Coin;
import ru.stoliarenko.gb.lonelycoraptor.objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;

/**
 * Game main screen
 *
 * Responsible for:
 * 1. User interaction processing,
 * 2. Containing all game objects
 *
 * @author Stoliarenko Alexander
 */
public final class MainScreen2D extends BaseScreen2D {

    private Background background;
    private static List<SpaceObject> spaceObjects = new CopyOnWriteArrayList<>();

    public static void addSpaceObject(@NotNull final SpaceObject object){
        spaceObjects.add(object);
    }

    @Override
    public void show() {
        super.show();
        background = new Background();
        spaceObjects.add(Corruptor.getCorruptor());
        for (int i = 0; i < 5; i++) {
            spaceObjects.add(new Coin());
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        removeExpired();
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawAll();
        batch.end();
    }

    /**
     * RIP lambdas and stream API
     */
    private void update(float dt) {
        background.update(dt);
        for (int i = 0; i < spaceObjects.size(); i++) {
            spaceObjects.get(i).move(dt);
            checkCollisions();
        }
    }

    private void checkCollisions(){
        for (int i = 0; i < spaceObjects.size(); i++) {
            if (Corruptor.getCorruptor().checkCollision(spaceObjects.get(i))){
                Corruptor.getCorruptor().collide(spaceObjects.get(i));
            }
        }
    }

    private void drawAll() {
        background.draw(batch);
        for (int i = 0; i < spaceObjects.size(); i++) {
            spaceObjects.get(i).draw(batch);
        }
    }

    private void removeExpired(){
        for (int i = 0; i < spaceObjects.size(); i++) {
            if (spaceObjects.get(i).isExpired()) spaceObjects.remove(i);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
        for (int i = 0; i < spaceObjects.size(); i++) {
            spaceObjects.get(i).dispose();
        }
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
        currentScreenToMyScreen.getScale(touch).scl(screenX, screenY);
        Corruptor.getCorruptor().shoot((int)touch.x, (int)ScreenParameters.myScreen.getHeight() - (int)touch.y);
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
