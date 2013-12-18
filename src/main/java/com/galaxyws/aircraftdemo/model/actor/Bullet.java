package com.galaxyws.aircraftdemo.model.actor;

import com.galaxyws.aircraftdemo.model.BulletType;
import com.galaxyws.aircraftdemo.model.Point;

public class Bullet extends Actor {

	private static final long serialVersionUID = -1071085780826554312L;

	private float direction;

	private Point originalPos;

	public Bullet(BulletType type) {
		this.setType(type);
	}

	public Point getOriginalPos() {
		return originalPos;
	}

	public void setOriginalPos(Point originalPos) {
		this.originalPos = originalPos;
	}

	public float getDirection() {
		return direction;
	}

	public void setDirection(float direction) {
		this.direction = direction;
	}

	@Override
	public void moveTo(Point pos, long currentTime) {

		Point currentPos = this.getShape().getPosition();
		float xSpeed = (float) (this.getType().getMoveSpeed() * Math.cos(this
				.getDirection()));
		float ySpeed = (float) (this.getType().getMoveSpeed() * Math.sin(this
				.getDirection()));
		float timeChunk = (currentTime - this.getCreateTime()) / 1000.0f;

		float x = originalPos.getX() + xSpeed * timeChunk;
		float y = originalPos.getY() + ySpeed * timeChunk;
		currentPos.setX(x);
		currentPos.setY(y);
	}

}
