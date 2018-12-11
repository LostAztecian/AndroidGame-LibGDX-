package ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships;

import com.badlogic.gdx.math.MathUtils;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Poolable;
import ru.stoliarenko.gb.lonelycoraptor.base.Ship;
import ru.stoliarenko.gb.lonelycoraptor.base.ShipWeapon;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public class SimpleEnemy extends Ship implements Poolable {

    public enum Type {
        REGULAR(0),
        YELLOW(1),
        GREEN(2),
        BLUE(3);

        private int imgIndex;
        Type(int index) {imgIndex = index;}
    }

    private final MainScreen2D gs;
    private final Sprite[] imgs;
    private Type type; // do i need this?
    private Sprite imgDamaged = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("simpleEnemyDamaged"), 0.3f); //TODO damaged animation

    private int shootPositionX;

    public SimpleEnemy(@NotNull final MainScreen2D gs, @NotNull final Sprite[] imgs) {
        super(imgs[0]);
        this.gs = gs;
        this.imgs = imgs;
    }

    public void init(@NotNull final Type type) {
        this.type = type;
        setImg(imgs[type.imgIndex]);

        position.x = ScreenParameters.myScreen.getWidth() + img.getRightShift();
        position.y = MathUtils.random(100, ScreenParameters.myScreen.getHeight() - 100);
        shootPositionX = (int)(ScreenParameters.myScreen.getWidth() + img.getLeftShift()*2);
        angle = 180;
        velocity.x = -150;

        weapon = new ShipWeapon(gs, ShipWeapon.Type.LASER_CANNON_GREEN); //TODO weapon must be versatile

        visible = true;
        active = true;
    }

    @Override
    public void move(float dt) {
        temp.set(velocity).scl(dt);
        position.add(temp);
        temp.set(Corruptor.getCorruptor().getPosition());
        if (checkOuterSpace()) destroy();
        if (position.x > shootPositionX) return;
        shoot((int)temp.x + MathUtils.random(-50, +50), (int)temp.y + MathUtils.random(-50, +50));
    }

    @Override
    public void destroy() {
        active = false;
    } //TODO should I pull up destroy method to SpaceObject?

}
