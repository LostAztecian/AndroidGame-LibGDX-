package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.screen.MenuScreen2D;

public final class ScreenManager {

    @Getter private final static ScreenManager instance = new ScreenManager();
    private ScreenManager(){}

    public enum Type {
        GAME, ANY_MENU, MAIN_MENU, PAUSE_MENU, GAME_OVER_SCREEN
    }

    private ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game;
    private ru.stoliarenko.gb.lonelycoraptor.screen.LoadingScreen loadingScreen;
    private ru.stoliarenko.gb.lonelycoraptor.screen.BaseScreen2D targetScreen;
    private ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen gameScreen;
    private MenuScreen2D menuScreen;

    private SpriteBatch batch;

    public void init(@NotNull final ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull final SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.gameScreen = new ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen(game, batch);
        this.menuScreen = new MenuScreen2D(game, batch);
        this.loadingScreen = new ru.stoliarenko.gb.lonelycoraptor.screen.LoadingScreen(game, batch);
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
                menuScreen.setPrimaryText("");
                break;
            }
            case PAUSE_MENU: {
                Assets.getInstance().loadAssets(Type.ANY_MENU);
                targetScreen = menuScreen;
                menuScreen.setPrimaryText("Paused");
                break;
            }
            case GAME_OVER_SCREEN: {
                Assets.getInstance().loadAssets(Type.ANY_MENU);
                targetScreen = menuScreen;
                menuScreen.setPrimaryText("Game Over!");
                break;
            }
        }
    }

    public void goToTarget() {
        game.setScreen(targetScreen);
    }

}
