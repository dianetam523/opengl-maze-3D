package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		// vertex data to create a rectangle
		float[] vertices = {
				-0.5f, 0.5f, 0f, //V0
				-0.5f, -0.5f, 0f, //V1
				0.5f, -0.5f, 0f,  //V2
				0.5f, 0.5f, 0f,  //V3
		};
		
		int[] indices = {
				0, 1, 3, // Top left triangle, V0, V1, V3
				3, 1, 2		// Bottom right triangle V3, V1, V2
		};
		
		// load vertices into model
		RawModel model = loader.loaderToVAO(vertices, indices);
		
		// main game loop
		while(!Display.isCloseRequested()){
			renderer.prepare();
			shader.start();
			// game logic
			// render
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		// close display once game loop is done executing
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
