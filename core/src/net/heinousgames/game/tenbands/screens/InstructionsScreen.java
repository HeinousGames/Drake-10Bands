package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/25/2016
 */
class InstructionsScreen extends GenericMenuScreen {
    private float quitWidth, quitHeight, instructionsX, instructionsY, instructionsWidth;

    InstructionsScreen(final MainClass game, TextureRegion tr) {
        super(game, tr);

        quitWidth = game.back.getRegionWidth();
        quitHeight = game.back.getRegionHeight();
        instructionsX = 167;
        instructionsY = 510;
        instructionsWidth = 1000;
        btnQuit = new ButtonActor(stage.getViewport().getScreenWidth() - quitWidth - 10,
                stage.getViewport().getScreenHeight()-quitHeight-15,
                quitWidth + 10, quitHeight + 15, game.assetManager.get("gfx/ui.png", Texture.class));
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
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.batch.draw(game.back, stage.getViewport().getScreenWidth() - quitWidth - 5,
                stage.getViewport().getScreenHeight()-quitHeight-5, quitWidth, quitHeight);
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            game.fontKenney50.draw(game.batch,
                    "1. Hold your device flat and rotate it to move Drake.\n2. Collect the required amount of bands each level to proceed.\n3. You've been at the house taking no calls.\n4. You ain't tripping, let em sleep.",
                    instructionsX, instructionsY, instructionsWidth, 1, true);
        } else if (Gdx.app.getType() == Application.ApplicationType.Desktop ||
                Gdx.app.getType() == Application.ApplicationType.WebGL) {
            if (game.customMethodsCallback.getClientWindowSize()[0] < 1000 ||
                    game.customMethodsCallback.getClientWindowSize()[1] < 600) {
                game.fontKenney35.draw(game.batch,
                        "1. Use the arrow or WASD keys to move Drake.\n2. Collect the required amount of bands each level to proceed.\n3. You've been at the house taking no calls.\n4. You ain't tripping, let em sleep.",
                        instructionsX, instructionsY, instructionsWidth, 1, true);
            } else {
                game.fontKenney50.draw(game.batch,
                        "1. Use the arrow or WASD keys to move Drake.\n2. Collect the required amount of bands each level to proceed.\n3. You've been at the house taking no calls.\n4. You ain't tripping, let em sleep.",
                        instructionsX, instructionsY, instructionsWidth, 1, true);
            }
        }
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)||
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
        instructionsX *= resizeRatioWidth;
        instructionsY *= resizeRatioHeight;
        instructionsWidth *= resizeRatioWidth;

        if (btnQuit != null) {
            btnQuit.setBounds(stage.getViewport().getScreenWidth() - quitWidth - 10,
                    stage.getViewport().getScreenHeight() - quitHeight - 15,
                    quitWidth + 10, quitHeight + 15);
        }
    }

}
