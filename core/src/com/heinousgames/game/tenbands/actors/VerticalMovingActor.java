package com.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 2/21/2016
 */
public class VerticalMovingActor extends GenericActor {

    private final float MOVING_SPEED = 0.075f;
    private float endY;
    public boolean movingDown;
    private boolean goingDownAtStart;

    public VerticalMovingActor(TextureRegion[] textureRegions, float startX, float startY,
                               float endY, boolean isLooping) {
        super(textureRegions, startX, startY);
        this.endY = endY;
        this.isLooping = isLooping;
        if (startY > endY) {
            movingDown = true;
            goingDownAtStart = true;
        } else {
            movingDown = false;
            goingDownAtStart = false;
        }
    }

    public VerticalMovingActor(TextureRegion textureRegion, float startX, float startY,
                               float endY, boolean isLooping) {
        super(textureRegion, startX, startY);
        this.endY = endY;
        this.isLooping = isLooping;

        if (startY > endY) {
            movingDown = true;
            goingDownAtStart = true;
        } else {
            movingDown = false;
            goingDownAtStart = false;
        }
    }

    public VerticalMovingActor(float startX, float startY, float endY, float speed, boolean isLooping) {
        super(startX, startY, speed, isLooping);
        this.endY = endY;
        if (startY > endY) {
            movingDown = true;
            goingDownAtStart = true;
        } else {
            movingDown = false;
            goingDownAtStart = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        if (isLooping) {

            if (movingDown) {
                rectangle.y -= (speed * Gdx.graphics.getDeltaTime());
            } else {
                rectangle.y += (speed * Gdx.graphics.getDeltaTime());
            }

            if (!goingDownAtStart) {
                if (rectangle.y <= startY) {
                    rectangle.y = startY;
                    flipTexture(false, true);
                    movingDown = false;
                } else if (rectangle.y >= endY) {
                    rectangle.y = endY;
                    flipTexture(false, true);
                    movingDown = true;
                }
            } else {
                if (rectangle.y <= endY) {
                    rectangle.y = endY;
                    flipTexture(false, true);
                    movingDown = false;
                } else if (rectangle.y >= startY) {
                    rectangle.y = startY;
                    flipTexture(false, true);
                    movingDown = true;
                }
            }
        } else {
            if (goingDownAtStart) {
                if (movingDown) {
                    rectangle.y -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y <= endY) {
                        rectangle.y = endY;
                        isAnimated = false;
                    }
                } else {
                    rectangle.y += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y >= endY) {
                        rectangle.y = endY;
                        isAnimated = false;
                    }
                }
            } else {
                if (movingDown) {
                    rectangle.y -= (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y <= startY) {
                        rectangle.y = startY;
                        isAnimated = false;
                    }
                } else {
                    rectangle.y += (speed * Gdx.graphics.getDeltaTime());
                    if (rectangle.y >= startY) {
                        rectangle.y = startY;
                        isAnimated = false;
                    }
                }
            }
        }
    }
}
