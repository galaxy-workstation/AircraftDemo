package com.galaxyws.aircraftdemo.view.gl;

import static javax.media.opengl.GL2.GL_VERTEX_SHADER_EXT;
import static javax.media.opengl.GL2ES2.GL_COMPILE_STATUS;

import java.nio.IntBuffer;
import java.util.List;
import java.util.Map;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class Shader {

	private final static int VS = 0;
	private final static int FS = 1;
	
	
	private Map<String, Integer> uniformMap;
	private Map<String, Integer> attributeMap;
	private int shader = -1;

	private int loadShader(GLAutoDrawable drawable, String[] shaderText) {
		if (shaderText.length > 1) {
			GL2 gl = drawable.getGL().getGL2();
			int vs = gl.glCreateShader(GL_VERTEX_SHADER_EXT);
			String[] dummy = new String[1];
			dummy[0] = shaderText[0];
			
			gl.glShaderSource(vs, 1, dummy, null);
			gl.glCompileShader(vs);
			
			int[] ret = new int[1];
			IntBuffer retBuffer = IntBuffer.wrap(ret);
			gl.glGetShaderiv(vs, GL_COMPILE_STATUS, retBuffer);
		}
		return 0;
	}

	public void use(GLAutoDrawable drawable) {

	}

	public Shader(GLAutoDrawable drawable, String[] shaderText,
			List<String> attributes, List<String> uniforms) {
		int programObject = loadShader(drawable, shaderText);

		if (programObject >= 0) {
			GL2 gl = drawable.getGL().getGL2();
			for (String attr : attributes) {
				int loc = gl.glGetAttribLocation(programObject, attr);
				uniformMap.put(attr, loc);
			}

			for (String uni : uniforms) {
				int loc = gl.glGetAttribLocation(programObject, uni);
				attributeMap.put(uni, loc);
			}
		}
	}
}
