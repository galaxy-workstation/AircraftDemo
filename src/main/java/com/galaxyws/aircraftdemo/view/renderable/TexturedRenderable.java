package com.galaxyws.aircraftdemo.view.renderable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.view.gl2es2.shader.TexturedShader;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class TexturedRenderable implements Renderable {

	private FloatBuffer positionData = null;
	private FloatBuffer texCoordData = null;
	private Texture texture = null;
	private TexturedShader texturedShader = null;
	private int vertexCount = 0;

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public void setPositionData(FloatBuffer data) {
		this.positionData = data;
	}

	public void setTexCoordData(FloatBuffer data) {
		this.texCoordData = data;
	}

	@Override
	public void render() {
		if (null != texture) {
			texturedShader.useTexture(texture);
		}

		texturedShader.render();
	}

	@Override
	public void bake(GLAutoDrawable drawable) {
		texturedShader = new TexturedShader(drawable);

		texturedShader.usePositionData(this.positionData);
		texturedShader.useTexCoordData(this.texCoordData);

		texturedShader.setVertexCount(this.vertexCount);
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	@Override
	public void rotate(float x, float y, float z, float angle) {
		texturedShader.rotate(x, y, z, angle);
	}

	@Override
	public void scale(float x, float y, float z) {
		texturedShader.scale(x, y, z);
	}

	@Override
	public void translate(float x, float y, float z) {
		texturedShader.translate(x, y, z);
	}

	@Override
	public void loadIdentity() {
		texturedShader.loadIdentity();
	}
	
	@Override
	public void ortho2D(float left, float bottom, float right, float top) {
		texturedShader.ortho2D(left, bottom, right, top);
	}
}
