package ru.stoliarenko.gb.lonelycoraptor.emitters;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ProjectileEmitter extends ObjectPool<Projectile> {

    private final MainScreen2D gs;
    private final Sprite[] imgs;


    public ProjectileEmitter(@NotNull final MainScreen2D gs) {
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

    public void spawn(@NotNull final Projectile.Type type, @NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition) {
        getActiveElement().init(type, currentPosition, destinationPosition);
    }

    public void spawnLaserBulletRed(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_RED, currentPosition, destinationPosition);
    }
    public void spawnLaserBulletBlue(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_BLUE, currentPosition, destinationPosition);
    }
    public void spawnLaserBulletGreen(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_GREEN, currentPosition, destinationPosition);
    }
    public void spawnLaserBulletPurple(@NotNull Vector2 currentPosition, @NotNull Vector2 destinationPosition) {
        getActiveElement().init(Projectile.Type.LASER_BULLET_PURPLE, currentPosition, destinationPosition);
    }

}