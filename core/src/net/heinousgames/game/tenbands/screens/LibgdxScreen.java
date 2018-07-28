package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import net.heinousgames.game.tenbands.MainClass;

/**
 * Created by Steve on 1/17/2016
 */
public class LibgdxScreen implements Screen {

    private final MainClass game;
    private OrthographicCamera camera;

    public LibgdxScreen(final MainClass game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 512, 256);
        camera.position.x = 256;
        camera.position.y = 128;

        this.game.batch.setProjectionMatrix(camera.combined);

        game.quit = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                555, 434, 698-555, 493-434);
        game.back = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                0, 0, 142, 51);
        game.retry = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                169, 0, 348-169, 59);
        game.loading = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                359, 0, 608-359, 54);
        game.credits = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                628, 0, 878-628, 58);
        game.x = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                909, 0, 40, 53);
        game.musicOn = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                0, 64, 290, 115-64);
        game.soundOn = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                314, 64, 602-314, 115-64);
        game.options = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                630, 60, 876-630, 117-60);
        game.musicUnedited = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                0, 125, 512, 185-125);
        game.optionLocked = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                531, 127, 1000-531, 188-127);
        game.levelSelect = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                0, 191, 430, 252-191);
        game.instructions = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                454, 195, 875-454, 250-195);
        game.tenBandsTR = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                0, 260, 434, 340-260);
        game.fiftyBandsTR = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                452, 264, 982-452, 342-264);
        game.hundredBandsTR = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                0, 348, 634, 426-348);
        game.fuckItManTR = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                0, 432, 526, 503-432);
        game.scoreTR = new TextureRegion(game.assetManager.get("gfx/WithMyWoesSheet.png", Texture.class),
                660, 352, 841-660, 412-352);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new HGScreen(game));
                dispose();
            }
        }, 3f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(game.assetManager.get("gfx/libgdxLogo.png", Texture.class), 0, 0);
        game.batch.end();

        camera.update();
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
        game.assetManager.unload("gfx/libgdxLogo.png");
    }
}