package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import net.heinousgames.game.tenbands.MainClass;

/**
 * Created by Steve on 1/17/2016
 */
class HGScreen implements Screen {

    private final MainClass game;
    private OrthographicCamera camera;

    HGScreen(final MainClass game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1024, 512);
        camera.position.x = 512;
        camera.position.y = 256;

        this.game.batch.setProjectionMatrix(camera.combined);

        FileHandleResolver resolver = new InternalFileHandleResolver();
        game.assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        game.assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter parameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parameter.fontFileName = "font/kenvector_future_thin.ttf";
        parameter.fontParameters.color = Color.BLACK;
        parameter.fontParameters.size = 20;
        game.assetManager.load("kenney20.ttf", BitmapFont.class, parameter);

        FreetypeFontLoader.FreeTypeFontLoaderParameter parameter2 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parameter2.fontFileName = "font/kenvector_future_thin.ttf";
        parameter2.fontParameters.color = Color.BLACK;
        parameter2.fontParameters.size = 30;
        game.assetManager.load("kenney30.ttf", BitmapFont.class, parameter2);

        FreetypeFontLoader.FreeTypeFontLoaderParameter parameter3 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parameter3.fontFileName = "font/kenvector_future_thin.ttf";
        parameter3.fontParameters.color = Color.BLACK;
        parameter3.fontParameters.size = 35;
        game.assetManager.load("kenney35.ttf", BitmapFont.class, parameter3);

        FreetypeFontLoader.FreeTypeFontLoaderParameter parameter4 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parameter4.fontFileName = "font/kenvector_future_thin.ttf";
        parameter4.fontParameters.color = Color.BLACK;
        parameter4.fontParameters.size = 45;
        game.assetManager.load("kenney45.ttf", BitmapFont.class, parameter4);

        FreetypeFontLoader.FreeTypeFontLoaderParameter parameter5 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parameter5.fontFileName = "font/kenvector_future_thin.ttf";
        parameter5.fontParameters.color = Color.BLACK;
        parameter5.fontParameters.size = 50;
        game.assetManager.load("kenney50.ttf", BitmapFont.class, parameter5);

        if (game.isNotWeb || game.customMethodsCallback.canGWTPlayOgg()) {
            game.assetManager.load("sfx/song.ogg", Music.class);
            game.assetManager.load("sfx/song_edited.ogg", Music.class);
            game.assetManager.load("sfx/10bands.ogg", Sound.class);
            game.assetManager.load("sfx/50bands.ogg", Sound.class);
            game.assetManager.load("sfx/100bands.ogg", Sound.class);
            game.assetManager.load("sfx/fuckitman.ogg", Sound.class);
            game.assetManager.load("sfx/cribphonesoff.ogg", Sound.class);
            game.assetManager.load("sfx/housenocall.ogg", Sound.class);
            game.assetManager.load("sfx/letemsleep.ogg", Sound.class);
            game.assetManager.load("sfx/rip.ogg", Sound.class);
            game.assetManager.load("sfx/gunshots.ogg", Sound.class);
        } else if (!game.isNotWeb && !game.customMethodsCallback.canGWTPlayOgg()) {
            game.assetManager.load("sfx/song.mp3", Music.class);
            game.assetManager.load("sfx/song_edited.mp3", Music.class);
            game.assetManager.load("sfx/10bands.mp3", Sound.class);
            game.assetManager.load("sfx/50bands.mp3", Sound.class);
            game.assetManager.load("sfx/100bands.mp3", Sound.class);
            game.assetManager.load("sfx/fuckitman.mp3", Sound.class);
            game.assetManager.load("sfx/cribphonesoff.mp3", Sound.class);
            game.assetManager.load("sfx/housenocall.mp3", Sound.class);
            game.assetManager.load("sfx/letemsleep.mp3", Sound.class);
            game.assetManager.load("sfx/rip.mp3", Sound.class);
            game.assetManager.load("sfx/gunshots.mp3", Sound.class);
        }

        game.assetManager.load("gfx/drake.png", Texture.class);
        game.assetManager.load("gfx/arrow.png", Texture.class);
        game.assetManager.load("gfx/phone.png", Texture.class);
        game.assetManager.load("gfx/sleeper.png", Texture.class);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.gl.glClearColor(0.1451f, 0.102f, 0.2f, 1);
        game.batch.begin();
        game.batch.draw(game.assetManager.get("gfx/hgLogo.png", Texture.class), 0, 0);
        //, 256, 128, game.hgLogo.getWidth()/2, game.hgLogo.getHeight()/2);
        game.batch.end();

        camera.update();

        if (game.assetManager.update()) {
            game.walkFrames = new TextureRegion[3];
            game.walkFrames[0] = new TextureRegion(game.assetManager.get("gfx/drake.png", Texture.class), 0, 0, 22, 43);
            game.walkFrames[1] = new TextureRegion(game.assetManager.get("gfx/drake.png", Texture.class), 22, 0, 22, 43);
            game.walkFrames[2] = new TextureRegion(game.assetManager.get("gfx/drake.png", Texture.class), 44, 0, 22, 43);
            game.walkAnimation = new Animation<TextureRegion>(0.15f, game.walkFrames);
            game.band = new TextureRegion(game.assetManager.get("gfx/drake.png", Texture.class), 66, 0, 62, 33);

            game.fontKenney20 = game.assetManager.get("kenney20.ttf", BitmapFont.class);
            game.fontKenney30 = game.assetManager.get("kenney30.ttf", BitmapFont.class);
            game.fontKenney35 = game.assetManager.get("kenney35.ttf", BitmapFont.class);
            game.fontKenney45 = game.assetManager.get("kenney45.ttf", BitmapFont.class);
            game.fontKenney50 = game.assetManager.get("kenney50.ttf", BitmapFont.class);
            if (game.isNotWeb || game.customMethodsCallback.canGWTPlayOgg()) {
                game.tenBands = game.assetManager.get("sfx/10bands.ogg", Sound.class);
                game.fiftyBands = game.assetManager.get("sfx/50bands.ogg", Sound.class);
                game.hundredBands = game.assetManager.get("sfx/100bands.ogg", Sound.class);
                game.fuckItMan = game.assetManager.get("sfx/fuckitman.ogg", Sound.class);
                game.cribPhonesOff = game.assetManager.get("sfx/cribphonesoff.ogg", Sound.class);
                game.houseNoCalls = game.assetManager.get("sfx/housenocall.ogg", Sound.class);
                game.letEmSleep = game.assetManager.get("sfx/letemsleep.ogg", Sound.class);
                game.RIP = game.assetManager.get("sfx/rip.ogg", Sound.class);
                game.gunshots = game.assetManager.get("sfx/gunshots.ogg", Sound.class);
                game.song = game.assetManager.get("sfx/song.ogg", Music.class);
                game.songEdited = game.assetManager.get("sfx/song_edited.ogg", Music.class);
            } else if (!game.isNotWeb && !game.customMethodsCallback.canGWTPlayOgg()) {
                game.tenBands = game.assetManager.get("sfx/10bands.mp3", Sound.class);
                game.fiftyBands = game.assetManager.get("sfx/50bands.mp3", Sound.class);
                game.hundredBands = game.assetManager.get("sfx/100bands.mp3", Sound.class);
                game.fuckItMan = game.assetManager.get("sfx/fuckitman.mp3", Sound.class);
                game.cribPhonesOff = game.assetManager.get("sfx/cribphonesoff.mp3", Sound.class);
                game.houseNoCalls = game.assetManager.get("sfx/housenocall.mp3", Sound.class);
                game.letEmSleep = game.assetManager.get("sfx/letemsleep.mp3", Sound.class);
                game.RIP = game.assetManager.get("sfx/rip.mp3", Sound.class);
                game.gunshots = game.assetManager.get("sfx/gunshots.mp3", Sound.class);
                game.song = game.assetManager.get("sfx/song.mp3", Music.class);
                game.songEdited = game.assetManager.get("sfx/song_edited.mp3", Music.class);
                game.song.setLooping(true);
                game.songEdited.setLooping(true);
            }
            game.setScreen(new TitleScreen(game, game.tenBandsTR));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        game.assetManager.unload("gfx/hgLogo.png");
    }
}