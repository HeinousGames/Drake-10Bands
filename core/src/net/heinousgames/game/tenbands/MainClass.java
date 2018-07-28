package net.heinousgames.game.tenbands;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import net.heinousgames.game.tenbands.screens.LibgdxScreen;

import java.util.Date;
import java.util.Random;

/**
 * Created by Steve on 4/12/2016
 */
public class MainClass extends Game {

    private boolean canProceed;
    private int quadrant;
    private Random random;

    public Animation<TextureRegion> walkAnimation;
    public AssetManager assetManager;
    public BitmapFont fontKenney20;
    public BitmapFont fontKenney30;
    public BitmapFont fontKenney35;
    public BitmapFont fontKenney45;
    public BitmapFont fontKenney50;
    public boolean isNotWeb;
    public HTMLCustomMethods customMethodsCallback;
    public int score;
    public Music song;
    public Music songEdited;
    public Preferences prefs;
    public Rectangle bandRect;
    public Rectangle drakeRect;
    public Rectangle drakeFeetRect;
//    public ShapeRenderer shapeRenderer;
    public Sound tenBands;
    public Sound fiftyBands;
    public Sound hundredBands;
    public Sound fuckItMan;
    public Sound cribPhonesOff;
    public Sound houseNoCalls;
    public Sound letEmSleep;
    public Sound RIP;
    public Sound gunshots;
    public SpriteBatch batch;
    public TextureRegion back;
    public TextureRegion quit;
    public TextureRegion retry;
    public TextureRegion loading;
    public TextureRegion credits;
    public TextureRegion x;
    public TextureRegion musicOn;
    public TextureRegion soundOn;
    public TextureRegion options;
    public TextureRegion musicUnedited;
    public TextureRegion optionLocked;
    public TextureRegion levelSelect;
    public TextureRegion instructions;
    public TextureRegion tenBandsTR;
    public TextureRegion fiftyBandsTR;
    public TextureRegion hundredBandsTR;
    public TextureRegion fuckItManTR;
    public TextureRegion scoreTR;
    public TextureRegion band;
    public TextureRegion[] walkFrames;

    public interface HTMLCustomMethods {
        String convertDate(Date date);
        boolean canGWTPlayOgg();
        int[] getClientWindowSize();
    }

    public MainClass(HTMLCustomMethods customMethodsCallback) {
        this.customMethodsCallback = customMethodsCallback;
    }

    public void create() {
        Gdx.input.setCatchBackKey(true);

        assetManager = new AssetManager();
        assetManager.load("gfx/libgdxLogo.png", Texture.class);
        assetManager.load("gfx/hgLogo.png", Texture.class);
        batch = new SpriteBatch();
        bandRect = new Rectangle(-1, -2 + 0.25f/2f, 1, 0.75f);
        canProceed = true;
        drakeRect = new Rectangle(0, 0, 0.75f, 1.4f);
        drakeFeetRect = new Rectangle(0, 0, 0.75f, 0.25f);
        isNotWeb = (Gdx.app.getType() == Application.ApplicationType.Desktop ||
                Gdx.app.getType() == Application.ApplicationType.Android);
        prefs = Gdx.app.getPreferences("settings");
        random = new Random();

        assetManager.load("gfx/WithMyWoesSheet.png", Texture.class);
        assetManager.load("gfx/ui.png", Texture.class);

//        shapeRenderer = new ShapeRenderer();

//        boolean isAccelerometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
//        if (isAccelerometerAvailable)
//        if (assetManager.update())
//            this.setScreen(new LibgdxScreen(this));
//        else
//            this.setScreen(new ErrorScreen(this, "Error"));
    }

    public void render() {
        super.render(); // important!
        if (canProceed && assetManager.update()) {
            canProceed = false;
            this.setScreen(new LibgdxScreen(this));
        }
    }

    public void dispose() {
        assetManager.dispose();
        batch.dispose();
//        shapeRenderer.dispose();
    }

    public void generateBandLocation(int maxBands) {
        int newQuadrant = random.nextInt(4);
        if (newQuadrant != quadrant) {
            if (newQuadrant == 0) {
                bandRect.x = random.nextInt(11);
                bandRect.y = random.nextInt(8) + 7;
            } else if (newQuadrant == 1) {
                bandRect.x = random.nextInt(11) + 10;
                bandRect.y = random.nextInt(8) + 7;
            } else if (newQuadrant == 2) {
                bandRect.x = random.nextInt(11);
                bandRect.y = random.nextInt(8);
            } else if (newQuadrant == 3) {
                bandRect.x = random.nextInt(11) + 10;
                bandRect.y = random.nextInt(8);
            }

            if (maxBands == 10) {
                if (bandRect.y == 3 && (bandRect.x == 9 || bandRect.x == 10 || bandRect.x == 11)) {
                    generateBandLocation(maxBands);
                } else {
                    quadrant = newQuadrant;
                    bandRect.y += 0.125f;
                }
            } else if (maxBands == 100) {
                if ((bandRect.y == 3 && (bandRect.x == 4 || bandRect.x == 5 || bandRect.x == 16 ||
                        bandRect.x == 17)) || (bandRect.y == 11 && (bandRect.x == 10 || bandRect.x == 11))) {
                    generateBandLocation(maxBands);
                } else {
                    quadrant = newQuadrant;
                    bandRect.y += 0.125f;
                }
            } else if (maxBands == Integer.MAX_VALUE){
                if (((bandRect.x == 8 || bandRect.x == 9) && (bandRect.y == 0 || bandRect.y == 1 ||
                        bandRect.y == 2 || bandRect.y == 3 || bandRect.y == 4 || bandRect.y == 5 ||
                        bandRect.y == 6)) ||
                        ((bandRect.x == 12 || bandRect.x == 13) && (bandRect.y == 8 ||
                                bandRect.y == 9 || bandRect.y == 10 || bandRect.y == 11 ||
                                bandRect.y == 12 || bandRect.y == 13 || bandRect.y == 14)) ||
                        ((bandRect.x == 16 || bandRect.x == 17 || bandRect.x == 18) &&
                                (bandRect.y == 7 || bandRect.y == 11)) ||
                        (bandRect.y == 7 && (bandRect.x == 0 || bandRect.x == 1 ||
                                bandRect.x == 2 || bandRect.x == 3 || bandRect.x == 4 ||
                                bandRect.x == 14 || bandRect.x == 15)) ||
                        (bandRect.y == 11 && (bandRect.x == 19 || bandRect. x == 20))) {
                    generateBandLocation(maxBands);
                } else {
                    quadrant = newQuadrant;
                    bandRect.y += 0.125f;
                }
            }
        } else {
            generateBandLocation(maxBands);
        }
    }
}