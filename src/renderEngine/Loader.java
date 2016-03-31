package renderEngine;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.jogamp.opengl.GL;

import models.RawModel;

public class Loader {
	// keep track of all the VAOs and VBOs that we've made
	private ArrayList<Integer> vaos = new ArrayList<Integer>();
	private ArrayList<Integer> vbos = new ArrayList<Integer>();
	private ArrayList<Integer> textures = new ArrayList<Integer>();
	
	// Loader class reads in data and converts it to be stored as VAOs.
	public RawModel loadToVAO(float[] positions, float[] textureCoordinates, float[] normals, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		// VBOs stored in the VAO
		storeDataInAttributeList(0,3, positions);
		storeDataInAttributeList(1,2, textureCoordinates);
		storeDataInAttributeList(2,3, normals);

		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	// load a texture from file
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int textureID = texture.getTextureID();
		textures.add(textureID);
		return textureID;
	}
	
	// delete all VBOs and VAOs when game ends
	public void cleanUp(){
		for (int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		for (int vbo:vbos){
			GL30.glDeleteVertexArrays(vbo);
		}
		for (int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}
	
	private int createVAO(){
		// Create a VAO object and bind it to the array
		int vaoID = GL30.glGenVertexArrays();
		// add this VAO to the list of vaos
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data){
		// Bind a buffer before storing data to it
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		// GL_STATIC_DRAW says that we will not edit the data again once its in the VBO
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		// put the VBO into the VAO
		GL20.glVertexAttribPointer(attributeNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		// unbind current VBO
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	private void unbindVAO(){
		// set target VAO to an ID of 0 to unbind particular ID
		GL30.glBindVertexArray(0);
	}
	
	private void bindIndicesBuffer(int[] indices){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		// Create FloatBuffer to actually store this data
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		// prepare buffer to be read from (finish writing to buffer)
		buffer.flip();
		return buffer;
	}
}
