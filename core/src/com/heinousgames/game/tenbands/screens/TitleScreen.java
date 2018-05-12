package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.heinousgames.game.tenbands.actors.ButtonActor;

public class TitleScreen extends GenericMenuScreen {

    private String txtLevelSelect, txtInstructions, txtOptions, txtCredits;
    private float levelSelectWidth, levelSelectHeight, quitWidth, quitHeight, instructionsWidth,
            creditsWidth, creditsHeight, optionsWidth;

    public TitleScreen(final Game game, String title) {
        super(game, title);

        txtQuit = "Quit";
        layout = new GlyphLayout(game.fontDrake60, txtQuit);
        quitWidth = layout.width;
        quitHeight = layout.height;

        txtLevelSelect = "Level Select";
        layout = new GlyphLayout(game.fontDrake60, txtLevelSelect);
        levelSelectWidth = layout.width;
        levelSelectHeight = layout.height;

        txtInstructions = "Instructions";
        layout = new GlyphLayout(game.fontDrake60, txtInstructions);
        instructionsWidth = layout.width;

        txtOptions = "Options";
        layout = new GlyphLayout(game.fontDrake60, txtOptions);
        optionsWidth = layout.width;

        txtCredits = "Credits";
        layout = new GlyphLayout(game.fontDrake60, txtCredits);
        creditsWidth = layout.width;
        creditsHeight = layout.height;

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

        final ButtonActor btnLevelSelect, btnInstructions, btnCredits, btnOptions;

        btnCredits = new ButtonActor(0, 750 - creditsHeight - 15, creditsWidth + 10, creditsHeight + 15);
        btnCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnCredits.removeListener(this);
                game.setScreen(new CreditsScreen(game, "Credits"));
                dispose();
            }
        });

        btnLevelSelect = new ButtonActor(667 - levelSelectWidth /2, 375 - levelSelectHeight /2, levelSelectWidth + 20, levelSelectHeight + 30);
        btnLevelSelect.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnLevelSelect.removeListener(this);
                game.setScreen(new LevelSelectScreen(game, "Select Level"));
                dispose();
            }
        });

        btnInstructions = new ButtonActor(667 - levelSelectWidth /2, 75 - levelSelectHeight /2, levelSelectWidth + 20, levelSelectHeight + 30);
        btnInstructions.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnInstructions.removeListener(this);
                game.setScreen(new InstructionsScreen(game, "Instructions"));
                dispose();
            }
        });

        btnOptions = new ButtonActor(667 - levelSelectWidth /2, 225 - levelSelectHeight /2, levelSelectWidth + 20, levelSelectHeight + 30);
        btnOptions.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new OptionsScreen(game, "Options"));
                dispose();
            }
        });

        stage.addActor(btnLevelSelect);
        stage.addActor(btnQuit);
        stage.addActor(btnInstructions);
        stage.addActor(btnOptions);
        stage.addActor(btnCredits);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.fontDrake60.draw(game.batch, txtLevelSelect, 667 - levelSelectWidth /2 + 10,  375 - levelSelectHeight /2 + levelSelectHeight + 25);
        game.fontDrake60.draw(game.batch, txtOptions, 667 - optionsWidth /2 + 10,  225 - levelSelectHeight /2 + levelSelectHeight + 25);
        game.fontDrake60.draw(game.batch, txtInstructions, 667 - instructionsWidth /2 + 10,  75 - levelSelectHeight /2 + levelSelectHeight + 25);
        game.fontDrake60.draw(game.batch, txtQuit, 1334 - quitWidth - 5, 750);
        game.fontDrake60.draw(game.batch, txtCredits, 0, 750);
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
    }
}
