package com.galaxyws.aircraftdemo.model.actor;

import com.galaxyws.aircraftdemo.model.Base;
import com.galaxyws.aircraftdemo.model.Model;
import com.galaxyws.aircraftdemo.model.Point;
import com.galaxyws.aircraftdemo.util.Constant;

public abstract class Actor extends Base {

	private static final long serialVersionUID = -3583653925431642110L;

	private float life;
	private long createTime;
	private Model type;
	private Shape shape;

	public Actor() {
		this.setCreateTime(System.currentTimeMillis());
	}

	public long getCreateTime() {
		return createTime;
	}

	private void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public float getLife() {
		return life;
	}

	public void setLife(float life) {
		this.life = life;
	}

	public Model getType() {
		return type;
	}

	public void setType(Model type) {
		this.type = type;
	}

	public boolean isOutOfStage() {
		return this.shape.isOutOfStage();
	}

	public abstract void moveTo(Point pos, long currentTime);



	public Shape getShape() {
		return shape;
	}



	public void setShape(Shape shape) {
		this.shape = shape;
	}
}
