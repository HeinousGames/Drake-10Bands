package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.graphics.Texture;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.GenericActor;
import net.heinousgames.game.tenbands.actors.SleepingActor;
import net.heinousgames.game.tenbands.actors.VerticalPhoneActor;

/**
 * Created by Steve on 4/13/2016
 */
class Level100Bands extends Level {

    Level100Bands(MainClass game) {
        super(game);

        maxBands = 100;

        dangerousActors.clear();
        sleepingActors.clear();

        dangerousActors.addAll(new VerticalPhoneActor(0.25f, 14, 0, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(3.25f, 0, 14, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(6.25f, 14, 0, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(9.25f, 0, 14, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(12.25f, 14, 0, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(15.25f, 0, 14, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(18.25f, 14, 0, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)));

        sleepingActors.addAll(new SleepingActor(4, 3, false,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)),
                new SleepingActor(16, 3, false,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)),
                new SleepingActor(10, 11, true,
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
