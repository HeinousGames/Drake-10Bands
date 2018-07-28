package net.heinousgames.game.tenbands.screens;

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

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.GenericActor;
import net.heinousgames.game.tenbands.actors.SleepingActor;

/**
 * Created by Steve on 4/16/2016
 */
class Level implements Screen {

    int maxBands;
    Array<GenericActor> dangerousActors;
    Array<SleepingActor> sleepingActors;
    public Stage stage;

    private MainClass game;
    private Texture tile;
    private TextureRegion currentFrame;
    private OrthographicCamera levelCamera;
    private boolean lastFacingLeft, hasLost, isAccelerometerAvailable;
    private float prevX, stateTime, soundByteTime;
    private Long startTime, finishTime;

    Level(final MainClass game) {
        this.game = game;
        game.batch.enableBlending();

        game.score = 0;

        levelCamera = new OrthographicCamera(21, 15);
        // position the levelCamera to the world units
        levelCamera.position.x = 10.5f;
        levelCamera.position.y = 7.5f;

        stage = new Stage(new ScreenViewport());
        stage.getViewport().setCamera(levelCamera);

        stateTime = 0f;

        dangerousActors = new Array<GenericActor>();
        sleepingActors = new Array<SleepingActor>();

        tile = new Texture(Gdx.files.internal("gfx/letter.png"));

        startTime = System.currentTimeMillis();

        if (game.prefs.getBoolean("musicOn", true)) {
            if (game.prefs.getBoolean("uneditedOn", false)) {
                game.song.play();
            } else {
                game.songEdited.play();
            }
        }

        game.drakeRect.x = 0;
        game.drakeRect.y = 0;

        isAccelerometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelCamera.update();

        if (isAccelerometerAvailable) {
            if ((int) Gdx.input.getAccelerometerX() == 0 && (int) Gdx.input.getAccelerometerY() == 0) {
                currentFrame = game.walkFrames[1];
            } else {
                stateTime += Gdx.graphics.getDeltaTime();
                currentFrame = game.walkAnimation.getKeyFrame(stateTime, true);
            }
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                    || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.UP)
                    || Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A)
                    || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                stateTime += Gdx.graphics.getDeltaTime();
                currentFrame = game.walkAnimation.getKeyFrame(stateTime, true);
            } else {
                currentFrame = game.walkFrames[1];
            }
        }

        game.batch.setProjectionMatrix(levelCamera.combined);
//        game.shapeRenderer.setProjectionMatrix(levelCamera.combined);


        if (!hasLost) {
            finishTime = System.currentTimeMillis() - startTime;
            prevX = game.drakeRect.x;
            if (isAccelerometerAvailable) {
                game.drakeRect.x += (int) Gdx.input.getAccelerometerY() * 2.5f * delta;
                game.drakeRect.y -= (int) Gdx.input.getAccelerometerX() * 2.5f * delta;
            } else {
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                    game.drakeRect.x -= (int) 8f * Gdx.graphics.getDeltaTime();
                }

                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                    game.drakeRect.x += (int) 8f * Gdx.graphics.getDeltaTime();
                }

                if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
                    game.drakeRect.y += (int) 8f * Gdx.graphics.getDeltaTime();
                }

                if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
                    game.drakeRect.y -= (int) 8f * Gdx.graphics.getDeltaTime();
                }
            }
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

//        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        game.shapeRenderer.setColor(1, 0, 0, 1);
//        game.shapeRenderer.rect(game.drakeFeetRect.x, game.drakeFeetRect.y, game.drakeFeetRect.width, game.drakeFeetRect.height);
//        game.shapeRenderer.end();
//
//        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        game.shapeRenderer.setColor(0, 1, 0, 1);
//        game.shapeRenderer.rect(game.drakeRect.x, game.drakeRect.y, game.drakeRect.width, game.drakeRect.height);
//        game.shapeRenderer.end();

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
                game.songEdited.stop();
//                if (game.isNotWeb) {
//                    game.setScreen(new LevelRecapScreen(game, "Score", maxBands, game.score, finishTime, true));
//                } else {
                    game.setScreen(new LevelRecapScreen(game, game.scoreTR, maxBands, game.score, finishTime, true));
//                }
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

            if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) ||
                    Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ||
                    Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
                if (game.prefs.getBoolean("musicOn", true)) {
                    if (game.prefs.getBoolean("uneditedOn", false)) {
                        game.song.stop();
                    } else {
                        game.songEdited.stop();
                    }
                }
//                if (game.isNotWeb) {
//                    game.setScreen(new LevelSelectScreen(game, "Select Level"));
//                } else {
                    game.setScreen(new LevelSelectScreen(game, game.levelSelect));
//                }
                dispose();
            }
        }

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
                game.song.stop();
            } else {
                game.songEdited.stop();
            }
        }

        if (game.prefs.getBoolean("soundOn", true)) {
            game.gunshots.play();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    game.gunshots.stop();
                    if (diedByPhone) {
                        if (game.prefs.getBoolean("diedByPhone")) {
                            soundByteTime = 2.404f;
                            game.houseNoCalls.play();
                        } else {
                            soundByteTime = 2.44f;
                            game.cribPhonesOff.play();
                        }
                        game.prefs.putBoolean("diedByPhone", !game.prefs.getBoolean("diedByPhone")).flush();
                    } else {
                        if (game.prefs.getBoolean("diedByTrippin")) {
                            soundByteTime = 1.713f;
                            game.letEmSleep.play();
                        } else {
                            soundByteTime = 1.67f;
                            game.RIP.play();
                        }
                        game.prefs.putBoolean("diedByTrippin", !game.prefs.getBoolean("diedByTrippin")).flush();
                    }

                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
//                            if (game.isNotWeb) {
//                                game.setScreen(new LevelRecapScreen(game, "Score", maxBands, game.score, finishTime, false));
//                            } else {
                                game.setScreen(new LevelRecapScreen(game, game.scoreTR, maxBands, game.score, finishTime, false));
//                            }
                            dispose();
                        }
                    }, soundByteTime);
                }
            }, 1.5f);
        } else {
//            if (game.isNotWeb) {
//                game.setScreen(new LevelRecapScreen(game, "Score", maxBands, game.score, finishTime, false));
//            } else {
                game.setScreen(new LevelRecapScreen(game, game.scoreTR, maxBands, game.score, finishTime, false));
//            }
            dispose();
        }
    }

    @Override
    public void show() {

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
        stage.dispose();
    }
}
