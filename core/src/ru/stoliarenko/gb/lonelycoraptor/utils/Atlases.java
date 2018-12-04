package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public final class Atlases {

    public static TextureAtlas background;
    public static TextureAtlas space;
    public static TextureAtlas foreground;

    public static void disposeAll() {
        if (background != null) background.dispose();
        if (space != null) space.dispose();
        if (foreground != null) foreground.dispose();
    }

}
