package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.ArrowActor;
import net.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/23/2016
 */
class CreditsScreen extends GenericMenuScreen {

    private ArrowActor leftArrow, rightArrow;
    private float quitWidth, quitHeight, creditsWidth, arrowWidth, arrowHeight,
            leftArrowX, rightArrowX, arrowY, creditsTextX, creditsTextY;
    private int creditsPosition;
    private String txtCredits[];

    CreditsScreen(final MainClass game, TextureRegion tr) {
        super(game, tr);
        quitWidth = game.back.getRegionWidth();
        quitHeight = game.back.getRegionHeight();
        txtCredits = new String[]{"Programming Help:\nRoss King\n\nTesting:\nCecilia Gardner\nRoss King",
                "10 Bands\n\nWritten by\nAubrey Graham\nMatthew Samuels\nRupert \"Sevn\" Thomas, Jr.\nQuentin Miller\nAdam Feeney\nStephen \"Ransom\" Hacker\n\n" +
                        "Produced By\nBoi-1da\nRupert \"Sevn\" Thomas, Jr.",
                "Drake Art obtained from\nEmilio D'Angelo\n\nOther art obtained from\nOpenGameArt.org\n\nKenney.nl\nSkab\nPhotoshopwizard\nCTatz (@CamTatz via Twitter)\nDelapouite"};
        leftArrowX = arrowWidth = arrowHeight = 128;
        rightArrowX = stage.getViewport().getScreenWidth() - 256;
        arrowY = stage.getViewport().getScreenHeight()/2 - 64;
        creditsTextX = leftArrowX + 39;
        creditsTextY = stage.getViewport().getScreenHeight()-220;
        creditsWidth = 1000;
        btnQuit = new ButtonActor(stage.getViewport().getScreenWidth()-quitWidth-10,
                stage.getViewport().getScreenHeight()-quitHeight-15,
                quitWidth+10, quitHeight+15, game.assetManager.get("gfx/ui.png", Texture.class));
        btnQuit.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnQuit.removeListener(this);
                game.setScreen(new TitleScreen(game, game.tenBandsTR));
            }
        });
        stage.addActor(btnQuit);

        leftArrow = new ArrowActor(leftArrowX, arrowY, arrowWidth, arrowHeight, 9,
                game.assetManager.get("gfx/arrow.png", Texture.class));
        rightArrow = new ArrowActor(rightArrowX, arrowY, arrowWidth, arrowHeight, 3,
                game.assetManager.get("gfx/arrow.png", Texture.class));

        leftArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                creditsPosition--;
                if (creditsPosition == -1) {
                    creditsPosition = 2;
                }
            }
        });

        rightArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                creditsPosition++;
                if (creditsPosition == 3) {
                    creditsPosition = 0;
                }
            }
        });

        stage.addActor(leftArrow);
        stage.addActor(rightArrow);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.batch.draw(game.back, stage.getViewport().getScreenWidth() - quitWidth - 5,
                stage.getViewport().getScreenHeight()-quitHeight-5, quitWidth, quitHeight);

        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            game.fontKenney35.draw(game.batch, txtCredits[creditsPosition], creditsTextX, creditsTextY,
                    creditsWidth, 1, true);
        } else if (Gdx.app.getType() == Application.ApplicationType.Desktop ||
                Gdx.app.getType() == Application.ApplicationType.WebGL) {
            if (game.customMethodsCallback.getClientWindowSize()[0] < 1000 ||
                    game.customMethodsCallback.getClientWindowSize()[1] < 600) {
                game.fontKenney20.draw(game.batch, txtCredits[creditsPosition], creditsTextX, creditsTextY,
                        creditsWidth, 1, true);
            } else {
                game.fontKenney35.draw(game.batch, txtCredits[creditsPosition], creditsTextX, creditsTextY,
                        creditsWidth, 1, true);
            }
        }
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) ||
                Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new TitleScreen(game, game.tenBandsTR));
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        quitWidth *= resizeRatioWidth;
        quitHeight *= resizeRatioHeight;
        creditsWidth *= resizeRatioWidth;
        arrowWidth *= resizeRatioWidth;
        arrowHeight *= resizeRatioHeight;
        leftArrowX *= resizeRatioWidth;
        rightArrowX *= resizeRatioWidth;
        arrowY *= resizeRatioHeight;
        creditsTextX *= resizeRatioWidth;
        creditsTextY *= resizeRatioHeight;

        if (btnQuit != null) {
            btnQuit.setBounds(stage.getViewport().getScreenWidth() - quitWidth - 10,
                    stage.getViewport().getScreenHeight() - quitHeight - 15,
                    quitWidth + 10, quitHeight + 15);
        }

        if (leftArrow != null) {
            leftArrow.setBounds(leftArrowX, arrowY, arrowWidth, arrowHeight);
        }

        if (rightArrow != null) {
            rightArrow.setBounds(rightArrowX, arrowY, arrowWidth, arrowHeight);
        }
    }

}
