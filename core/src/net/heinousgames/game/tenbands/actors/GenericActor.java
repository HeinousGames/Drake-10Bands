package net.heinousgames.game.tenbands.actors;

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
    Animation<TextureRegion> animation;
    TextureRegion textureRegion;
    private TextureRegion deadTextureRegion, currentAnimationFrame;
    TextureRegion[] textureRegions;
    float startX, startY, speed;
    private float stateTime;
    boolean isAnimated, isLooping;

    GenericActor(TextureRegion[] textureRegions, float startX, float startY) {
        this(startX, startY);
        this.textureRegions = textureRegions;
        animation = new Animation<TextureRegion>(0.075f, textureRegions);
        rectangle.width = textureRegions[0].getRegionWidth() / 70f;
        rectangle.height = textureRegions[0].getRegionHeight() / 70f;
        isAnimated = true;
    }

    GenericActor(TextureRegion deadTextureRegion, float startX, float startY) {
        this(startX, startY);
        this.deadTextureRegion = deadTextureRegion;
        rectangle.width = deadTextureRegion.getRegionWidth() / 70f;
        rectangle.height = deadTextureRegion.getRegionHeight() / 70f;
        isAnimated = false;
    }

    GenericActor(float startX, float startY, float speed, boolean isLooping) {
        this(startX, startY);
        this.speed = speed;
        this.isLooping = isLooping;
    }

    GenericActor(float startX, float startY) {
        this.startX = startX;
        this.startY = startY;

        rectangle = new Rectangle();
        rectangle.x = startX;
        rectangle.y = startY;
    }

    void flipTexture(boolean horizontal, boolean vertical) {
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
