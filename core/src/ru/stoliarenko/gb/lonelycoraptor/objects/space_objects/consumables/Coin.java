package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.consumables;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.Consumable;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class Coin extends Consumable {

    public Coin(){
        super(Type.COIN, new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("coin"), 0.1f));
    }

    @Override
    public void move(float dt) {
        position.add(velocity);
        if (checkCollision(Corruptor.getCorruptor())) destroy();
        if (position.x + img.getRightShift() < 0) destroy();
    }

    public void init() {
        img.setScale(MathUtils.random(1f, 2f));
        position.x = ScreenParameters.myScreen.getWidth() + img.getLeftShift();
        position.y = MathUtils.random(ScreenParameters.myScreen.getHeight() + img.getTopShift());
        velocity.x = - MathUtils.random(1f, 3f);
        active = true;
        visible = true;
    }

}
