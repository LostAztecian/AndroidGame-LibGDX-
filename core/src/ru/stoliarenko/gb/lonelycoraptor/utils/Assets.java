package ru.stoliarenko.gb.lonelycoraptor.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import lombok.Getter;
import ru.stoliarenko.gb.lonelycoraptor.objects.Background;

@Getter
public class Assets {
    private static final Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    private Assets() {
        this.assetManager = new AssetManager();
    }

    private AssetManager assetManager;
    private Background background;

    private TextureAtlas backgroundAtlas = new TextureAtlas("textures/background.pack");
    private TextureAtlas loadingScreenAtlas = new TextureAtlas("textures/foreground.pack");
    private TextureAtlas spaceAtlas;
    private TextureAtlas foregroundAtlas;
    private TextureAtlas explosionsAtlas;

    private Music menuMusic;
    private Music lvl01Music;

    private Sound laserBulletSound;

    public void loadAssets(ScreenManager.Type type) {
        switch (type) {
            case GAME: {
                break;
            }
            case ANY_MENU: {
                break;
            }
        }
        if (spaceAtlas != null) return; //only 1st loading

        generateStdFont(28);
        generateStdFont(32);
        generateStdFont(96);

        assetManager.load("textures/background.pack", TextureAtlas.class);
        assetManager.load("textures/spaceObjects.pack", TextureAtlas.class);
        assetManager.load("textures/foreground.pack", TextureAtlas.class);
        assetManager.load("textures/explosions.pack", TextureAtlas.class);

        assetManager.load("music/music01.mp3", Music.class);
        assetManager.load("music/menuMusic.mp3", Music.class);

        assetManager.load("sounds/laserBullet.mp3", Sound.class);
        assetManager.load("sounds/charging.mp3", Sound.class);
        assetManager.load("sounds/releasing.mp3", Sound.class);
        assetManager.load("sounds/bileExplosion.mp3", Sound.class);
        assetManager.load("sounds/shipDestruction.mp3", Sound.class);
        assetManager.load("sounds/consumable.mp3", Sound.class);

        assetManager.finishLoading();
        makeLinks();
    }

    public void generateStdFont(int size) {
        final FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParameter.fontFileName = "fonts/mainFont.ttf";
        fontParameter.fontParameters.size = size;
        fontParameter.fontParameters.color = Color.BROWN;
        fontParameter.fontParameters.borderWidth = 2;
        fontParameter.fontParameters.borderColor = Color.BLACK;
        fontParameter.fontParameters.shadowOffsetX = 2;
        fontParameter.fontParameters.shadowOffsetY = 2;
        fontParameter.fontParameters.shadowColor = Color.DARK_GRAY;

        assetManager.load("mainFont"+size+".ttf", BitmapFont.class, fontParameter);
    }

    public void makeLinks() {
        backgroundAtlas = assetManager.get("textures/background.pack", TextureAtlas.class);
        spaceAtlas = assetManager.get("textures/spaceObjects.pack", TextureAtlas.class);
        foregroundAtlas = assetManager.get("textures/foreground.pack", TextureAtlas.class);
        explosionsAtlas = assetManager.get("textures/explosions.pack", TextureAtlas.class);

        menuMusic = assetManager.get("music/menuMusic.mp3", Music.class);
        lvl01Music = assetManager.get("music/music01.mp3", Music.class);

        laserBulletSound = assetManager.get("sounds/laserBullet.mp3", Sound.class);

        background = Background.getInstance();
    }

    public void clear() {
        assetManager.clear();
    }

}
