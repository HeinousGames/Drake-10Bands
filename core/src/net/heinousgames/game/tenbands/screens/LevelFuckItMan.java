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
class LevelFuckItMan extends Level {

    LevelFuckItMan(MainClass game) {
        super(game);

        maxBands = Integer.MAX_VALUE;

        dangerousActors.clear();
        sleepingActors.clear();

        dangerousActors.addAll(new VerticalPhoneActor(2.25f, 0, 4, 5, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new VerticalPhoneActor(5.25f, 6, 2, 5, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new HorizontalPhoneActor(0, 11, 11.5f, 8, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new HorizontalPhoneActor(10, 3, 20.5f, 8, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)));

        for (int i = 0; i < 7; i++) {
            sleepingActors.add(new SleepingActor(8, i, false,
                    game.assetManager.get("gfx/sleeper.png", Texture.class)));
            sleepingActors.add(new SleepingActor(12, 14-i, true,
                    game.assetManager.get("gfx/sleeper.png", Texture.class)));
        }

        sleepingActors.addAll(new SleepingActor(0.5f, 7, false,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)),
                new SleepingActor(2.5f, 7, true,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)),
                new SleepingActor(18.5f, 11, true,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)),
                new SleepingActor(16.5f, 11, false,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)),
                new SleepingActor(14.5f, 7, false,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)),
                new SleepingActor(16.5f, 7, true,
                        game.assetManager.get("gfx/sleeper.png", Texture.class)));

        for (GenericActor a : dangerousActors) {
            stage.addActor(a);
        }

        for (GenericActor a : sleepingActors) {
            stage.addActor(a);
        }

        game.generateBandLocation(maxBands);
    }
}
