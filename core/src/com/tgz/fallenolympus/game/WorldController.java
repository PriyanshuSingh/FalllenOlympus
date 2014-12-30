package com.tgz.fallenolympus.game;

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
        level = new Level();
    }

    public void update (float deltaTime) {
        //world.step(1.0f / 60.0f, 6, 2);
        player.update(deltaTime);
    }


}
