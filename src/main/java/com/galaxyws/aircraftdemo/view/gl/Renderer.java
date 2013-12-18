package com.galaxyws.aircraftdemo.view.gl;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.glu.GLU;

import com.galaxyws.aircraftdemo.model.Point;

public class Renderer {

	private GLU glu; // for the GL Utility
	
	public void clear(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void renderModel(GLAutoDrawable drawable, GlModel model, Point pos,
			float scale) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glLoadIdentity();

		gl.glTranslatef(pos.getX(), pos.getY(), 0.0f);
		gl.glScalef(scale, scale, 1.0f);

		Builder.buildModel(drawable, model);
		model.getTexture().enable(gl);
		model.getTexture().bind(gl);
		model.getVbo().render(drawable);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		if (height == 0)
			height = 1;
		float aspect = (float) width / height;
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

		glu = new GLU();
	}
}
