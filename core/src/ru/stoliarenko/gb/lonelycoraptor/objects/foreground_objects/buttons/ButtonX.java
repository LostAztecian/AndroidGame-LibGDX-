package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.base.Button;
import ru.stoliarenko.gb.lonelycoraptor.utils.Atlases;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ButtonX extends Button {

    public ButtonX(@NotNull final Vector2 position) {
        super(new Sprite(Atlases.foreground.findRegion("X"), 0.7f), position);
    }

    @Override
    public void onClick() {
        Gdx.app.exit();
    }
}
