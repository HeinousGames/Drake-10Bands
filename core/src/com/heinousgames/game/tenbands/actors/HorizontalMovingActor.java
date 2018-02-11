package com.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 2/21/2016.
 */
public class HorizontalMovingActor extends GenericActor {

    private float endX;
    public boolean movingLeft;
    private boolean goingLeftAtStart;

    public HorizontalMovingActor(TextureRegion[] textureRegions, float startX, float startY,
                                 float endX, float speed, boolean isLooping) {
        super(textureRegions, startX, startY);
        this.endX = endX;
        this.isLooping = isLooping;
        if (startX < endX) {
            movingLeft = false;
            goingLeftAtStart = false;
        } else {
            movingLeft = true;
            goingLeftAtStart = true;
        }
    }

    public HorizontalMovingActor(TextureRegion textureRegion, float startX, float startY,
                                 float endX, float speed, boolean isLooping) {
        super(textureRegion, startX, startY);
        this.endX = endX;
        this.isLooping = isLooping;
        if (startX < endX) {
            movingLeft = false;
            goingLeftAtStart = false;
        } else {
            movingLeft = true;
            goingLeftAtStart = true;
        }
    }

    public HorizontalMovingActor(float startX, float startY, float endX, float speed, boolean isLooping) {
        super(startX, startY, speed, isLooping);
        this.endX = endX;
        if (startX < endX) {
            movingLeft = false;
            goingLeftAtStart = false;
        } else {
            movingLeft = true;
            goingLeftAtStart = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        if (isLooping) {

            if (movingLeft) {
                rectangle.x -= (speed * Gdx.graphics.getDeltaTime());
            } else {
                rectangle.x += (speed * Gdx.graphics.getDeltaTime());
            }

            if (!goingLeftAtStart) {
                if (rectangle.x <= startX) {
                    rectangle.x = startX;
                    flipTexture(true, false);
                    movingLeft = false;
                } else if (rectangle.x >= endX) {
                    rectangle.x = endX;
                    flipTexture(true, false);
                    movingLeft = true;
                }
            } else {
                if (rectangle.x <= endX) {
                    rectangle.x = endX;
                    flipTexture(true, false);
                    movingLeft = false;
                } else if (rectangle.x >= startX) {
                    rectangle.x = startX;
                    flipTexture(true, false);
                    movingLeft = true;
                }
            }
        } else {
            if (goingLeftAtStart) {
                if (movingLeft) {
                    rectangle.x -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x <= endX) {
                        rectangle.x = endX;
                        isAnimated = false;
                    }
                } else {
                    rectangle.x += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x >= endX) {
                        rectangle.x = endX;
                        isAnimated = false;
                    }
                }
            } else {
                if (movingLeft) {
                    rectangle.x -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x <= startX) {
                        rectangle.x = startX;
                        isAnimated = false;
                    }
                } else {
                    rectangle.x += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.x >= startX) {
                        rectangle.x = startX;
                        isAnimated = false;
                    }
                }
            }
        }
    }
}
