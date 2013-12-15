package com.galaxyws.aircraftdemo.view;

import static javax.media.opengl.GL.GL_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_ELEMENT_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_STATIC_DRAW;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.model.Model;
import com.galaxyws.aircraftdemo.model.Model.Face;
import com.galaxyws.aircraftdemo.model.VBO;
import com.galaxyws.aircraftdemo.util.Vector3f;
import com.jogamp.common.nio.Buffers;

public class Builder {

	public static void buildModel(GLAutoDrawable drawable, Model model) {
		if(model.getVbo() == null) {
			GL2 gl = drawable.getGL().getGL2();
			
			List<Vector3f> vertices = model.getVertices();
			List<Face> faces = model.getFaces();
			
			IntBuffer bufferId = Buffers.newDirectIntBuffer(2);
			FloatBuffer vertexData = Buffers
					.newDirectFloatBuffer(vertices.size() * 3);
			IntBuffer vertexIndicesData = Buffers
					.newDirectIntBuffer(faces.size() * 3);
			
			gl.glGenBuffers(2, bufferId);
			gl.glBindBuffer(GL_ARRAY_BUFFER, bufferId.get(0));
			for (Vector3f vertex : model.getVertices()) {
				vertexData.put(vertex.x);
				vertexData.put(vertex.y);
				vertexData.put(vertex.z);
			}
			vertexData.rewind();
			gl.glBufferData(GL_ARRAY_BUFFER, /*9 * 4, FloatBuffer.wrap(v), */
					4 * vertices.size() * 3, vertexData,
					GL_STATIC_DRAW);

			gl.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferId.get(1));
			for (Face face : faces) {
				int[] indices = face.getVertexIndices();
				for(int i = indices.length - 1; i >= 0; i --){
					indices[i]--;
				}
				vertexIndicesData.put(indices);
			}
			vertexIndicesData.rewind();
			gl.glBufferData(GL_ELEMENT_ARRAY_BUFFER, 
					/*4 * 3, IntBuffer.wrap(idx), */
					4 * faces.size() * 3, vertexIndicesData, GL_STATIC_DRAW);

			gl.glBindBuffer(GL_ARRAY_BUFFER, 0);
			gl.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
			
			model.setVbo(new VBO(bufferId.get(0), bufferId.get(1), faces.size() * 3));
		}
	}
}
