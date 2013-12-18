package com.galaxyws.aircraftdemo.view.gl;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

import com.galaxyws.aircraftdemo.model.actor.Actor;
import com.galaxyws.aircraftdemo.model.actor.ActorsQueue;
import com.galaxyws.aircraftdemo.model.actor.NullActor;

public class Scene extends GLCanvas implements GLEventListener {

	private static final long serialVersionUID = -7985416299847073299L;

	private Renderer renderer;

	public Scene() {
		renderer = new Renderer();

		this.addGLEventListener(this);
	}

	public void display(GLAutoDrawable drawable) {
		renderer.clear(drawable);

		while (true) {
			Actor actor = ActorsQueue.getInstance().poll();
			if (actor instanceof NullActor) {
				break;
			}
			renderer.renderModel(drawable, actor.getType().getModel(),
					actor.getShape().getPosition(), 0.02f);
		}
	}

	public void init(GLAutoDrawable drawable) {
		renderer.init(drawable); // get the OpenGL graphics
									// context);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		renderer.reshape(drawable, x, y, width, height);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}
}
