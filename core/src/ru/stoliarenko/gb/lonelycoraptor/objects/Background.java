package ru.stoliarenko.gb.lonelycoraptor.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.stoliarenko.gb.lonelycoraptor.base.BackgroundObject;
import ru.stoliarenko.gb.lonelycoraptor.objects.BackgroundObjects.BackgroundStar;
import ru.stoliarenko.gb.lonelycoraptor.utils.ScreenParameters;

public final class Background {
    private static Texture space_texture = new Texture("wp_space.jpg");
    private TextureRegion wallpaper = new TextureRegion(space_texture, (int)(1024 * ScreenParameters.myScreen.getRatio()), 1024);

    @NotNull private final List<BackgroundObject> backgroundObjects = new CopyOnWriteArrayList<>();

    public Background() {
        for (int i = 0; i < 50; i++) {
            backgroundObjects.add(new BackgroundStar());
        }
    }

    public void draw(@NotNull final SpriteBatch batch){
        batch.draw(wallpaper, 0, 0);
        for (int i = 0; i < backgroundObjects.size(); i++) {
            backgroundObjects.get(i).draw(batch);
        }
    }

    public void update(float dt){
        for (int i = 0; i < backgroundObjects.size(); i++) {
            backgroundObjects.get(i).move(dt);
        }
    }

    public void setWallpaper(@NotNull final TextureRegion textureRegion) {
        this.wallpaper = textureRegion;
    }

    public void dispose(){
        space_texture.dispose();
    }
}
