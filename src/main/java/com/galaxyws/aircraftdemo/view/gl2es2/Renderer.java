package com.galaxyws.aircraftdemo.view.gl2es2;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import com.galaxyws.aircraftdemo.model.Point;
import com.galaxyws.aircraftdemo.view.ActorModel;
import com.galaxyws.aircraftdemo.view.renderable.Renderable;

public class Renderer {

	private GLU glu; // for the GL Utility

	public void clear(GLAutoDrawable drawable) {
		GL2ES2 gl = drawable.getGL().getGL2();
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void renderModel(GLAutoDrawable drawable, ActorModel model,
			Point pos, float scale) {
		Builder.buildTextureRenderableFromModel(drawable, model);
		
		Renderable renderable = model.getRenderable();
		if (renderable != null) {
			model.getRenderable().loadIdentity();
			model.getRenderable().scale(scale, scale, 1.0f);
			model.getRenderable().translate(pos.getX(), pos.getY(), 0.0f);
			model.getRenderable().render(drawable);
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		// TODO: use gles
		if (height == 0)
			height = 1;
		float aspect = (float) width / height;
		GL2 gl = drawable.getGL().getGL2();

		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(0.0f, aspect, 0.0f, 1.0f);

	}

	public void init(GLAutoDrawable drawable) {
		GL2ES2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glu = new GLU();
	}
}
