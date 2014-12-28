package com.tgz.fallenolympus.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.tgz.fallenolympus.game.MyGame;
import com.tgz.fallenolympus.game.WorldController;
import com.tgz.fallenolympus.game.WorldRender;

/**
 * Created by priyanshu on 25/12/14.
 */
public class PlayScreen extends AbstractGameScreen {


    public WorldRender worldRender;
    public WorldController worldController;


    public PlayScreen(MyGame game) {
        super(game);

    }

    @Override
    public InputProcessor getInputProcessor() {
        return null;
    }

    @Override
    public void dispose() {
        super.dispose();
        worldRender.dispose();
    }

    @Override
    public void show() {
        worldController = new WorldController();
        worldController.init();
        worldRender = new WorldRender(worldController);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        worldController.update(delta);
        worldRender.render();

    }

    @Override
    public void resize(int width, int height) {
        worldRender.resize (width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }



}
