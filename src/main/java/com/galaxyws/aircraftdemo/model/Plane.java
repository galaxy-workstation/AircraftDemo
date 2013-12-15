package com.galaxyws.aircraftdemo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxyws.aircraftdemo.util.CollectionUtil;

public class Plane extends ActorObject {

	private static final long serialVersionUID = 5176602335439345887L;

	private PlaneType planeType;
	private BulletType currentBulletType;
	private Map<BulletType, Integer> specialBulletType = new HashMap<BulletType, Integer>();

	Plane(){
		this(JetType.getInstance());
	}
	public Plane(PlaneType planeType){
		this.setPlaneType(planeType);
		this.setCurrentBulletType(planeType.getPreLoadBulletType().get(0));
	}
	
	public PlaneType getPlaneType() {
		return planeType;
	}

	private void setPlaneType(PlaneType planeType) {
		this.planeType = planeType;
	}

	public BulletType getCurrentBulletType() {
		return currentBulletType;
	}

	private void setCurrentBulletType(BulletType currentBulletType) {
		this.currentBulletType = currentBulletType;
	}

	private List<Bullet> fixFiredBulletPosition(List<Bullet> firedBulletList){
		if(CollectionUtil.isEmpty(firedBulletList)){
			return null;
		}
		for(Bullet bullet : firedBulletList){
			bullet.setPosition(this.getPosition());
		}
		return firedBulletList;
	}
	
	public List<Bullet> fire(){
		return this.fixFiredBulletPosition(currentBulletType.produceBullet());
	}
	
	public List<Bullet> fire(BulletType specialBulletType){
		Integer count = this.specialBulletType.get(specialBulletType);
		if(null == count || count <= 0){
			return null;
		}
		count --;
		if(count <= 0){
			this.specialBulletType.remove(specialBulletType);
		}
		return this.fixFiredBulletPosition(specialBulletType.produceBullet());
	}
	
	public boolean changeBulletType(BulletType bulletType){
		if(CollectionUtil.isEmpty(this.planeType.getPreLoadBulletType())){
			return false;
		}
		if(this.planeType.getPreLoadBulletType().contains(bulletType)){
			this.currentBulletType = bulletType;
			return true;
		}
		return false;
	}
	@Override
	public void moveTo(Point pos) {
		
	}

}
