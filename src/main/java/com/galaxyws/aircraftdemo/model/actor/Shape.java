package com.galaxyws.aircraftdemo.model.actor;

import com.galaxyws.aircraftdemo.model.Point;

public abstract class Shape {

	private Point position;
	private float angle;
	
	public Shape(Point position){
		this(position, 0);
	}
	
	public Shape(Point position, float angle){
		this.position = position;
		this.angle = angle;
	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public abstract boolean containPoint(Point point);

	public abstract boolean collision(Shape shape);
	
	public abstract boolean isOutOfStage();

}
