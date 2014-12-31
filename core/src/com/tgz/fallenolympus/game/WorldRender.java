package com.tgz.fallenolympus.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.tgz.fallenolympus.utils.Box2DConstants;
import com.tgz.fallenolympus.utils.Constants;

/**
 * Created by priyanshu on 25/12/14.
 */

public class WorldRender implements Disposable{

    private OrthographicCamera cam;

    private SpriteBatch batch;
    private Box2DDebugRenderer box2DDebugRenderer;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tmr;
    private WorldController worldController;
    private OrthographicCamera b2cam;


    public WorldRender(WorldController worldController) {
        tiledMap = worldController.level.tiledMap;
        tmr = new OrthogonalTiledMapRenderer(tiledMap);
        cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT);
        cam.position.set(cam.viewportWidth / 2.0f,cam.viewportHeight / 2.0f, 0);
        worldController.cameraHelper.init(cam);
        batch = new SpriteBatch();
        box2DDebugRenderer = new Box2DDebugRenderer();
        b2cam = new OrthographicCamera(Box2DConstants.BOX2D_VIEWPORT_WIDTH, Box2DConstants.BOX2D_VIEWPORT_HEIGHT);
        b2cam.position.set(b2cam.viewportWidth / 2.0f, b2cam.viewportHeight / 2.0f, 0);
        this.worldController = worldController;
    }


    public void render(){
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        worldController.cameraHelper.applyTo(cam);
        cam.update();
        b2cam.update();
        tmr.setView(cam);
        tmr.render();
        worldController.player.render(batch);
        box2DDebugRenderer.render(worldController.world, b2cam.combined);


    }


    public void resize(int width, int height) {
        cam.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        b2cam.viewportWidth = (Box2DConstants.BOX2D_VIEWPORT_HEIGHT / (height / Box2DConstants.PPM)) * width / Box2DConstants.PPM;
    }

    @Override
    public void dispose() {
        batch.dispose();
        box2DDebugRenderer.dispose();
        tmr.dispose();
        tiledMap.dispose();
    }
}
