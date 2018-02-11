package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.heinousgames.game.tenbands.actors.GenericActor;
import com.heinousgames.game.tenbands.actors.SleepingActor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Steve on 4/16/2016
 */
public class Level implements Screen {

    public int maxBands;
    public Array<GenericActor> dangerousActors;
    public Array<SleepingActor> sleepingActors;
    public Stage stage;

    private Stage uiStage;
    private Game game;
    private Texture tile;
    private OrthographicCamera levelCamera, uiCamera;
    private boolean lastFacingLeft, hasLost;
    private float prevX, stateTime, soundByteTime;
    private Long startTime, finishTime;

    public Level(final Game game) {
        this.game = game;
        game.batch.enableBlending();

        game.score = 0;

        levelCamera = new OrthographicCamera(21, 15);
        // position the levelCamera to the world units
        levelCamera.position.x = 10.5f;
        levelCamera.position.y = 7.5f;

        uiCamera = new OrthographicCamera(1334, 750);
        uiCamera.position.x = 667;
        uiCamera.position.y = 375;

        stage = new Stage(new ScreenViewport());
        stage.getViewport().setCamera(levelCamera);

        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().setCamera(uiCamera);

        stateTime = 0f;

        dangerousActors = new Array<GenericActor>();
        sleepingActors = new Array<SleepingActor>();

        tile = new Texture(Gdx.files.internal("gfx/letter.png"));

        startTime = System.currentTimeMillis();

        if (game.prefs.getBoolean("musicOn", true)) {
            if (game.prefs.getBoolean("uneditedOn", false)) {
//                game.song.play();
            } else {
//                game.songEdited.play();
            }
        }

        game.drakeRect.x = 0;
        game.drakeRect.y = 0;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelCamera.update();
        uiCamera.update();

        TextureRegion currentFrame;

        if ((int)Gdx.input.getAccelerometerX() == 0 && (int)Gdx.input.getAccelerometerY() == 0) {
            currentFrame = game.walkFrames[1];
        } else {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = game.walkAnimation.getKeyFrame(stateTime, true);
        }

        game.batch.setProjectionMatrix(levelCamera.combined);

        if (!hasLost) {
            finishTime = System.currentTimeMillis() - startTime;
            prevX = game.drakeRect.x;
            game.drakeRect.x += (int) Gdx.input.getAccelerometerY() * 2.5f * delta;
            game.drakeRect.y -= (int) Gdx.input.getAccelerometerX() * 2.5f * delta;
            game.drakeFeetRect.x = game.drakeRect.x;
            game.drakeFeetRect.y = game.drakeRect.y + 0.125f;

            if (game.drakeRect.x >= 19.5f)
                game.drakeRect.x = 19.5f;
            if (game.drakeRect.x <= 0)
                game.drakeRect.x = 0;
            if (game.drakeRect.y >= 13.5f)
                game.drakeRect.y = 13.5f;
            if (game.drakeRect.y <= 0)
                game.drakeRect.y = 0;

            //Need to call game.batch.begin(); in 2 spots -> bad design
            game.batch.begin();
            for (int i = 0; i < 21; i++) {
                for (int j = 0; j < 15; j++) {
                    game.batch.draw(tile, i, j, 1, 1);
                }
            }
            game.batch.draw(game.band, game.bandRect.x, game.bandRect.y, 1, 0.75f);
        } else {
            //Need to call game.batch.begin(); in 2 spots -> bad design
            game.batch.begin();
            for (int i = 0; i < 21; i++) {
                for (int j = 0; j < 15; j++) {
                    game.batch.draw(tile, i, j, 1, 1);
                }
            }
            currentFrame = game.walkFrames[1];
        }

        game.batch.end();

        stage.draw();

        game.batch.begin();

        if (game.drakeRect.x > prevX) {
            game.batch.draw(currentFrame, game.drakeRect.x + 1.5f, game.drakeRect.y, -1.5f, 3);
            lastFacingLeft = false;
        } else if (game.drakeRect.x < prevX) {
            game.batch.draw(currentFrame, game.drakeRect.x, game.drakeRect.y, 1.5f, 3);
            lastFacingLeft = true;
        } else if (game.drakeRect.x == prevX) {
            if (lastFacingLeft) {
                game.batch.draw(currentFrame, game.drakeRect.x, game.drakeRect.y, 1.5f, 3);
            } else {
                game.batch.draw(currentFrame, game.drakeRect.x + 1.5f, game.drakeRect.y, -1.5f, 3);
            }
        }

        game.batch.end();

        if (game.drakeRect.overlaps(game.bandRect)) {
            game.generateBandLocation(maxBands);
            game.score += 1;

            if (game.score >= maxBands) {
                if (maxBands == 10) {
                    if (game.prefs.getLong("level10time") == 0 || finishTime < game.prefs.getLong("level10time")) {
                        game.prefs.putLong("level10time", finishTime);
                    }
                } else if (maxBands == 50) {
                    if (game.prefs.getLong("level50time") == 0 || finishTime < game.prefs.getLong("level50time")) {
                        game.prefs.putLong("level50time", finishTime);
                    }
                } else if (maxBands == 100) {
                    if (game.prefs.getLong("level100time") == 0 || finishTime < game.prefs.getLong("level100time")) {
                        game.prefs.putLong("level100time", finishTime);
                    }
                }
//                game.songEdited.stop();
                game.setScreen(new LevelRecapScreen(game, "Score", maxBands, game.score, finishTime, true));
                dispose();
            }
            if (maxBands == 10) {
                if (game.score > Integer.valueOf(game.prefs.getString("level10bandRecord", "0"))) {
                    game.prefs.putString("level10bandRecord", String.valueOf(game.score));
                }
            } else if (maxBands == 50) {
                if (game.score > Integer.valueOf(game.prefs.getString("level50bandRecord", "0"))) {
                    game.prefs.putString("level50bandRecord", String.valueOf(game.score));
                }
            } else if (maxBands == 100) {
                if (game.score > Integer.valueOf(game.prefs.getString("level100bandRecord", "0"))) {
                    game.prefs.putString("level100bandRecord", String.valueOf(game.score));
                }
            }

            game.prefs.flush();
        }

        DateFormat formatter = new SimpleDateFormat("mm:ss:SSS", Locale.US);
        Date date = new Date(finishTime);
        String timeFormatted = formatter.format(date);

        game.batch.setProjectionMatrix(uiCamera.combined);
        game.batch.begin();
        game.fontKenney50.draw(game.batch, String.valueOf(game.score), 1214, 730);
        game.fontKenney50.draw(game.batch, String.valueOf(timeFormatted), 20, 730);
        game.batch.end();
        uiStage.draw();

        if (!hasLost) {
            for (GenericActor ga : dangerousActors) {
                if (game.drakeRect.overlaps(ga.rectangle)) {
                    dangerousActors.clear();
                    endGame(true);
                }
            }

            for (GenericActor ga : sleepingActors) {
                if (game.drakeFeetRect.overlaps(ga.rectangle)) {
                    sleepingActors.clear();
                    endGame(false);
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
                if (game.prefs.getBoolean("musicOn", true)) {
                    if (game.prefs.getBoolean("uneditedOn", false)) {
//                        game.song.stop();
                    } else {
//                        game.songEdited.stop();
                    }
                }
                game.setScreen(new LevelSelectScreen(game, "Select Level"));
                dispose();
            }

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
        tile.dispose();
        uiStage.dispose();
    }

    private void endGame(final boolean diedByPhone) {
        hasLost = true;

        if (maxBands == Integer.MAX_VALUE) {
            if (game.score >= Integer.valueOf(game.prefs.getString("levelMAXbandRecord", "0"))) {
                game.prefs.putString("levelMAXbandRecord", String.valueOf(game.score));
                if (game.prefs.getLong("levelMAXtime") == 0 || finishTime < game.prefs.getLong("levelMAXtime")) {
                    game.prefs.putLong("levelMAXtime", finishTime);
                }
                game.prefs.flush();
            }
        }

        if (game.prefs.getBoolean("musicOn", true)) {
            if (game.prefs.getBoolean("uneditedOn", false)) {
//                game.song.stop();
            } else {
//                game.songEdited.stop();
            }
        }

        if (game.prefs.getBoolean("soundOn", true)) {
//            game.gunshots.play();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
//                    game.gunshots.stop();
                    if (diedByPhone) {
                        if (game.prefs.getBoolean("diedByPhone")) {
                            soundByteTime = 2.404f;
//                            game.houseNoCalls.play();
                        } else {
                            soundByteTime = 2.44f;
//                            game.cribPhonesOff.play();
                        }
                        game.prefs.putBoolean("diedByPhone", !game.prefs.getBoolean("diedByPhone")).flush();
                    } else {
                        if (game.prefs.getBoolean("diedByTrippin")) {
                            soundByteTime = 1.713f;
//                            game.letEmSleep.play();
                        } else {
                            soundByteTime = 1.67f;
//                            game.RIP.play();
                        }
                        game.prefs.putBoolean("diedByTrippin", !game.prefs.getBoolean("diedByTrippin")).flush();
                    }

                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            game.setScreen(new LevelRecapScreen(game, "Score", maxBands, game.score, finishTime, false));
                            dispose();
                        }
                    }, soundByteTime);
                }
            }, 1.5f);
        } else {
            game.setScreen(new LevelRecapScreen(game, "Score", maxBands, game.score, finishTime, false));
            dispose();
        }
    }
}
