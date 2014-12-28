package com.tgz.fallenolympus.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by priyanshu on 25/12/14.
 */
public class AssetPlayer {

    public Array<TextureAtlas.AtlasRegion> running;
    public Array<TextureAtlas.AtlasRegion> idle;
    public Array<TextureAtlas.AtlasRegion> preMelee;
    public Array<TextureAtlas.AtlasRegion> melee;

    public AssetPlayer(TextureAtlas atlas) {
        //TODO : Here more animation will load
        running = atlas.findRegions("running");
        idle = atlas.findRegions("idle");
        preMelee = atlas.findRegions("premelee");
        melee = atlas.findRegions("melee");

    }

}
