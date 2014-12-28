package com.tgz.fallenolympus;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.tgz.fallenolympus.game.MyGame;
import com.tgz.fallenolympus.assets.Assets;

public class FallenOlympus extends MyGame{
	@Override
	public void create() {
		super.create();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());

	}
}
