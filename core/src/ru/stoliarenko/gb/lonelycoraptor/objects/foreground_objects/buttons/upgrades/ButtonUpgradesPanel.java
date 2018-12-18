package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.upgrades;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.Button;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class ButtonUpgradesPanel extends Button {
    private final ru.stoliarenko.gb.lonelycoraptor.screen.UpgradesScreen upgradesScreen;

    public ButtonUpgradesPanel(@NotNull ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull Vector2 position, @NotNull ru.stoliarenko.gb.lonelycoraptor.screen.UpgradesScreen screen) {
        super(game, new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("rectangleBrown"), 0.6f), position);
        this.upgradesScreen = screen;
        setFonts(Assets.getInstance().getAssetManager().get("mainFont16.ttf"), Assets.getInstance().getAssetManager().get("mainFont14.ttf"));
        text = "Upgrades";
    }

    @Override
    protected void onClick() {
        game.setScreen(upgradesScreen);
    }

}
