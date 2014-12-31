package com.tgz.fallenolympus.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.tgz.fallenolympus.entities.AbstractGameObject;

/**
 * Created by tgz on 31/12/14.
 */
public class CameraHelper {

    private AbstractGameObject target;

    private boolean hasTarget;
    private float zoom;
    private Vector3 position;
    private static final float ZOOM_MAX = 10.0f;
    private static final float ZOOM_MIN = 0.25f;

    public CameraHelper() {
        hasTarget = false;
        zoom = 1.0f;
        position = new Vector3();
    }

    public void init(OrthographicCamera camera) {
        position.set(camera.position);
        zoom = camera.zoom;
    }

    public void update(float deltaTime) {
        Vector2 destination = target.body.getPosition();
        position.interpolate(new Vector3(destination.x, destination.y, 0), 1.0f , Interpolation.pow2In);
    }

    public void setTarget(AbstractGameObject GameObject) {
        target = GameObject;
    }

    public void applyTo (OrthographicCamera camera) {
        camera.position.set(position);
        camera.zoom = zoom;
    }

}
