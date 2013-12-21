package com.galaxyws.aircraftdemo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.galaxyws.aircraftdemo.PBModel.BaseModel;
import com.galaxyws.aircraftdemo.model.Model;
import com.galaxyws.aircraftdemo.util.Constant;
import com.galaxyws.aircraftdemo.util.OBJLoader;
import com.galaxyws.aircraftdemo.util.Vector2f;
import com.galaxyws.aircraftdemo.util.Vector3f;
import com.galaxyws.aircraftdemo.view.ActorModel;
import com.galaxyws.aircraftdemo.view.ActorModel.Face;

public class TestOBJLoader {

	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub
		String fileName = Model.class.getClassLoader()
				.getResource(Constant.STRAIGHT_LINE_BULLET_MODEL).getPath();
		File f = new File(fileName);
		System.out.println(f.getPath());
		if (f.exists()) {
			ActorModel m = OBJLoader.loadTexturedModel(f);
			BaseModel.Builder pbm = BaseModel.newBuilder();
					
			List<Vector3f> vertices = m.getVertices();
		    List<Vector2f> textureCoordinates = m.getTextureCoordinates();
		    List<Vector3f> normals = m.getNormals();
		    List<Face> faces = m.getFaces();
		    
		    int vertexCount = 0;
		    for(Face face : faces) {
		    	int[] vertexIndices = face.getVertexIndices();
		    	for(int i : vertexIndices) {
		    		pbm.addVertex(BaseModel.Vertex3f.newBuilder()
		    				.setX(vertices.get(i - 1).x)
		    				.setY(vertices.get(i - 1).y)
		    				.setZ(vertices.get(i - 1).z)
		    				.build());
		    		vertexCount++;
		    	}
		    	
		    	if (m.hasNormals()) {
		    		int[] normalIndices = face.getNormalIndices();
			    	for(int i : normalIndices) {
			    		pbm.addNormal(BaseModel.Normal3f.newBuilder()
			    				.setX(normals.get(i - 1).x)
			    				.setY(normals.get(i - 1).y)
			    				.setZ(normals.get(i - 1).z)
			    				.build()
			    				);
			    	}
		    	}
		    	if (m.hasTextureCoordinates()) {
		    		int[] texCoordIndices = face.getTextureCoordinateIndices();
			    	for(int i : texCoordIndices) {
			    		pbm.addTexCoord(BaseModel.TexCoord2f.newBuilder()
			    				.setS(textureCoordinates.get(i - 1).x)
			    				.setT(textureCoordinates.get(i - 1).y)
			    				.build()
			    				);
			    	}
		    	}
		    	
		    	if (m.getTextureFile() != null) {
		    		pbm.setTexture(m.getTextureFile());
		    	}
		    	pbm.setName("Dan");
		    	pbm.setVertexNumber(vertexCount);
		    	
		    	FileOutputStream output = new FileOutputStream("test.pbm");
		    	pbm.build().writeTo(output);
		    	output.close();
		    	
		    }

		}
	}

}
