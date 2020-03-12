package tonmat.geneticalg.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import tonmat.geneticalg.GeneticAlg;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final var config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Genetic Alg");
		config.setWindowedMode(400, 400);
		config.setBackBufferConfig(8, 8, 8, 0, 0, 0, 4);
		config.setResizable(false);
		new Lwjgl3Application(new GeneticAlg(), config);
	}
}
