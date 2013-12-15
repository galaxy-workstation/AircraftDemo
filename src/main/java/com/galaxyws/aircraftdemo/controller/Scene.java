package com.galaxyws.aircraftdemo.controller;

import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.view.Renderer;

public class Scene {

	private Background background;
	private UserInterface userInterface;
	private Renderer renderer;

	public static String TITLE = "JOGL 2.0 Setup (GLCanvas)"; // window's title
	public static final int CANVAS_WIDTH = 480; // width of the drawable
	public static final int CANVAS_HEIGHT = 800; // height of the drawable
	public static final int FPS = 60; // animator's target frames per second
	public static final float RADIO = CANVAS_WIDTH / (float) CANVAS_HEIGHT;

	public Scene() {
		renderer = new Renderer();
		background = new Background();
	}

	private void update() {

	}

	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		update();

		//renderer.render(drawable);
		renderer.renderModel(drawable, background.model);
	}

	public void init(GLAutoDrawable drawable) {

		renderer.init(drawable); // get the OpenGL graphics
									// context);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		renderer.reshape(drawable, x, y, width, height);
	}
}
