package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.ButtonActor;

/**
 * Created by Steve on 4/23/2016
 */
class OptionsScreen extends GenericMenuScreen {

    private ButtonActor btnMusic, btnSound, btnEdited;
    private float quitWidth, quitHeight, musicWidth, soundWidth, soundHeight, musicHeight,
            btnMusicX, btnMusicY, btnSoundX, btnSoundY, btnEditedX, btnEditedY, editedWidth,
            editedHeight, optionWidth, optionHeight, xWidth, xHeight;

    OptionsScreen(final MainClass game, TextureRegion tr) {
        super(game, tr);
        quitWidth = game.back.getRegionWidth();
        quitHeight = game.back.getRegionHeight();
        musicWidth = game.musicOn.getRegionWidth();
        musicHeight = game.musicOn.getRegionHeight();
        soundWidth = game.soundOn.getRegionWidth();
        soundHeight = game.soundOn.getRegionHeight();
        editedWidth = game.musicUnedited.getRegionWidth();
        editedHeight = game.musicUnedited.getRegionHeight();
        optionWidth = game.optionLocked.getRegionWidth();
        optionHeight = game.optionLocked.getRegionHeight();
        xWidth = game.x.getRegionWidth();
        xHeight = game.x.getRegionHeight();

        btnMusicX = stage.getViewport().getScreenWidth() - stage.getViewport().getScreenWidth()/4 - soundHeight/2;
        btnMusicY = stage.getViewport().getScreenHeight()/2+100 - soundHeight/2;
        btnSoundX = stage.getViewport().getScreenWidth() - stage.getViewport().getScreenWidth()/4 - soundHeight/2;
        btnSoundY = stage.getViewport().getScreenHeight()/2 - soundHeight/2;
        btnEditedX = stage.getViewport().getScreenWidth() - stage.getViewport().getScreenWidth()/4 - soundHeight/2;
        btnEditedY = stage.getViewport().getScreenHeight()/2-100 - soundHeight/2;
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
        btnMusic = new ButtonActor(btnMusicX, btnMusicY, musicHeight, musicHeight,
                game.assetManager.get("gfx/ui.png", Texture.class));
        btnSound = new ButtonActor(btnSoundX, btnSoundY, soundHeight, soundHeight,
                game.assetManager.get("gfx/ui.png", Texture.class));
        btnEdited = new ButtonActor(btnEditedX, btnEditedY, soundHeight, soundHeight,
                game.assetManager.get("gfx/ui.png", Texture.class));

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

        stage.addActor(btnMusic);
        stage.addActor(btnSound);

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

        if (game.prefs.getString("level50complete").equalsIgnoreCase("Hundred Bands")) {
            stage.addActor(btnEdited);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.batch.draw(game.back, stage.getViewport().getScreenWidth() - quitWidth - 5,
                stage.getViewport().getScreenHeight()-quitHeight-5, quitWidth, quitHeight);

        game.batch.draw(game.musicOn, stage.getViewport().getScreenWidth()/4 - musicWidth/2,
                btnMusicY, musicWidth, musicHeight);
        game.batch.draw(game.soundOn, stage.getViewport().getScreenWidth()/4 - musicWidth/2,
                btnSoundY, soundWidth, soundHeight);

        if (game.prefs.getString("level50complete").equalsIgnoreCase("Hundred Bands")) {
            game.batch.draw(game.musicUnedited, stage.getViewport().getScreenWidth()/4 - musicWidth/2,
                    btnEditedY, editedWidth, editedHeight);
        } else {
            game.batch.draw(game.optionLocked, stage.getViewport().getScreenWidth()/4 - musicWidth/2,
                    btnEditedY, optionWidth, optionHeight);
        }

        if (game.prefs.getBoolean("musicOn", true)) {
            game.batch.draw(game.x,
                    stage.getViewport().getScreenWidth() - stage.getViewport().getScreenWidth()/4 - soundHeight/2 + 5,
                    btnMusicY, xWidth, xHeight);
        }

        if (game.prefs.getBoolean("soundOn", true)) {
            game.batch.draw(game.x,
                    stage.getViewport().getScreenWidth() - stage.getViewport().getScreenWidth()/4 - soundHeight/2 + 5,
                    btnSoundY, xWidth, xHeight);
        }

        if (game.prefs.getString("level50complete").equalsIgnoreCase("Hundred Bands")) {
            if (game.prefs.getBoolean("uneditedOn")) {
                game.batch.draw(game.x,
                        stage.getViewport().getScreenWidth() - stage.getViewport().getScreenWidth()/4 - soundHeight/2 + 5,
                        btnEditedY, xWidth, xHeight);
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
        btnMusicX *= resizeRatioWidth;
        btnMusicY *= resizeRatioHeight;
        btnSoundX *= resizeRatioWidth;
        btnSoundY *= resizeRatioHeight;
        btnEditedX *= resizeRatioWidth;
        btnEditedY *= resizeRatioHeight;

        musicWidth *= resizeRatioWidth;
        musicHeight *= resizeRatioHeight;
        soundWidth *= resizeRatioWidth;
        soundHeight *= resizeRatioHeight;

        editedWidth *= resizeRatioWidth;
        editedHeight *= resizeRatioHeight;
        optionWidth *= resizeRatioWidth;
        optionHeight *= resizeRatioHeight;
        xWidth *= resizeRatioWidth;
        xHeight *= resizeRatioHeight;

        if (btnQuit != null) {
            btnQuit.setBounds(stage.getViewport().getScreenWidth() - quitWidth - 10,
                    stage.getViewport().getScreenHeight() - quitHeight - 15,
                    quitWidth + 10, quitHeight + 15);
        }

        if (btnMusic != null) {
            btnMusic.setBounds(btnMusicX, btnMusicY, musicHeight, musicHeight);
        }

        if (btnSound != null) {
            btnSound.setBounds(btnSoundX, btnSoundY, soundHeight, soundHeight);
        }

        if (btnEdited != null) {
            btnEdited.setBounds(btnEditedX, btnEditedY, soundHeight, soundHeight);
        }
    }

}
