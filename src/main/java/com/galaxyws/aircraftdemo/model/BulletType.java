package com.galaxyws.aircraftdemo.model;

import java.util.List;

public abstract class BulletType extends ModelObject {

	private static final long serialVersionUID = -6729217996510720331L;

	private float power;

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}

	public abstract List<Bullet> produceBullet();

	@Override
	public String toString() {
		return "BulletType [power=" + power + "]" + super.toString();
	}
	
}
