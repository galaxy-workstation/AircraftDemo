package com.galaxyws.aircraftdemo.view;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;

import com.galaxyws.aircraftdemo.model.Model;
import com.galaxyws.aircraftdemo.model.Model.Face;
import com.galaxyws.aircraftdemo.util.Vector3f;
import com.jogamp.common.nio.Buffers;

import static javax.media.opengl.GL.*; // GL constants
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Renderer {

	private GLU glu; // for the GL Utility

	private float aspect;

	public void render(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
	}

	public void renderModel(GLAutoDrawable drawable, Model model) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		gl.glTranslatef(aspect / 2, 0.5f, 0.0f);
		gl.glScalef(0.2f, 0.2f, 0.2f);
		gl.glColor3f(1.0f, 0.0f, 0.0f);

		Builder.buildModel(drawable, model);
		model.getVbo().render(drawable);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		if (height == 0)
			height = 1;
		aspect = (float) width / height;
		GL2 gl = drawable.getGL().getGL2();

		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(0.0f, aspect, 0.0f, 1.0f);

		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);

		glu = new GLU();
	}
}
