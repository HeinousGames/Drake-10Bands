package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/24/2016
 */
public class GenericMenuScreen implements Screen {

    private Camera camera;
    private String title;

    public Game game;
    public Stage stage;
    public ButtonActor btnQuit;
    public GlyphLayout layout;
    public String txtQuit;
    public float titleWidth;

    public GenericMenuScreen(Game game, String title) {
        this.game = game;
        this.title = title;

        camera = new OrthographicCamera(1334, 750);
        camera.position.x = 667;
        camera.position.y = 375;

        stage = new Stage(new ScreenViewport());
        stage.getViewport().setCamera(camera);
        Gdx.input.setInputProcessor(stage);

        layout = new GlyphLayout(game.fontDrake60, title);
        titleWidth = layout.width;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.draw();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.fontDrake60.draw(game.batch, title, 667 - titleWidth/2, 650);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
