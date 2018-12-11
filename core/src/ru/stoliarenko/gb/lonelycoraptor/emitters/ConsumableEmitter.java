package ru.stoliarenko.gb.lonelycoraptor.emitters;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Consumable;
import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ConsumableEmitter extends ObjectPool<Consumable> {

    private final Screen gs;
    private final Sprite[] imgs;

    private float coinSpawnTimer = 5;
    private float starSpawnTimer = 10;
    private float healSpawnTimer = 100;
    private float x5SpawnTimer = 100;

    public ConsumableEmitter(@NotNull final Screen gs) {
        this.gs = gs;
        imgs = new Sprite[4];
        imgs[0] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("coin"), 0.1f);
        imgs[1] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("powerupStar"), 0.4f);
        imgs[2] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("powerupHeal"), 0.4f);
        imgs[3] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("powerupX5"), 0.4f);
        addObjectsToFreeList(10);
    }

    @Override
    protected Consumable newObject() {
        return new Consumable(gs, imgs);
    }

    @Override
    public void move(float dt) {
        super.move(dt);
        spawnCoin(dt);
        spawnStar(dt);
        spawnHeal(dt);
        spawnX5(dt);
    }

    public void spawnCoin(float dt) {
        coinSpawnTimer -= dt;
        if (coinSpawnTimer > 0) return;
        getActiveElement().init(Consumable.Type.COIN);
        coinSpawnTimer = MathUtils.random(3f, 5f);
    }

    public void spawnStar(float dt) {
        starSpawnTimer -= dt;
        if (starSpawnTimer > 0) return;
        getActiveElement().init(Consumable.Type.STAR);
        starSpawnTimer = MathUtils.random(7f, 15f);
    }

    public void spawnHeal(float dt) {
        healSpawnTimer -= dt;
        if (healSpawnTimer > 0) return;
        getActiveElement().init(Consumable.Type.HEAL);
        healSpawnTimer = MathUtils.random(50, 100);
    }

    public void spawnX5(float dt) {
        x5SpawnTimer -= dt;
        if (x5SpawnTimer > 0) return;
        getActiveElement().init(Consumable.Type.X5);
        x5SpawnTimer = MathUtils.random(50, 100);
    }

}
