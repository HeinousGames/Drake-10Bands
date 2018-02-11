package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.heinousgames.game.tenbands.actors.ButtonActor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LevelRecapScreen extends GenericMenuScreen {
    private float quitWidth, quitHeight, bandsCollectedWidth, timeSpentWidth, unlockedWidth, bandsCollectedHeight;
    private String txtTimeSpent, txtBandsCollected, txtUnlocked;

	public LevelRecapScreen(final Game game, String title, final int lastBandLevel, int bandsCollected,
                            long timeSpent, boolean levelWon) {
        super(game, title);

        DateFormat formatter = new SimpleDateFormat("mm:ss:SSS", Locale.US);
        Date date = new Date(timeSpent);
        String timeFormatted = formatter.format(date);

        txtQuit = "Level Select";

        layout = new GlyphLayout(game.fontDrake60, txtQuit);
        quitWidth = layout.width;
        quitHeight = layout.height;

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
                txtUnlocked = "WOW You Beat the Fucking Game!";
                layout = new GlyphLayout(game.fontKenney45, txtUnlocked);
            }
        }
        unlockedWidth = layout.width;

        btnQuit = new ButtonActor(1334 - quitWidth - 10, 750 - quitHeight - 15, quitWidth + 10, quitHeight + 15);
        btnQuit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                btnQuit.removeListener(this);
                game.setScreen(new LevelSelectScreen(game, "Select Level"));
                dispose();
            }
        });
        stage.addActor(btnQuit);

        layout = new GlyphLayout(game.fontDrake60, "Retry");
        final ButtonActor btnRetry = new ButtonActor(0, 735 - layout.height, layout.width + 10, layout.height + 15);
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
                dispose();
            }
        });
        stage.addActor(btnRetry);
    }

	@Override
	public void render(float delta) {
		super.render(delta);

        game.batch.begin();
        game.fontDrake60.draw(game.batch, txtQuit, 1334 - quitWidth - 5, 750);
        game.fontDrake60.draw(game.batch, "Retry", 5, 750);
        game.fontKenney45.draw(game.batch, txtBandsCollected, 667 - bandsCollectedWidth / 2, 475 + bandsCollectedHeight/2);
        game.fontKenney45.draw(game.batch, txtTimeSpent, 667 - timeSpentWidth / 2, 375 + bandsCollectedHeight/2);
        if (!txtUnlocked.equals(""))
            game.fontKenney45.draw(game.batch, txtUnlocked, 667 - unlockedWidth / 2, 275 + bandsCollectedHeight/2);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            game.setScreen(new LevelSelectScreen(game, "Select Level"));
            dispose();
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
		super.dispose();
	}
}
