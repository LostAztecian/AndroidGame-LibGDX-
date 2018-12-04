package ru.stoliarenko.gb.lonelycoraptor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.screen.MenuScreen2D;

public class TestCorruptor extends Game {

    private SpriteBatch batch;
    private MainScreen2D mainScreen;
    private MenuScreen2D menuScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainScreen2D(batch));
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}
