package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.stoliarenko.gb.lonelycoraptor.objects.Background;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Text;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.Button;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.ButtonNewGame;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.ButtonX;

public final class MenuScreen2D extends ru.stoliarenko.gb.lonelycoraptor.screen.BaseScreen2D {

    private final BitmapFont bigFont;
    private final BitmapFont smallFont;
    private final Background background;
    private final List<Button> buttons;
    private final List<Text> textList;

    public MenuScreen2D(@NotNull final ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull final SpriteBatch batch) {
        super(game, batch);
        background = Assets.getInstance().getBackground();

        bigFont = Assets.getInstance().getAssetManager().get("mainFont96.ttf");
        smallFont = Assets.getInstance().getAssetManager().get("mainFont28.ttf");

        music = Assets.getInstance().getMenuMusic();
        music.setLooping(true);
        music.setVolume(0.3f);

        buttons = new CopyOnWriteArrayList<>();
        buttons.add(new ButtonX(game, new Vector2(ScreenParameters.myScreen.getWidth() - 45, ScreenParameters.myScreen.getHeight() - 45)));
        buttons.add(new ButtonNewGame(game, (new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight()/2))));

        textList = new ArrayList<>();
        textList.add(new Text(bigFont, new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight() - 120), ""));
        textList.add(new Text(smallFont, new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight() - 185), ""));
    }

    public void setPrimaryText(@NotNull final String text) {
        textList.get(0).setText(text);
    }
    public void setSecondaryText(@NotNull final String text) {
        textList.get(1).setText(text);
    }
    public void clearText() {
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).setText("");
        }
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
        for (int i = 0; i < textList.size(); i++) {
            textList.get(i).draw(batch);
        }
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
