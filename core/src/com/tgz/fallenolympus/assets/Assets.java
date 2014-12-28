package com.tgz.fallenolympus.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.tgz.fallenolympus.utils.Constants;

/**
 * Created by priyanshu on 25/12/14.
 */
public class Assets implements Disposable, AssetErrorListener {


    public AssetPlayer player;

    private AssetManager assetManager;

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    private Assets(){

    }

    public void init(AssetManager assetManager) {

        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        // loading all the assets
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();

        Gdx.app.debug(TAG, "# assets loaded : " + assetManager.getAssetNames().size);
        for (String assets : assetManager.getAssetNames()) {
            Gdx.app.debug(TAG, assets);
        }

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

        // setting filter to Linear
        for(Texture t: atlas.getTextures()){
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        player = new AssetPlayer(atlas);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }


    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.debug(TAG," couldn't load the asset " + asset.fileName + ", "+ (Exception)throwable);
    }
}
