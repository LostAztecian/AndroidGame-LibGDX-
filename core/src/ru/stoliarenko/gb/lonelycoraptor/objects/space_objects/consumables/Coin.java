package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.consumables;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.Consumable;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class Coin extends Consumable {

    public Coin(){
        super(Type.COIN, new Sprite(Atlases.space.findRegion("coin"), 0.1f));
        img.setScale(MathUtils.random(1f, 2f));
        position.x = ScreenParameters.myScreen.getWidth() + img.getLeftShift();
        position.y = MathUtils.random(ScreenParameters.myScreen.getHeight() + img.getTopShift());
        velocity.x = - MathUtils.random(1f, 3f);
        visible = true;
    }

    @Override
    public void move(float dt) {
        position.add(velocity);
        if (checkCollision(Corruptor.getCorruptor())) destroy();
        if (position.x + img.getRightShift() < 0) destroy();
    }

    @Override
    public void destroy() {
        img.setScale(MathUtils.random(1f, 2f));
        position.x = ScreenParameters.myScreen.getWidth() + img.getLeftShift();
        position.y = MathUtils.random(ScreenParameters.myScreen.getHeight() + img.getTopShift());
    }

//    private void checkCollisions(){
//        if (this.position.dst(Corruptor.getCorruptor().getPosition()) < (img.getRadius() + Corruptor.getCorruptor().img.getRadius()) * 0.8) {
//            destroy();
//        }
//    }

}
