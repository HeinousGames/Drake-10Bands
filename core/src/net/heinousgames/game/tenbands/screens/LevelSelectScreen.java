package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.heinousgames.game.tenbands.LevelRecordModel;
import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.ArrowActor;
import net.heinousgames.game.tenbands.actors.ButtonActor;

import java.util.Date;

class LevelSelectScreen extends GenericMenuScreen {

    private ArrowActor leftArrow, rightArrow;
    private ButtonActor btnLevelSelector;
    private float quitWidth, quitHeight, btnWidth, btnHeight, btnX, btnY, arrowWidth,
            arrowHeight, leftArrowX, rightArrowX, arrowY, titleX, titleY, bandRecordX, bandRecordY,
            timeX, timeY;
    private int levelRecordPosition, maxLevels;
    private LevelRecordModel[] levelRecords;

    LevelSelectScreen(final MainClass game, TextureRegion tr) {
        super(game, tr);
        btnX = stage.getViewport().getScreenWidth()/2 - (tr.getRegionWidth()+120)/2;
        btnY = stage.getViewport().getScreenHeight()/2 - 150;
        btnWidth = tr.getRegionWidth() + 120;

        quitWidth = game.back.getRegionWidth();
        quitHeight = game.back.getRegionHeight();

        leftArrowX = arrowWidth = arrowHeight = 128;
        rightArrowX = stage.getViewport().getScreenWidth() - 256;
        arrowY = stage.getViewport().getScreenHeight()/2 - 64;
        btnHeight = 300;
        btnLevelSelector = new ButtonActor(btnX, btnY, btnWidth, btnHeight,
                game.assetManager.get("gfx/ui.png", Texture.class));
        btnQuit = new ButtonActor(stage.getViewport().getScreenWidth() - quitWidth - 10,
                stage.getViewport().getScreenHeight()-quitHeight-15,
                quitWidth + 10, quitHeight + 15,
                game.assetManager.get("gfx/ui.png", Texture.class));
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

        Date date = new Date(game.prefs.getLong("level10time"));
        String tenTime = game.customMethodsCallback.convertDate(date);
        date = new Date(game.prefs.getLong("level50time"));
        String fiftyTime = game.customMethodsCallback.convertDate(date);
        date = new Date(game.prefs.getLong("level100time"));
        String hundredTime = game.customMethodsCallback.convertDate(date);
        date = new Date(game.prefs.getLong("levelMAXtime"));
        String fuckTime = game.customMethodsCallback.convertDate(date);

        levelRecords[0] = new LevelRecordModel("Ten Bands", "Record: " + game.prefs.getString("level10bandRecord", "?") + " Bands", "Time: " + tenTime);
        if (maxLevels >= 2)
            levelRecords[1] = new LevelRecordModel(game.prefs.getString("level10complete", "?"), "Record: " + game.prefs.getString("level50bandRecord", "?") + " Bands", "Time: " + fiftyTime);
        if (maxLevels >= 3)
            levelRecords[2] = new LevelRecordModel(game.prefs.getString("level50complete", "?"), "Record: " + game.prefs.getString("level100bandRecord", "?") + " Bands", "Time: " + hundredTime);
        if (maxLevels >= 4)
            levelRecords[3] = new LevelRecordModel(game.prefs.getString("level100complete", "?"), "Record: " + game.prefs.getString("levelMAXbandRecord", "?" + " Bands"), "Time: " + fuckTime);

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

        stage.addActor(leftArrow);
        stage.addActor(rightArrow);

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
        stage.addActor(btnLevelSelector);

        levelRecordPosition = -1;
        setStrings(true);
    }

    private void setStrings(boolean increment) {

        float levelTitleWidth, levelTitleHeight, bandRecordWidth, bandRecordHeight, timeSpentWidth,
                timeSpentHeight;

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

        if (game.customMethodsCallback.getClientWindowSize()[0] < 1000 ||
                game.customMethodsCallback.getClientWindowSize()[1] < 600) {

            layout = new GlyphLayout(game.fontKenney30, lrm.title);
            levelTitleWidth = layout.width;
            levelTitleHeight = layout.height;

            layout = new GlyphLayout(game.fontKenney30, lrm.bandRecord);
            bandRecordWidth = layout.width;
            bandRecordHeight = layout.height;

            layout = new GlyphLayout(game.fontKenney30, lrm.timeSpent);
            timeSpentWidth = layout.width;
            timeSpentHeight = layout.height;
        } else {
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

        titleX = btnX + (btnWidth/2 - levelTitleWidth/2) - 8; //game.customMethodsCallback.getClientWindowSize()[0]/2 - levelTitleWidth/2;
        titleY = btnY + btnHeight - levelTitleHeight; //game.customMethodsCallback.getClientWindowSize()[1]/2+100 + levelTitleHeight/2;
        bandRecordX = btnX + (btnWidth/2 - bandRecordWidth/2) - 8; //game.customMethodsCallback.getClientWindowSize()[0]/2 - bandRecordWidth/2;
        bandRecordY = btnY + btnHeight/2 + bandRecordHeight/2;
        timeX = btnX + (btnWidth/2 - timeSpentWidth/2) - 8; //game.customMethodsCallback.getClientWindowSize()[0]/2 - timeSpentWidth/2;
        timeY = btnY + timeSpentHeight + 30;//game.customMethodsCallback.getClientWindowSize()[1]/2-100 + timeSpentHeight/2;
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.batch.draw(game.back, stage.getViewport().getScreenWidth() - quitWidth - 5,
                stage.getViewport().getScreenHeight()-quitHeight-5, quitWidth, quitHeight);
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].title, titleX, titleY);
            game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].bandRecord, bandRecordX, bandRecordY);
            game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].timeSpent, timeX, timeY);
        } else if (Gdx.app.getType() == Application.ApplicationType.Desktop ||
                Gdx.app.getType() == Application.ApplicationType.WebGL) {
            if (game.customMethodsCallback.getClientWindowSize()[0] < 1000 ||
                    game.customMethodsCallback.getClientWindowSize()[1] < 600) {
                game.fontKenney30.draw(game.batch, levelRecords[levelRecordPosition].title, titleX, titleY);
                game.fontKenney30.draw(game.batch, levelRecords[levelRecordPosition].bandRecord, bandRecordX, bandRecordY);
                game.fontKenney30.draw(game.batch, levelRecords[levelRecordPosition].timeSpent, timeX, timeY);
            } else {
                game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].title, titleX, titleY);
                game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].bandRecord, bandRecordX, bandRecordY);
                game.fontKenney45.draw(game.batch, levelRecords[levelRecordPosition].timeSpent, timeX, timeY);
            }
        }
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)||
                Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) ||
                Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new TitleScreen(game, game.tenBandsTR));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ||
                Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Ten Bands")) {
                game.setScreen(new LevelIntroScreen(game, 10));
            } else if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Fifty Bands")) {
                game.setScreen(new LevelIntroScreen(game, 50));
            } else if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Hundred Bands")) {
                game.setScreen(new LevelIntroScreen(game, 100));
            } else if (levelRecords[levelRecordPosition].title.equalsIgnoreCase("Fuck It Man")) {
                game.setScreen(new LevelIntroScreen(game, Integer.MAX_VALUE));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        quitWidth *= resizeRatioWidth;
        quitHeight *= resizeRatioHeight;
        btnX *= resizeRatioWidth;
        btnY *= resizeRatioHeight;
        btnWidth *= resizeRatioWidth;
        btnHeight *= resizeRatioHeight;
        arrowWidth *= resizeRatioWidth;
        arrowHeight *= resizeRatioHeight;
        leftArrowX *= resizeRatioWidth;
        rightArrowX *= resizeRatioWidth;
        arrowY *= resizeRatioHeight;
        titleX *= resizeRatioWidth;
        titleY *= resizeRatioHeight;
        bandRecordX *= resizeRatioWidth;
        bandRecordY *= resizeRatioHeight;
        timeX *= resizeRatioWidth;
        timeY *= resizeRatioHeight;

        if (btnQuit != null) {
            btnQuit.setBounds(stage.getViewport().getScreenWidth() - quitWidth - 10,
                    stage.getViewport().getScreenHeight() - quitHeight - 15,
                    quitWidth + 10, quitHeight + 15);
        }

        if (btnLevelSelector != null) {
            btnLevelSelector.setBounds(btnX, btnY, btnWidth, btnHeight);
        }

        if (leftArrow != null) {
            leftArrow.setBounds(leftArrowX, arrowY, arrowWidth, arrowHeight);
        }

        if (rightArrow != null) {
            rightArrow.setBounds(rightArrowX, arrowY, arrowWidth, arrowHeight);
        }
    }

}
