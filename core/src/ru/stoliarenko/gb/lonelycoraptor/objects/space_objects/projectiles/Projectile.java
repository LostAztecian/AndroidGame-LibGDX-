package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ru.stoliarenko.gb.lonelycoraptor.base.Poolable;
import ru.stoliarenko.gb.lonelycoraptor.base.SpaceObject;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Short-living space objects, which may interact with {@code = Ship}
 */
public class Projectile extends SpaceObject implements Poolable {

    public enum Type {
        LASER_BULLET_RED(0, Assets.getInstance().getLaserBulletSound()),
        LASER_BULLET_BLUE(1, Assets.getInstance().getLaserBulletSound()),
        LASER_BULLET_GREEN(2, Assets.getInstance().getLaserBulletSound()),
        LASER_BULLET_PURPLE(3, Assets.getInstance().getLaserBulletSound()),
        CORROSIVE_BILE(4, Assets.getInstance().getLaserBulletSound(), true);

        private int imgIndex;
        private Sound sound;
        private boolean isChargeable = false;
        Type(int index, Sound sound) {
            imgIndex = index;
            this.sound = sound;
        }
        Type(int index, Sound sound, boolean isChargeable) {
            imgIndex = index;
            this.sound = sound;
            this.isChargeable = isChargeable;
        }
    }

    private final MainScreen2D gs;
    private final Sprite[] imgs;

    private  Type type;
    protected boolean isAllied = false; //TODO add friendly fire check
    protected float SPEED = 750f;
    protected Vector2 direction = new Vector2();
    private  Vector2 destination = new Vector2();

    public Projectile(@NotNull final MainScreen2D gs, @NotNull final Sprite[] imgs){
        super(SpaceObject.Type.PROJECTILE, imgs[0]);
        this.gs = gs;
        this.imgs = imgs;
    }

    protected void checkDestination() {
        if (checkOuterSpace()) destroy();
        if (type.isChargeable && destination.dst(position) < SPEED / 120) destroy();

        if (!isAllied) {
            if (checkCollision(gs.getPlayer())) {
                gs.getPlayer().takeDamage(3);
                destroy();
            }
        } else {
            final List<SimpleEnemy> enemyList = gs.getSimpleEnemyEmitter().getActiveList();
            for (int i = 0; i < enemyList.size(); i++) {
                if (checkCollision(enemyList.get(i))) {
                    enemyList.get(i).destroy(); //TODO take damage instead of destroy
                    System.out.println("enemy hit");
                    destroy();
                    break;
                }
            }
        }
    }

    @Override
    public void destroy() {
        active = false;
    }

    @Override
    public void move(float dt) {
        position.mulAdd(direction, SPEED * dt);
        checkDestination();
    }

    public void init(@NotNull final Type type, @NotNull final Vector2 currentPosition, @NotNull final Vector2 destinationPosition) {
        this.type = type;
        setImg(imgs[type.imgIndex]);
        position.set(currentPosition.sub(WIDTH/2, HEIGHT/2));
        destination.set(destinationPosition.sub(WIDTH/2, HEIGHT/2));
        direction.set(destinationPosition).sub(currentPosition);
        angle = direction.angle();
        direction.nor();
        visible = true;
        active = true;
        type.sound.play(0.3f);
    }

}
