package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class LaserBullet extends Projectile {

    public LaserBullet(final @NotNull Vector2 currentPosition, final @NotNull Vector2 destinationPosition) {
        super(new Sprite(Atlases.space.findRegion("laserBullet"), 0.15f));
        position.set(currentPosition.sub(WIDTH/2, HEIGHT/2));
        direction.set(destinationPosition.sub(WIDTH/2, HEIGHT/2)).sub(currentPosition);
        angle = direction.angle();
        direction.nor();
        visible = true;
    }

    @Override
    public void move(float dt) {
        position.mulAdd(direction, SPEED * dt);
        checkDestination();
    }

    @Override
    protected void checkDestination() {
        if (checkOuterSpace()) expire();
        if (checkCollision(Corruptor.getCorruptor())) expire();
    }

}
