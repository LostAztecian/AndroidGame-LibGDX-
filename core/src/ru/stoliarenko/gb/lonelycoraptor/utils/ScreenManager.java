package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.TestCorruptor;
import ru.stoliarenko.gb.lonelycoraptor.base.BaseScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.screen.LoadingScreen;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.screen.MenuScreen2D;

public final class ScreenManager {

    @Getter private final static ScreenManager instance = new ScreenManager();
    private ScreenManager(){}

    public enum Type {
        GAME, ANY_MENU, MAIN_MENU, PAUSE_MENU, GAME_OVER_SCREEN
    }

    private TestCorruptor game;
    private LoadingScreen loadingScreen;
    private BaseScreen2D targetScreen;
    private MainScreen2D gameScreen;
    private MenuScreen2D menuScreen;

    private SpriteBatch batch;

    public void init(@NotNull final TestCorruptor game, @NotNull final SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.gameScreen = new MainScreen2D(game, batch);
        this.menuScreen = new MenuScreen2D(game, batch);
        this.loadingScreen = new LoadingScreen(game, batch);
    }

    public void changeScreen(Type type) {
        Screen screen = game.getScreen();
        Assets.getInstance().clear();
        //clear resources here

        game.setScreen(loadingScreen);
        switch (type) {
            case GAME: {
                targetScreen = gameScreen;
                Assets.getInstance().loadAssets(Type.GAME);
                break;
            }
            case MAIN_MENU: {
                Assets.getInstance().loadAssets(Type.ANY_MENU);
                targetScreen = menuScreen;
                menuScreen.setTopText("");
                break;
            }
            case PAUSE_MENU: {
                Assets.getInstance().loadAssets(Type.ANY_MENU);
                targetScreen = menuScreen;
                menuScreen.setTopText("Paused");
                break;
            }
            case GAME_OVER_SCREEN: {
                Assets.getInstance().loadAssets(Type.ANY_MENU);
                targetScreen = menuScreen;
                menuScreen.setTopText("Game Over!");
                break;
            }
        }
    }

    public void goToTarget() {
        game.setScreen(targetScreen);
    }

}
