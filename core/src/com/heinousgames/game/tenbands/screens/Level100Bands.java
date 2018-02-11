package com.heinousgames.game.tenbands.screens;

import com.heinousgames.game.tenbands.actors.GenericActor;
import com.heinousgames.game.tenbands.actors.SleepingActor;
import com.heinousgames.game.tenbands.actors.VerticalPhoneActor;

/**
 * Created by Steve on 4/13/2016.
 */
public class Level100Bands extends Level {

    public Level100Bands(Game game) {
        super(game);

        maxBands = 100;

        dangerousActors.clear();
        sleepingActors.clear();

        dangerousActors.addAll(new VerticalPhoneActor(0.25f, 14, 0, 7.5f, true),
                new VerticalPhoneActor(3.25f, 0, 14, 7.5f, true),
                new VerticalPhoneActor(6.25f, 14, 0, 7.5f, true),
                new VerticalPhoneActor(9.25f, 0, 14, 7.5f, true),
                new VerticalPhoneActor(12.25f, 14, 0, 7.5f, true),
                new VerticalPhoneActor(15.25f, 0, 14, 7.5f, true),
                new VerticalPhoneActor(18.25f, 14, 0, 7.5f, true));

        sleepingActors.addAll(new SleepingActor(4, 3, false), new SleepingActor(16, 3, false),
                new SleepingActor(10, 11, true));

        for (GenericActor a : dangerousActors) {
            stage.addActor(a);
        }

        for (SleepingActor a : sleepingActors) {
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

    }
}
