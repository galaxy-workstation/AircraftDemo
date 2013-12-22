package com.galaxyws.aircraftdemo.model;

import java.io.File;
import java.io.IOException;

import com.galaxyws.aircraftdemo.tools.RenderableModel;
import com.galaxyws.aircraftdemo.util.pb.PBModelLoader;
import com.galaxyws.aircraftdemo.view.RenderablePBModel;

public abstract class Model extends Base {

	private static final long serialVersionUID = -4919689732501903323L;

	private RenderablePBModel pbModel;
	private RenderableModel model;
	private float width;
	private float height;
	private float moveSpeed;

	public RenderableModel getRenderableModel() {
		return model;
	}


	public void parseModelFile(String resource) {
		String fileName = Model.class.getClassLoader().getResource(resource)
				.getPath();
		File file = new File(fileName);
		if (!file.exists()) {
			// TODO need to record log
			return;
		}
		RenderablePBModel model = null;
		try {
			model = PBModelLoader.loadPBModel(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setPbModel(model);
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

	public RenderablePBModel getPbModel() {
		return pbModel;
	}

	public void setPbModel(RenderablePBModel pbModel) {
		this.pbModel = pbModel;
	}
}
