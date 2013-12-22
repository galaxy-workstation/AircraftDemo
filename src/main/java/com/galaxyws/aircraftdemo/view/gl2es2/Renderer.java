package com.galaxyws.aircraftdemo.view.gl2es2;

import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;

import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.model.Point;
import com.galaxyws.aircraftdemo.view.RenderablePBModel;
import com.galaxyws.aircraftdemo.view.renderable.Renderable;

public class Renderer {

	private float aspect;
	
	public void clear(GLAutoDrawable drawable) {
		drawable.getGL().getGL2ES2().glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void renderModel(GLAutoDrawable drawable, RenderablePBModel model,
			Point pos, float scale) {
		ActorModelBuilder.getInstance().buildTextureRenderableFromPBModel(drawable, model);
		
		Renderable renderable = model.getRenderable();
		if (renderable != null) {
			model.getRenderable().ortho2D(0.0f, 0.0f, aspect, 1.0f);
			model.getRenderable().loadIdentity();
			model.getRenderable().scale(scale, scale, 1.0f);
			model.getRenderable().translate(pos.getX(), pos.getY(), 0.0f);
			model.getRenderable().render();
		}
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		if (height == 0)
			height = 1;
		aspect = (float) width / height;

		drawable.getGL().getGL2ES2().glViewport(0, 0, width, height);
	}

	public void init(GLAutoDrawable drawable) {
		drawable.getGL().getGL2ES2().glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	private Renderer() {
	}
	
	public static Renderer getInstance() {
		return Renderer.Builder.instance;
	}

	private static class Builder {
		private final static Renderer instance = new Renderer();
	}
}
