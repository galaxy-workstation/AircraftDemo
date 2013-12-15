package com.galaxyws.aircraftdemo.view;

import static javax.media.opengl.GL.GL_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_ELEMENT_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_FLOAT;
import static javax.media.opengl.GL.GL_TRIANGLES;
import static javax.media.opengl.fixedfunc.GLPointerFunc.GL_TEXTURE_COORD_ARRAY;
import static javax.media.opengl.fixedfunc.GLPointerFunc.GL_VERTEX_ARRAY;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

public class VBO {

	private int vertexBufferId;
	private int texCoordBufferId;
	
	private int vertexNumber;
	
	public VBO(int vertexBufferId, int texCoordBufferId, int vertexNumber){
		this.vertexBufferId = vertexBufferId;
		this.texCoordBufferId = texCoordBufferId;
		this.vertexNumber = vertexNumber;
	}
	
	public void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glEnableClientState(GL_VERTEX_ARRAY);   
    	gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, vertexBufferId);
		gl.glVertexPointer(3, GL_FLOAT, 0, 0);

    	gl.glBindBuffer(GL_ARRAY_BUFFER, texCoordBufferId);
    	gl.glTexCoordPointer(2, GL_FLOAT, 0, 0);
    	
		gl.glDrawArrays(GL_TRIANGLES, 0, vertexNumber);
		gl.glDisableClientState(GL_VERTEX_ARRAY);   
    	gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, 0);
		gl.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
}
