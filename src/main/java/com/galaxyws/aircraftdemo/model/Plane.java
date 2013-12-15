package com.galaxyws.aircraftdemo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxyws.aircraftdemo.util.CollectionUtil;

public class Plane extends ActorObject {

	private static final long serialVersionUID = 5176602335439345887L;

//	private PlaneType planeType;
	private BulletType currentBulletType;
	private long lastFireTime;
	private Map<BulletType, Integer> specialBulletType = new HashMap<BulletType, Integer>();

	Plane() {
		this(JetType.getInstance());
	}

	public Plane(PlaneType planeType) {
		this.setType(planeType);
		this.setCurrentBulletType(planeType.getPreLoadBulletType().get(0));
		this.setLastFireTime(this.getCreateTime());
	}

	public BulletType getCurrentBulletType() {
		return currentBulletType;
	}

	private void setCurrentBulletType(BulletType currentBulletType) {
		this.currentBulletType = currentBulletType;
	}

	private List<Bullet> fixFiredBulletPosition(List<Bullet> firedBulletList) {
		if (CollectionUtil.isEmpty(firedBulletList)) {
			return null;
		}
		for (Bullet bullet : firedBulletList) {
			bullet.setPosition(this.getPosition());
		}
		return firedBulletList;
	}

	public List<Bullet> fire() {
		long now = System.currentTimeMillis();
		int fireChuck = (int) (now - this.getLastFireTime());
		if (fireChuck > ((PlaneType)this.getType()).getFireInterval()) {
			this.setLastFireTime(now);
			return this.fixFiredBulletPosition(currentBulletType
					.produceBullet());
		}
		return null;

	}

	public List<Bullet> fire(BulletType specialBulletType) {
		Integer count = this.specialBulletType.get(specialBulletType);
		if (null == count || count <= 0) {
			return null;
		}
		count--;
		if (count <= 0) {
			this.specialBulletType.remove(specialBulletType);
		}
		return this.fixFiredBulletPosition(specialBulletType.produceBullet());
	}

	public boolean changeBulletType(BulletType bulletType) {
		if (CollectionUtil.isEmpty(((PlaneType)this.getType()).getPreLoadBulletType())) {
			return false;
		}
		if (((PlaneType)this.getType()).getPreLoadBulletType().contains(bulletType)) {
			this.currentBulletType = bulletType;
			return true;
		}
		return false;
	}

	@Override
	public void moveTo(Point pos) {

	}

	public long getLastFireTime() {
		return lastFireTime;
	}

	private void setLastFireTime(long lastFireTime) {
		this.lastFireTime = lastFireTime;
	}

}
