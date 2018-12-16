package ru.stoliarenko.gb.lonelycoraptor.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class Explosion extends SpaceObject implements Poolable {

    public enum Type {
        SHIP(0),
        CORROSIVE_BILE(1);

        Type(float damage) {this.damage = damage;}
        private float damage;
    }

    private final MainScreen2D gs;
    private final TextureRegion[] imgs;
    private Type type;
    private Ship owner;

    private int currentImgIndex;
    private float currentTime;
    private float frameTime = 1.5f/18;

    public Explosion(@NotNull final MainScreen2D gs, @NotNull final TextureRegion[] imgs) {
        super(SpaceObject.Type.EXPLOSION, new Sprite(imgs[0].split(128, 128)[0][0], 1f));
        this.gs = gs;
        this.imgs = imgs;
    }

    public void destroy() {
        active = false;
    }

    @Override
    public void move(float dt) {
        currentTime += dt;
        currentImgIndex = (int)(currentTime / frameTime);
        if (currentImgIndex >= 18) {
            destroy();
            return;
        }
        img.setImg(imgs[type.ordinal()].split(128, 128)[0][currentImgIndex]);
        final List<SimpleEnemy> list = gs.getSimpleEnemyEmitter().activeList;
        if (type.damage == 0) return;
        for (int i = 0; i < list.size(); i++) {
            if (checkCollision(list.get(i))) list.get(i).takeDamage(type.damage * owner.getDamageMultiplier());
        }
        if (checkCollision(gs.getPlayer())) gs.getPlayer().takeDamage(type.damage * owner.getDamageMultiplier());
    }

    public void init(Type type, Vector2 position, Ship owner) {
        this.type = type;
        this.owner = owner;
        img.setImg(imgs[type.ordinal()].split(128, 128)[0][0]);
        this.position.set(position);
        currentImgIndex = 0;
        currentTime = 0;
        active = true;
        visible = true;
    }
}
