package com.mygdx.game.animation;
import com.mygdx.game.tileBasedWorld.PhysicsWorld;
import com.mygdx.game.utility.CurrentDirection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mygdx.game.utility.Constants.FORCE_X;
import static com.mygdx.game.utility.Constants.FORCE_Y;
import static com.mygdx.game.utility.Constants.MAX_HEIGHT;
import static com.mygdx.game.utility.Constants.MAX_VELOCITY;
import static com.mygdx.game.utility.Constants.PLAYER_OFFSET_X;
import static com.mygdx.game.utility.Constants.PLAYER_OFFSET_Y;
/**
 * Created by gja10 on 13/02/2017.
 */
public class PlayerCharacter extends AnimatedSprite {
    private Body playerBody;
    private boolean facingRight =true;

    public PlayerCharacter(String atlas, Texture t, PhysicsWorld physicsWorld) {
        super(atlas, t);
        playerBody = physicsWorld.createPlayeBody(this);
    }

    @Override
    public void update(float stateTime) {
        super.update(stateTime);
        this.setX(playerBody.getPosition().x-PLAYER_OFFSET_X);
        this.setY(playerBody.getPosition().y-PLAYER_OFFSET_Y);
        if(!facingRight){flip(true,false);}
    }

    public void move(CurrentDirection direction){
        Vector2 vel = playerBody.getLinearVelocity();
        Vector2 pos = playerBody.getPosition();
        switch(direction){
            case LEFT:
                facingRight=false;
                if (vel.x > -MAX_VELOCITY) {
                    playerBody.applyLinearImpulse(-FORCE_X, 0, pos.x, pos.y, true);
                }
                break;
            case RIGHT:
                facingRight=true;
                if (vel.x < MAX_VELOCITY) {
                    playerBody.applyLinearImpulse(FORCE_X, 0, pos.x, pos.y, true);
                }
                break;
            case UP:
                if (pos.y< MAX_HEIGHT && vel.y < MAX_VELOCITY) {
                    playerBody.applyLinearImpulse(0, FORCE_Y, pos.x, pos.y, true);
                }
                break;
            case STOP:
        }
    }
}