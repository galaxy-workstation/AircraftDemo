package com.galaxyws.aircraftdemo.model;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import static javax.media.opengl.GL2.GL_VERTEX_ARRAY;
import static javax.media.opengl.GL.GL_FLOAT;
import static javax.media.opengl.GL.GL_UNSIGNED_INT;
import static javax.media.opengl.GL.GL_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_ELEMENT_ARRAY_BUFFER;
import static javax.media.opengl.GL.GL_TRIANGLES;

public class VBO {

	private int vertexBufferId;
	private int indicesBufferId;
	
	private int vertexNumber;
	
	public VBO(int vertexBufferId, int indicesBufferid, int vertexNumber){
		this.vertexBufferId = vertexBufferId;
		this.indicesBufferId = indicesBufferid;
		this.vertexNumber = vertexNumber;
	}
	
	public void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glEnableClientState(GL_VERTEX_ARRAY);   
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, vertexBufferId);
		gl.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
		
		gl.glVertexPointer(3, GL_FLOAT, 0, 0);
		
		gl.glDrawElements(GL_TRIANGLES, vertexNumber, GL_UNSIGNED_INT, 0);
		
		gl.glDisableClientState(GL_VERTEX_ARRAY);   
		
		gl.glBindBuffer(GL_ARRAY_BUFFER, 0);
		gl.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
}
