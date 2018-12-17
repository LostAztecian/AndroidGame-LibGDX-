package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ru.stoliarenko.gb.lonelycoraptor.emitters.pool.Poolable;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Ship;
import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class Consumable extends SpaceObject implements Poolable {

    public enum Type{
        COIN(),
        STAR(),
        HEAL(),
        X5();

        private Sound sound;
        Type() { this.sound = Assets.getInstance().getAssetManager().get("sounds/consumable.mp3", Sound.class); }
    }

    private final GameScreen gs;
    private final Sprite[] imgs;
    private Type type;

    public Consumable(@NotNull final GameScreen gs, @NotNull final Sprite[] imgs) {
        super(SpaceObject.Type.CONSUMABLE, imgs[0]);
        this.gs = gs;
        this.imgs = imgs;

    }

    public void destroy() {
        active = false;
    }

    @Override
    public void move(float dt) {
        position.add(velocity);
        if (position.x + img.getRightShift() < 0) destroy();
        if (checkCollision(gs.getPlayer())) {
            destroy();
            giveBonus();
        }
    }

    private void giveBonus() {
        type.sound.play(0.05f);
        switch (type) {
            case COIN: {
                gs.getPlayer().getScore((int)(100 * img.getScale()));
                break;
            }
            case HEAL: {
                gs.getPlayer().heal(30);
                break;
            }
            case X5: {
                for (int i = 0; i < 5; i++) {
                    gs.getConsumableEmitter().spawnCoin(0, true);
                }
                break;
            }
            case STAR: {
                final List<Ship> enemies = gs.getEnemyEmitter().getActiveList();
                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).takeDamage(15);
                }
            }
        }
    }

    public void init(Type type) {
        this.type = type;
        setImg(imgs[type.ordinal()]);
        if (type == Type.COIN) img.setScale(MathUtils.random(1f, 2f));
        position.x = ScreenParameters.myScreen.getWidth() + img.getRightShift();
        position.y = MathUtils.random(img.getTopShift(), ScreenParameters.myScreen.getHeight() + img.getBottomShift());
        velocity.x = - MathUtils.random(1f, 3f);
        active = true;
        visible = true;
    }
}
