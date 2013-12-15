package com.galaxyws.aircraftdemo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.media.opengl.GLAutoDrawable;

import com.galaxyws.aircraftdemo.model.ActorObject;
import com.galaxyws.aircraftdemo.model.Bullet;
import com.galaxyws.aircraftdemo.model.JetType;
import com.galaxyws.aircraftdemo.model.Plane;
import com.galaxyws.aircraftdemo.util.CollectionUtil;
import com.galaxyws.aircraftdemo.view.Renderer;

public class Scene {

	private Background background;
	private UserInterface userInterface;
	private Renderer renderer;
	private List<ActorObject> actorList;


	public Scene() {
		renderer = new Renderer();
		background = new Background();
		actorList = new ArrayList<ActorObject>();
		Plane plane = JetType.getInstance().producePlane();
		actorList.add(plane);
	}

	private void update() {
		List<Bullet> bulletList = null;
		for (Iterator<ActorObject> iterator = actorList.iterator(); iterator
				.hasNext();) {
			ActorObject actorObject = iterator.next();
			if (actorObject instanceof Plane) {
				bulletList = ((Plane) actorObject).fire();
			} else if (actorObject instanceof Bullet) {
				actorObject.moveTo(null);
				if (actorObject.isOutOfStage()) {
					iterator.remove();
				}
			}
		}
		if (!CollectionUtil.isEmpty(bulletList)) {
			actorList.addAll(bulletList);
		}
	}

	public void display(GLAutoDrawable drawable) {
		renderer.render(drawable);

		for (ActorObject actorObject : actorList) {
			renderer.renderModel(drawable, actorObject.getType().getModel(), actorObject.getPosition(), 0.02f);
		}

		update();
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
