package com.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 4/16/2016.
 */
public class VerticalPhoneActor extends VerticalMovingActor {

    public VerticalPhoneActor(float startX, float startY, float endY, float speed, boolean isLooping) {
        super(startX, startY, endY, speed, isLooping);
        textureRegion = new TextureRegion(new Texture(
                Gdx.files.internal("gfx/phone.png")), 0, 0, 233, 512);
        rectangle.width = 0.5f;
        rectangle.height = 1;
        isAnimated = false;
    }
}
