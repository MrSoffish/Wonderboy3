package com.mygdx.game.tileBasedWorld;

import com.mygdx.game.animation.PlayerCharacter;
import com.mygdx.game.gameInterface.GameControl;
import com.mygdx.game.utility.CameraManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import static com.mygdx.game.utility.Constants.PLAYER_ATLAS_PATH;
import static com.mygdx.game.utility.Constants.SMALL;
import static com.mygdx.game.utility.Constants.UNITSCALE;
import static com.mygdx.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.mygdx.game.utility.Constants.VIRTUAL_WIDTH;

/**
 * Created by gerard on 12/02/2017.
 */

public class GameScreenTBW extends ScreenAdapter {
    private OrthographicCamera camera;
    private CameraManager cameraManager;
    private SpriteBatch batch;
    private TBWGame tbwGame;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private PlayerCharacter smif;
    private GameControl control;
    private PhysicsWorld pWorld;
    private float frameDelta = 0;

    public GameScreenTBW(TBWGame tbwGame){
        this.tbwGame = tbwGame;
    }
    @Override
    public void show() {
        super.show();
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        tiledMap = tbwGame.getAssetManager().get("tileData/testset2.tmx");
        pWorld = new PhysicsWorld(tiledMap,camera);
        smif = new PlayerCharacter(PLAYER_ATLAS_PATH,SMALL,pWorld);
        cameraManager = new CameraManager(camera,tiledMap);
        cameraManager.setTarget(smif);
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap,
                UNITSCALE);
        orthogonalTiledMapRenderer.setView(camera);
        control = new GameControl(smif);
        Gdx.input.setInputProcessor(control);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, VIRTUAL_WIDTH * UNITSCALE, VIRTUAL_HEIGHT * UNITSCALE);
        batch.setProjectionMatrix(camera.combined);
    }
    @Override
    public void render(float delta) {
        frameDelta += delta;
        smif.update(frameDelta);
        batch.setProjectionMatrix(camera.combined);
        clearScreen();
        draw();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void draw() {
        pWorld.debugRender();
        cameraManager.update();
        orthogonalTiledMapRenderer.setView(camera);
        orthogonalTiledMapRenderer.render();
        batch.begin();
        smif.draw(batch);
        batch.end();
    }
}