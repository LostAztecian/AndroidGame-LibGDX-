package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor;
import ru.stoliarenko.gb.lonelycoraptor.emitters.ConsumableEmitter;
import ru.stoliarenko.gb.lonelycoraptor.emitters.EnemyEmitter;
import ru.stoliarenko.gb.lonelycoraptor.emitters.ExplosionEmitter;
import ru.stoliarenko.gb.lonelycoraptor.emitters.ProjectileEmitter;
import ru.stoliarenko.gb.lonelycoraptor.objects.Background;
import ru.stoliarenko.gb.lonelycoraptor.objects.UserInterface;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.FireButton;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.sliders.Slider;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Text;

/**
 * Game main screen
 *
 * Responsible for:
 * 1. User interaction processing,
 * 2. Containing all game objects
 *
 * @author Stoliarenko Alexander
 */
public final class GameScreen extends BaseScreen2D {

    //background
    private final Background background;
    private final BitmapFont font;
    private final Text topText;

    //payload
    @Getter private final Corruptor player;

    @Getter private ConsumableEmitter consumableEmitter;
    @Getter private ProjectileEmitter projectileEmitter;
    @Getter private EnemyEmitter enemyEmitter;
    @Getter private ExplosionEmitter explosionEmitter;

    //foreground
    private UserInterface ui;
    private final Slider slider;
    private final FireButton fireButton;

    public GameScreen(@NotNull final SpaceSurvivor game, @NotNull final SpriteBatch batch) {
        super(game, batch);
        background = Assets.getInstance().getBackground();
        font = Assets.getInstance().getAssetManager().get("mainFont96.ttf");
        topText = new Text(font, new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight() -120), "Paused");

        music = Assets.getInstance().getLvl01Music();
        music.setVolume(0.5f);
        music.setLooping(true);

        player = new Corruptor(this);

        slider = new Slider(new Vector2(120, 120));
        fireButton = new FireButton(this, new Vector2(ScreenParameters.myScreen.getWidth() - 120, 120));

        consumableEmitter = new ConsumableEmitter(this);
        projectileEmitter = new ProjectileEmitter(this);
        enemyEmitter = new EnemyEmitter(this);
        explosionEmitter = new ExplosionEmitter(this);

        ui = new UserInterface(this);
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
        drawAll();
        batch.end();
        updateAll(delta);
    }

    private void updateAll(float dt) {
        background.update(dt);
        if (isPaused) return;

        consumableEmitter.move(dt);
        projectileEmitter.move(dt);
        enemyEmitter.move(dt);
        explosionEmitter.move(dt);

        player.setAcceleration(slider.getShift());
        player.move(dt);
    }

    private void drawAll() {
        background.draw(batch);

        consumableEmitter.draw(batch);
        projectileEmitter.draw(batch);
        enemyEmitter.draw(batch);
        explosionEmitter.draw(batch);

        player.draw(batch);

        slider.draw(batch);
        fireButton.draw(batch);
        ui.draw(batch);

        if (isPaused) topText.draw(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }



    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        currentScreenToMyScreen.getScale(touch).scl(screenX, screenY);
        float x = touch.x;
        float y = ScreenParameters.myScreen.getHeight() - touch.y;
        if (slider.isInside(x, y)) {
            slider.press(pointer);
            return true;
        }
        if (fireButton.isInside(x, y)) {
            fireButton.press(pointer);
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        currentScreenToMyScreen.getScale(touch).scl(screenX, screenY);
        float x = touch.x;
        float y = ScreenParameters.myScreen.getHeight() - touch.y;
        slider.release(pointer);
        fireButton.release(pointer);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        super.touchDragged(screenX, screenY, pointer);
        currentScreenToMyScreen.getScale(touch).scl(screenX, screenY);
        float x = touch.x;
        float y = ScreenParameters.myScreen.getHeight() - touch.y;

        slider.drag(x, y, pointer);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        switch (keycode) {
            case Input.Keys.UP: {
                player.accelerateUp();
                break;
            }
            case Input.Keys.DOWN: {
                player.accelerateDown();
                break;
            }
            case Input.Keys.LEFT: {
                player.accelerateLeft();
                break;
            }
            case Input.Keys.RIGHT: {
                player.accelerateRight();
                break;
            }
            case Input.Keys.P: {
                isPaused = !isPaused;
            }

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        super.keyUp(keycode);
        switch (keycode) {
            case Input.Keys.UP: {
                player.accelerateDown();
                break;
            }
            case Input.Keys.DOWN: {
                player.accelerateUp();
                break;
            }
            case Input.Keys.LEFT: {
                player.accelerateRight();
                break;
            }
            case Input.Keys.RIGHT: {
                player.accelerateLeft();
            }
        }
        return false;
    }
}
