package net.heinousgames.game.tenbands.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Steve on 4/23/2016
 */
public class ButtonActor extends Actor {

    private float x, y, width, height;
    private TextureRegion textureRegion;

    public ButtonActor(float x, float y, float width, float height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setBounds(x, y, width, height);
        textureRegion = new TextureRegion(texture, 190, 45);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, x, y, width, height);
    }

    @Override
    public void setBounds (float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        super.setBounds(x, y, width, height);
    }
}
