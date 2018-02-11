package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/25/2016.
 */
public class InstructionsScreen extends GenericMenuScreen {
    private float quitWidth, quitHeight;

    public InstructionsScreen(final Game game, String title) {
        super(game, title);

        txtQuit = "Back";
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
                game.setScreen(new TitleScreen(game, "Ten Bands"));
                dispose();
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
        game.fontKenney50.draw(game.batch, "1. Hold your device flat, do not hold it upright as if reading a book.\n2. Rotate the device to move Drake and collect bands.\n3. You've been at the house taking no calls.\n4. You ain't tripping, let them sleep.", 167, 510, 1000, 1, true);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.setScreen(new TitleScreen(game, "Ten Bands"));
            dispose();
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
