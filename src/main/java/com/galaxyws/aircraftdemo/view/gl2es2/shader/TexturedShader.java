package com.galaxyws.aircraftdemo.view.gl2es2.shader;

import static javax.media.opengl.GL.GL_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_STATIC_DRAW;
import static javax.media.opengl.GL.GL_TRIANGLES;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.view.gl2es2.shader.matrix.BaseMatrix;
import com.galaxyws.aircraftdemo.view.gl2es2.shader.matrix.Rotate;
import com.galaxyws.aircraftdemo.view.gl2es2.shader.matrix.Scale;
import com.galaxyws.aircraftdemo.view.gl2es2.shader.matrix.Translate;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;

public class TexturedShader implements Shader {
	private GL2ES2 gl2es2 = null;
	private ShaderImpl shader = null;

	private int posBufferId = 0;
	private int posLocation = 0;
	
	private int texCoordBufferId = 0;
	private int texCoordLocation = 0;

	private int samplerLocation = 0;

	private BaseMatrix modelMat = new BaseMatrix();
	private int modelMatrixLocation = 0;

	private int vertexCount = 0;

	private String[] vShaderStr = {
			// vertex shader
			"\nattribute vec4 a_position;		\n"
					+ "attribute vec2 a_texCoord;   \n"
					+ "varying vec2 v_texCoord;     \n"
					+ "uniform mat4 m_model;    \n"
					+ "uniform mat4 gl_ProjectionMatrix;    \n"
					+ "void main()                  \n"
					+ "{                            \n"
					+ "   vec4 p = (gl_ProjectionMatrix * m_model * a_position);       \n"
					+ "   gl_Position = p; \n"
					+ "   v_texCoord = a_texCoord;  \n"
					+ "}                            \n",

			// fragment shader
			"\nprecision mediump float;		\n" 
					+ "varying vec2 v_texCoord;		\n"
					+ "uniform sampler2D s_texture;	\n" 
					+ "void main()					\n"
					+ "{							\n"
					+ "  gl_FragColor = texture2D( s_texture, v_texCoord );\n"
					+ "}							\n" };

	private static final String ATTR_POSITION = "a_position";
	private static final String ATTR_TEXCOORD = "a_texCoord";
	private static final String UNIF_TEXCOORD = "s_texture";
	private static final String UNIF_MODEL = "m_model";

	public TexturedShader(GLAutoDrawable drawable) {
		this.gl2es2 = drawable.getGL().getGL2ES2();
		shader = new ShaderImpl(this.gl2es2, vShaderStr);

		this.posLocation = shader.getAttributeLoc(ATTR_POSITION);
		this.texCoordLocation = shader.getAttributeLoc(ATTR_TEXCOORD);

		this.samplerLocation = shader.getUniformLoc(UNIF_TEXCOORD);
		this.modelMatrixLocation = shader.getUniformLoc(UNIF_MODEL);
		
	}

	@Override
	public void render() {

		gl2es2.glUseProgram(shader.getProgram());

		gl2es2.glBindBuffer(GL_ARRAY_BUFFER, this.posBufferId);
		gl2es2.glVertexAttribPointer(this.posLocation, 3, GL2ES2.GL_FLOAT,
				false, 3 * 4, 0);

		gl2es2.glBindBuffer(GL_ARRAY_BUFFER, this.texCoordBufferId);
		gl2es2.glVertexAttribPointer(this.texCoordLocation, 2, GL2ES2.GL_FLOAT,
				false, 2 * 4, 0);
		
		gl2es2.glEnableVertexAttribArray(this.posLocation);
		gl2es2.glEnableVertexAttribArray(this.texCoordLocation);

		gl2es2.glUniform1i(this.samplerLocation, 0);
		
		gl2es2.glUniformMatrix4fv(this.modelMatrixLocation, 1, false, this.modelMat.getMatrix(), 0);

		gl2es2.glDrawArrays(GL_TRIANGLES, 0, this.vertexCount);

		gl2es2.glDisableVertexAttribArray(this.posLocation);
		gl2es2.glDisableVertexAttribArray(this.texCoordLocation);
		
		gl2es2.glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void usePositionData(FloatBuffer data) {
		IntBuffer bufferId = Buffers.newDirectIntBuffer(1);

		gl2es2.glGenBuffers(1, bufferId);
		this.posBufferId = bufferId.get(0);

		gl2es2.glBindBuffer(GL_ARRAY_BUFFER, this.posBufferId);
		gl2es2.glBufferData(GL_ARRAY_BUFFER, data.capacity() * 4, data,
				GL_STATIC_DRAW);
		gl2es2.glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void useTexCoordData(FloatBuffer data) {
		IntBuffer bufferId = Buffers.newDirectIntBuffer(1);

		gl2es2.glGenBuffers(1, bufferId);
		this.texCoordBufferId = bufferId.get(0);

		gl2es2.glBindBuffer(GL_ARRAY_BUFFER, this.texCoordBufferId);
		gl2es2.glBufferData(GL_ARRAY_BUFFER, data.capacity() * 4, data,
				GL_STATIC_DRAW);
		gl2es2.glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
	
	public void useTexture(Texture texture) {
		texture.enable(gl2es2);
		texture.bind(gl2es2);
	}

	public void rotate(float x, float y, float z, float angle) {
		this.modelMat.mul(new Rotate(x, y, z, angle));
	}
	public void scale(float x, float y, float z) {
		this.modelMat.mul(new Scale(x, y, z));
	}
	public void translate(float x, float y, float z) {
		this.modelMat.mul(new Translate(x, y, z));
	}

	public void loadIdentity() {
		this.modelMat.one();
	}
}
