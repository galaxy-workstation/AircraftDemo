package com.galaxyws.aircraftdemo.view.gl2es2.shader;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2ES2;

public class ShaderImpl {
	private GL2ES2 gl2es2 = null;
	private final static int VS = 0;
	private final static int FS = 1;

	private int programObject = 0;
	
	private void printCompileLog(int shader) {
		int[] infoLen = new int[1];
		IntBuffer infoBuffer = IntBuffer.wrap(infoLen);

		gl2es2.glGetShaderiv(shader, GL2ES2.GL_INFO_LOG_LENGTH, infoBuffer);

		if (infoLen[0] > 1) {
			byte[] infoLog = new byte[infoLen[0]];
			ByteBuffer infoLogBuffer = ByteBuffer.wrap(infoLog);

			gl2es2.glGetShaderInfoLog(shader, infoLen[0], null, infoLogBuffer);
			
			try {
				System.out.println("Error compiling shader: " + new String(infoLog, "UTF_8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	private void printLinkLog(int programObject) {
		int[] infoLen = new int[1];
		IntBuffer infoBuffer = IntBuffer.wrap(infoLen);

		gl2es2.glGetProgramiv(programObject, GL2ES2.GL_INFO_LOG_LENGTH, infoBuffer);

		if (infoLen[0] > 1) {
			byte[] infoLog = new byte[infoLen[0]];
			ByteBuffer infoLogBuffer = ByteBuffer.wrap(infoLog);

			gl2es2.glGetProgramInfoLog(programObject, infoLen[0], null, infoLogBuffer);
			
			try {
				System.out.println("Error compiling shader: " + new String(infoLog, "UTF_8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	private int compileShader(int type, String shaderText) {

		int shader = gl2es2.glCreateShader(type);
		String[] dummy = new String[1];
		dummy[0] = shaderText;

		gl2es2.glShaderSource(shader, 1, dummy, null);
		gl2es2.glCompileShader(shader);

		int[] ret = new int[1];
		IntBuffer retBuffer = IntBuffer.wrap(ret);
		gl2es2.glGetShaderiv(shader, GL2ES2.GL_COMPILE_STATUS, retBuffer);
		if (0 == retBuffer.get(0)) {
			printCompileLog(shader);
			gl2es2.glDeleteShader(shader);
			shader = 0;
		}

		return shader;
	}

	private int linkShader(int vertexShader, int fragmentShader) {
		int programObject = 0;
		
		if (vertexShader > 0 && fragmentShader > 0) {
			programObject = gl2es2.glCreateProgram();
			if (programObject > 0) {
				gl2es2.glAttachShader(programObject, vertexShader);
				gl2es2.glAttachShader(programObject, fragmentShader);
				
				gl2es2.glLinkProgram(programObject);
				
				int[] ret = new int[1];
				IntBuffer retBuffer = IntBuffer.wrap(ret);
				gl2es2.glGetProgramiv(programObject, GL2ES2.GL_LINK_STATUS, retBuffer);
				if (0 == retBuffer.get(0)) {
					printLinkLog(programObject);
					gl2es2.glDeleteProgram(programObject);
					programObject = 0;
				}

				gl2es2.glDeleteShader(vertexShader);
				gl2es2.glDeleteShader(fragmentShader);
			}
		}
		
		return programObject;
	}
	private void loadShader(String[] shaderText) {
		
		if (shaderText.length > 1) {
			
			int vs = compileShader(GL2ES2.GL_VERTEX_SHADER, shaderText[VS]);
			int fs = compileShader(GL2ES2.GL_FRAGMENT_SHADER, shaderText[FS]);

			programObject = linkShader(vs, fs);
			
		}
	}

	public boolean usable() {
		return programObject > 0;
	}
	
	public void use(GL2ES2 gl2es2) {
		gl2es2.glUseProgram(programObject);
	}
	
	public int getProgram() {
		return programObject;
	}

	public ShaderImpl(GL2ES2 gl2es2, String[] shaderText) {
		this.gl2es2 = gl2es2;
		loadShader(shaderText);
	}

	public int getAttributeLoc(String name) {
		return gl2es2.glGetAttribLocation(programObject, name);
	}
	
	public int getUniformLoc(String name) {
		return gl2es2.glGetUniformLocation(programObject, name);
	}
}
