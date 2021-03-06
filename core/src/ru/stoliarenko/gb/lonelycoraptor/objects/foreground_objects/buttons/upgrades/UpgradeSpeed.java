package ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.upgrades;

import com.badlogic.gdx.math.Vector2;

import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Assets;
import ru.stoliarenko.gb.lonelycoraptor.objects.foreground_objects.buttons.Button;
import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public final class UpgradeSpeed extends Button {
    private ru.stoliarenko.gb.lonelycoraptor.screen.UpgradesScreen us;

    public UpgradeSpeed(@NotNull ru.stoliarenko.gb.lonelycoraptor.SpaceSurvivor game, @NotNull Vector2 position, @NotNull ru.stoliarenko.gb.lonelycoraptor.screen.UpgradesScreen us) {
        super(game, new Sprite(Assets.getInstance().getForegroundAtlas().findRegion("rectangleBrown"), 0.7f), position);
        this.us = us;
        text = "add speed";
    }

    @Override
    protected void onClick() {
        us.upgradeSpeed();
    }
}
