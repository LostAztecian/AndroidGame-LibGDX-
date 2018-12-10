package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.Poolable;
import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.objects.weapons.LaserCannon;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class SimpleEnemy extends Ship implements Poolable {

    public SimpleEnemy() {
        super(new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("simpleEnemy"), 0.3f));
        weapon = new LaserCannon();
        angle = 180;
        velocity.x = -150;
    }

    public void init() {
        position.x = ScreenParameters.myScreen.getWidth() + img.getRightShift();
        position.y = MathUtils.random(100, ScreenParameters.myScreen.getHeight() - 100);
        visible = true;
        active = true;
    }

    @Override
    public void move(float dt) {
        temp.set(velocity).scl(dt);
        position.add(temp);
        temp.set(Corruptor.getCorruptor().getPosition());
        shoot((int)temp.x + MathUtils.random(-50, +50), (int)temp.y + MathUtils.random(-50, +50));
        if (checkOuterSpace()) destroy();
    }

    @Override
    protected boolean checkOuterSpace() {
        return position.x + img.getRightShift() < 0;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void destroy() {
        active = false;
    }

}
