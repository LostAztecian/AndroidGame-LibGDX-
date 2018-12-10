package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.projectiles;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Projectile;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

/**
 * Basic projectile
 */
public class CorrosiveBile extends Projectile {

    @NotNull private final Vector2 destination = new Vector2();
    private final float minimumRange = 100;
    private final float chargeableRange = 900;

    public CorrosiveBile(){
        super(new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("corrosiveBile"), 1f));
    }

    @Override
    protected void checkDestination() {
        if (position.dst(destination) <= SPEED / 120)
            destroy();
    }

    public void move(float dt) {
        temp.set(destination);
        position.mulAdd(temp.sub(position).nor(), SPEED * dt);
        checkDestination();
    }

    public void init(final @NotNull Vector2 currentPosition, final @NotNull Vector2 direction, float charge) {
        position.set(currentPosition);
        destination.set(currentPosition).mulAdd(direction, minimumRange + chargeableRange * charge);
        angle = temp.set(direction).sub(currentPosition).angle();
        visible = true;
        active = true;
    }

}
