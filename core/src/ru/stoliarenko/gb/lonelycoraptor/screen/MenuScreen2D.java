package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.stoliarenko.gb.lonelycoraptor.TestCorruptor;
import ru.stoliarenko.gb.lonelycoraptor.base.BaseScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.base.Button;
import ru.stoliarenko.gb.lonelycoraptor.objects.Background;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.ButtonNewGame;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.ButtonX;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Text;

public final class MenuScreen2D extends BaseScreen2D {

    private final BitmapFont font;
    private final Background background;
    private final List<Button> buttons;

    private final Text topText;

    public MenuScreen2D(@NotNull final TestCorruptor game, @NotNull final SpriteBatch batch) {
        super(game, batch);
        background = Assets.getInstance().getBackground();

        font = Assets.getInstance().getAssetManager().get("mainFont96.ttf");

        music = Assets.getInstance().getMenuMusic();
        music.setLooping(true);
        music.setVolume(0.3f);

        buttons = new CopyOnWriteArrayList<>();
        buttons.add(new ButtonX(game, new Vector2(ScreenParameters.myScreen.getWidth() - 45, ScreenParameters.myScreen.getHeight() - 45)));
        buttons.add(new ButtonNewGame(game, (new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight()/2))));

        topText = new Text(font , new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight() - 120), "");
    }

    public MenuScreen2D(@NotNull final TestCorruptor game, @NotNull final SpriteBatch batch, @NotNull final String text) {
        this(game, batch);
        setTopText(text);
    }

    public void setTopText(@NotNull final String text) {
        topText.setText(text);
    }

    @Override
    public void show() {
        super.show();
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        background.update(delta);
        drawAll();
        batch.end();
    }

    private void drawAll() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(batch);
        }
        topText.draw(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        currentScreenToMyScreen.getScale(touch).scl(screenX, screenY);
        float x = touch.x;
        float y = ScreenParameters.myScreen.getHeight() - touch.y;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isInside(x, y)) {
                buttons.get(i).press(pointer);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        currentScreenToMyScreen.getScale(touch).scl(screenX, screenY);
        float x = touch.x;
        float y = ScreenParameters.myScreen.getHeight() - touch.y;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).release(x, y, pointer)) return true;
        }
        return false;
    }

}
