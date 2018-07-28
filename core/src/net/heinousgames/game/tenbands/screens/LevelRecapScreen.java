package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.ButtonActor;

import java.util.Date;

class LevelRecapScreen extends GenericMenuScreen {

    private ButtonActor btnRetry;
    private float quitWidth, quitHeight, bandsCollectedWidth, timeSpentWidth, unlockedWidth,
            bandsCollectedHeight, retryWidth, retryHeight, bandsTextX, bandsTextY, timeSpentX,
            timeSpentY, unlockedX, unlockedY;
    private int lastBandLevel;
    private String txtTimeSpent, txtBandsCollected, txtUnlocked;

    LevelRecapScreen(final MainClass game, TextureRegion tr, final int lastBandLevel,
                     int bandsCollected, long timeSpent, boolean levelWon) {
        super(game, tr);

        quitWidth = game.back.getRegionWidth();
        quitHeight = game.back.getRegionHeight();
        retryWidth = game.retry.getRegionWidth();
        retryHeight = game.retry.getRegionHeight();
        this.lastBandLevel = lastBandLevel;

        Date date = new Date(timeSpent);
        String timeFormatted = game.customMethodsCallback.convertDate(date);

        txtBandsCollected = "Bands Collected: " + bandsCollected;
        layout = new GlyphLayout(game.fontKenney45, txtBandsCollected);
        bandsCollectedWidth = layout.width;
        bandsCollectedHeight = layout.height;

        txtTimeSpent = "Time Spent: " + timeFormatted;
        layout = new GlyphLayout(game.fontKenney45, txtTimeSpent);
        timeSpentWidth = layout.width;

        txtUnlocked = "";

        if (levelWon) {
            if (lastBandLevel == 10 && game.prefs.getBoolean("firstWinOn10", true)) {
                game.prefs.putBoolean("firstWinOn10", false).flush();
                game.prefs.putString("level10complete", "Fifty Bands").flush();
                txtUnlocked = "New Level Unlocked: 50 Bands";
                layout = new GlyphLayout(game.fontKenney45, txtUnlocked);
            } else if (lastBandLevel == 50 && game.prefs.getBoolean("firstWinOn50", true)) {
                game.prefs.putBoolean("firstWinOn50", false).flush();
                game.prefs.putString("level50complete", "Hundred Bands").flush();
                txtUnlocked = "New Level Unlocked: 100 Bands\nNew Option Unlocked: Unedited Music";
                layout = new GlyphLayout(game.fontKenney45, txtUnlocked);
            } else if (lastBandLevel == 100 && game.prefs.getBoolean("firstWinOn100", true)) {
                game.prefs.putBoolean("firstWinOn100", false).flush();
                game.prefs.putString("level100complete", "Fuck It Man").flush();
                txtUnlocked = "New Level Unlocked: Fuck It Man";
                layout = new GlyphLayout(game.fontKenney45, txtUnlocked);
            } else if (lastBandLevel == Integer.MAX_VALUE && game.prefs.getBoolean("firstWinOnMAX", true)) {
                game.prefs.putBoolean("firstWinOnMAX", false).flush();
                txtUnlocked = "WOW You Beat the Fucking MainClass!";
                layout = new GlyphLayout(game.fontKenney45, txtUnlocked);
            }
        }
        unlockedWidth = layout.width;

        bandsTextX = stage.getViewport().getScreenWidth()/2 - bandsCollectedWidth / 2;
        bandsTextY = stage.getViewport().getScreenHeight()/2+100 + bandsCollectedHeight/2;
        timeSpentX = stage.getViewport().getScreenWidth()/2 - timeSpentWidth / 2;
        timeSpentY = stage.getViewport().getScreenHeight()/2 + bandsCollectedHeight/2;
        unlockedX = stage.getViewport().getScreenWidth()/2 - unlockedWidth / 2;
        unlockedY = stage.getViewport().getScreenHeight()/2-100 + bandsCollectedHeight/2;

        btnQuit = new ButtonActor(stage.getViewport().getScreenWidth() - quitWidth - 10,
                stage.getViewport().getScreenHeight() - quitHeight - 15,
                quitWidth + 10, quitHeight + 15,
                game.assetManager.get("gfx/ui.png", Texture.class));
        btnQuit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnQuit.removeListener(this);
                game.setScreen(new LevelSelectScreen(game, game.levelSelect));
            }
        });
        stage.addActor(btnQuit);

        btnRetry = new ButtonActor(0,
                stage.getViewport().getScreenHeight() - retryHeight - 15, retryWidth + 10,
                retryHeight + 15, game.assetManager.get("gfx/ui.png", Texture.class));
        btnRetry.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnRetry.removeListener(this);
                if (lastBandLevel == 10)
                    game.setScreen(new Level10Bands(game));
                else if (lastBandLevel == 50)
                    game.setScreen(new Level50Bands(game));
                else if (lastBandLevel == 100)
                    game.setScreen(new Level100Bands(game));
                else if (lastBandLevel == Integer.MAX_VALUE)
                    game.setScreen(new LevelFuckItMan(game));
            }
        });
        stage.addActor(btnRetry);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.batch.draw(game.back, stage.getViewport().getScreenWidth() - quitWidth - 5,
                stage.getViewport().getScreenHeight()-quitHeight-5, quitWidth, quitHeight);
        game.batch.draw(game.retry, 5, stage.getViewport().getScreenHeight()-retryHeight-5,
                retryWidth, retryHeight);
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            game.fontKenney45.draw(game.batch, txtBandsCollected, bandsTextX, bandsTextY);
            game.fontKenney45.draw(game.batch, txtTimeSpent, timeSpentX, timeSpentY);
            if (!txtUnlocked.equals(""))
                game.fontKenney45.draw(game.batch, txtUnlocked, unlockedX, unlockedY);
        } else if (Gdx.app.getType() == Application.ApplicationType.Desktop ||
                Gdx.app.getType() == Application.ApplicationType.WebGL) {
            if (game.customMethodsCallback.getClientWindowSize()[0] < 1000 ||
                    game.customMethodsCallback.getClientWindowSize()[1] < 600) {
                game.fontKenney30.draw(game.batch, txtBandsCollected, bandsTextX, bandsTextY);
                game.fontKenney30.draw(game.batch, txtTimeSpent, timeSpentX, timeSpentY);
                if (!txtUnlocked.equals(""))
                    game.fontKenney30.draw(game.batch, txtUnlocked, unlockedX, unlockedY);
            } else {
                game.fontKenney45.draw(game.batch, txtBandsCollected, bandsTextX, bandsTextY);
                game.fontKenney45.draw(game.batch, txtTimeSpent, timeSpentX, timeSpentY);
                if (!txtUnlocked.equals(""))
                    game.fontKenney45.draw(game.batch, txtUnlocked, unlockedX, unlockedY);
            }
        }
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) ||
                Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new LevelSelectScreen(game, game.levelSelect));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.R) ||
                Gdx.input.isKeyJustPressed(Input.Keys.SPACE) ||
                Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (lastBandLevel == 10)
                game.setScreen(new Level10Bands(game));
            else if (lastBandLevel == 50)
                game.setScreen(new Level50Bands(game));
            else if (lastBandLevel == 100)
                game.setScreen(new Level100Bands(game));
            else if (lastBandLevel == Integer.MAX_VALUE)
                game.setScreen(new LevelFuckItMan(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        quitWidth *= resizeRatioWidth;
        quitHeight *= resizeRatioHeight;
        bandsCollectedWidth *= resizeRatioWidth;
        bandsCollectedHeight *= resizeRatioHeight;
        bandsTextX *= resizeRatioWidth;
        bandsTextY *= resizeRatioHeight;
        timeSpentX *= resizeRatioWidth;
        timeSpentY *= resizeRatioHeight;
        unlockedX *= resizeRatioWidth;
        unlockedY *= resizeRatioHeight;
        retryWidth *= resizeRatioWidth;
        retryHeight *= resizeRatioHeight;

        if (btnQuit != null) {
            btnQuit.setBounds(stage.getViewport().getScreenWidth() - quitWidth - 10,
                    stage.getViewport().getScreenHeight() - quitHeight - 15,
                    quitWidth + 10, quitHeight + 15);
        }

        if (btnRetry != null) {
            btnRetry.setBounds(0, stage.getViewport().getScreenHeight() - retryHeight - 15,
                    retryWidth + 10, retryHeight + 15);
        }
    }

}
