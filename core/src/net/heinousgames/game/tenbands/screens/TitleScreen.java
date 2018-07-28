package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.ButtonActor;

class TitleScreen extends GenericMenuScreen {

    private ButtonActor btnInstructions, btnOptions, btnCredits, btnLevelSelect;
    private float levelSelectWidth, levelSelectHeight, quitWidth, quitHeight, instructionsWidth,
            instructionsHeight, optionsWidth, optionsHeight, creditsWidth, creditsHeight, levelSelectX,
            levelSelectY, optionsX, optionsY, instructionsX, instructionsY, btnWidth, btnHeight;

    TitleScreen(final MainClass game, TextureRegion tr) {
        super(game, tr);

        quitWidth = game.quit.getRegionWidth();
        quitHeight = game.quit.getRegionHeight();

        levelSelectWidth = game.levelSelect.getRegionWidth();
        levelSelectHeight = game.levelSelect.getRegionHeight();
        levelSelectX = stage.getViewport().getScreenWidth()/2 - levelSelectWidth/2;
        levelSelectY = stage.getViewport().getScreenHeight()/2 - levelSelectHeight/2;

        instructionsWidth = game.instructions.getRegionWidth();
        instructionsHeight = game.instructions.getRegionHeight();
        instructionsX = stage.getViewport().getScreenWidth()/2 - levelSelectWidth/2;
        instructionsY = stage.getViewport().getScreenHeight()/2-300 - levelSelectHeight/2;

        optionsWidth = game.options.getRegionWidth();
        optionsHeight = game.options.getRegionHeight();
        optionsX = stage.getViewport().getScreenWidth()/2 - levelSelectWidth/2;
        optionsY = stage.getViewport().getScreenHeight()/2-150 - levelSelectHeight/2;

        creditsWidth = game.credits.getRegionWidth();
        creditsHeight = game.credits.getRegionHeight();

        btnWidth = levelSelectWidth+20;
        btnHeight = levelSelectHeight+30;

        int[] sizes = game.customMethodsCallback.getClientWindowSize();
        resize(sizes[0], sizes[1]);

        //todo why remove only one listen and dispose? shouldn't i remove all listeners?

        if (game.isNotWeb) {
            btnQuit = new ButtonActor(stage.getViewport().getScreenWidth() - quitWidth - 10,
                    stage.getViewport().getScreenHeight() - creditsHeight - 15, quitWidth + 10,
                    creditsHeight, game.assetManager.get("gfx/ui.png", Texture.class));
            btnQuit.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    btnQuit.removeListener(this);
                    Gdx.app.exit();
                }
            });
        }

        btnCredits = new ButtonActor(0, stage.getViewport().getScreenHeight() - creditsHeight - 15,
                creditsWidth, creditsHeight, game.assetManager.get("gfx/ui.png", Texture.class));
        btnCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnCredits.removeListener(this);
                game.setScreen(new CreditsScreen(game, game.credits));
            }
        });

        btnLevelSelect = new ButtonActor(levelSelectX, levelSelectY, btnWidth, btnHeight,
                game.assetManager.get("gfx/ui.png", Texture.class));
        btnLevelSelect.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnLevelSelect.removeListener(this);
                game.setScreen(new LevelSelectScreen(game, game.levelSelect));
                dispose();
            }
        });

        btnInstructions = new ButtonActor(instructionsX, instructionsY, btnWidth, btnHeight,
                game.assetManager.get("gfx/ui.png", Texture.class));
        btnInstructions.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnInstructions.removeListener(this);
                game.setScreen(new InstructionsScreen(game, game.instructions));
            }
        });

        btnOptions = new ButtonActor(optionsX, optionsY, btnWidth, btnHeight,
                game.assetManager.get("gfx/ui.png", Texture.class));
        btnOptions.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnOptions.removeListener(this);
                game.setScreen(new OptionsScreen(game, game.options));
            }
        });

        stage.addActor(btnLevelSelect);
        stage.addActor(btnInstructions);
        stage.addActor(btnOptions);
        stage.addActor(btnCredits);
        if (game.isNotWeb)
            stage.addActor(btnQuit);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
            game.batch.draw(game.levelSelect, levelSelectX + 8, levelSelectY + levelSelectHeight/3 - 8,
                    levelSelectWidth, levelSelectHeight);
            game.batch.draw(game.options, optionsX + optionsWidth/3 + 15, optionsY + optionsHeight/3,
                    optionsWidth, optionsHeight);
            game.batch.draw(game.instructions, instructionsX+15, instructionsY + instructionsHeight/3,
                    instructionsWidth, instructionsHeight);
            game.batch.draw(game.credits, 0, stage.getViewport().getScreenHeight()-creditsHeight-10,
                    creditsWidth, creditsHeight);
            if (game.isNotWeb)
                game.batch.draw(game.quit, stage.getViewport().getScreenWidth() - quitWidth - 5,
                        stage.getViewport().getScreenHeight()-quitHeight-10, quitWidth, quitHeight);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) ||
                Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        quitWidth *= resizeRatioWidth;
        quitHeight *= resizeRatioHeight;
        levelSelectWidth *= resizeRatioWidth;
        levelSelectHeight *= resizeRatioHeight;
        levelSelectX *= resizeRatioWidth;
        levelSelectY *= resizeRatioHeight;
        instructionsX *= resizeRatioWidth;
        instructionsY *= resizeRatioHeight;
        optionsX *= resizeRatioWidth;
        optionsY *= resizeRatioHeight;
        creditsWidth *= resizeRatioWidth;
        creditsHeight *= resizeRatioHeight;
        instructionsWidth *= resizeRatioWidth;
        instructionsHeight *= resizeRatioHeight;
        optionsWidth *= resizeRatioWidth;
        optionsHeight *= resizeRatioHeight;
        btnWidth *= resizeRatioWidth;
        btnHeight *= resizeRatioHeight;

        if (btnQuit != null) {
            btnQuit.setBounds(stage.getViewport().getScreenWidth() - quitWidth-10,
                    stage.getViewport().getScreenHeight() - creditsHeight - 15,
                    quitWidth + 10, creditsHeight + 15);
        }

        if (btnLevelSelect != null) {
            btnLevelSelect.setBounds(levelSelectX, levelSelectY, btnWidth, btnHeight);
        }

        if (btnInstructions != null) {
            btnInstructions.setBounds(instructionsX, instructionsY, btnWidth, btnHeight);
        }

        if (btnOptions != null) {
            btnOptions.setBounds(optionsX, optionsY, btnWidth, btnHeight);
        }

        if (btnCredits != null) {
            btnCredits.setBounds(0, stage.getViewport().getScreenHeight() - creditsHeight - 15,
                    creditsWidth + 10, creditsHeight + 15);
        }
    }
}
