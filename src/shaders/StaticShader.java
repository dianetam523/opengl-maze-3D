package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{
	
	public static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	public static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}


	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "postion");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}
	
	public void loadTransformatioNMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}

}
