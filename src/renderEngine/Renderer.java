package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
// render a RawModel
public class Renderer {
	public void prepare(){
		GL11.glClearColor(1, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}
	
	public void render(RawModel model){
		// bind the VAO and get Attribute array
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		// unbind the VAO and unbind attribute array
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}
