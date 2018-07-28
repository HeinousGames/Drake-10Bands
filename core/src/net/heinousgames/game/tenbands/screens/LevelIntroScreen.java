package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

import net.heinousgames.game.tenbands.MainClass;

class LevelIntroScreen implements Screen {

    private boolean allowPlayStart;
//    private GlyphLayout layout;
    private int bands;
    private MainClass game;
    private OrthographicCamera camera;
    private TextureRegion tr;
//    private String str;

	LevelIntroScreen(final MainClass game, final int bands) {
		this.game = game;
        this.bands = bands;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1334, 750);

//		if (game.isNotWeb) {
//            if (bands == 10) {
//                str = "Ten Bands";
//                layout = new GlyphLayout(game.fontDrake80, str);
//            } else if (bands == 50) {
//                str = "Fifty Bands";
//                layout = new GlyphLayout(game.fontDrake80, str);
//            } else if (bands == 100) {
//                str = "Hundred Bands";
//                layout = new GlyphLayout(game.fontDrake80, str);
//            } else if (bands == Integer.MAX_VALUE) {
//                str = "Fuck It Man";
//                layout = new GlyphLayout(game.fontDrake80, str);
//            }
//        } else {
            if (bands == 10) {
                tr = game.tenBandsTR;
            } else if (bands == 50) {
                tr = game.fiftyBandsTR;
            } else if (bands == 100) {
                tr = game.hundredBandsTR;
            } else if (bands == Integer.MAX_VALUE) {
                tr = game.fuckItManTR;
            }
//        }

        if (game.prefs.getBoolean("soundOn", true)) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if (bands == 10) {
                        game.tenBands.play();
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                allowPlayStart = true;
                            }
                        }, 0.74f);
                    } else if (bands == 50) {
                        game.fiftyBands.play();
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                allowPlayStart = true;
                            }
                        }, 1);
                    } else if (bands == 100) {
                        game.hundredBands.play();
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                allowPlayStart = true;
                            }
                        }, 0.67f);
                    } else if (bands == Integer.MAX_VALUE) {
                        game.fuckItMan.play();
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                allowPlayStart = true;
                            }
                        }, 0.63f);
                    }

                }
            }, 0.5f);
        } else {
            allowPlayStart = true;
        }
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
//		if (game.isNotWeb) {
//            game.fontDrake80.draw(game.batch, str, camera.viewportWidth / 2 - layout.width / 2,
//                    camera.viewportHeight / 2 + layout.height / 2);
//        } else {
		   game.batch.draw(tr, camera.viewportWidth / 2 - tr.getRegionWidth() / 2,
                   camera.viewportHeight / 2 - tr.getRegionHeight() / 2);
//        }
		game.batch.end();

        if (allowPlayStart &&
                (Gdx.input.justTouched() ||
                        Gdx.input.isKeyJustPressed(Input.Keys.ENTER) ||
                        Gdx.input.isKeyJustPressed(Input.Keys.SPACE))) {
            if (bands == 10) {
                game.setScreen(new Level10Bands(game));
            } else if (bands == 50) {
                game.setScreen(new Level50Bands(game));
            } else if (bands == 100) {
                game.setScreen(new Level100Bands(game));
            } else if (bands == Integer.MAX_VALUE) {
                game.setScreen(new LevelFuckItMan(game));
            }
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
}
