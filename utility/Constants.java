package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * Created by gerard on 09/11/2016.
 */

public class Constants {
    //Screen Size
    public static final float VIRTUAL_WIDTH = Gdx.graphics.getWidth();
    public static final float VIRTUAL_HEIGHT = Gdx.graphics.getHeight();
    //World to screen scale
    public static final float TILE_SIZE   = 32;
    public static final float UNITSCALE = 1.0f / TILE_SIZE;
    //Animation Speed
    public static final float FRAME_DURATION = 1.0f / 30.0f;
    //Speed
    public static final float MAX_VELOCITY = 2f;
    public static final float MAX_HEIGHT = 18;
    //player body
    public static int PLAYER_WIDTH= 3;
    public static int PLAYER_HEIGHT=4;
    public static float PLAYER_OFFSET_Y=2;
    public static float PLAYER_OFFSET_X=1.5f;
    //Player forces
    public static final float FORCE_X=25f;
    public static final float FORCE_Y=18f;
    //Textures
    public static final Texture MEDIUM = new Texture(Gdx.files.internal("gfx/mediumSize.png"));
    public static final Texture SMALL = new Texture(Gdx.files.internal("gfx/smallSize.png"));
    public static final Texture TINY = new Texture(Gdx.files.internal("gfx/tinySize.png"));
    //Paths
    public static final String PLAYER_ATLAS_PATH = "gfx/smurf_assets.atlas";
    public static final String PHYSICS_MATERIALS_PATH = "tileData/physicsData.json";
    //Camera
    public static final float MAX_ZOOM_IN = 0.75f;
    public static final float MAX_ZOOM_OUT = 1.25f;
}
