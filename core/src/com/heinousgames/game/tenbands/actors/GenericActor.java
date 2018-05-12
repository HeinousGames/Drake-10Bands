package com.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 2/22/2016
 */
public class GenericActor extends Actor {

    public Rectangle rectangle;
    public Animation animation;
    public TextureRegion textureRegion, deadTextureRegion, currentAnimationFrame;
    public TextureRegion[] textureRegions;
    public float startX, startY, speed;
    private float stateTime;
    public boolean isAnimated, isLooping;

    public GenericActor(TextureRegion[] textureRegions, float startX, float startY) {
        this(startX, startY);
        this.textureRegions = textureRegions;
        animation = new Animation(0.075f, textureRegions);
        rectangle.width = textureRegions[0].getRegionWidth() / 70f;
        rectangle.height = textureRegions[0].getRegionHeight() / 70f;
        isAnimated = true;
    }

    public GenericActor(TextureRegion deadTextureRegion, float startX, float startY) {
        this(startX, startY);
        this.deadTextureRegion = deadTextureRegion;
        rectangle.width = deadTextureRegion.getRegionWidth() / 70f;
        rectangle.height = deadTextureRegion.getRegionHeight() / 70f;
        isAnimated = false;
    }

    public GenericActor(float startX, float startY, float speed, boolean isLooping) {
        this(startX, startY);
        this.speed = speed;
        this.isLooping = isLooping;
    }

    public GenericActor(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;

        rectangle = new Rectangle();
        rectangle.x = startX;
        rectangle.y = startY;
    }

    public void flipTexture(boolean horizontal, boolean vertical) {
        if (isAnimated) {
            for (TextureRegion tr : textureRegions) {
                tr.flip(horizontal, vertical);
            }
        } else {
            textureRegion.flip(horizontal, vertical);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isAnimated) {
            stateTime += Gdx.graphics.getDeltaTime();
            currentAnimationFrame = animation.getKeyFrame(stateTime, true);
            batch.draw(currentAnimationFrame, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else {
            batch.draw(textureRegion, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
}
