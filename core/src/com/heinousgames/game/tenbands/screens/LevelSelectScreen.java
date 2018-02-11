package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.heinousgames.game.tenbands.*;
import com.heinousgames.game.tenbands.actors.ArrowActor;
import com.heinousgames.game.tenbands.actors.ButtonActor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LevelSelectScreen extends GenericMenuScreen {

    private LevelRecordModel[] levelRecords;
    private ArrowActor leftArrow, rightArrow;
    private ButtonActor btnLevelSelector;
    private float levelTitleWidth, levelTitleHeight, bandRecordWidth, bandRecordHeight, timeSpentWidth,
            timeSpentHeight, quitWidth, quitHeight;
    private int levelRecordPosition, maxLevels;

    public LevelSelectScreen(final Game game, String title) {
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

        levelRecordPosition = 0;
        maxLevels = 2;

        if (game.prefs.getString("level10complete").equalsIgnoreCase("Fifty Bands"))
            maxLevels++;
        if (game.prefs.getString("level50complete").equalsIgnoreCase("Hundred Bands"))
            maxLevels++;
        if (game.prefs.getString("level100complete").equalsIgnoreCase("Fuck It Man"))
            maxLevels++;

        if (maxLevels >= 5) {
            maxLevels = 4;
        }

        levelRecords = new LevelRecordModel[maxLevels];

        DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
        Date date = new Date(game.prefs.getLong("level10time"));
        String tenTime = formatter.format(date);
        date = new Date(game.prefs.getLong("level50time"));
        String fiftyTime = formatter.format(date);
        date = new Date(game.prefs.getLong("level100time"));
        String hundredTime = formatter.format(date);
        date = new Date(game.prefs.getLong("levelMAXtime"));
        String fuckTime = formatter.format(date);

        levelRecords[0] = new LevelRecordModel("Ten Bands", "Record: " + game.prefs.getString("level10bandRecord", "?") + " Bands", "Time: " + tenTime);
        if (maxLevels >= 2)
            levelRecords[1] = new LevelRecordModel(game.prefs.getString("level10complete", "?"), "Record: " + game.prefs.getString("level50bandRecord", "?") + " Bands", "Time: " + fiftyTime);
        if (maxLevels >= 3)
            levelRecords[2] = new LevelRecordModel(game.prefs.getString("level50complete", "?"), "Record: " + game.prefs.getString("level100bandRecord", "?") + " Bands", "Time: " + hundredTime);
        if (maxLevels >= 4)
            levelRecords[3] = new LevelRecordModel(game.prefs.getString("level100complete", "?"), "Record: " + game.prefs.getString("levelMAXbandRecord", "?" + " Bands"), "Time: " + fuckTime);

        layout = new GlyphLayout(game.fontKenney45, levelRecords[0].title);
        levelTitleWidth = layout.width;
        levelTitleHeight = layout.height;

        layout = new GlyphLayout(game.fontKenney45, levelRecords[0].bandRecord);
        bandRecordWidth = layout.width;
        bandRecordHeight = layout.height;

        layout = new GlyphLayout(game.fontKenney45, levelRecords[0].timeSpent);
        timeSpentWidth = layout.width;
        timeSpentHeight = layout.height;

        leftArrow = new ArrowActor(128, 375 - 64, 128, 128, 9);
        rightArrow = new ArrowActor(1334-256, 375 - 64, 128, 128, 3);
        btnLevelSelector = new ButtonActor(667 - (titleWidth+120) / 2, 375 - 150, titleWidth + 120, 300);

        btnLevelSelector.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Ten Bands")) {
                    btnLevelSelector.removeListener(this);
                    game.setScreen(new LevelIntroScreen(game, 10));
                } else if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Fifty Bands")) {
                    btnLevelSelector.removeListener(this);
                    game.setScreen(new LevelIntroScreen(game, 50));
                } else if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Hundred Bands")) {
                    btnLevelSelector.removeListener(this);
                    game.setScreen(new LevelIntroScreen(game, 100));
                } else if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Fuck It Man")) {
                    btnLevelSelector.removeListener(this);
                    game.setScreen(new LevelIntroScreen(game, Integer.MAX_VALUE));
                }
            }
        });

        leftArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setStrings(false);
            }
        });

        rightArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                setStrings(true);
            }
        });

        stage.addActor(btnQuit);
        stage.addActor(leftArrow);
        stage.addActor(rightArrow);
        stage.addActor(btnLevelSelector);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.fontDrake60.draw(game.batch, txtQuit, 1334 - quitWidth - 5, 750);
        game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].title, 667 - levelTitleWidth / 2, 475 + levelTitleHeight/2);
        game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].bandRecord, 667 - bandRecordWidth / 2, 375 + bandRecordHeight/2);
        game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].timeSpent, 667 - timeSpentWidth / 2, 275 + timeSpentHeight/2);
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

    private void setStrings(boolean increment) {

        if (increment) {
            levelRecordPosition++;
        } else {
            levelRecordPosition--;
        }

        if (levelRecordPosition <= -1) {
            levelRecordPosition = maxLevels-1;
        } else if (levelRecordPosition >= maxLevels) {
            levelRecordPosition = 0;
        }

        LevelRecordModel lrm = levelRecords[levelRecordPosition];

        layout = new GlyphLayout(game.fontKenney45, lrm.title);
        levelTitleWidth = layout.width;
        levelTitleHeight = layout.height;

        layout = new GlyphLayout(game.fontKenney45, lrm.bandRecord);
        bandRecordWidth = layout.width;
        bandRecordHeight = layout.height;

        layout = new GlyphLayout(game.fontKenney45, lrm.timeSpent);
        timeSpentWidth = layout.width;
        timeSpentHeight = layout.height;
    }
}
