package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class LaserBullet extends Projectile {

    private final Sound sound = Assets.getInstance().getLaserBulletSound();

    public LaserBullet() {
        super(new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("laserBullet"), 0.15f));
    }

    @Override
    public void move(float dt) {
        position.mulAdd(direction, SPEED * dt);
        checkDestination();
    }

    @Override
    protected void checkDestination() {
        if (checkOuterSpace()) destroy();
        if (checkCollision(Corruptor.getCorruptor())) destroy();
    }

    public void init(final @NotNull Vector2 currentPosition, final @NotNull Vector2 destinationPosition) {
        position.set(currentPosition.sub(WIDTH/2, HEIGHT/2));
        direction.set(destinationPosition.sub(WIDTH/2, HEIGHT/2)).sub(currentPosition);
        angle = direction.angle();
        direction.nor();
        visible = true;
        active = true;
        sound.play(0.3f);
    }

}
