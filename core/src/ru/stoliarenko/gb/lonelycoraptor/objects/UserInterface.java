package ru.stoliarenko.gb.lonelycoraptor.objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class UserInterface {

    private final GameScreen gs;

    private Sprite hpBorder;
    private Vector2 hpBorderPosition;
    private Sprite hpBar;
    private Vector2 hpBarPosition;

    private BitmapFont font;

    public UserInterface(@NotNull final GameScreen gs) {
        this.gs = gs;

        hpBorder = new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("hpBorder"), 0.75f);
        hpBar = new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("hpBar"), 0.75f);
        hpBorderPosition = new Vector2(10 + hpBorder.getRightShift(), ScreenParameters.myScreen.getHeight() + hpBorder.getBottomShift() - 10);
        hpBarPosition = new Vector2();
        hpBarPosition.set(hpBorderPosition).add(25, -10);

        font = Assets.getInstance().getAssetManager().get("mainFont32.ttf");
    }

    public void draw(SpriteBatch batch) {
        hpBar.drawAt(batch, hpBarPosition, (float)gs.getPlayer().getHp()/gs.getPlayer().getMaxHp(), 1);
        hpBorder.drawAt(batch, hpBorderPosition, 1, 1);

        String text = String.format("Score: %06d", gs.getPlayer().getScore());
        font.draw(batch, text, ScreenParameters.myScreen.getWidth() - 60 - text.length()*font.getXHeight(), ScreenParameters.myScreen.getHeight() - 20);
    }

}
