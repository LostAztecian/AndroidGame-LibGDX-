package ru.stoliarenko.gb.lonelycoraptor.emitters;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy;
import ru.stoliarenko.gb.lonelycoraptor.screen.MainScreen2D;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class SimpleEnemyEmitter extends ObjectPool<SimpleEnemy> {

    private final MainScreen2D gs;
    private final Sprite[] imgs;

    private float spawnDelay = 0;

    public SimpleEnemyEmitter(MainScreen2D gs) {
        this.gs = gs;
        imgs = new Sprite[4];

        imgs[0] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("simpleEnemyRegular"), 0.3f);
        imgs[1] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("simpleEnemyYellow"), 0.3f);
        imgs[2] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("simpleEnemyGreen"), 0.3f);
        imgs[3] = new Sprite(Assets.getInstance().getSpaceAtlas().findRegion("simpleEnemyBlue"), 0.3f);

        addObjectsToFreeList(10);
    }

    @Override
    protected SimpleEnemy newObject() {
        return new SimpleEnemy(gs, imgs);
    }


    public void spawn() {
        getActiveElement().init(SimpleEnemy.Type.REGULAR);
    }

    @Override
    public void move(float dt) {
        super.move(dt);
        if (activeList.size() < 2) spawnWithDelay(dt);
    }

    private void spawnWithDelay(float dt) {
        if (spawnDelay < 0) {
            spawn();
            spawnDelay = MathUtils.random(2f, 5f);
        } else spawnDelay -= dt;

    }

}
