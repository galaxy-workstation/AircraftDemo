package com.galaxyws.aircraftdemo.view.gl2es2;

import java.nio.FloatBuffer;
import java.util.List;

import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.util.Vector2f;
import com.galaxyws.aircraftdemo.util.Vector3f;
import com.galaxyws.aircraftdemo.view.ActorModel;
import com.galaxyws.aircraftdemo.view.ActorModel.Face;
import com.galaxyws.aircraftdemo.view.renderable.TexturedRenderable;
import com.jogamp.common.nio.Buffers;

public class Builder {

	private static FloatBuffer getPositionBuffer(ActorModel model) {
		List<Vector3f> vertices = model.getVertices();
		List<Face> faces = model.getFaces();

		FloatBuffer vertexData = Buffers
				.newDirectFloatBuffer(faces.size() * 3 * 3);

		for (Face face : faces) {
			int[] indices = face.getVertexIndices();
			for (int i = 0; i < indices.length; i++) {
				vertexData.put(vertices.get(indices[i] - 1).x);
				vertexData.put(vertices.get(indices[i] - 1).y);
				vertexData.put(vertices.get(indices[i] - 1).z);
			}
		}
		return (FloatBuffer) vertexData.rewind();
	}

	private static FloatBuffer getTexCoordBuffer(ActorModel model) {
		List<Face> faces = model.getFaces();
		List<Vector2f> textureCoordinates = model.getTextureCoordinates();

		FloatBuffer texCoordData = Buffers
				.newDirectFloatBuffer(faces.size() * 3 * 2);

		for (Face face : faces) {
			int[] indices = face.getTextureCoordinateIndices();
			float[] coord = new float[indices.length * 2];
			for (int i = 0; i < indices.length; i++) {
				coord[2 * i] = textureCoordinates.get(indices[i] - 1).x;
				coord[2 * i + 1] = textureCoordinates.get(indices[i] - 1).y;
			}
			texCoordData.put(coord);
		}
		return (FloatBuffer) texCoordData.rewind();
	}

	public static int getVertexCount(ActorModel model) {
		List<Face> faces = model.getFaces();
		return faces.size() * 3;
	}

	public static void buildTextureRenderableFromModel(GLAutoDrawable drawable,
			ActorModel model) {

		if (model.getRenderable() == null) {
			TexturedRenderable renderable = new TexturedRenderable();
			renderable.setTexture(model.getTextureFile());

			renderable.setPositionData(getPositionBuffer(model));
			renderable.setTexCoordData(getTexCoordBuffer(model));

			renderable.setVertexCount(getVertexCount(model));

			renderable.bake(drawable);
			
			model.setRenderable(renderable);
		}
	}
}
