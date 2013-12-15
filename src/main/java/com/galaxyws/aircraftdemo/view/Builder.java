package com.galaxyws.aircraftdemo.view;

import static javax.media.opengl.GL.GL_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_STATIC_DRAW;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.model.Model;
import com.galaxyws.aircraftdemo.model.Model.Face;
import com.galaxyws.aircraftdemo.util.Vector2f;
import com.galaxyws.aircraftdemo.util.Vector3f;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class Builder {

	private static void buildVBO(GLAutoDrawable drawable, Model model) {
		GL2 gl = drawable.getGL().getGL2();

		List<Vector3f> vertices = model.getVertices();
		List<Face> faces = model.getFaces();
		List<Vector2f> textureCoordinates = model.getTextureCoordinates();

		IntBuffer bufferId = Buffers.newDirectIntBuffer(2);
		FloatBuffer vertexData = Buffers
				.newDirectFloatBuffer(faces.size() * 3 * 3);
		FloatBuffer texCoordData = Buffers
				.newDirectFloatBuffer(faces.size() * 3 * 2);

		gl.glGenBuffers(2, bufferId);

		// / feed vertex
		gl.glBindBuffer(GL_ARRAY_BUFFER, bufferId.get(0));

		for (Face face : faces) {
			int[] indices = face.getVertexIndices();
			for (int i = 0; i < indices.length; i++) {
				vertexData.put(vertices.get(indices[i] - 1).x);
				vertexData.put(vertices.get(indices[i] - 1).y);
				vertexData.put(vertices.get(indices[i] - 1).z);
			}
		}
		vertexData.rewind();
		gl.glBufferData(GL_ARRAY_BUFFER, 4 * faces.size() * 3 * 3, vertexData,
				GL_STATIC_DRAW);

		// / feed texture coordinates
		gl.glBindBuffer(GL_ARRAY_BUFFER, bufferId.get(1));
		for (Face face : faces) {
			int[] indices = face.getTextureCoordinateIndices();
			float[] coord = new float[indices.length * 2];
			for (int i = 0; i < indices.length; i++) {
				coord[2 * i] = textureCoordinates.get(indices[i] - 1).x;
				coord[2 * i + 1] = textureCoordinates.get(indices[i] - 1).y;
			}
			texCoordData.put(coord);
		}
		texCoordData.rewind();
		gl.glBufferData(GL_ARRAY_BUFFER, 4 * faces.size() * 3 * 2,
				texCoordData, GL_STATIC_DRAW);

		gl.glBindBuffer(GL_ARRAY_BUFFER, 0);

		model.setVbo(new VBO(bufferId.get(0), bufferId.get(1), 
				faces.size() * 3));
	}

	private static void buildTexture(GLAutoDrawable drawable, Model model) {
		GL2 gl = drawable.getGL().getGL2();
		Texture texture = null;
        try {
            InputStream stream = new FileInputStream("O:\\star.png");
            TextureData data = TextureIO.newTextureData(gl.getGLProfile(), stream, false, "png");
            texture = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace(); 
            System.exit(1);
        }
		model.setTexture(texture);
	}

	public static void buildModel(GLAutoDrawable drawable, Model model) {
		if (model.getVbo() == null) {
			buildVBO(drawable, model);
		}

		if (model.getTexture() == null) {
			buildTexture(drawable, model);
		}
	}
}
