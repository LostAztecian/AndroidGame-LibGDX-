package ru.stoliarenko.gb.lonelycoraptor.objects.consumables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import ru.stoliarenko.gb.lonelycoraptor.base.Consumable;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;

public final class Coin extends Consumable {

    public Coin(){
        super(Type.COIN, new TextureRegion(new Texture("coin_icon.png")));
        scale = MathUtils.random(0.03f, 0.07f);
        position.x = ScreenParameters.myScreen.getWidth() - WIDTH/2;
        position.y = MathUtils.random(-HEIGHT/2f, ScreenParameters.myScreen.getHeight() - HEIGHT/2f);
        velocity.x = - MathUtils.random(1f, 5f);
        visible = true;
    }

    @Override
    public void move(float dt) {
        position.add(velocity);
        if (position.x < WIDTH/2*scale - WIDTH/2) destroy();
    }

    @Override
    public void destroy() {
        scale = MathUtils.random(0.03f, 0.07f);
        position.x = ScreenParameters.myScreen.getWidth() - WIDTH/2;
        position.y = MathUtils.random(-HEIGHT/2f, ScreenParameters.myScreen.getHeight() - HEIGHT/2f);
        velocity.x = - MathUtils.random(1f, 5f);
    }

}
