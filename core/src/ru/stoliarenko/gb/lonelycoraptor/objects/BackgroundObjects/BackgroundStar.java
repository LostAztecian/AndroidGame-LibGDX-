package ru.stoliarenko.gb.lonelycoraptor.objects.BackgroundObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.BackgroundObject;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;

public final class BackgroundStar extends BackgroundObject {

    private static final Texture texture = new Texture("star.png");

    public BackgroundStar() {
        super(new TextureRegion(texture));
        position.x = MathUtils.random(500f * ScreenParameters.myScreen.getRatio());
        position.y = MathUtils.random(500f);
        scale = MathUtils.random(0.35f, 0.7f);
        velocity.x = -scale/1.5f;
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
            scale = MathUtils.random(0.35f, 0.7f);
            velocity.x = -scale/1.5f;
        }
    }

}
