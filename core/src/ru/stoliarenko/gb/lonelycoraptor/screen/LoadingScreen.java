package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor;
import ru.stoliarenko.gb.lonelycoraptor.objects.Background;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenManager;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class LoadingScreen extends BaseScreen2D {

    private Background background;

    private Sprite loadingBarBorder;
    private Sprite loadingBar;
    private Vector2 loadingBarPosition;

    public LoadingScreen(@NotNull final SpaceSurvivor game, @NotNull final SpriteBatch batch) {
        super(game, batch);
    }

    @Override
    public void show() {
        super.show();
        loadingBarBorder = new Sprite(Assets.getInstance().getLoadingScreenAtlas().findRegion("loadingBorder"), 1);
        loadingBar = new Sprite(Assets.getInstance().getLoadingScreenAtlas().findRegion("loadingBar"), 1);
        loadingBarPosition = new Vector2(ScreenParameters.myScreen.getWidth()/2, 100);
        background = Background.getInstance();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Assets.getInstance().getAssetManager().update()) {
            Assets.getInstance().makeLinks();
            ScreenManager.getInstance().goToTarget();
        }

        batch.begin();
        background.draw(batch);
        background.update(delta);
        loadingBar.drawAt(batch, loadingBarPosition, Assets.getInstance().getAssetManager().getProgress(), 1);
        loadingBarBorder.drawAt(batch, loadingBarPosition, 1, 1);
        batch.end();
    }
}
