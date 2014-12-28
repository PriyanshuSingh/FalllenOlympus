package com.tgz.fallenolympus.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by priyanshu on 28/12/14.
 */
public class Animation extends com.badlogic.gdx.graphics.g2d.Animation {

    public float stateTime;

    public Animation(float frameDuration, Array<? extends TextureRegion> keyFrames) {
        super(frameDuration, keyFrames);
        reset();
    }

    public Animation(float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
        reset();
    }

    public Animation(float frameDuration, TextureRegion... keyFrames) {
        super(frameDuration, keyFrames);
        reset();
    }


    public void reset() {
        stateTime = 0.0f;
    }

    @Override
    public TextureRegion getKeyFrame(float deltaTime, boolean looping) {
        return super.getKeyFrame(stateTime, looping);
    }

    @Override
    public TextureRegion getKeyFrame(float deltaTime) {
        // these function when get override they call from parent class
        stateTime += deltaTime;
        return super.getKeyFrame(stateTime);
    }

    @Override
    public int getKeyFrameIndex(float deltaTime) {
        return super.getKeyFrameIndex(stateTime);
    }

    @Override
    public TextureRegion[] getKeyFrames() {
        return super.getKeyFrames();
    }

    @Override
    public PlayMode getPlayMode() {
        return super.getPlayMode();
    }

    @Override
    public void setPlayMode(PlayMode playMode) {
        super.setPlayMode(playMode);
    }

    @Override
    public boolean isAnimationFinished(float deltaTime) {
        return super.isAnimationFinished(stateTime + deltaTime);
    }

    @Override
    public void setFrameDuration(float frameDuration) {
        super.setFrameDuration(frameDuration);
    }

    @Override
    public float getFrameDuration() {
        return super.getFrameDuration();
    }

    @Override
    public float getAnimationDuration() {
        return super.getAnimationDuration();
    }
}
