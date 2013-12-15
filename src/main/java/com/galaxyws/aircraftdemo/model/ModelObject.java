package com.galaxyws.aircraftdemo.model;

import java.io.File;
import java.io.IOException;

import com.galaxyws.aircraftdemo.util.OBJLoader;

public abstract class ModelObject extends BaseObject {

	private static final long serialVersionUID = -4919689732501903323L;

	private Model model;
	private float width;
	private float height;
	private float moveSpeed;

	public Model getModel() {
		return model;
	}

	private void setModel(Model model) {
		this.model = model;
	}

	public void parseModelFile(String resource) {
		String fileName = ModelObject.class.getClassLoader()
				.getResource(resource).toString();
		File file = new File(fileName);
		if (!file.exists()) {
			// TODO need to record log
			return;
		}
		Model model = null;
		try {
			model = OBJLoader.loadTexturedModel(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setModel(model);
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}
