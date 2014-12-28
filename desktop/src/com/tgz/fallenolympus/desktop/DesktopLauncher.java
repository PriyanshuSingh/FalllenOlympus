package com.tgz.fallenolympus.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.tgz.fallenolympus.FallenOlympus;

public class DesktopLauncher {

	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = true;


	public static void main (String[] arg) {

		if (rebuildAtlas) {

			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.debug = drawDebugOutline;
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			TexturePacker.process(settings,"../../desktop/assets-raw/","images","fallenOlympus");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new FallenOlympus(), config);
	}
}
