package ru.stoliarenko.gb.lonelycoraptor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.stoliarenko.gb.lonelycoraptor.TestCorruptor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 512;
		config.resizable = false;
		new LwjglApplication(new TestCorruptor(), config);
	}
}
