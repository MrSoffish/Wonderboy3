package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import static com.mygdx.game.utility.Constants.FRAME_DURATION;
import static com.mygdx.game.utility.Constants.PLAYER_HEIGHT;
import static com.mygdx.game.utility.Constants.PLAYER_WIDTH;

/**
 * Created by gerard on 09/11/2016.
 */

abstract class AnimatedSprite extends Sprite {
    private Animation animation;
    private TextureAtlas atlas;

    public AnimatedSprite(String atlasString, Texture t){
        super(t,PLAYER_WIDTH,PLAYER_HEIGHT);
        initAtlas(atlasString);
    }
    public void update(float animationTime){
        this.setRegion((TextureRegion) animation.getKeyFrame(animationTime,true));
    }
    private void initAtlas(String atlasString){
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        //load animations
        Array<TextureAtlas.AtlasRegion> regions = new
                Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animation = new Animation(FRAME_DURATION,regions, Animation.PlayMode.LOOP);
    }
    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion> {
        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }
}