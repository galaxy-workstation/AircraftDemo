package com.galaxyws.aircraftdemo.view;

import com.galaxyws.aircraftdemo.util.pb.PBModel.BaseModel;
import com.galaxyws.aircraftdemo.view.renderable.Renderable;

public class RenderablePBModel {
    private BaseModel baseModel = null;
    private Renderable renderable = null;

	public Renderable getRenderable() {
		return renderable;
	}

	public void setRenderable(Renderable renderable) {
		this.renderable = renderable;
	}

	public void setBaseModel(BaseModel baseModel) {
		this.baseModel = baseModel;
	}
	public BaseModel getBaseModel() {
		return this.baseModel;
	}
}
