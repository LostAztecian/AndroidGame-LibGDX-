package ru.stoliarenko.gb.lonelycoraptor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.stoliarenko.gb.lonelycoraptor.LonelyCoraptor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new LonelyCoraptor(), config);
	}
}
