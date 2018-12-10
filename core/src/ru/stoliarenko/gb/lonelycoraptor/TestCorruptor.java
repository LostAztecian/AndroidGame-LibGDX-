package ru.stoliarenko.gb.lonelycoraptor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.screen.MenuScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;

public class TestCorruptor extends Game {

    private SpriteBatch batch;
    private MainScreen2D mainScreen;
    private MenuScreen2D menuScreen;

    @Override
    public void create() {
        Assets.getInstance().loadAssets();
        batch = new SpriteBatch();
        menuScreen = new MenuScreen2D(this, batch);
        setScreen(menuScreen);
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

    public void newGame() {
        mainScreen = new MainScreen2D(this, batch);
        setScreen(mainScreen);
    }

    public void resumeGame() {
        setScreen(mainScreen);
    }

    public void mainMenu() {
        setScreen(menuScreen);
    }

}
