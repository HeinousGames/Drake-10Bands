package com.heinousgames.game.tenbands.screens;

import com.heinousgames.game.tenbands.actors.GenericActor;
import com.heinousgames.game.tenbands.actors.HorizontalPhoneActor;

/**
 * Created by Steve on 4/13/2016.
 */
public class Level50Bands extends Level {

    public Level50Bands(Game game) {
        super(game);

        maxBands = 50;

        dangerousActors.clear();

        dangerousActors.addAll(new HorizontalPhoneActor(0, 2, 19.75f, 7.5f, true),
                new HorizontalPhoneActor(19.75f, 5, 0, 7.5f, true),
                new HorizontalPhoneActor(0, 8, 19.75f, 7.5f, true),
                new HorizontalPhoneActor(19.75f, 11, 0, 7.5f, true),
                new HorizontalPhoneActor(0, 14, 19.75f, 7.5f, true));

        for (GenericActor a : dangerousActors) {
            stage.addActor(a);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
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
        super.dispose();
    }
}
