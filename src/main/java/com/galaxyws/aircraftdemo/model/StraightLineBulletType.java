package com.galaxyws.aircraftdemo.model;

import java.util.Arrays;
import java.util.List;

import com.galaxyws.aircraftdemo.model.actor.Bullet;
import com.galaxyws.aircraftdemo.model.actor.Circle;
import com.galaxyws.aircraftdemo.util.Constant;

public class StraightLineBulletType extends BulletType {

	private static final long serialVersionUID = -6479516205025169485L;

	private StraightLineBulletType() {
		this.setPower(Constant.STRAIGHT_LINE_BULLET_POWER);
		this.setMoveSpeed(Constant.STRAIGHT_LINE_BULLET_MOVE_SPEED);
		this.parseModelFile(Constant.STRAIGHT_LINE_BULLET_MODEL);
		this.setHeight(Constant.STRAIGHT_LINE_BULLET_MODE_HEIGHT);
		this.setWidth(Constant.STRAIGHT_LINE_BULLET_MODE_WIDTH);
	}

	@Override
	public List<Bullet> produceBullet() {
		Bullet bullet = new Bullet(this);
		bullet.setDirection((float) (Math.PI / 2));
		bullet.setShape(new Circle(Constant.STRAIGHT_LINE_BULLET_SIZE, new Point()));
		return Arrays.asList(new Bullet[] { bullet });
	}

	public static BulletType getInstance() {
		return Builder.instance;
	}

	private static class Builder {
		private final static BulletType instance = new StraightLineBulletType();
	}

}
