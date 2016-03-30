package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		// main game loop
		while(!Display.isCloseRequested()){
			// game logic
			// render
			DisplayManager.updateDisplay();
		}
		// close display once game loop is done executing
		DisplayManager.closeDisplay();
	}

}
