package com.tgz.fallenolympus.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tgz.fallenolympus.utils.Box2DConstants;

/**
 * Created by priyanshu on 25/12/14.
 */
public abstract class AbstractGameObject {

    // All entity in our game have body has box2D body for their movements and everything

    Body body;

    public Vector2 position;

    public Vector2 scale;
    public Vector2 dimension;
    public Vector2 origin;
    // rotation is done in degree
    public float rotation;
    // Bounds used in case of box shaped body
    public Vector2 bounds;

    // sprite that's going to render
    public TextureRegion region;


    public AbstractGameObject () {
        scale = new Vector2(1.0f, 1.0f);
        dimension = new Vector2(1.0f, 1.0f);
        rotation = 0;
        origin = new Vector2();
        position = new Vector2();
        bounds = new Vector2();
    }


    public void update(float deltaTime) {
        if (body != null) {
            position.set(body.getPosition().scl(Box2DConstants.PPM));
            rotation = body.getAngle()  * MathUtils.radiansToDegrees;
        }
    }

    public abstract void render(SpriteBatch batch);

}
