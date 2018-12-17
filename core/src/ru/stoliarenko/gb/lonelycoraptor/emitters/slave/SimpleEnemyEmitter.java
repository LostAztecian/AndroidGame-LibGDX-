package ru.stoliarenko.gb.lonelycoraptor.emitters.slave;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.time.Year;

import ru.stoliarenko.gb.lonelycoraptor.emitters.pool.ObjectPool;
import ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy;
import ru.stoliarenko.gb.lonelycoraptor.screen.GameScreen;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

import static ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy.Type.BLUE;
import static ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy.Type.GREEN;
import static ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy.Type.REGULAR;
import static ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.ships.SimpleEnemy.Type.YELLOW;

public final class SimpleEnemyEmitter extends ObjectPool<SimpleEnemy> {

    private final GameScreen gs;
    private final Sprite[] imgs;
    private Vector2 tempPosition = new Vector2();
    private Vector2 tempDirection = new Vector2();

    public SimpleEnemyEmitter(GameScreen gs) {
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


    public void spawnSimple(SimpleEnemy.Type type, boolean horizontal, boolean downleft, float positionPercent, int ofsetX, int ofsetY) {
        final SimpleEnemy enemy = getActiveElement();
        if (horizontal) {
            if (downleft) {
                tempPosition.set(ScreenParameters.myScreen.getWidth() + enemy.getImg().getRightShift() + ofsetX*enemy.getImg().getRightShift()*2, 100 + (ScreenParameters.myScreen.getHeight() - 100) * positionPercent + ofsetY*enemy.getImg().getTopShift()*2);
                tempDirection.set(-1, 0);
            }
            else {
                tempPosition.set(enemy.getImg().getLeftShift() - ofsetX*enemy.getImg().getRightShift()*2*enemy.getImg().getRightShift()*2, 100 + (ScreenParameters.myScreen.getHeight() - 100) * positionPercent - ofsetY*enemy.getImg().getTopShift()*2);
                tempDirection.set(1, 0);
            }
        } else {
            if (downleft) {
                tempPosition.set(100 + (ScreenParameters.myScreen.getWidth() - 100) * positionPercent + ofsetX*enemy.getImg().getRightShift()*2, ScreenParameters.myScreen.getHeight() + enemy.getImg().getTopShift() + ofsetY*enemy.getImg().getTopShift()*2);
                tempDirection.set(0, -1);
            }
            else {
                tempPosition.set(100 + (ScreenParameters.myScreen.getWidth() - 100) * positionPercent - ofsetX*enemy.getImg().getRightShift()*2, enemy.getImg().getBottomShift() - ofsetY*enemy.getImg().getTopShift()*2);
                tempDirection.set(0, 1);
            }
        }
        enemy.init(type, tempPosition, tempDirection);
    }
    public void spawnAngled(SimpleEnemy.Type type, boolean descending, int ofsetX, int ofsetY) {
        final SimpleEnemy enemy = getActiveElement();
        tempPosition.set(ScreenParameters.myScreen.getWidth()+enemy.getImg().getRightShift() + ofsetX*enemy.getImg().getRightShift()*2, descending ? ScreenParameters.myScreen.getHeight() + enemy.getImg().getTopShift() + ofsetY*enemy.getImg().getTopShift()*2 : enemy.getImg().getBottomShift() - ofsetY*enemy.getImg().getTopShift()*2);
        tempDirection.set(-1, descending ? -1 : 1);
        enemy.init(type, tempPosition, tempDirection);
    }
    public void spawnLongAngled(SimpleEnemy.Type type, boolean descending, int ofsetX, int ofsetY) {
        final SimpleEnemy enemy = getActiveElement();
        tempPosition.set(ScreenParameters.myScreen.getWidth()+enemy.getImg().getRightShift() + ofsetX*enemy.getImg().getRightShift()*2, descending ? ScreenParameters.myScreen.getHeight() + enemy.getImg().getTopShift() + ofsetY*enemy.getImg().getTopShift()*2 : enemy.getImg().getBottomShift() - ofsetY*enemy.getImg().getTopShift()*2);
        tempDirection.set(-1.5f, descending ? -1 : 1);
        enemy.init(type, tempPosition, tempDirection);
    }
    public void spawnShortAngled(SimpleEnemy.Type type, boolean descending, int ofsetX, int ofsetY) {
        final SimpleEnemy enemy = getActiveElement();
        tempPosition.set(ScreenParameters.myScreen.getWidth()+enemy.getImg().getRightShift() + ofsetX*enemy.getImg().getRightShift()*2, descending ? ScreenParameters.myScreen.getHeight() + enemy.getImg().getTopShift() + ofsetY*enemy.getImg().getTopShift()*2 : enemy.getImg().getBottomShift() - ofsetY*enemy.getImg().getTopShift()*2);
        tempDirection.set(-0.6f, descending ? -1 : 1);
        enemy.init(type, tempPosition, tempDirection);
    }


    @Override
    public void move(float dt) {
        super.move(dt);
    }

}
