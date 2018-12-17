package ru.stoliarenko.gb.lonelycoraptor.objects.background_objects;

import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class BackgroundStar extends BackgroundObject {

    public BackgroundStar() {
        super(new Sprite(Assets.getInstance().getBackgroundAtlas().findRegion("backgroundStar"), 0.35f));
        position.x = MathUtils.random(500f * ScreenParameters.myScreen.getRatio());
        position.y = MathUtils.random(500f);
        final float rnd = MathUtils.random(1f, 2f);
        img.setScale(rnd);
        velocity.x = rnd / -6f;
        visible = true;
    }

    @Override
    public void move(float dt) {
        position.add(velocity);
        checkBorders();
    }

    private void checkBorders() {
        if (position.x < 0) {
            position.x = ScreenParameters.myScreen.getWidth();
            position.y = MathUtils.random(ScreenParameters.myScreen.getHeight());
            final float rnd = MathUtils.random(1f, 2f);
            img.setScale(rnd);
            velocity.x = rnd / -6f;
        }
    }

}
