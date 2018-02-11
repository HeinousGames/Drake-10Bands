package com.heinousgames.game.tenbands.screens;

import com.heinousgames.game.tenbands.actors.GenericActor;
import com.heinousgames.game.tenbands.actors.HorizontalPhoneActor;
import com.heinousgames.game.tenbands.actors.SleepingActor;
import com.heinousgames.game.tenbands.actors.VerticalPhoneActor;

/**
 * Created by Steve on 4/13/2016
 */
public class Level10Bands extends Level {

    public Level10Bands(Game game) {
        super(game);

        maxBands = 10;

        dangerousActors.clear();
        sleepingActors.clear();

        dangerousActors.addAll(
                new HorizontalPhoneActor(20.75f, 7, 10.5f, 12f, true),
                new HorizontalPhoneActor(0, 7, 10.25f, 12f, true),
                new VerticalPhoneActor(10.25f, 14, 8, 7f, true));

        sleepingActors.add(new SleepingActor(9.5f, 3, false));

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
