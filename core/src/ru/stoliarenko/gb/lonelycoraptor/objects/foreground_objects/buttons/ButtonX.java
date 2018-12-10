package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.TestCorruptor;
import ru.stoliarenko.gb.lonelycoraptor.base.Button;
import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ButtonX extends Button {

    public ButtonX(@NotNull final TestCorruptor game, @NotNull final Vector2 position) {
        super(game, new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("squareBrown"), 0.7f), position);
        text = "X";
    }

    @Override
    protected void onClick() {
        Gdx.app.exit();
    }
}
