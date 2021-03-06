package com.galaxyws.aircraftdemo.model;

import java.util.Arrays;

import com.galaxyws.aircraftdemo.model.actor.Circle;
import com.galaxyws.aircraftdemo.model.actor.Plane;
import com.galaxyws.aircraftdemo.util.Constant;

public class JetType extends PlaneType {

	private static final long serialVersionUID = 785927679326935704L;

	private JetType() {
		this.setMoveSpeed(Constant.JET_PLANE_MOVE_SPEED);
		this.setPreLoadBulletType(Arrays
				.asList(new BulletType[] { StraightLineBulletType.getInstance() }));
		this.setHeight(Constant.JET_PLANE_MODE_HEIGHT);
		this.setWidth(Constant.JET_PLANE_MODE_WIDTH);
		this.setFireInterval(Constant.JET_PLANE_FIRE_INTERVAL);
		this.parseModelFile(Constant.JET_PLANE_MODEL);
	}

	public static PlaneType getInstance() {
		return Builder.instance;
	}

	private static class Builder {
		private static final PlaneType instance = new JetType();
	}

	@Override
	public Plane producePlane() {
		float x = Constant.WIDTH_RELATIVE_AXIS / 2;
		float y = this.getHeight() / 2;
		return producePlane(new Point(x, y));
	}

	@Override
	public Plane producePlane(Point pos) {
		Plane plane = new Plane(this);
		plane.setLife(Constant.DEFAULT_LIFE);
		plane.setShape(new Circle(Constant.JET_SHAPE_SIZE, new Point(pos)));
		return plane;
	}

}
