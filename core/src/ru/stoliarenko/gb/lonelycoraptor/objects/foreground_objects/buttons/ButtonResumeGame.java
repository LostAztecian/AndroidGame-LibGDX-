package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ButtonResumeGame extends ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.Button {

    public ButtonResumeGame(@NotNull ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull Vector2 position) {
        super(game, new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("rectangleBrown"), 0.6f), position);
        text = "Continue";
        this.setFonts(Assets.getInstance().getAssetManager().get("mainFont16.ttf"), Assets.getInstance().getAssetManager().get("mainFont14.ttf"));
    }

    @Override
    protected void onClick() {
        game.resumeGame();

    }

}
