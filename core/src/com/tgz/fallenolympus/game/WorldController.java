package com.tgz.fallenolympus.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.tgz.fallenolympus.entities.Player;
import com.tgz.fallenolympus.level.Level;

/**
 * Created by priyanshu on 25/12/14.
 */
public class WorldController extends InputAdapter{


    public World world;
    public Player player;
    public Level level;

    public void init() {
        world = new World(new Vector2(0.0f, -9.81f), true);
        player = new Player(world);
        level = new Level(world);
        level.createBox2DLevel();

    }

    public void update (float deltaTime) {
        handleInput();
        world.step(1.0f / 60.0f, 6, 2);
        player.update(deltaTime);
    }

    public void handleInput() {

        // Player controls
        float mass = player.body.getMass();
        float accel = 15.0f;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.body.applyForceToCenter(mass * accel, 0.0f, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.body.applyForceToCenter(-mass * accel, 0.0f, true);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            player.setCurrentAnimation(Player.AnimationType.MELEE);
        }
        if (Math.abs(player.body.getLinearVelocity().x) > 0.5f) {
            player.setCurrentAnimation(Player.AnimationType.RUNNING);
        }
        return false;
    }
}
