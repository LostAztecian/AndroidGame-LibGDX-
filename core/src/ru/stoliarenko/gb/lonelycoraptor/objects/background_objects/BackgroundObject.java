package ru.stoliarenko.gb.lonelycoraptor.objects.background_objects;


import org.jetbrains.annotations.NotNull;

import ru.stoliarenko.gb.lonelycoraptor.utils.Sprite;

public abstract class BackgroundObject extends ru.stoliarenko.gb.lonelycoraptor.objects.space_objects.SpaceObject {

    protected BackgroundObject(@NotNull final Sprite img) {
        super(Type.BACKGROUND, img);
    }

}
