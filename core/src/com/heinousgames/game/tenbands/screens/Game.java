package com.heinousgames.game.tenbands.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Steve on 4/12/2016
 */
public class Game extends com.badlogic.gdx.Game {

    private int quadrant;
    private Texture sheet;
    private Random random;

    int score;
    SpriteBatch batch;
    Sound tenBands, fiftyBands, hundredBands, fuckItMan, cribPhonesOff, houseNoCalls,
            letEmSleep, RIP, gunshots;
    Music song, songEdited;
    TextureRegion[] walkFrames;
    TextureRegion drakeRegion, band;
    Animation walkAnimation;
    Rectangle bandRect, drakeRect, drakeFeetRect;
    BitmapFont fontDrake40, fontDrake60, fontDrake80, fontKenney30, fontKenney35, fontKenney40,
            fontKenney45, fontKenney50;
    Preferences prefs;

    public void create() {
        boolean isAccelerometerAvailable = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);

        Gdx.input.setCatchBackKey(true);

        batch = new SpriteBatch();
        random = new Random();

        prefs = Gdx.app.getPreferences("settings");

        tenBands = Gdx.audio.newSound(Gdx.files.internal("sfx/10bands.ogg"));
        fiftyBands = Gdx.audio.newSound(Gdx.files.internal("sfx/50bands.ogg"));
        hundredBands = Gdx.audio.newSound(Gdx.files.internal("sfx/100bands.ogg"));
        fuckItMan = Gdx.audio.newSound(Gdx.files.internal("sfx/fuckitman.ogg"));
        cribPhonesOff = Gdx.audio.newSound(Gdx.files.internal("sfx/cribphonesoff.ogg"));
        houseNoCalls = Gdx.audio.newSound(Gdx.files.internal("sfx/housenocall.ogg"));
        letEmSleep = Gdx.audio.newSound(Gdx.files.internal("sfx/letemsleep.ogg"));
        RIP = Gdx.audio.newSound(Gdx.files.internal("sfx/rip.ogg"));
        gunshots = Gdx.audio.newSound(Gdx.files.internal("sfx/gunshots.ogg"));
        song = Gdx.audio.newMusic(Gdx.files.internal("sfx/song.ogg"));
        songEdited = Gdx.audio.newMusic(Gdx.files.internal("sfx/song edited.ogg"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/with_my_woes.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = 60;
        fontDrake60 = generator.generateFont(parameter);

        parameter.size = 40;
        fontDrake40 = generator.generateFont(parameter);

        parameter.size = 80;
        fontDrake80 = generator.generateFont(parameter);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/kenvector_future_thin.ttf"));
        parameter.size = 45;
        fontKenney45 = generator.generateFont(parameter);

        parameter.size = 40;
        fontKenney40 = generator.generateFont(parameter);

        parameter.size = 35;
        fontKenney35 = generator.generateFont(parameter);

        parameter.size = 30;
        fontKenney30 = generator.generateFont(parameter);

        parameter.size = 50;
        fontKenney50 = generator.generateFont(parameter);

        generator.dispose();

        sheet = new Texture(Gdx.files.internal("gfx/drake.png"));
        drakeRegion = new TextureRegion(sheet, 0, 0, 66, 43);
        walkFrames = new TextureRegion[3];
        walkFrames[0] = new TextureRegion(drakeRegion, 0, 0, 22, 43);
        walkFrames[1] = new TextureRegion(drakeRegion, 22, 0, 22, 43);
        walkFrames[2] = new TextureRegion(drakeRegion, 44, 0, 22, 43);
        walkAnimation = new Animation(0.15f, walkFrames);

        band = new TextureRegion(sheet, 66, 0, 62, 33);

        bandRect = new Rectangle(-1, -2 + 0.25f/2f, 1, 0.75f);
        generateBandLocation(50);
        drakeRect = new Rectangle(0, 0, 1, 1.5f);
        drakeFeetRect = new Rectangle(0, 0, 1, 0.25f);

        if (isAccelerometerAvailable)
            this.setScreen(new TitleScreen(this, "Ten Bands"));
        else
            this.setScreen(new ErrorScreen(this, "Error"));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        tenBands.dispose();
        fiftyBands.dispose();
        hundredBands.dispose();
        fuckItMan.dispose();
        cribPhonesOff.dispose();
        houseNoCalls.dispose();
        letEmSleep.dispose();
        RIP.dispose();
        gunshots.dispose();
        song.dispose();
        songEdited.dispose();
        sheet.dispose();
        fontDrake40.dispose();
        fontDrake60.dispose();
        fontDrake80.dispose();
        fontKenney30.dispose();
        fontKenney35.dispose();
        fontKenney40.dispose();
        fontKenney45.dispose();
        fontKenney50.dispose();
    }

    public void generateBandLocation(int maxBands) {
        int newQuadrant = random.nextInt(4);
        if (quadrant != newQuadrant) {
            quadrant = newQuadrant;
            if (quadrant == 0) {
                bandRect.x = random.nextInt(11);
                bandRect.y = random.nextInt(8) + 7 + 0.25f/2f;
            } else if (quadrant == 1) {
                bandRect.x = random.nextInt(11) + 10;
                bandRect.y = random.nextInt(8) + 7 + 0.25f/2f;
            } else if (quadrant == 2) {
                bandRect.x = random.nextInt(11);
                bandRect.y = random.nextInt(8) + 0.25f/2f;
            } else if (quadrant == 3) {
                bandRect.x = random.nextInt(11) + 10;
                bandRect.y = random.nextInt(8) + 0.25f/2f;
            }

            if (maxBands == 10) {
                if (bandRect.y == 3 && (bandRect.x == 9 || bandRect.x == 10 || bandRect.x == 11)) {
                    generateBandLocation(maxBands);
                }
            } else if (maxBands == 100) {
                if ((bandRect.y == 3 && (bandRect.x == 4 || bandRect.x == 5 || bandRect.x == 16 || bandRect.x == 17)) ||
                        (bandRect.y == 11 && (bandRect.x == 10 || bandRect.x == 11))) {
                    generateBandLocation(maxBands);
                }
            } else if (maxBands == Integer.MAX_VALUE){
                if (((bandRect.x == 8 || bandRect.x == 9) && (bandRect.y == 0 || bandRect.y == 1 || bandRect.y == 2 || bandRect.y == 3
                        || bandRect.y == 4 || bandRect.y == 5 || bandRect.y == 6)) ||
                        ((bandRect.x == 12 || bandRect.x == 13) && (bandRect.y == 8 || bandRect.y == 9 || bandRect.y == 10 || bandRect.y == 11
                                || bandRect.y == 12 || bandRect.y == 13 || bandRect.y == 14)) ||
                        ((bandRect.x == 16 || bandRect.x == 17 || bandRect.x == 18) && (bandRect.y == 7 || bandRect.y == 11)) ||
                        (bandRect.y == 7 && (bandRect.x == 0 || bandRect.x == 1 || bandRect.x == 2 || bandRect.x == 3 || bandRect.x == 4 ||
                                bandRect.x == 14 || bandRect.x == 15)) ||
                        (bandRect.y == 11 && (bandRect.x == 19 || bandRect. x == 20))) {
                    generateBandLocation(maxBands);
                }
            }
        } else {
            generateBandLocation(maxBands);
        }
    }
}