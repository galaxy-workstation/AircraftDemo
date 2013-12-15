package com.galaxyws.aircraftdemo.model;

import com.galaxyws.aircraftdemo.util.Constant;

public abstract class ActorObject extends BaseObject {

	private static final long serialVersionUID = -3583653925431642110L;

	private float life;
	private long createTime;
	private Point position;
	private ModelObject type;

	public ActorObject() {
		this.setCreateTime(System.currentTimeMillis());
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
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

	public ModelObject getType() {
		return type;
	}

	public void setType(ModelObject type) {
		this.type = type;
	}

	public boolean isOutOfStage() {
		return this.position.getX() >= Constant.MAX_RELATIVE_AXIS
				|| this.getPosition().getX() <= 0
				|| this.getPosition().getY() >= Constant.MAX_RELATIVE_AXIS
				|| this.getPosition().getY() <= 0;
	}

	public abstract void moveTo(Point pos);
}
