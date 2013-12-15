package com.galaxyws.aircraftdemo.model;


public abstract class ActorObject extends BaseObject {

	private static final long serialVersionUID = -3583653925431642110L;

	private float life;
	private long createTime;
	private Point position;

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
	
	public abstract void moveTo(Point pos);
	
}
