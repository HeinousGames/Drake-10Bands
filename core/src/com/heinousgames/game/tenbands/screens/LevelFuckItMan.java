package com.heinousgames.game.tenbands.screens;

import com.heinousgames.game.tenbands.actors.GenericActor;
import com.heinousgames.game.tenbands.actors.HorizontalPhoneActor;
import com.heinousgames.game.tenbands.actors.SleepingActor;
import com.heinousgames.game.tenbands.actors.VerticalPhoneActor;

/**
 * Created by Steve on 4/13/2016
 */
public class LevelFuckItMan extends Level {

    public LevelFuckItMan(Game game) {
        super(game);

        maxBands = Integer.MAX_VALUE;

        dangerousActors.clear();
        sleepingActors.clear();

        dangerousActors.addAll(new VerticalPhoneActor(2.25f, 0, 4, 5, true),
                new VerticalPhoneActor(5.25f, 6, 2, 5, true),
                new HorizontalPhoneActor(0, 11, 11.5f, 8, true),
                new HorizontalPhoneActor(10, 3, 20.5f, 8, true));

        for (int i = 0; i < 7; i++) {
            sleepingActors.add(new SleepingActor(8, i, false));
            sleepingActors.add(new SleepingActor(12, 14-i, true));
        }

        sleepingActors.addAll(new SleepingActor(0.5f, 7, false), new SleepingActor(2.5f, 7, true),
                new SleepingActor(18.5f, 11, true), new SleepingActor(16.5f, 11, false),
                new SleepingActor(14.5f, 7, false), new SleepingActor(16.5f, 7, true));

        for (GenericActor a : dangerousActors) {
            stage.addActor(a);
        }

        for (GenericActor a : sleepingActors) {
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
