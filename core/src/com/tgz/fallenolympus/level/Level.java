package com.tgz.fallenolympus.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.tgz.fallenolympus.utils.Box2DConstants;
import com.tgz.fallenolympus.utils.Constants;
import com.tgz.fallenolympus.utils.MapBodyManager;

/**
 * Created by priyanshu on 28/12/14.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    public TiledMap tiledMap;
    private MapBodyManager mapBodyManager;

    public Level(World world) {
        tiledMap =  new TmxMapLoader().load(Constants.TEST_MAP);
        if (tiledMap == null) {
            Gdx.app.error(TAG, "could not load tiled map.");
        }
        mapBodyManager = new MapBodyManager(world, Box2DConstants.PPM, Gdx.files.internal(Box2DConstants.LEVEL_TEST), 0);
    }

    public void createBox2DLevel() {
        mapBodyManager.createPhysics(tiledMap, "physics");
    }


}
