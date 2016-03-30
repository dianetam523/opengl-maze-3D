package renderEngine;
// Creates a RawModel 3D object using VAO (Vertex Attribute Object) data model
// Vertex Buffer Objects (VBO) data stored in VAO attribute lists
public class RawModel {
	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
}
