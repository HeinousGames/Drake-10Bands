package net.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.graphics.Texture;

import net.heinousgames.game.tenbands.MainClass;
import net.heinousgames.game.tenbands.actors.GenericActor;
import net.heinousgames.game.tenbands.actors.HorizontalPhoneActor;

/**
 * Created by Steve on 4/13/2016
 */
class Level50Bands extends Level {

    Level50Bands(MainClass game) {
        super(game);

        maxBands = 50;

        dangerousActors.clear();

        dangerousActors.addAll(new HorizontalPhoneActor(0, 2, 19.75f, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new HorizontalPhoneActor(19.75f, 5, 0, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new HorizontalPhoneActor(0, 8, 19.75f, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new HorizontalPhoneActor(19.75f, 11, 0, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)),
                new HorizontalPhoneActor(0, 14, 19.75f, 7.5f, true,
                        game.assetManager.get("gfx/phone.png", Texture.class)));

        for (GenericActor a : dangerousActors) {
            stage.addActor(a);
        }

        game.generateBandLocation(maxBands);
    }
}
