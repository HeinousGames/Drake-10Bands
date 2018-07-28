package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.graphics.Texture;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.GenericActor;
import net.heinousgames.game.tenbands.actors.HorizontalPhoneActor;
import net.heinousgames.game.tenbands.actors.SleepingActor;
import net.heinousgames.game.tenbands.actors.VerticalPhoneActor;

/**
 * Created by Steve on 4/13/2016
 */
class Level10Bands extends Level {

    Level10Bands(MainClass game) {
        super(game);

        maxBands = 10;

        dangerousActors.clear();
        sleepingActors.clear();

        dangerousActors.addAll(
                new HorizontalPhoneActor(20.75f, 7, 10.5f, 12f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new HorizontalPhoneActor(0, 7, 10.25f, 12f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(10.25f, 14, 8, 7f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)));

        sleepingActors.add(new SleepingActor(9.5f, 3, false,
                game.assetManager.get("gfx/sleeper.png", Texture.class)));

        for (GenericActor a : dangerousActors) {
            stage.addActor(a);
        }

        for (SleepingActor a : sleepingActors) {
            stage.addActor(a);
        }

        game.generateBandLocation(maxBands);
    }
}
