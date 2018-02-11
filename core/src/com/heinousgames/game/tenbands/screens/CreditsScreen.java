package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.heinousgames.game.tenbands.actors.ArrowActor;
import com.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/23/2016.
 */
public class CreditsScreen extends GenericMenuScreen {

    private float quitWidth, quitHeight;
    private ArrowActor leftArrow, rightArrow;
    private String txtCredits;
    private boolean isMusicCredits;

    public CreditsScreen(final Game game, String title) {
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

        leftArrow = new ArrowActor(128, 375 - 64, 128, 128, 9);
        rightArrow = new ArrowActor(1334-256, 375 - 64, 128, 128, 3);

        leftArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setTxtCredits();
            }
        });

        rightArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setTxtCredits();
            }
        });

        stage.addActor(leftArrow);
        stage.addActor(rightArrow);

        setTxtCredits();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.fontDrake60.draw(game.batch, txtQuit, 1334 - quitWidth - 5, 750);
        game.fontKenney35.draw(game.batch, txtCredits, 167, 500, 1000, 1, true);
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

    private void setTxtCredits() {
        isMusicCredits = !isMusicCredits;
        if (isMusicCredits) {
            txtCredits = "10 Bands\n\nWritten by\nAubrey Graham\nMatthew Samuels\nRupert \"Sevn\" Thomas, Jr.\nQuentin Miller\nAdam Feeney\nStephen \"Ransom\" Hacker\n\n" +
                    "Produced By\nBoi-1da\nRupert \"Sevn\" Thomas, Jr.";
        } else {
            txtCredits = "Drake Art obtained from\nEmilio D'Angelo\n\nOther art obtained from\nOpenGameArt.org\n\nKenney.nl\nSkab\nPhotoshopwizard\nCTatz (@CamTatz via Twitter)\nDelapouite";
        }
    }
}
