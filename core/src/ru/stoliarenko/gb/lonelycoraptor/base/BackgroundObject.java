package ru.stoliarenko.gb.lonelycoraptor.base;


import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public abstract class BackgroundObject extends SpaceObject {

    protected BackgroundObject(@NotNull final Sprite img) {
        super(Type.BACKGROUND, img);
    }

}
