package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Corruptor;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class Consumable extends SpaceObject implements Poolable {

    public enum Type{
        COIN(0),
        STAR(1),
        HEAL(2),
        X5(3);

        private int imgIndex;
        Type(int imgIndex) { this.imgIndex = imgIndex; }
    }

    private final Screen gs;
    private final Sprite[] imgs;
    private Type type;

    public Consumable(@NotNull final Screen gs, @NotNull final Sprite[] imgs) {
        super(SpaceObject.Type.CONSUMABLE, imgs[0]);
        this.gs = gs;
        this.imgs = imgs;

    }

    public void destroy() {
        active = false;
    }

    @Override
    public void move(float dt) {
        position.add(velocity);
        if (checkCollision(Corruptor.getCorruptor())) destroy();
        if (position.x + img.getRightShift() < 0) destroy();
    }

    public void init(Type type) {
        this.type = type;
        setImg(imgs[type.imgIndex]);
        if (type == Type.COIN) img.setScale(MathUtils.random(1f, 2f));
        position.x = ScreenParameters.myScreen.getWidth() + img.getLeftShift();
        position.y = MathUtils.random(ScreenParameters.myScreen.getHeight() + img.getTopShift());
        velocity.x = - MathUtils.random(1f, 3f);
        active = true;
        visible = true;
    }
}
