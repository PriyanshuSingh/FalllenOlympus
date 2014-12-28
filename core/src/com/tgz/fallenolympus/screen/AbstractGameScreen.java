package com.tgz.fallenolympus.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.tgz.fallenolympus.assets.Assets;
import com.tgz.fallenolympus.game.MyGame;

/**
 * Created by priyanshu on 25/12/14.
 */
public abstract class AbstractGameScreen implements Screen{

    protected MyGame game;

    public AbstractGameScreen(MyGame game) {
        this.game = game;
    }

    public abstract InputProcessor getInputProcessor();

    @Override
    public void dispose() {
        Assets.instance.dispose();
    }

    @Override
    public void resume() {
        Assets.instance.init(new AssetManager());
    }
}
