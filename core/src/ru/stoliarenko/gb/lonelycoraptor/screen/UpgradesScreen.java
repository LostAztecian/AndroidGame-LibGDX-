package ru.stoliarenko.gb.lonelycoraptor.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor;
import ru.stoliarenko.gb.lonelycoraptor.objects.Background;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.Button;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.ButtonResumeGame;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.upgrades.UpgradeCollisionDamage;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.upgrades.UpgradeDamage;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.upgrades.UpgradeHp;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.upgrades.UpgradeRotation;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.upgrades.UpgradeSpeed;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;
import ru.stoliarenko.gb.lonelycoraptor.utils.Text;

public final class UpgradesScreen extends ru.stoliarenko.gb.lonelycoraptor.screen.BaseScreen2D {

    private final ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen gs;
    private final BitmapFont bigFont;
    private final BitmapFont smallFont;
    private final BitmapFont buttonFont;
    private final BitmapFont buttonPressedFont;

    private final Background background;
    private final Sprite upgradesPanel;
    private final Vector2 upgradesPanelPosition;
    private final List<Button> buttons;
    private final List<Text> textList;
    private final int[] costs;
    private final int[] stages;

    public UpgradesScreen(@NotNull final SpaceSurvivor game, @NotNull final ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen gameScreen, @NotNull final SpriteBatch batch) {
        super(game, batch);
        gs = gameScreen;
        background = Assets.getInstance().getBackground();

        bigFont = Assets.getInstance().getAssetManager().get("mainFont96.ttf");
        smallFont = Assets.getInstance().getAssetManager().get("mainFont28.ttf");
        buttonFont = Assets.getInstance().getAssetManager().get("mainFont16.ttf");
        buttonPressedFont = Assets.getInstance().getAssetManager().get("mainFont14.ttf");

        music = Assets.getInstance().getMenuMusic();
        music.setLooping(true);
        music.setVolume(0.3f);

        upgradesPanel = new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("upgradesPanel"), 0.5f);
        upgradesPanelPosition = new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight()/2 - 50);

        buttons = new ArrayList<>();
        buttons.add(new ButtonResumeGame(game, new Vector2(ScreenParameters.myScreen.getWidth()/2, 40)));
        buttons.add(new UpgradeSpeed(game, new Vector2(ScreenParameters.myScreen.getWidth()/2 - 150, 315), this));
        buttons.add(new UpgradeRotation(game, new Vector2(ScreenParameters.myScreen.getWidth()/2 - 150, 225), this));
        buttons.add(new UpgradeHp(game, new Vector2(ScreenParameters.myScreen.getWidth()/2 - 150, 135), this));
        buttons.add(new UpgradeDamage(game, new Vector2(ScreenParameters.myScreen.getWidth()/2 + 150, 315), this));
        buttons.add(new UpgradeCollisionDamage(game, new Vector2(ScreenParameters.myScreen.getWidth()/2 + 150, 225), this));

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setFonts(buttonFont, buttonPressedFont);
        }

        textList = new ArrayList<>();
        textList.add(new Text(smallFont, new Vector2(ScreenParameters.myScreen.getWidth()/2 + 150, 120), "Coins: 00"));
        textList.add(new Text(buttonFont, new Vector2(ScreenParameters.myScreen.getWidth()/2 - 150, 280), "lvl: 0, cost: 100"));
        textList.add(new Text(buttonFont, new Vector2(ScreenParameters.myScreen.getWidth()/2 - 150, 190), "lvl: 0, cost: 100"));
        textList.add(new Text(buttonFont, new Vector2(ScreenParameters.myScreen.getWidth()/2 - 150, 100), "lvl: 0, cost: 100"));
        textList.add(new Text(buttonFont, new Vector2(ScreenParameters.myScreen.getWidth()/2 + 150, 280), "lvl: 0, cost: 100"));
        textList.add(new Text(buttonFont, new Vector2(ScreenParameters.myScreen.getWidth()/2 + 150, 190), "lvl: 0, cost: 100"));
        textList.add(new Text(bigFont, new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight() - 50), "Game Paused"));
        textList.add(new Text(smallFont, new Vector2(ScreenParameters.myScreen.getWidth()/2, ScreenParameters.myScreen.getHeight() - 130), "Ship upgrades"));

        costs = new int[buttons.size()];
        Arrays.fill(costs, 100);
        stages = new int[buttons.size()];
        Arrays.fill(stages, 0);

    }

    public boolean upgradeSpeed() {
        if (gs.getPlayer().getCoins() < costs[1]) return false;
        gs.getPlayer().setAcceleration(gs.getPlayer().getAcceleration()*1.1f);
        gs.getPlayer().changeCoins(-costs[1]);
        costs[1] *= 2;
        stages[1] += 1;
        textList.get(1).setText(String.format("lvl: %d, cost: %d", stages[1], costs[1]));
        updateCoins();
        return true;
    }
    public boolean upgradeRotation() {
        if (gs.getPlayer().getCoins() < costs[2]) return false;
        gs.getPlayer().setROTATION(gs.getPlayer().getROTATION()*1.1f);
        gs.getPlayer().changeCoins(-costs[2]);
        costs[2] *= 2;
        stages[2] += 1;
        textList.get(2).setText(String.format("lvl: %d, cost: %d", stages[2], costs[2]));
        updateCoins();
        return true;
    }
    public boolean upgradeHp() {
        if (gs.getPlayer().getCoins() < costs[3]) return false;
        gs.getPlayer().setMaxHp((int)(gs.getPlayer().getMaxHp()*1.1f));
        gs.getPlayer().changeCoins(-costs[3]);
        costs[3] *= 2;
        stages[3] += 1;
        textList.get(3).setText(String.format("lvl: %d, cost: %d", stages[3], costs[3]));
        updateCoins();
        return true;
    }
    public boolean upgradeDamage() {
        if (gs.getPlayer().getCoins() < costs[4]) return false;
        gs.getPlayer().setDamageMultiplier(gs.getPlayer().getDamageMultiplier()+0.2f);
        gs.getPlayer().changeCoins(-costs[4]);
        costs[4] *= 2;
        stages[4] += 1;
        textList.get(4).setText(String.format("lvl: %d, cost: %d", stages[4], costs[4]));
        updateCoins();
        return true;
    }
    public boolean upgradeCollisionDamage() {
        if (gs.getPlayer().getCoins() < costs[5]) return false;
        gs.getPlayer().setCollisionDamageMultiplier(gs.getPlayer().getCollisionDamageMultiplier()+0.2f);
        gs.getPlayer().changeCoins(-costs[5]);
        costs[5] *= 2;
        stages[5] += 1;
        textList.get(5).setText(String.format("lvl: %d, cost: %d", stages[5], costs[5]));
        updateCoins();
        return true;
    }

    private void updateCoins() {
        textList.get(0).setText("Coins: " + gs.getPlayer().getCoins());
    }


    @Override
    public void show() {
        super.show();
        music.play();
        updateCoins();
    }

    @Override
    public void resume() {
        super.resume();
        updateCoins();
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
        upgradesPanel.drawAt(batch, upgradesPanelPosition, 0);
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
