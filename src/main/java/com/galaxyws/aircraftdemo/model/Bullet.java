package com.galaxyws.aircraftdemo.model;

public class Bullet extends ActorObject {

	private static final long serialVersionUID = -1071085780826554312L;

	private float direction;

	private Point originalPos;

	Bullet(BulletType type) {
		this.setType(type);
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
				+ this.getPosition() + "]" + super.toString();
	}

	@Override
	public void moveTo(Point pos) {
		if (originalPos == null) {
			originalPos = new Point(getPosition());
		}

		Point currentPos = this.getPosition();
		float xSpeed = (float) (this.getType().getMoveSpeed() * Math.cos(this
				.getDirection()));
		float ySpeed = (float) (this.getType().getMoveSpeed() * Math.sin(this
				.getDirection()));
		float timeChunk = (System.currentTimeMillis() - this.getCreateTime()) / 1000.0f;

		float x = originalPos.getX() + xSpeed * timeChunk;
		float y = originalPos.getY() + ySpeed * timeChunk;
		currentPos.setX(x);
		currentPos.setY(y);
	}

}
