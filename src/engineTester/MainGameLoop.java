package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		// vertex data to create a rectangle
		float[] vertices = {
				// Left bottom triangle
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				// Right top triangle
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};
		
		// load vertices into model
		RawModel model = loader.loaderToVAO(vertices);
		
		// main game loop
		while(!Display.isCloseRequested()){
			renderer.prepare();
			// game logic
			// render
			renderer.render(model);
			DisplayManager.updateDisplay();
		}
		// close display once game loop is done executing
		
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
	}

}
