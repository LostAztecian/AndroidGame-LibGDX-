package ru.stoliarenko.gb.lonelycoraptor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;
import ru.stoliarenko.gb.lonelycoraptor.screen.MenuScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenManager;

public class SpaceSurvivor extends Game {

    private SpriteBatch batch;
    private GameScreen mainScreen;
    private MenuScreen2D menuScreen;

    @Override
    public void create() {
        Assets.getInstance().loadAssets(ScreenManager.Type.GAME);
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
        mainScreen = new GameScreen(this, batch);
        setScreen(mainScreen);
    }

    public void resumeGame() {
        setScreen(mainScreen);
    }

    public void mainMenu(String primaryText, String secondaryText) {
        menuScreen.setPrimaryText(primaryText);
        menuScreen.setSecondaryText(secondaryText);
        setScreen(menuScreen);
    }

}
