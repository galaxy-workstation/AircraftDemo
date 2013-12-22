package com.galaxyws.aircraftdemo.view.gl2es2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.List;

import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.tools.RenderableModel;
import com.galaxyws.aircraftdemo.tools.Vector2f;
import com.galaxyws.aircraftdemo.tools.Vector3f;
import com.galaxyws.aircraftdemo.tools.RenderableModel.Face;
import com.galaxyws.aircraftdemo.util.pb.PBModel.BaseModel.TexCoord2f;
import com.galaxyws.aircraftdemo.util.pb.PBModel.BaseModel.Vertex3f;
import com.galaxyws.aircraftdemo.view.RenderablePBModel;
import com.galaxyws.aircraftdemo.view.renderable.TexturedRenderable;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class ActorModelBuilder {

	private FloatBuffer getPositionBuffer(RenderablePBModel model) {
		List<Vertex3f> vertices = model.getBaseModel().getVertexList();

		FloatBuffer vertexData = Buffers
				.newDirectFloatBuffer(vertices.size() * 3);

		for (Vertex3f v : vertices) {
			vertexData.put(v.getX());
			vertexData.put(v.getY());
			vertexData.put(v.getZ());
		}
		return (FloatBuffer) vertexData.rewind();
	}

	private FloatBuffer getTexCoordBuffer(RenderablePBModel model) {
		List<TexCoord2f> texCoords = model.getBaseModel().getTexCoordList();

		FloatBuffer texCoordData = Buffers
				.newDirectFloatBuffer(texCoords.size() * 2);

		for (TexCoord2f t : texCoords) {
			texCoordData.put(t.getS());
			texCoordData.put(t.getT());
		}
		return (FloatBuffer) texCoordData.rewind();
	}

	private int getVertexCount(RenderablePBModel model) {
		return model.getBaseModel().getVertexCount();
	}

	private void loadTextureToModel(GLAutoDrawable drawable, RenderablePBModel model,
			TexturedRenderable renderable) {

		Texture texture;
		try {
			InputStream stream = new FileInputStream(model.getBaseModel().getTexture());
			TextureData data = TextureIO.newTextureData(
					drawable.getGLProfile(), stream, false, "png");
			texture = TextureIO.newTexture(data);
			renderable.setTexture(texture);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}
	
	public void buildTextureRenderableFromPBModel(GLAutoDrawable drawable,
			RenderablePBModel model) {

		if (model.getRenderable() == null) {
			TexturedRenderable renderable = new TexturedRenderable();
			loadTextureToModel(drawable, model, renderable);

			renderable.setPositionData(getPositionBuffer(model));
			renderable.setTexCoordData(getTexCoordBuffer(model));

			renderable.setVertexCount(getVertexCount(model));

			renderable.bake(drawable);

			model.setRenderable(renderable);
		}
	}
	
	private ActorModelBuilder() {
		
	}
	
	public static ActorModelBuilder getInstance() {
		return Builder.instance;
	}

	private static class Builder {
		private final static ActorModelBuilder instance = new ActorModelBuilder();
	}
}
