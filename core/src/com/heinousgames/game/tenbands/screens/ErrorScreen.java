package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/16/2016
 */
public class ErrorScreen extends GenericMenuScreen {

    private float quitWidth, quitHeight;
    private String errorStr;

    public ErrorScreen(Game game, String title) {
        super(game, title);

        errorStr = "We are sorry.\nThis device is incompatible with this game.";

        txtQuit = "Quit";
        layout = new GlyphLayout(game.fontDrake60, txtQuit);
        quitWidth = layout.width;
        quitHeight = layout.height;

        btnQuit = new ButtonActor(1334 - quitWidth - 10, 750-quitHeight-15, quitWidth + 10, quitHeight + 15);
        btnQuit.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnQuit.removeListener(this);
                dispose();
                Gdx.app.exit();
            }
        });

        stage.addActor(btnQuit);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        game.batch.begin();
        game.fontDrake60.draw(game.batch, txtQuit, 1334 - quitWidth - 5, 750);
        game.fontDrake60.draw(game.batch, errorStr, 167, 375, 1000, 1, true);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            dispose();
            Gdx.app.exit();
        }
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
