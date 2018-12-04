package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.stoliarenko.gb.lonelycoraptor.base.BaseScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.base.Button;
import ru.stoliarenko.gb.lonelycoraptor.objects.Background;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.ButtonNewGame;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.ButtonX;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;

public final class MenuScreen2D extends BaseScreen2D {

    private Background background;
    private final List<Button> buttons = new CopyOnWriteArrayList<>();

    public MenuScreen2D(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        super.show();
        Atlases.background = new TextureAtlas("spaceObjects.pack");
        Atlases.foreground = new TextureAtlas("buttons.pack");
        background = Background.getInstance();
        buttons.add(new ButtonX(new Vector2(ScreenParameters.myScreen.getWidth() - 45, ScreenParameters.myScreen.getHeight() - 45)));
        buttons.add(new ButtonNewGame((new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight()/2))));
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
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
        Atlases.disposeAll();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        currentScreenToMyScreen.getScale(touch).scl(screenX, screenY);
        float x = touch.x;
        float y = ScreenParameters.myScreen.getHeight() - touch.y;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isInside(x, y)) {
                buttons.get(i).press();
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
            if (buttons.get(i).release(x, y)) return true;
        }
        return false;
    }
}
