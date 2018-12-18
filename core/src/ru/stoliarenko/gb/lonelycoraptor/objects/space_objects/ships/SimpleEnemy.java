package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.objects.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Explosion;
import ru.stoliarenko.gb.lonelycoraptor.emitters.pool.Poolable;
import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class SimpleEnemy extends Ship implements Poolable {

    public enum Type {
        REGULAR(ShipWeapon.Type.LASER_CANNON_RED),
        YELLOW(ShipWeapon.Type.LASER_CANNON_PURPLE),
        GREEN(ShipWeapon.Type.LASER_CANNON_GREEN),
        BLUE(ShipWeapon.Type.LASER_CANNON_BLUE);

        Type(ShipWeapon.Type weapon){this.weapon = weapon;}
        private ShipWeapon.Type weapon;
    }

    private final GameScreen gs;
    private final Sprite[] imgs;
    private Type type; // do i need this?

    private int baseSpeen = 150;
    private boolean eneteredScreen;
    private float noShootTimer;

    public SimpleEnemy(@NotNull final GameScreen gs, @NotNull final Sprite[] imgs) {
        super(imgs[0], gs);
        this.gs = gs;
        this.imgs = imgs;
        damaged = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("simpleEnemyDamaged"), 0.3f);
    }

    public void init(@NotNull Type type, Vector2 position, Vector2 direction) {
        this.type = type;
        regular = imgs[type.ordinal()];
        setImg(regular);

        this.position.set(position);
        this.velocity.set(direction).nor().scl(baseSpeen);
        angle = velocity.angle();

        weapon = new ShipWeapon(gs, type.weapon);

        switch (type) {
            case REGULAR: {
                hp = 10;
                scoreBounty = 15;
                break;
            }
            case BLUE: {
                weapon.setFireRateScale(1.8f);
                scoreBounty = 40;
                break;
            }
            case GREEN: {
                velocity.scl(0.7f);
                weapon.setFireRateScale(2);
                hp = 15;
                break;
            }
            case YELLOW: {
                velocity.scl(1.2f);
                weapon.setFireRateScale(0.6f);
                scoreBounty = 60;
                break;
            }
        }

        setDamageMultiplier(1 + gs.getPlayer().getScore() / 2000);
        setCollisionDamageMultiplier(1 + gs.getPlayer().getScore() / 4000);
        hp *= 1+gs.getPlayer().getScore()/5000;
        active = true;
        visible = true;
        eneteredScreen = false;
        noShootTimer = 1f;
    }

    @Override
    public void move(float dt) {
        checkDamagedTimer(dt);
        temp.set(velocity).scl(dt);
        position.add(temp);
        if (!this.eneteredScreen) {
            if (checkOuterSpace()) return;
            else eneteredScreen = true;
        }
        if (checkOuterSpace()) destroy();
        if (noShootTimer > 0) {
            noShootTimer -= dt;
            return;
        }
        shoot();
    }

    private void shoot() {
        temp.set(gs.getPlayer().getPosition());
        switch (type) {
            case REGULAR: {
                shoot(temp.x - position.x, position.y);
                break;
            }
            case BLUE: {
                shoot((int)temp.x + MathUtils.random(-70, +70), (int)temp.y + MathUtils.random(-70, +70));
                break;
            }
            case GREEN: {
                shoot(position.x + velocity.x, position.y + velocity.y);
                break;
            }
            case YELLOW: {
                shoot((int)temp.x + MathUtils.random(-10, +10), (int)temp.y + MathUtils.random(-10, +10));
                break;
            }
        }
    }

    @Override
    public void destroy() {
        active = false;
        if (hp <= 0) gs.getExplosionEmitter().spawn(Explosion.Type.SHIP, position, this);
    } //TODO should I pull up destroy method to SpaceObject?

}
