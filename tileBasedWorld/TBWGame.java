package com.mygdx.game.tileBasedWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by gerard on 12/02/2017.
 */

public class TBWGame extends Game {
    private final AssetManager assetManager = new AssetManager();
    private OrthographicCamera camera;

    @Override
    public void create() {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new
                InternalFileHandleResolver()));
        setScreen(new LoadingScreen(this));

    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
