package com.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 4/14/2016
 */
public class SleepingActor extends GenericActor {

    public SleepingActor(float startX, float startY, boolean flippedHorizontally) {
        super(startX, startY);
        textureRegions = new TextureRegion[5];
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("gfx/sleeper.png")), 0, 2, 125, 126);
        textureRegions[0] = new TextureRegion(textureRegion, 0, 84, 125, 42);
        textureRegions[1] = new TextureRegion(textureRegion, 0, 42, 125, 42);
        textureRegions[2] = new TextureRegion(textureRegion, 0, 0, 125, 42);
        textureRegions[3] = new TextureRegion(textureRegion, 0, 42, 125, 42);
        textureRegions[4] = new TextureRegion(textureRegion, 0, 84, 125, 42);
        rectangle.width = 2;
        rectangle.height = 1;
        animation = new Animation(0.5f, textureRegions);
        isAnimated = true;

        if (flippedHorizontally) {
            flipTexture(true, false);
        }
    }
}
