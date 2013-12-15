package com.galaxyws.aircraftdemo.model;

public class Bullet extends ActorObject {

	private static final long serialVersionUID = -1071085780826554312L;

	private float direction;
	private BulletType type;

	Bullet(BulletType type) {
		this.setType(type);
	}

	public BulletType getType() {
		return type;
	}

	private void setType(BulletType type) {
		this.type = type;
	}

	public float getDirection() {
		return direction;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Bullet [direction=" + direction + "&position="
				+ this.getPosition() + "]" + super.toString() + "["
				+ type.toString() + "]";
	}

	@Override
	public void moveTo(Point pos) {
		Point currentPos = this.getPosition();
		float xSpeed = (float) (this.getType().getMoveSpeed() * Math.cos(this
				.getDirection()));
		float ySpeed = (float) (this.getType().getMoveSpeed() * Math.sin(this
				.getDirection()));
		float timeChunk = (System.currentTimeMillis() - this.getCreateTime()) / 1000.0f;

		float x = currentPos.getX() + xSpeed * timeChunk;
		float y = currentPos.getY() + ySpeed * timeChunk;
		currentPos.setX(x);
		currentPos.setY(y);
	}

}
