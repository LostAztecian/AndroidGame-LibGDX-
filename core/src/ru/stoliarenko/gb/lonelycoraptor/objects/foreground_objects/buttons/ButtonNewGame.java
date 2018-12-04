package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Button;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ButtonNewGame extends Button {

    public ButtonNewGame(@NotNull final Vector2 position) {
        super(new Sprite(Atlases.foreground.findRegion("NewGame"), 1f), position);
    }

    @Override
    public void onClick() {
    }

}
