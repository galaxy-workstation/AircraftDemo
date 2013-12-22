package com.galaxyws.aircraftdemo.view;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;

import com.galaxyws.aircraftdemo.model.actor.Actor;
import com.galaxyws.aircraftdemo.model.actor.ActorsQueue;
import com.galaxyws.aircraftdemo.model.actor.NullActor;
import com.galaxyws.aircraftdemo.view.gl2es2.Renderer;

public class Scene extends GLCanvas implements GLEventListener {

	private static final long serialVersionUID = -7985416299847073299L;


	public Scene() {
		this.addGLEventListener(this);
	}

	public void display(GLAutoDrawable drawable) {
		
		Renderer.getInstance().clear(drawable);

		while (true) {
			Actor actor = ActorsQueue.getInstance().poll();
			if (actor instanceof NullActor) {
				break;
			}
			Renderer.getInstance().renderModel(drawable, actor.getType().getPbModel(),
					actor.getShape().getPosition(), 0.02f);
		}
	}

	public void init(GLAutoDrawable drawable) {
		Renderer.getInstance().init(drawable);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		Renderer.getInstance().reshape(drawable, x, y, width, height);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {

	}
}
