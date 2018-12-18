package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ru.stoliarenko.gb.lonelycoraptor.emitters.pool.Poolable;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Ship;
import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Short-living space objects, which may interact with {@code = Ship}
 */
public class Projectile extends SpaceObject implements Poolable {

    public enum Type {
        LASER_BULLET_RED(3, Assets.getInstance().getLaserBulletSound(), null),
        LASER_BULLET_BLUE(3, Assets.getInstance().getLaserBulletSound(), null),
        LASER_BULLET_GREEN(3, Assets.getInstance().getLaserBulletSound(), null),
        LASER_BULLET_PURPLE(3, Assets.getInstance().getLaserBulletSound(), null),
        CORROSIVE_BILE(5, Assets.getInstance().getAssetManager().get("sounds/releasing.mp3", Sound.class), ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Explosion.Type.CORROSIVE_BILE, true);

        private int damage;
        private Sound sound;
        private boolean isChargeable = false;
        private ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Explosion.Type afterEffect;
        Type(int damage, Sound sound, ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Explosion.Type aftereffect) {
            this.damage = damage;
            this.sound = sound;
            this.afterEffect = aftereffect;
        }
        Type(int damage, Sound sound, ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Explosion.Type aftereffect, boolean isChargeable) {
            this.damage = damage;
            this.sound = sound;
            this.afterEffect = aftereffect;
            this.isChargeable = isChargeable;
        }
    }

    private final GameScreen gs;
    private final Sprite[] imgs;

    private  Type type;
    private Ship owner;
    protected float SPEED = 750f;
    protected Vector2 direction = new Vector2();
    private  Vector2 destination = new Vector2();

    public Projectile(@NotNull final GameScreen gs, @NotNull final Sprite[] imgs){
        super(SpaceObject.Type.PROJECTILE, imgs[0]);
        this.gs = gs;
        this.imgs = imgs;
    }

    protected void checkDestination() {
        if (checkOuterSpace()) destroy();
        if (type.isChargeable && destination.dst(position) < SPEED / 120) destroy();

        if (owner != gs.getPlayer()) {
            if (checkCollision(gs.getPlayer())) {
                gs.getPlayer().takeDamage(3);
                destroy();
            }
        } else {
            final List<Ship> enemyList = gs.getEnemyEmitter().getActiveList();
            for (int i = 0; i < enemyList.size(); i++) {
                if (checkCollision(enemyList.get(i))) {
                    enemyList.get(i).takeDamage(type.damage * gs.getPlayer().getDamageMultiplier()); //TODO take damage instead of destroy
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
        if (type.afterEffect != null) gs.getExplosionEmitter().spawn(type.afterEffect, position, owner);
    }

    @Override
    public void move(float dt) {
        position.mulAdd(direction, SPEED * dt);
        checkDestination();
    }

    public void init(@NotNull final Type type, @NotNull final Vector2 currentPosition, @NotNull final Vector2 destinationPosition, Ship owner) {
        this.type = type;
        this.owner = owner;
        setImg(imgs[type.ordinal()]);
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
