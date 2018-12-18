package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ButtonNewGame extends Button {

    public ButtonNewGame(@NotNull final ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull final Vector2 position) {
        super(game, new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("rectangleBrown"), 1f), position);
        text = "New Game";
    }

    @Override
    protected void onClick() {
        game.newGame();
    }

}
