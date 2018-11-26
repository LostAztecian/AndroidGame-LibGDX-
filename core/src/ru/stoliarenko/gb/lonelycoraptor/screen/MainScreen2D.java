package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ru.stoliarenko.gb.lonelycoraptor.base.BaseScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.base.SpaceObject;
import ru.stoliarenko.gb.lonelycoraptor.objects.ships.Corruptor;

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

    private Texture wallPaper;
    private TextureRegion wall;

    private static Queue<SpaceObject> spaceObjects = new ConcurrentLinkedQueue<>();

    public static void addSpaceObject(@NotNull final SpaceObject object){
        spaceObjects.add(object);
    }

    @Override
    public void show() {
        super.show();
        wallPaper = new Texture("wp_space.jpg");
        wall = new TextureRegion(wallPaper, 0, 0, 2048, 1024);
        spaceObjects.add(Corruptor.getCorruptor());
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        removeExpired();
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(wall, 0, 0);
        drawAll();
        batch.end();
    }

    /**
     * RIP lambdas and stream API
     */
    public void update(float dt) {
        for (SpaceObject object : spaceObjects) {
            object.move(dt);
        }
    }

    public void drawAll() {
        for (SpaceObject object : spaceObjects) {
            object.draw(batch);
        }
    }

    public void removeExpired(){
        Iterator<SpaceObject> iterator = spaceObjects.iterator();
        while (iterator.hasNext()) {
            SpaceObject o = iterator.next();
            if (o.isExpired()) iterator.remove();
        }
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
        Corruptor.getCorruptor().shoot(screenX, Gdx.graphics.getHeight() - screenY);
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
