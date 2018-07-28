package net.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 4/24/2016
 */
public class ArrowActor extends Actor {

    private TextureRegion texture;
    private float x, y, width, height;
    private int direction;

    public ArrowActor(float x, float y, float width, float height, int directionOClock, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = directionOClock;
        this.texture = new TextureRegion(texture);
        setBounds(x, y, width, height);

//        if (directionOClock == 12) {
//            texture.flip(false, true);
//        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        if (direction == 12 || direction == 6) {
//            batch.draw(texture, x, y, width, height);
//        } else
        if (direction == 3) {
            batch.draw(texture, x, y, 1, 1, width, height, 1, 1, 0, false);
        } else if (direction == 9) {
            batch.draw(texture, x, y, 1, 1, width, height, 1, 1, 0, true);
        }

    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        super.setBounds(x, y, width, height);
    }
}
