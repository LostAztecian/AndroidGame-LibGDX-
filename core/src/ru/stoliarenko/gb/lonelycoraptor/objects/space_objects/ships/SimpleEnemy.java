package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.objects.weapons.LaserCannon;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class SimpleEnemy extends Ship {

    protected float suspensionTimer = 0;

    public SimpleEnemy() {
        super(new Sprite(Atlases.space.findRegion("simpleEnemy"), 0.3f));
        weapon = new LaserCannon();
        angle = 180;
        position.x = ScreenParameters.myScreen.getWidth() + img.getRightShift();
        position.y = MathUtils.random(100, ScreenParameters.myScreen.getHeight() - 100);
        velocity.x = -150;
        visible = true;
    }

    @Override
    public void move(float dt) {
        if (checkSuspension(dt)) return;
        temp.set(velocity).scl(dt);
        position.add(temp);
        temp.set(Corruptor.getCorruptor().getPosition());
        shoot((int)temp.x + MathUtils.random(-50, +50), (int)temp.y + MathUtils.random(-50, +50));
        if (checkOuterSpace()) destroy();
    }

    private boolean checkSuspension(float dt) {
        if (suspensionTimer > 0) {
            suspensionTimer -= dt;
            return true;
        }
        return false;
    }

    @Override
    protected boolean checkOuterSpace() {
        return position.x + img.getRightShift() < 0;
    }

    private void destroy() {
        suspensionTimer = 5;
        position.x = ScreenParameters.myScreen.getWidth() + img.getRightShift();
        position.y = MathUtils.random(100, ScreenParameters.myScreen.getHeight() - 100);
    }
}
