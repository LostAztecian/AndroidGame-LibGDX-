package ru.stoliarenko.gb.lonelycoraptor.emitters;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.emitters.pool.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Ship;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ProjectileEmitter extends ObjectPool<Projectile> {

    private final GameScreen gs;
    private final Sprite[] imgs;


    public ProjectileEmitter(@NotNull final GameScreen gs) {
        this.gs = gs;
        imgs = new Sprite[5];
        imgs[0] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("laserBulletRed"), 0.15f);
        imgs[1] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("laserBulletBlue"), 0.15f);
        imgs[2] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("laserBulletGreen"), 0.15f);
        imgs[3] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("laserBulletPurple"), 0.15f);
        imgs[4] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("corrosiveBile"), 1f);
        addObjectsToFreeList(20);
    }

    @Override
    protected Projectile newObject() {
        return new Projectile(gs, imgs);
    }

    @Override
    public void move(float dt) {
        super.move(dt);
    }

    public void spawn(@NotNull final Projectile.Type type, @NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition, Ship owner) {
        getActiveElement().init(type, currentPosition, destinationPosition, owner);
    }

    public void spawnLaserBulletRed(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition, Ship owner) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_RED, currentPosition, destinationPosition, owner);
    }
    public void spawnLaserBulletBlue(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition, Ship owner) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_BLUE, currentPosition, destinationPosition, owner);
    }
    public void spawnLaserBulletGreen(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition, Ship owner) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_GREEN, currentPosition, destinationPosition, owner);
    }
    public void spawnLaserBulletPurple(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition, Ship owner) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_PURPLE, currentPosition, destinationPosition, owner);
    }

}
