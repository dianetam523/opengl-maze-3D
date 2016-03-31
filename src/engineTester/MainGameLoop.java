package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);



		// load vertices into model
		RawModel model = OBJLoader.loadObjModel("stall", loader);

		// load ModelTexture
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stall")));

		Entity entity = new Entity(staticModel, new Vector3f(0,0,-50), 0f, 0f, 0f, 1f);
		
		Camera camera = new Camera();
		
		// main game loop
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 1, 0);
			camera.move(); //takes in keyboard inputs, allows you to control camera
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);

			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		// close display once game loop is done executing

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
