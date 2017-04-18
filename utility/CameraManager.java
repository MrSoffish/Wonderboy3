package com.mygdx.game.utility;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.utility.Constants.UNITSCALE;
import static com.mygdx.game.utility.Constants.VIRTUAL_WIDTH;
import static com.mygdx.game.utility.Constants.MAX_ZOOM_IN;
import static com.mygdx.game.utility.Constants.MAX_ZOOM_OUT;
/**
 * Created by gerard on 14/03/2017.
 */
public class CameraManager {
    private Vector2 position;
    private float zoom;
    private Sprite target;
    private OrthographicCamera camera;
    private TiledMap tiledMap;

    public CameraManager (OrthographicCamera camera, TiledMap tiledMap) {
        this.camera = camera;
        this.tiledMap = tiledMap;
        position = new Vector2();
        zoom = 1f;
    }
    public void update () {
        if (!hasTarget()) return;
        if(cameraTrackX()) {
            position.x = target.getX() + target.getOriginX();
            camera.position.set(position.x, camera.position.y, 0);
            camera.zoom = zoom;
            camera.update();
        }
    }
    private boolean cameraTrackX() {
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer)
                tiledMap.getLayers().get(0);
        float levelWidth = tiledMapTileLayer.getWidth();
        if ((target.getX() > (VIRTUAL_WIDTH * UNITSCALE) / 2f) &&
                (target.getX() < (levelWidth - (VIRTUAL_WIDTH * UNITSCALE )/2))) {
            return true;
        }
        return false;
    }
    public void setPosition (float x, float y) {
        this.position.set(x, y);
    }
    public Vector2 getPosition () { return position; }
    public void addZoom (float amount) { setZoom(zoom + amount); }
    public void setTarget (Sprite target) { this.target = target; }
    public Sprite getTarget () { return target; }
    public OrthographicCamera getCamera(){return camera;}
    public boolean hasTarget () { return target != null; }

    public void setZoom (float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }
}
