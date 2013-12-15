package com.galaxyws.aircraftdemo.model;

import java.util.List;

public abstract class PlaneType extends ModelObject {

	private static final long serialVersionUID = -1773016154769088348L;


	private List<BulletType> preLoadBulletType;

	public List<BulletType> getPreLoadBulletType() {
		return preLoadBulletType;
	}

	public void setPreLoadBulletType(List<BulletType> preLoadBulletType) {
		this.preLoadBulletType = preLoadBulletType;
	}
	
	public abstract Plane producePlane();
	public abstract Plane producePlane(Point pos);

}
