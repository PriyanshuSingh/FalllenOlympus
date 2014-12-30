package com.tgz.fallenolympus.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.tgz.fallenolympus.utils.Constants;

/**
 * Created by priyanshu on 28/12/14.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    public TiledMap tiledMap;

    public Level() {
        tiledMap =  new TmxMapLoader().load(Constants.TEST_MAP);
        if (tiledMap == null) {
            Gdx.app.error(TAG, "could not load tiled map.");
        }
    }

    public void createBox2DLevel() {
        TiledMapTileSets tiledMapTileSets = tiledMap.getTileSets();

    }


}
