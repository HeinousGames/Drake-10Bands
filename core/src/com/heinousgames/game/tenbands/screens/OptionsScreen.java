package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/23/2016
 */
public class OptionsScreen extends GenericMenuScreen {

    private float quitWidth, quitHeight, musicWidth, musicHeight, soundHeight, editedHeight;
    private String txtMusic, txtSound, txtEdited;
    private ButtonActor btnMusic, btnSound, btnEdited;

    public OptionsScreen(final Game game, String title) {
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

        txtMusic = "Music On";
        txtSound = "Sound On";

        layout = new GlyphLayout(game.fontDrake60, txtMusic);
        musicWidth = layout.width;
        musicHeight = layout.height;

        layout = new GlyphLayout(game.fontDrake60, txtSound);
        soundHeight = layout.height;

        btnMusic = new ButtonActor(1334 - 667/2 - soundHeight/2, 475 - soundHeight/2, musicHeight, musicHeight);
        btnSound = new ButtonActor(1334 - 667/2 - soundHeight/2, 375 - soundHeight/2, soundHeight, soundHeight);
        btnEdited = new ButtonActor(1334 - 667/2 - soundHeight/2, 275 - soundHeight/2, soundHeight, soundHeight);

        btnMusic.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.prefs.putBoolean("musicOn", !game.prefs.getBoolean("musicOn")).flush();
            }
        });

        btnSound.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.prefs.putBoolean("soundOn", !game.prefs.getBoolean("soundOn")).flush();
            }
        });

        btnEdited.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.prefs.putBoolean("uneditedOn", !game.prefs.getBoolean("uneditedOn")).flush();
            }
        });

        stage.addActor(btnMusic);
        stage.addActor(btnSound);

        if (game.prefs.getString("level50complete").equalsIgnoreCase("Hundred Bands")) {
            txtEdited = "Music Unedited";
            stage.addActor(btnEdited);
        } else {
            txtEdited = "Option Locked";
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        super.render(delta);

        game.batch.begin();
        game.fontDrake60.draw(game.batch, txtQuit, 1334 - quitWidth - 5, 750);

        game.fontDrake60.draw(game.batch, txtMusic, 667/2 - musicWidth/2, 475 + soundHeight/2);
        game.fontDrake60.draw(game.batch, txtSound, 667/2 - musicWidth/2, 375 + soundHeight/2);
        game.fontDrake60.draw(game.batch, txtEdited, 667/2 - musicWidth/2, 275 + soundHeight/2);

        if (game.prefs.getBoolean("musicOn", true)) {
            game.fontDrake60.draw(game.batch, "x", 1334 - 667/2 - soundHeight/2 + 5, 475 + soundHeight/2 + 5);
        }

        if (game.prefs.getBoolean("soundOn", true)) {
            game.fontDrake60.draw(game.batch, "x", 1334 - 667/2 - soundHeight/2 + 5, 375 + soundHeight/2 + 5);
        }

        if (game.prefs.getString("level50complete").equalsIgnoreCase("Hundred Bands")) {
            if (game.prefs.getBoolean("uneditedOn")) {
                game.fontDrake60.draw(game.batch, "x", 1334 - 667/2 - soundHeight/2 + 5, 275 + soundHeight/2 + 5);
            }
        }

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
