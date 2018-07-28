package net.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Steve on 4/16/2016
 */
public class VerticalPhoneActor extends VerticalMovingActor {

    public VerticalPhoneActor(float startX, float startY, float endY, float speed, boolean isLooping, Texture texture) {
        super(startX, startY, endY, speed, isLooping);
        textureRegion = new TextureRegion(texture, 0, 0, 233, 512);
        rectangle.width = 0.5f;
        rectangle.height = 1;
        isAnimated = false;
    }
}
