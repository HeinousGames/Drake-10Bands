package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/24/2016
 */
class GenericMenuScreen implements Screen {

    private Camera camera;
    private float titleWidth, titleHeight, titleX, titleY;
    private TextureRegion tr;

    public MainClass game;
    public Stage stage;

    ButtonActor btnQuit;
    float resizeRatioWidth, resizeRatioHeight;
    GlyphLayout layout;

    GenericMenuScreen(MainClass game, TextureRegion tr) {
        this.game = game;
        camera = new OrthographicCamera(1334, 750);
        camera.position.x = 667;
        camera.position.y = 375;
        stage = new Stage(new ScreenViewport());
        stage.getViewport().setCamera(camera);
        Gdx.input.setInputProcessor(stage);
        resize(1334, 750);
        this.tr = tr;
        titleWidth = tr.getRegionWidth();
        titleHeight = tr.getRegionHeight();
        titleX = stage.getViewport().getScreenWidth()/2 - titleWidth/2;
        titleY = stage.getViewport().getScreenHeight()-150;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.draw();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(tr, titleX, titleY, titleWidth, titleHeight);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float screenWidth = stage.getViewport().getScreenWidth();
        float screenHeight = stage.getViewport().getScreenHeight();

        resizeRatioWidth = (float) width / screenWidth;
        resizeRatioHeight = (float) height / screenHeight;

        titleWidth *= resizeRatioWidth;
        titleHeight *= resizeRatioHeight;
        titleX *= resizeRatioWidth;
        titleY *= resizeRatioHeight;

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
