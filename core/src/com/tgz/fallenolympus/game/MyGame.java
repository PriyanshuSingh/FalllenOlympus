package com.tgz.fallenolympus.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.tgz.fallenolympus.screen.AbstractGameScreen;
import com.tgz.fallenolympus.screen.MenuScreen;
import com.tgz.fallenolympus.screen.PlayScreen;
import com.tgz.fallenolympus.screen.transitions.ScreenTransition;

import java.util.Stack;

/**
 * Created by priyanshu on 25/12/14.
 */
public class MyGame implements ApplicationListener {

    private Stack<AbstractGameScreen> screenStack;
    private ScreenTransition screenTransition;
    private AbstractGameScreen nextScreenToBeRendered;



    public enum GameScreen {
        PLAYSCREEN,
        MENUSCREEN

    }

    public MyGame() {
    }
    
    public void setScreen (GameScreen screen) {
        switch(screen) {
            case PLAYSCREEN:
                screenStack.add(new PlayScreen(this));
                break;
            case MENUSCREEN:
                screenStack.add(new MenuScreen(this));
                break;
        }
        screenStack.peek().show();
        Gdx.input.setInputProcessor(screenStack.peek().getInputProcessor());
    }

    public void setScreen (GameScreen screen, ScreenTransition screenTransition) {
        // TODO : set with screen transition
        switch (screen) {
            case PLAYSCREEN:
                nextScreenToBeRendered = new PlayScreen(this);
                break;
            case MENUSCREEN:
                nextScreenToBeRendered = new PlayScreen(this);
                break;
        }
        this.screenTransition = screenTransition;
        nextScreenToBeRendered.show();
        Gdx.input.setInputProcessor(screenStack.peek().getInputProcessor());
    }

    @Override
    public void create() {
        init();
    }

    private void init() {
        screenStack = new Stack<AbstractGameScreen>();
        setScreen(GameScreen.MENUSCREEN);

    }

    @Override
    public void resize(int width, int height) {
        if (!screenStack.empty()) {
            screenStack.peek().resize(width, height);
        }
    }

    @Override
    public void render() {
        if(nextScreenToBeRendered != null) {
            // TODO : rendering logic when screen transition taking place
        } else {
            screenStack.peek().render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void pause() {
        if (!screenStack.empty()) {
            screenStack.peek().pause();
        }
    }

    @Override
    public void resume() {
        if (!screenStack.empty()) {
            screenStack.peek().resume();
        }
    }

    @Override
    public void dispose() {
        while (!screenStack.empty()) {
            screenStack.pop().dispose();
        }
    }
}
