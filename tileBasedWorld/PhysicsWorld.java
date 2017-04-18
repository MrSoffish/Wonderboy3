package com.mygdx.game.tileBasedWorld;
import com.mygdx.game.animation.PlayerCharacter;
import com.mygdx.game.utility.MapBodyManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.utility.Constants.PHYSICS_MATERIALS_PATH;
import static com.mygdx.game.utility.Constants.TILE_SIZE;
/**
 * Created by gerard on 01/03/2017.
 */
public class PhysicsWorld {
    private Vector2 gravity;
    private World world;
    private MapBodyManager mapBodyManager;
    private Box2DDebugRenderer debugRenderer;
    private Body playerBody;
    private PlayerCharacter playerCharacter;
    private OrthographicCamera camera;

    public PhysicsWorld(Map map,OrthographicCamera camera){
        gravity = new Vector2(0,-9.8f);
        Box2D.init();
        world = new World(gravity, true);
        mapBodyManager = new MapBodyManager(world, TILE_SIZE,
                Gdx.files.internal(PHYSICS_MATERIALS_PATH));
        mapBodyManager.createPhysics(map,"physics");
        debugRenderer = new Box2DDebugRenderer();
        this.camera = camera;
    }

    public void debugRender() {
        debugRenderer.render(world, camera.combined);
        world.step(1 / 60f, 6, 2);
    }

    public World getWorld(){
        return world;
    }

    public Body createPlayeBody(PlayerCharacter pCharacter){
        playerCharacter = pCharacter;
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(5,15);
        playerBody = world.createBody(playerBodyDef);
        playerBody.setUserData(playerCharacter);
        playerBody.setFixedRotation(true);
        //create shape for playercharacter
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((playerCharacter.getWidth()/2)-.75f,
                playerCharacter.getHeight()/2);
        // Create fixture def for the shape
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        playerBody.createFixture(fixtureDef);
        shape.dispose();
        return playerBody;
    }
}
