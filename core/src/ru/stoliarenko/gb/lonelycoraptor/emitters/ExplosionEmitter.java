package ru.stoliarenko.gb.lonelycoraptor.emitters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.Explosion;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;

public final class ExplosionEmitter extends ru.stoliarenko.gb.lonelycoraptor.emitters.pool.ObjectPool<Explosion> {

    private final ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen gs;
    private final TextureRegion[] imgs;

    public ExplosionEmitter(@NotNull final ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen gs) {
        this.gs = gs;
        imgs = new TextureRegion[2];
        imgs[0] = Assets.getInstance().getExplosionsAtlas().findRegion("shipExplosion");
        imgs[1] = Assets.getInstance().getExplosionsAtlas().findRegion("bileExplosion");
        addObjectsToFreeList(15);
    }

    @Override
    protected Explosion newObject() {
        return new Explosion(gs, imgs);
    }

    @Override
    public void move(float dt) {
        super.move(dt);
    }

    public void spawn(Explosion.Type type, Vector2 position, ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.Ship owner) {
        getActiveElement().init(type, position, owner);
    }

}
