package com.galaxyws.aircraftdemo.model.actor;

import com.galaxyws.aircraftdemo.model.Point;
import com.galaxyws.aircraftdemo.util.Constant;

public class Circle extends Shape {

	private float radius;

	public Circle(float radius, Point point) {
		super(point);
		this.radius = radius;

	}

	@Override
	public boolean containPoint(Point point) {
		return Math.pow(point.getX() - this.getPosition().getX(), 2.0) 
				+ Math.pow(point.getY() - this.getPosition().getY(), 2.0)
				< this.radius * this.radius;
	}

	@Override
	public boolean collision(Shape shape) {
		// TODO Auto-generated method stub
		return false;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public boolean isOutOfStage() {
		Point pos = getPosition();
		return pos.getX() < -radius
				|| pos.getX() > Constant.WIDTH_RELATIVE_AXIS + radius
				|| pos.getY() < -radius
				|| pos.getY() > Constant.HEIGHT_RELATIVE_AXIS + radius;
	}
}
