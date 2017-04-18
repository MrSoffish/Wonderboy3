package com.mygdx.game.gameInterface;

/**
 * Created by gerard on 17/02/2017.
 */

import com.mygdx.game.animation.PlayerCharacter;
import com.mygdx.game.utility.CurrentDirection;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameControl extends Stage {
    private PlayerCharacter playerCharacter;
    private boolean move = false;
    private CurrentDirection direction = CurrentDirection.STOP;

    public GameControl(PlayerCharacter pc) {
        playerCharacter = pc;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        move = true;
        if (screenY > 250) {
            direction = CurrentDirection.UP;
        } else if (screenY < 200 && screenX < 600) {
            direction = CurrentDirection.LEFT;
        } else if (screenY < 200 && screenX > 600) {
            direction = CurrentDirection.RIGHT;
        }
        playerCharacter.move(direction);
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        direction = CurrentDirection.STOP;
        playerCharacter.move(direction);
        move = false;
        return false;
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
