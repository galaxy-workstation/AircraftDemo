package com.galaxyws.aircraftdemo.view.renderable;

import javax.media.opengl.GLAutoDrawable;

public interface Renderable {
	
	public abstract void bake(GLAutoDrawable drawable);
	public abstract void render(GLAutoDrawable drawable);

	public abstract void loadIdentity();
	public abstract void rotate(float x, float y, float z, float angle);
	public abstract void scale(float x, float y, float z);
	public abstract void translate(float x, float y, float z);
}
